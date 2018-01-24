package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.AdvertiserTransaction;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertiserTransactionDao extends BaseDao{

    public AdvertiserTransaction getById(int id){
        Session session = this.getCurrentSession();
        return (AdvertiserTransaction)session.createQuery("FROM AdvertiserTransaction aTran WHERE aTran.id=:id  ")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public AdvertiserTransaction getLastByAdvertiserId(int advertiserId){
        Session session = this.getCurrentSession();
        return (AdvertiserTransaction)session.createQuery("FROM AdvertiserTransaction aTran WHERE " +
                "aTran.advertiserId=:advertiserId " +
                "ORDER BY aTran.id desc ")
                .setParameter("advertiserId",advertiserId)
                .setMaxResults(1)
                .uniqueResult();
    }
}