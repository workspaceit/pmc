package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.AdvertisersOtherImage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 1/8/18.
 */
@Repository
public class AdvertisersOtherImageDao extends BaseDao {

    public List<AdvertisersOtherImage> getById(int advertiserId,Integer[] ids){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM AdvertisersOtherImage WHERE id in :ids and advertiserId=:advertiserId ")
                .setParameter("ids", Arrays.asList(ids))
                .setParameter("advertiserId", advertiserId)
                .list();
    }
}
