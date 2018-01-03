package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Location;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Repository
public class LocationDao extends BaseDao{
    public List<Location> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Location ORDER BY id DESC")
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