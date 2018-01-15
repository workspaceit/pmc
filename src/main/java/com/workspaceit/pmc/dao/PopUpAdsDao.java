package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.constant.advertisement.PopupAdType;
import com.workspaceit.pmc.entity.PopupAd;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by mi_rafi on 1/8/18.
 */
@Repository
public class PopUpAdsDao extends BaseDao{
    public PopupAd getByAdvertiserId(int advertiserId){
        Session session = this.getCurrentSession();
        return (PopupAd)session.createQuery("FROM PopupAd where advertiserId=:advertiserId")
                .setMaxResults(1)
                .setParameter("advertiserId",advertiserId)
                .uniqueResult();
    }

    public PopupAd getByAdvertiserId(int advertiserId, PopupAdType popupAdType){
        Session session = this.getCurrentSession();
        return (PopupAd)session.createQuery("FROM PopupAd p" +
                                                        " where p.advertiserId=:advertiserId" +
                                                        " AND p.type =:popupAdType ")
                .setMaxResults(1)
                .setParameter("advertiserId",advertiserId)
                .setParameter("popupAdType",popupAdType)
                .uniqueResult();
    }

    public PopupAd getById(int id){
        Session session = this.getCurrentSession();
        return (PopupAd)session.createQuery("FROM PopupAd where id=:id ")
                .setMaxResults(1)
                .setParameter("id",id)
                .uniqueResult();
    }
    public PopupAd getById(int id,int advertiserId){
        Session session = this.getCurrentSession();
        return (PopupAd)session.createQuery("FROM PopupAd " +
                                                        " where id=:id " +
                                                        " and advertiserId=:advertiserId")
                .setMaxResults(1)
                .setParameter("id",id)
                .setParameter("advertiserId",advertiserId)

                .uniqueResult();
    }
}