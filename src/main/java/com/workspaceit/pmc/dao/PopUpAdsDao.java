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
        return (PopupAd)session.createQuery("FROM PopupAd where advertiserId=:advertiserId AND type =:popupAdType ")
                .setMaxResults(1)
                .setParameter("advertiserId",advertiserId)
                .setParameter("popupAdType",popupAdType)
                .uniqueResult();
    }
}