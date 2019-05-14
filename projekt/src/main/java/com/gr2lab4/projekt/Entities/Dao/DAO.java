package com.gr2lab4.projekt.Entities.Dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public abstract class DAO<T, Id extends Serializable> extends HibernateUtil{
	   
    public void save(T entity) {     
        openCurrentSessionwithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }
    
    public void update(T entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().update(entity);
        closeCurrentSessionwithTransaction();
    }
      
    public abstract T findById(Id id);
      
    public abstract List<T> findAll();
    
    
    public void delete(T entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().delete(entity);
        closeCurrentSessionwithTransaction();
    }

    public void deleteAll() {      
        List<T> entityList = findAll();
        for (T entity : entityList) {
            delete(entity);
        }
        
        
    }
}