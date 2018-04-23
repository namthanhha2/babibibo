/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.backend.controller;

import com.havm.babibibo.backend.BO.Modules;
import com.havm.babibibo.backend.da.ModulesDA;
import com.havm.babibibo.common.BaseCtrl;
import com.havm.babibibo.common.Constants;

/**
 *
 * @author Vu Manh Ha
 */
public class ModulesCtrl extends BaseCtrl {

    Modules searchForm;
    Modules createForm;

    public String onSearch() {
        ModulesDA da = new ModulesDA();
        if(start == null){
            start = 0l;
        }
        if(take == null){
            take = 20l;
        }
        json = da.search(searchForm, start.intValue(), take.intValue());
        return JSON;
    }

    public String onSave() {
        ModulesDA da = new ModulesDA();
        boolean b = da.save(createForm);
        if (b) {
            json.setResultCode(Constants.RESULT_CODE.SUCCESS);
        } else {
            json.setResultCode(Constants.RESULT_CODE.ERROR);
        }
        return JSON;
    }

    public String onDelete() {
        return JSON;
    }

    public Modules getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(Modules searchForm) {
        this.searchForm = searchForm;
    }

    public Modules getCreateForm() {
        return createForm;
    }

    public void setCreateForm(Modules createForm) {
        this.createForm = createForm;
    }
}
