package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.advertisement.galleryads.images.GalleryAdsTopBannerImage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 1/5/18.
 */
@Repository
public class GalleryAdsTopBannerImageDao extends BaseDao {


    public List<GalleryAdsTopBannerImage> getById(Integer[] ids,int galleryId){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM GalleryAdsTopBannerImage where id in :ids and galleryAdId=:galleryId ")
                .setParameter("ids", Arrays.asList(ids))
                .setParameter("galleryId",galleryId)
                .list();
    }
}
