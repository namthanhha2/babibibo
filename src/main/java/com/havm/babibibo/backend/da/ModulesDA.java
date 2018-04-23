/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.backend.da;

import com.havm.babibibo.backend.BO.Modules;
import com.havm.babibibo.frontend.da.*;
import com.havm.babibibo.common.ReturnResult;
import com.havm.babibibo.common.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Vu Manh Ha
 */
public class ModulesDA extends BaseDA {

    public boolean save(Modules m) {
        try {
            session.getTransaction().begin();
            session.save(m);
            session.getTransaction().commit();
            return true;
        } catch (Exception en) {
            session.getTransaction().rollback();
            return false;
        }
    }
    
    public boolean delete(Long moduleId){
        String hql = "update Modules m set m.status = -1 where m.moduleId = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, moduleId);
        int n = query.executeUpdate();
        return n == 1;
    }
   
    public ReturnResult search(Modules search, int start, int take){
        String selectHql = "select m ";
        String countHql = "select count(m)";
        String hql = "from Modules m where m.status = 1";
        List lstParam = new ArrayList();
        if(search != null){
            if(search.getName() != null){
                hql += " and lower(m.name) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.escapeSql(search.getName()));
            }
        }
        
        Query query = session.createQuery(selectHql + hql);
        Query countQuery = session.createQuery(countHql + hql);
        if(!lstParam.isEmpty()){
            for(int i=0;i<lstParam.size();i++){
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
        }
        query.setFirstResult(start);
        query.setMaxResults(take);
        List lstItem = query.list();
        Long count = (Long)countQuery.uniqueResult();
        ReturnResult result= new ReturnResult();
        result.setLstItems(lstItem);
        result.setTotalItem(count);
        return result;
    }
}
