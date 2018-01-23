package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowBannerImage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 1/8/18.
 */
@Repository
public class SlideshowBannerImageDao extends BaseDao{
    public List<SlideshowBannerImage> getById(Integer[] ids, int slideshowAdId){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM SlideshowBannerImage where id in :ids and slideshowAdId=:slideshowAdId")
                .setParameter("slideshowAdId",slideshowAdId)
                .setParameter("ids", Arrays.asList(ids))
                .list();
    }
}