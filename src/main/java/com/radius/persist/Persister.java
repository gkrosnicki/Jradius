/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radius.persist;

import com.radius.main.Jrad;
import com.radius.entities.*;
import com.radius.util.LoggerFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.tinyradius.packet.AccountingRequest;

/**
 *
 * @author gkrosnicki
 */
@Stateless
public class Persister {

    private static Logger logger = LoggerFactory.make();
    private boolean sendmail = true;
    @PersistenceContext
    EntityManager em;

    public Persister() {
    }

    public void insertRecord(Radact radact) {
        String querys = "insert into radact" + dayFind()
                + "("
                + "sessionid, called, calling, inputoctets, outputoctets,"
                + " sessiontime, username, nasip, setupdate, setuptime, "
                + "callorigin, calltype, protocol, connectdate, connecttime, "
                + "disconnectdate, disconnecttime, cause, framedip, relsource, mediaip, nasport"
                + ")"
                + "values("
                + "'" + radact.getSessionid() + "',"
                + "'" + radact.getCalled() + "',"
                + "'" + radact.getCalling() + "',"
                + "" + radact.getInputoctets() + ","
                + "" + radact.getOutputoctets() + ","
                + "" + radact.getSessiontime() + ","
                + "'" + radact.getUsername() + "',"
                + "'" + radact.getNasip() + "',"
                + "'" + radact.getSetupdate() + "',"
                + "'" + radact.getSetuptime() + "',"
                + "'" + radact.getCallorigin() + "',"
                + "'" + radact.getCalltype() + "',"
                + "'" + radact.getProtocol() + "',"
                + "'" + radact.getConnectdate() + "',"
                + "'" + radact.getConnecttime() + "',"
                + "'" + radact.getDisconnectdate() + "',"
                + "'" + radact.getDisconnecttime() + "',"
                + "'" + radact.getCause() + "',"
                + "'" + radact.getFramedip() + "',"
                + "'" + radact.getRelsource() + "',"
                + "'" + radact.getMediaip() + "',"
                + "'" + radact.getNasport() + "'"
                + ");";
        Query query = em.createNativeQuery(querys);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public boolean returnNumber(String username) {
        boolean exists = false;
        Query query = em.createQuery("select n from Nol n where substring(?1, 1, length(n.numer))=n.numer order by n.id");
        query.setParameter(1, username);
        List<Nol> nols = query.getResultList();
        if (nols.size() > 0) {
            for(Nol nol:nols){
                System.out.println("Numer znaleziony w bazie "+nol.getNumer());
            }
            exists = true;
        }

        return exists;
    }

    
    private String dayFind() {
        String day = "";
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        day = new Integer(cal.get(Calendar.DAY_OF_MONTH)).toString();
        if (day.length() == 1) {
            day = "0" + day;
        }
        return day;
    }

    /*
    @Override
    public void run() {
        while (true) {
            Radact radact = concurrentLinkedQueue.poll();
            AccountingRequest request = requestQueue.poll();
            if (radact != null) {
                insertRecord(radact);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    */
}
