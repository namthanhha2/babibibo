 package com.havm.util;
 
 import java.io.Serializable;
 import java.util.List;
 import org.hibernate.Criteria;
 import org.hibernate.LockMode;
 import org.hibernate.Session;
 import org.hibernate.criterion.Criterion;
 
 public abstract class GenericHibernateDAO<T, ID extends Serializable>
 {
   private Class<T> persistentClass;
 
   public GenericHibernateDAO()
   {
     this.persistentClass = ((Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
   }
  
   protected Session getSession() {
     return HibernateUtil.getCurrentSession();
   }
 
   public Class<T> getPersistentClass() {
     return this.persistentClass;
   }
 
   public T findById(String sessionName, ID id, boolean lock)
   {
     T entity;
     if (lock)
       entity = getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
     else {
       entity = getSession().load(getPersistentClass(), id);
     }
 
     return entity;
   }
 
   public List<T> findAll(String sessionName)
   {
     return findByCriteria(sessionName, new Criterion[0]);
   }
   
   public T makePersistent(String sessionName, T entity)
   {
     getSession().saveOrUpdate(entity);
     return entity;
   }
 
   public void makeTransient(String sessionName, T entity)
   {
     getSession().delete(entity);
   }
 
   public void flush(String sessionName)
   {
     getSession().flush();
   }
 
   public void clear(String sessionName)
   {
     getSession().clear();
   }
 
   protected List<T> findByCriteria(String sessionName, Criterion[] criterion)
   {
     return findByCriteria(sessionName, -1, -1, criterion);
   }
 
   protected List<T> findByCriteria(String sessionName, int firstResult, int maxResult, Criterion[] criterion)
   {
     Criteria crit = getSession().createCriteria(getPersistentClass());
     for (Criterion c : criterion) {
       crit.add(c);
     }
     if (firstResult >= 0) {
       crit.setFirstResult(firstResult);
     }
     if (maxResult >= 0) {
       crit.setMaxResults(maxResult);
     }
     return crit.list();
   }
 
   protected List<T> findByCriteria(int firstResult, int maxResult, Criterion[] criterion)
   {
     return findByCriteria("default session", firstResult, maxResult, criterion);
   }
 
   protected List<T> findByCriteria(Criterion[] criterion)
   {
     return findByCriteria("default session", criterion);
   }
 
   public T findById(ID id, boolean lock)
   {
     return findById("default session", id, lock);
   }
 
   public T makePersistent(T entity)
   {
     return makePersistent("default session", entity);
   }
 
   public void makeTransient(T entity)
   {
     makeTransient("default session", entity);
   }
 
   public void flush()
   {
     flush("default session");
   }
 
   public void clear()
   {
     clear("default session");
   }
 
   public List<T> findAll()
   {
     return findAll("default session");
   }
 }