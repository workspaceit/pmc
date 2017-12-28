package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.TempFile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    protected Session getCurrentSession() {
    	return this.sessionFactory.getCurrentSession();
    }
    protected Session openSession() {
        return this.sessionFactory.openSession();
    }

}