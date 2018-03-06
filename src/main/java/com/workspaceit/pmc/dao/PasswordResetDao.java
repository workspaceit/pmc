package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.PasswordResetToken;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordResetDao extends BaseDao {

    public PasswordResetToken insert(PasswordResetToken passwordResetToken){
        Session session = this.sessionFactory.getCurrentSession();
        session.save(passwordResetToken);
        return passwordResetToken;
    }
    public PasswordResetToken findByToken(String token){
        Session session = this.sessionFactory.getCurrentSession();
        PasswordResetToken passwordResetToken = (PasswordResetToken) session.createQuery("FROM PasswordResetToken where token=:token")
                .setParameter("token",token)
                .setMaxResults(1)
                .uniqueResult();
        return passwordResetToken;
    }
}
