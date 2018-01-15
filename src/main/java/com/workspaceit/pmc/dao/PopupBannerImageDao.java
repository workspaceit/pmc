package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.PopupBannerImage;
import com.workspaceit.pmc.entity.SlideshowAd;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 1/8/18.
 */
@Repository
public class PopupBannerImageDao extends BaseDao {

    public PopupBannerImage getById(int id){
        Session session = this.getCurrentSession();
        return (PopupBannerImage)session.createQuery("FROM PopupBannerImage where id=:id")
                .setMaxResults(1)
                .setParameter("id",id)
                .uniqueResult();
    }

    public List<PopupBannerImage> getById(Integer[] ids){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM PopupBannerImage where id in :ids")
                .setParameter("ids", Arrays.asList(ids))
                .list();
    }
    public List<PopupBannerImage> getById(Integer[] ids,int popupAdId){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM PopupBannerImage where id in :ids and popupAdId=:popupAdId")
                .setParameter("ids", Arrays.asList(ids))
                .setParameter("popupAdId",popupAdId)
                .list();
    }
}