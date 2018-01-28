/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.frontend.da;

import com.havm.babibibo.frontend.BO.Users;
import org.hibernate.Query;

/**
 *
 * @author Vu Manh Ha
 */
public class UsersDA extends BaseDA {

    public boolean saveUsers(Users u) {
        try {
            session.getTransaction().begin();
            session.save(u);
            session.getTransaction().commit();
            return true;
        } catch (Exception en) {
            return false;
        }
    }
    
    public boolean checkUser(String userName, String password){
        String hql = "select count(*) from Users u where lower(u.userName) = ? and password = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, userName.toLowerCase());
        query.setParameter(1, password);
        Long count = (Long)query.uniqueResult();
        if(count >0l){
            return true;
        } else {
            return false;
        }
    }
}
