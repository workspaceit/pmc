package com.workspaceit.pmc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by anik on 12/19/17.
 */

public class BaseDao {

    @Autowired
    protected SessionFactory sessionFactory;
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        System.out.println(" ***** **** *** ** * SessionFactory Is Created * ** *** **** *****");
        this.sessionFactory = sessionFactory;
    }

}
