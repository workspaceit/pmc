package com.workspaceit.pmc.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by anik on 1/26/18.
 */

@Repository
public class CommonDao extends BaseDao {

    public Boolean activate(String entityName, Integer id) {
        Session session = this.getCurrentSession();
        Query query = session.createQuery("UPDATE " + entityName + " SET active=true WHERE id=:id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    public Boolean deActivate(String entityName, Integer id) {
        Session session = this.getCurrentSession();
        Query query = session.createQuery("UPDATE " + entityName + " SET active=false WHERE id=:id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

}
