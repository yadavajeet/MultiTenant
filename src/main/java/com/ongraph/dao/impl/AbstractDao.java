package com.ongraph.dao.impl;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;;

import javax.annotation.Resource;

public class AbstractDao<T > {

    private final Class<T> type;

    @Autowired
    protected SessionFactory sessionFactory;

    public AbstractDao(Class<T> type) {
        this.type = type;
    }


    public Session getSession() {
        return this.sessionFactory.openSession();
    }

    public Criteria getCriteriaforClass()
    {
        return getSession().createCriteria(type);
    }

    public T save(T entity) {
        getSession().save(entity);
        return entity;
    }

    public List<T> findAll() {
        return getSession().createCriteria(type).list();
    }


    public T findById(Long Id) {
        return (T) getSession().createCriteria(type)
                .add(Restrictions.eq("id", Id)).uniqueResult();

    }


    public T update(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

}
