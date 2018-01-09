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
        System.out.println(id);
        Session session = this.getCurrentSession();
        return (GalleryAd)session.createQuery("FROM GalleryAd where id=:id")
                .setMaxResults(1)
                .setParameter("id",id)
                .uniqueResult();
    }
}
