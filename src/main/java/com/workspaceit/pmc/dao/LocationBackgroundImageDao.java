package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.LocationBackgroundImage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 1/2/18.
 */
@Repository
public class LocationBackgroundImageDao extends BaseDao {
    public LocationBackgroundImage getById(int id){
        Session session = this.getCurrentSession();
        return (LocationBackgroundImage)session.createQuery("FROM LocationBackgroundImage where id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public List<LocationBackgroundImage> getById(Integer[] ids, Location location){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM LocationBackgroundImage where location_id=:locationId and id in :ids")
                .setParameter("locationId",location.getId())
                .setParameter("ids", Arrays.asList(ids))
                .getResultList();
    }
}
