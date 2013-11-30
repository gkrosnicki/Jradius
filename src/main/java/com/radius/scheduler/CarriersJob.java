/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.radius.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author gkrosnicki
 */
public class CarriersJob implements Job{

    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("execut carriers Job");
    }
    
}
