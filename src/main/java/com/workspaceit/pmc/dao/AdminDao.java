package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Admin;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by anik on 12/19/17.
 */

@Repository

public class AdminDao extends BaseDao {

    public Admin insert(Admin admin){
        Session session = this.sessionFactory.getCurrentSession();
        session.save(admin);
        return admin;
    }

}
