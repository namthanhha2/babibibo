/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.common;

import java.util.List;

/**
 *
 * @author Vu Manh Ha
 */
public class ReturnResult {
    Long resultCode;
    String message;
    String description;
    Long totalItem;
    List lstItems;

    public Long getResultCode() {
        return resultCode;
    }

    public void setResultCode(Long resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List getLstItems() {
        return lstItems;
    }

    public void setLstItems(List lstItems) {
        this.lstItems = lstItems;
    }

    public Long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Long totalItem) {
        this.totalItem = totalItem;
    }
}
