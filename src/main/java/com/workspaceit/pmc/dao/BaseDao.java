package com.workspaceit.pmc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by anik on 12/19/17.
 */

public class BaseDao {


    protected SessionFactory sessionFactory;
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    protected void setSessionFactory(SessionFactory sessionFactory) {
        System.out.println(" ***** **** *** ** * SessionFactory Is Created * ** *** **** *****");
        this.sessionFactory = sessionFactory;
    }
    public void insert(Object obj){
        Session session = this.getCurrentSession();
        session.save(obj);
    }
    public void insertAll(Collection<? extends Object> entities){

        Session session = this.getCurrentSession();
        for (Object entity:entities) {
            session.save(entity);
        }
    }
    public void update(Object obj){
        Session session = this.getCurrentSession();
        session.update(obj);
    }

    public void updateAll(Collection<? extends Object> entities){
        Session session = this.getCurrentSession();
        for (Object entity:entities) {
            session.update(entity);
        }
    }
    public void delete(Object obj){
        Session session = this.getCurrentSession();
        session.delete(obj);
        session.getTransaction().commit();
    }

    protected Session getCurrentSession() {
    	return this.sessionFactory.getCurrentSession();
    }
    protected Session openSession() {
        return this.sessionFactory.openSession();
    }

}