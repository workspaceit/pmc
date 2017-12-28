package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Photographer;
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
    public Admin getByEmail(String email){
        Session session = this.openSession();
        try{
            return (Admin)session.createQuery("FROM Admin  WHERE email=:email")
                    .setParameter("email",email)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            if(session!=null)session.close();
        }
    }

}
