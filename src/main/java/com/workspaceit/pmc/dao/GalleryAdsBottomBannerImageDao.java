package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.advertisement.galleryads.images.GalleryAdsBottomBannerImage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 1/15/18.
 */
@Repository
public class GalleryAdsBottomBannerImageDao extends BaseDao {
    public List<GalleryAdsBottomBannerImage> getById(Integer[] ids,int galleryAdId){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM GalleryAdsBottomBannerImage where id in :ids and galleryAdId=:galleryAdId")
                .setParameter("galleryAdId",galleryAdId)
                .setParameter("ids", Arrays.asList(ids))
                .list();
    }
}
