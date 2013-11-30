/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.radius.plans;

import javax.ejb.Stateless;

/**
 *
 * @author gkrosnicki
 */

public interface Plan {
    public void setLimit_value(int limit_value);
    public int getLimit();
    public void setLimit(int limit);
    public String[] getIptable();
    public void setIptable(String[] iptable);
    public int getInterval();
    public void setInterval(int interval);
    public boolean executeRadiusRule();
    public void executeTimerRule();
}
