/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.common;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Vu Manh Ha
 */
public class BaseCtrl extends ActionSupport {
    public ReturnResult json = new ReturnResult();
    public static String JSON = "jsonReturn";
    public static String PAGE = "page";  
    public Long start;
    public Long take;
    
    public HttpServletRequest getRequest(){
        return ServletActionContext.getRequest();
    }
    public HttpServletResponse getResponse(){
        return ServletActionContext.getResponse();
    }

    public ReturnResult getJson() {
        return json;
    }

    public void setJson(ReturnResult json) {
        this.json = json;
    }
    
    public String toPage(){
        return PAGE;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getTake() {
        return take;
    }

    public void setTake(Long take) {
        this.take = take;
    }
}
