package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.AdminRole;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by anik on 5/8/18.
 */

@Repository
public class AdminRoleDao extends BaseDao {

    public AdminRole getById(int id) {
        Session session = this.getCurrentSession();
        return (AdminRole) session.createQuery("FROM AdminRole where id=:id ")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }

    public AdminRole getByName(String roleName) {
        Session session = this.getCurrentSession();
        return (AdminRole) session.createQuery("FROM AdminRole where role=:role")
                .setParameter("role", roleName)
                .setMaxResults(1)
                .uniqueResult();
    }

}
