/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.radius.util;

import com.radius.plans.LimitCalls;
import com.radius.plans.Plan;
import com.radius.plans.Plans;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author gkrosnicki
 */
@Stateless
public class ConfigRules {
    private Plans plans;
    
    public ConfigRules(){
    }
    
    public void startConfig(){
             try {
 
		File file = new File("/home/serwis/jradius_next/plans.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Plans.class);
                
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		plans = (Plans)jaxbUnmarshaller.unmarshal(file);;
                //List<Plan> planList = plans.getPlans();
                //System.out.println("customer.getLimit() - "+planList.get(0).getClass());
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
    }

    public Plans getPlans() {
        return plans;
    }
    
    
}
