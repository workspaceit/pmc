package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by mi_rafi on 1/5/18.
 */
@Repository
public class GalleryAdDao extends BaseDao {
   /* public GalleryAd getById(int id){
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
    public List<GalleryAd> getByAdvertiserId(int[] advertiserId){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM GalleryAd where advertiserId in :advertiserId")
                .setParameter("advertiserId", Arrays.asList(advertiserId))
                .list();
    }
    public GalleryAd getByAdvertiserId(int advertiserId,Date sDate,Date eDate){
        Session session = this.getCurrentSession();
        return (GalleryAd)session.createQuery("select gAd FROM GalleryAd gAd " +
                                                 "where gAd.advertiserId=:advertiserId " +
                                                 "and :eDate BETWEEN :currentDate and gAd.topBannerExpiryDate ")
                .setMaxResults(1)
                .setParameter("currentDate",new Date())
                .setParameter("eDate",eDate)
                .setParameter("advertiserId",advertiserId)
                .uniqueResult();
    }*/

}