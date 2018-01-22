package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.AdvertisementPrices;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tomal on 1/10/2018.
 */
@Repository
public class AdvertisementPricesDao extends BaseDao {

        public AdvertisementPrices getById(int id){
            Session session = this.getCurrentSession();
            return (AdvertisementPrices)session.createQuery("FROM AdvertisementPrices WHERE id=:id")
                    .setParameter("id",id)
                    .setMaxResults(1)
                    .uniqueResult();
        }
        public List<AdvertisementPrices> getAll(){
            Session session = this.getCurrentSession();
            return session.createQuery("FROM AdvertisementPrices ORDER BY id desc")
                    .list();
        }
    public AdvertisementPrices getByType(String type){
        Session session = this.getCurrentSession();
        return (AdvertisementPrices)session.createQuery("FROM AdvertisementPrices ap WHERE ap.type=:id")
                .setParameter("id",type)
                .setMaxResults(1)
                .uniqueResult();
    }
    public List<AdvertisementPrices> getByType(String... type){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM AdvertisementPrices ap WHERE ap.type in :adType")
                .setParameter("adType", Arrays.asList(type))
                .list();
    }
}
