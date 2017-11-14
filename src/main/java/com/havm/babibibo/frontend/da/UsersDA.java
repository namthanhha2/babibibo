/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.frontend.da;

import com.havm.babibibo.frontend.BO.Users;

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
}
