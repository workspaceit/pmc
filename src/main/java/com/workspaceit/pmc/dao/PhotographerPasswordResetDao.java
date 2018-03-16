package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.PasswordResetToken;
import com.workspaceit.pmc.entity.PhotographerPasswordResetToken;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PhotographerPasswordResetDao extends BaseDao {

    public PhotographerPasswordResetToken insert(PhotographerPasswordResetToken passwordResetToken){
        Session session = this.sessionFactory.getCurrentSession();
        session.save(passwordResetToken);
        return passwordResetToken;
    }
    public PhotographerPasswordResetToken findByToken(String token){
        Session session = this.sessionFactory.getCurrentSession();
        PhotographerPasswordResetToken passwordResetToken = (PhotographerPasswordResetToken) session.createQuery("FROM PhotographerPasswordResetToken where token=:token")
                .setParameter("token",token)
                .setMaxResults(1)
                .uniqueResult();
        return passwordResetToken;
    }
}
