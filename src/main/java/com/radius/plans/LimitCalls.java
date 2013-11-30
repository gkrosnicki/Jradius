/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radius.plans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gkrosnicki
 */
@XmlRootElement
public class LimitCalls implements Plan {
    private int limit_value;
    
    private int limit;
    
    private String[] iptable;
    
    private int interval;

    public void setLimit_value(int limit_value) {
        this.limit_value = limit_value;
    }
    
    public int getLimit() {
        return limit;
    }

    @XmlElement
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String[] getIptable() {
        return iptable;
    }

    @XmlElement
    public void setIptable(String[] iptable) {
        this.iptable = iptable;
    }

    public int getInterval() {
        return interval;
    }

    @XmlElement
    public void setInterval(int interval) {
        this.interval = interval;
    }

    private void resetLt() {
        limit = 0;
    }

    private void decreaseLt() {
        if (limit > 0) {
            limit--;
        }
    }
    
    public boolean executeRadiusRule(){
        decreaseLt();
        return limit>=0;
    }
    
    public void executeTimerRule(){
        resetLt();
    }
}
