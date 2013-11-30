/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.radius.main;

import com.radius.scheduler.MainScheduler;
import com.radius.util.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;

import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author gkrosnicki
 */
public class Main {
    private static Logger logger = LoggerFactory.make();
    
    public static void main(String... args){
        Jrad jrad = null;
       
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        logger.info("Container "+container);
        try {
            //jrad = (Jrad)container.getContext().lookup("java:global/classes/Jrad");
            jrad = (Jrad)container.getContext().lookup("java:global/Jradius-1.0-SNAPSHOT/Jrad");
            System.out.println("jrad found "+jrad);
            
        } catch (NamingException ex) {
            System.out.println(ex.getMessage());
            logger.error("Can't create jrad object "+ex);
        }
        jrad.init();
        MainScheduler mainScheduler = new MainScheduler();
        new Thread(mainScheduler).start();
        //jrad.start();
    }
}
