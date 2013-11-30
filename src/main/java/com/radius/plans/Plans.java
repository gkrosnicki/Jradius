/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.radius.plans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gkrosnicki
 */
@XmlRootElement
public class Plans {
    
    private List<Plan> plans = new ArrayList<Plan>();

    public List<Plan> getPlans() {
        return plans;
    }
    
    @XmlElement(name = "limitCalls", type = LimitCalls.class)
    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
    
    
}
