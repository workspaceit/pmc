package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.SlideshowAd;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by mi_rafi on 1/8/18.
 */
@Repository
public class SlideshowAdDao extends BaseDao {
    public SlideshowAd getByAdvertiserId(int advertiserId){
        Session session = this.getCurrentSession();
        return (SlideshowAd)session.createQuery("FROM SlideshowAd where advertiserId=:advertiserId")
                .setMaxResults(1)
                .setParameter("advertiserId",advertiserId)
                .uniqueResult();
    }
    public SlideshowAd getById(int id){
        Session session = this.getCurrentSession();
        return (SlideshowAd)session.createQuery("FROM SlideshowAd where id=:id")
                .setMaxResults(1)
                .setParameter("id",id)
                .uniqueResult();
    }
    public SlideshowAd getById(int id,int advertiserId){
        Session session = this.getCurrentSession();
        return (SlideshowAd)session.createQuery("FROM SlideshowAd where id=:id and advertiserId=:advertiserId")
                .setMaxResults(1)
                .setParameter("id",id)
                .setParameter("advertiserId",advertiserId)
                .uniqueResult();
    }
}
