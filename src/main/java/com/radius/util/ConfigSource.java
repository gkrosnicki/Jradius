/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.radius.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.ejb.Stateless;

/**
 *
 * @author gkrosnicki
 */
@Stateless
public class ConfigSource {
    Properties properties = new Properties();
    private boolean authenticate;
    private boolean accounting;
    private int authport;
    private int accport;
    

    public boolean isAuthenticate() {
        return authenticate;
    }

    public boolean isAccounting() {
        return accounting;
    }

    public int getAuthport() {
        return authport;
    }

    public int getAccport() {
        return accport;
    }
       
    public void startConfig() throws IOException{
            properties.load(new FileInputStream("/home/serwis/jradius_next/init.properties"));
            authenticate = Boolean.parseBoolean(properties.getProperty("authenticate"));
            accounting = Boolean.parseBoolean(properties.getProperty("accounting"));
            authport = Integer.parseInt(properties.getProperty("authport"));
            accport = Integer.parseInt(properties.getProperty("accport"));
    }
}
