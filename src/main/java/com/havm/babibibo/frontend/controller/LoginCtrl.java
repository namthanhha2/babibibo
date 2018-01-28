/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.frontend.controller;

import com.havm.babibibo.common.BaseCtrl;
import com.havm.babibibo.common.Constants;
import com.havm.babibibo.frontend.da.UsersDA;
import facebook4j.Account;
import facebook4j.Album;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.api.AccountMethods;
import facebook4j.auth.AccessToken;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 *
 * @author Vu Manh Ha
 */
public class LoginCtrl extends BaseCtrl {
    String userName;
    String password;
    String email;

    public String toPage() {
        return PAGE;
    }

    public String loginFB() throws FacebookException, IOException {
        Facebook facebook = new FacebookFactory().getInstance();
        ResourceBundle fb = ResourceBundle.getBundle("application");
        String appId = fb.getString("spring.social.facebook.appId");
        String appSecret = fb.getString("spring.social.facebook.appSecret");
        facebook.setOAuthAppId(appId, appSecret);
        String code = getRequest().getParameter("code");
        facebook.setOAuthPermissions("public_profile");
        String redirect = "http://localhost:8084/babibibo/loginAction!loginFB.do";
        String url = facebook.getOAuthAuthorizationURL(redirect);
        if (code != null) {
            facebook.getOAuthAccessToken(code);
            if (facebook.getAccounts().size() > 0) {
                Account acc = facebook.getAccounts().get(0);
                System.out.println(acc.getName());
            };
            ResponseList<Post> feed  = facebook.getHome();
            if(feed != null && feed.size()>0){
                for(Post post : feed){
                    System.out.println(post.getId());
                }
            }
        } else {
            json.setMessage(url);
        }
        return JSON;
    }
    
    public String login(){
        UsersDA da = new UsersDA();
        if(da.checkUser(userName, password)){
            json.setResultCode(Constants.RESULT_CODE.SUCCESS);
        } else {
            json.setResultCode(Constants.RESULT_CODE.ERROR);
        }
        return JSON;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
