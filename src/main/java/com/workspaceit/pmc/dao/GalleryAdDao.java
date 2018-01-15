package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by mi_rafi on 1/5/18.
 */
@Repository
public class GalleryAdDao extends BaseDao {
    public GalleryAd getById(int id){
        Session session = this.getCurrentSession();
        return (GalleryAd)session.createQuery("FROM GalleryAd where id=:id")
                .setMaxResults(1)
                .setParameter("id",id)
                .uniqueResult();
    }

    public GalleryAd getById(int id,int advertiserId){
        Session session = this.getCurrentSession();
        return (GalleryAd)session.createQuery("FROM GalleryAd where id=:id AND advertiserId=:advertiserId")
                .setMaxResults(1)
                .setParameter("id",id)
                .setParameter("advertiserId",advertiserId)
                .uniqueResult();
    }

    public GalleryAd getByAdvertiserId(int advertiserId){
        Session session = this.getCurrentSession();
        return (GalleryAd)session.createQuery("FROM GalleryAd where advertiserId=:advertiserId")
                .setMaxResults(1)
                .setParameter("advertiserId",advertiserId)
                .uniqueResult();
    }
}
