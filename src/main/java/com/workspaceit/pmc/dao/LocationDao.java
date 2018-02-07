package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Location;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Repository
public class LocationDao extends BaseDao{
    public List<Location> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Location l where l.deleted = false  ORDER BY  l.id DESC")
                .list();
    }

    public List<Location> getActiveLocations(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Location l WHERE active=true ORDER BY  l.id DESC")
                .list();
    }

    public List<Location> getAll(Integer limit, Integer offset) {
        Session session = this.getCurrentSession();
        Query query = session.createQuery("FROM Location l order by l.name");
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Location> locations = query.list();
        return locations;
    }

    public Integer getActiveLocationCount(){
        Session session = this.getCurrentSession();
        int count = ((Long) session.createQuery("SELECT DISTINCT COUNT(l) FROM Location l").uniqueResult()).intValue();
        return count;
    }

    public List<Location> getAll(Integer[] ids){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Location where id in :ids")
                .setParameter("ids", Arrays.asList(ids))
                .list();
    }

    public Location getById(int id){
        Session session = this.getCurrentSession();
        return (Location)session.createQuery("FROM Location where id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
}