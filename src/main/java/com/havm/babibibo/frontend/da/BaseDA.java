/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.frontend.da;

import com.havm.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Vu Manh Ha
 */
public class BaseDA {
    Session session;
    public BaseDA(){
        session = HibernateUtil.getCurrentSession();
    }
}
