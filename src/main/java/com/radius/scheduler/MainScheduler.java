/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radius.scheduler;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.QuartzServer;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.DateBuilder.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

/**
 *
 * @author gkrosnicki
 */
public class MainScheduler implements Runnable {

    public void run() {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException ex) {
            Logger.getLogger(MainScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // and start it off
            scheduler.start();
        } catch (SchedulerException ex) {
            Logger.getLogger(MainScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        JobDetail job = newJob(CarriersJob.class).withIdentity("job1", "group1").build();
        CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/20 * * * * ?")).build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(MainScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainScheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
