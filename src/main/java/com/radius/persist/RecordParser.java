/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radius.persist;

/**
 *
 * @author gkrosnicki
 */
import com.radius.entities.Radact;
import com.radius.util.LoggerFactory;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

@Stateless
public class RecordParser {

    private static Logger logger = LoggerFactory.make();

    public void parse(String attributename, String attributevalue, Radact record) {
        //System.out.println(attributename + ":" + attributevalue);
        if (attributename.indexOf("Acct-Session-Id") != -1) {
            record.setSessionid(attributevalue);
        } else if (attributename.indexOf("Called-Station-Id") != -1) {
            record.setCalled(attributevalue);
        } else if (attributename.indexOf("Calling-Station-Id") != -1) {
            record.setCalling(attributevalue);
        } else if (attributename.indexOf("Acct-Input-Octets") != -1) {
            record.setInputoctets((new Integer(attributevalue)).intValue());
        } else if (attributename.indexOf("Acct-Output-Octets") != -1) {
            record.setOutputoctets((new Integer(attributevalue)).intValue());
        } else if (attributename.indexOf("Acct-Session-Time") != -1) {
            record.setSessiontime((new Integer(attributevalue)).intValue());
        } else if (attributename.indexOf("User-Name") != -1) {
            record.setUsername(attributevalue);
        } else if (attributename.indexOf("NAS-IP-Address") != -1) {
            record.setNasip(attributevalue);
        } else if (attributename.indexOf("Vendor-Specific") != -1) {
            if (attributevalue.indexOf("cisco-nas-port") != -1) {
                record.setNasport(attributevalue.substring(attributevalue.indexOf(":") + 2));
            } else {
                attributevalue = attributevalue.substring(attributevalue.indexOf(":") + 2);
                //System.out.println("to jest atrybut ... "+attributevalue);
                String key = attributevalue.substring(0, attributevalue.indexOf("="));
                //System.out.println("to jest klucz ::::: "+key);
                if(attributevalue.indexOf("=.")!=-1 || attributevalue.indexOf("=*")!=-1){
                    attributevalue = attributevalue.substring(attributevalue.indexOf("=") + 2);
                }else{
                    attributevalue = attributevalue.substring(attributevalue.indexOf("=") + 1);
                }
                //System.out.println("to jest atrybut koncowy ... "+attribute);
                if (key.indexOf("call-id") != -1) {
                    //record.setUniqueid(attribute);
                    return;
                } else if (key.indexOf("h323-setup-time") != -1) {
                    if (attributevalue.substring(0, 1).equals("*")) {
                        record.setSetuptime(timeParser(attributevalue.substring(1, 9)));
                    } else {
                        record.setSetuptime(timeParser(attributevalue.substring(0, 8)));
                    }
                    record.setSetupdate(dateParser(attributevalue.substring(13, attributevalue.length())));
                } else if (key.indexOf("h323-call-origin") != -1) {
                    record.setCallorigin(attributevalue);
                } else if (key.indexOf("h323-call-type") != -1) {
                    record.setCalltype(attributevalue);
                } else if (key.indexOf("session-protocol") != -1) {
                    record.setProtocol(attributevalue);
                } else if (key.indexOf("h323-connect-time") != -1) {
                    if (attributevalue.substring(0, 1).equals("*")) {
                        record.setConnecttime(timeParser(attributevalue.substring(1, 9)));
                    } else {
                        record.setConnecttime(timeParser(attributevalue.substring(0, 8)));
                    }
                    record.setConnectdate(dateParser(attributevalue.substring(13, attributevalue.length())));
                } else if (key.indexOf("h323-disconnect-time") != -1) {
                    if (attributevalue.substring(0, 1).equals("*")) {
                        record.setDisconnecttime(timeParser(attributevalue.substring(1, 9)));
                    } else {
                        record.setDisconnecttime(timeParser(attributevalue.substring(0, 8)));
                    }
                    record.setDisconnectdate(dateParser(attributevalue.substring(13, attributevalue.length())));
                } else if (key.indexOf("h323-disconnect-cause") != -1) {
                    record.setCause(attributevalue);
                } else if (key.indexOf("h323-remote-address") != -1) {
                    record.setFramedip(attributevalue);
                } else if (key.indexOf("release-source") != -1) {
                    record.setRelsource(attributevalue);
                } else if (key.indexOf("remote-media-address") != -1) {
                    record.setMediaip(attributevalue);
                }
            }
        }
    }

    private Date dateParser(String dt) {
        String data = null;
        Date dat = null;
        SimpleDateFormat sdf = null;
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        if (dt.indexOf("METDST") != -1) {
            dt = dt.substring(11);
            sdf = new SimpleDateFormat("MMM dd yyyy", Locale.US);
        } else {
            sdf = new SimpleDateFormat("zzz EEE MMM dd yyyy", Locale.US);
        }
        try {
            dat = sdf.parse(dt);
        } catch (ParseException ex) {
            logger.warn(ex);
        }
        data = sdfDate.format(dat);
        return dat;
    }

    private Date timeParser(String dt) {
        Date time = null;
        try {
            time = new SimpleDateFormat("HH:mm:ss").parse(dt);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(RecordParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }
    private String getatr(String attribute) {
        return attribute.substring(attribute.indexOf(":") + 2);
    }
}
