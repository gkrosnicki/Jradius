/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radius.main;

import java.text.ParseException;
import org.apache.log4j.*;
import org.tinyradius.packet.*;
import org.tinyradius.util.*;
import java.util.*;
import org.tinyradius.attribute.RadiusAttribute;
import java.net.*;
import com.radius.entities.*;
import com.radius.persist.*;
import com.radius.util.LoggerFactory;
import com.radius.util.ConfigSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.tinyradius.attribute.VendorSpecificAttribute;

/**
 *
 * @author gkrosnicki
 */
@Stateless
public class Jrad {
    private static final int VENDOR_ID = 9;
    private static Logger logger = LoggerFactory.make();
    RadiusServer server = new Server();
    

    @EJB
    ConfigSource configSource;
    @EJB
    Persister persister;
    @EJB
    RecordParser recordParser;

    public Jrad() {
    }

    
    public void init() {
        System.out.println("asdfasdfasdfadadfadfadf");
        try {
            configSource.startConfig();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            System.exit(0);
        }
        if (configSource.isAuthenticate()) {
            server.setAuthPort(configSource.getAuthport());
        }
        if (configSource.isAccounting()) {
            server.setAcctPort(configSource.getAccport());
        }
        if(configSource.isAccounting() || configSource.isAuthenticate()){
            server.start(configSource.isAuthenticate(), configSource.isAccounting());
        }
    }

    public void start() {
        if(configSource.isAccounting() || configSource.isAuthenticate()){
            server.start(configSource.isAuthenticate(), configSource.isAccounting());
        }

    }

    
    class Server extends RadiusServer {

        public String getSharedSecret(InetSocketAddress client) {
            System.out.println("get shared secret ");
            return "buleczka";
        }

        public String getUserPassword(String userName) {
            System.out.println("get username " + userName);
            if (persister.returnNumber(userName)) {
                return "1234";
            } else {
                return "bad username";
            }
        }

        @Override
        public RadiusPacket accessRequestReceived(AccessRequest accessRequest, java.net.InetSocketAddress client) throws RadiusException {
            //List<RadiusAttribute> attributes = accessRequest.getAttributes();
            System.out.println("access request received ...");
            List attributes = accessRequest.getAttributes();
            Iterator iterator = attributes.iterator();
            while (iterator.hasNext()) {
                RadiusAttribute radiusAttribute = (RadiusAttribute) iterator.next();
            }
            System.out.println("Username z access " + accessRequest.getUserName());
            String username = getUserPassword(accessRequest.getUserName());
            System.out.println("Przyszedł username " + username);
            int type = RadiusPacket.ACCESS_REJECT;
            if (username != null && accessRequest.verifyPassword(username)) {
                type = RadiusPacket.ACCESS_ACCEPT;
            }
            RadiusPacket answer = new RadiusPacket(type, accessRequest.getPacketIdentifier());
            copyProxyState(accessRequest, answer);
            return answer;
        }

        @Override
        public RadiusPacket accountingRequestReceived(AccountingRequest accountingRequest, InetSocketAddress isa) throws RadiusException {
            Radact record = new Radact();
            List attributes = accountingRequest.getAttributes();
            Iterator iterator = attributes.iterator();
            while (iterator.hasNext()) {
                RadiusAttribute radiusAttribute = (RadiusAttribute) iterator.next();
                recordParser.parse(radiusAttribute.getAttributeTypeObject().getName(), radiusAttribute.getAttributeValue(), record);
            }
            List attrs = accountingRequest.getVendorAttributes(VENDOR_ID);
            Iterator atit = attrs.iterator();
            while (atit.hasNext()) {
                VendorSpecificAttribute vendorSpecificAttribute = (VendorSpecificAttribute) atit.next();
                recordParser.parse(vendorSpecificAttribute.getAttributeTypeObject().getName(), vendorSpecificAttribute.getAttributeValue(), record);
            }
            persister.insertRecord(record);
            RadiusPacket answer = new RadiusPacket(RadiusPacket.ACCOUNTING_RESPONSE, accountingRequest.getPacketIdentifier());
            copyProxyState(accountingRequest, answer);
            return answer;
        }
    }

    public static void main(String[] args) {
        Jrad jrr = new Jrad();
    }
}
