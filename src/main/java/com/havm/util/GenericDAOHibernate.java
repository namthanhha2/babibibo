/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * GenericDAOHibernate
 *
 * @author GPDN_NgocTM3
 */
public class GenericDAOHibernate<T, ID extends Serializable> extends GenericHibernateDAO<T, ID> {

    private Class<T> type;
    protected Session session = getSession();

    public static String convertToLikeString(String source) {
        if (source == null) {
            return "";
        }

        source = source.trim().toLowerCase();
        String des = "";
        Boolean isSpecial;

        char[] specialChar = {'%', '_', '?', '\''};
        for (int i = 0; i < source.length(); i++) {
            isSpecial = false;
            for (int j = 0; j < specialChar.length; j++) {
                if (specialChar[j] == source.charAt(i)) {
                    isSpecial = true;
                    break;

                }
            }
            if (isSpecial) {
                des = des + '!' + source.charAt(i);
            } else {
                des = des + source.charAt(i);
            }

        }
        return des;
    }

    public static boolean checkEmptyString(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();
        if (str.length() == 0) {
            return true;
        }
        return false;
    }

    public GenericDAOHibernate(Class<T> type) {
        this.type = type;
    }

    public ID create(T o) {
        return (ID) session.save(o);
    }

    public T read(ID id) {
        return (T) session.get(this.type, id);
    }

    public void update(T o) {
        session.update(o);
    }

    public void delete(T o) {
        session.delete(o);
    }

    public List<T> getDataByFields(Class entityClass, List<String> key, List<Object> valueOfKey, String sortField) {
        Criteria crite = session.createCriteria(entityClass);
        if (key != null && valueOfKey != null && key.size() > 0 && (key.size() == valueOfKey.size())) {
            for (int i = 1; i < key.size(); i++) {
                crite.add(Restrictions.eq(key.get(i), valueOfKey.get(i)));
            }
        }
        if (!"".equals(sortField)) {
            crite.addOrder(Order.asc(sortField));
        }

        return crite.list();
    }

    public List getListByKeys(Class entityClass, String idField, List<ID> key) {
        Criteria crite = session.createCriteria(entityClass);
        crite.add(Restrictions.in(idField, key));
        return crite.list();
    }

    public List getListByKeys(String idField, List<ID> key) {
        return getListByKeys(type, idField, key);
    }

    /**
     * Kiem tra trung khi them moi
     *
     * @param codeField
     * @param entityCode
     * @return
     */
    public Boolean checkEntityExistedForInsert(String codeField, String entityCode) {
        Boolean isExisted = false;
        try {

//            Criterion[] criterion = {Restrictions.like(codeField, entityCode.toLowerCase(),MatchMode.EXACT).ignoreCase()};// new Criterion[1];
//            List result = this.findByCriteria(-1,-1,criterion);
            Criteria crite = session.createCriteria(type);
            crite.add(Restrictions.like(codeField, entityCode).ignoreCase());
            List result = crite.list();
            if ((result != null) && (!result.isEmpty()) && (result.size() > 0)) {
                isExisted = true;
            }
        } catch (Exception ex) {
            // throw ex;
        }
        return isExisted;
    }

    public List findAll(Class entityClass, String... orderFields) {
        //String entityName = entityClass.getSimpleName();
        // Build sql
        Criteria crite = session.createCriteria(entityClass);
//        StringBuilder sqlBuilder = new StringBuilder();
//        sqlBuilder.append(" FROM ").append(entityName);
        if (orderFields != null && orderFields.length > 0) {
            int length = orderFields.length;
            for (int i = 0; i < length; i++) {
                crite.addOrder(Order.asc(orderFields[i]));
//                sqlBuilder.append("  nlssort(lower(");
//                sqlBuilder.append(orderFields[i]);
//                sqlBuilder.append("),'nls_sort = BINARY_AI')");
//                if (i < length - 1) {
//                    sqlBuilder.append(",");
//                }
            }
        }
//        Query query = session.createQuery(sqlBuilder.toString());
        List<Object> lsObject = crite.list();
        return lsObject;
    }

    public Object findById(Class entityClass, String idName, Long id) {
        Object returnOb = null;
        // Build sql
        if (id != null && id > 0L) {
            Criteria crite = session.createCriteria(entityClass);
            crite.add(Restrictions.eq(idName, id));
            List<Object> lsObject = crite.list();
            if (lsObject != null && lsObject.size() > 0) {
                returnOb = lsObject.get(0);
            }
        }
        return returnOb;
    }

    @Override
    public T findById(ID id, boolean lock) {
        return super.findById(id, false);
    }

    public T findById(ID id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

    @SuppressWarnings("unchecked")
    public List<T> getByProperty(Map<String, ?> properties) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (String key : properties.keySet()) {
            crit.add(Restrictions.eq(key, properties.get(key)));
        }
        return (List<T>) crit.list();
    }

    public String UpcaseFirst(String str) {
        String first = str.substring(0, 1);
        String concat = str.substring(1);
        return first.toUpperCase() + concat;
    }
}
