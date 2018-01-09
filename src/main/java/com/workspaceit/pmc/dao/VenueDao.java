package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Venue;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by Tomal on 1/9/2018.
 */
@Repository
public class VenueDao extends BaseDao{
    public List<Venue> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Venue ORDER BY id DESC")
                .list();
    }
    public Venue getById(int id){
        Session session = this.getCurrentSession();
        return (Venue)session.createQuery("FROM Venue where id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
}