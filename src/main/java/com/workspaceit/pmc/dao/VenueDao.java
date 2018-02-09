package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Venue;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
/**
 * Created by Tomal on 1/9/2018.
 */
@Repository
public class VenueDao extends BaseDao{

    public List<Venue> getSuggestedVenues(String searchTerm, Boolean active){
        Session session = this.getCurrentSession();
        Query query = session.createQuery("SELECT DISTINCT(v) FROM Venue v WHERE active=:active AND v.name LIKE :searchTerm ORDER BY " +
                "CASE " +
                "   WHEN v.name=:txt THEN 0" +
                "   WHEN v.name LIKE :ptxt THEN 1" +
                "   WHEN v.name LIKE :txtp THEN 3" +
                "   ELSE 2 " +
                "END, v.name ASC, id DESC");
        query.setParameter("searchTerm", "%"+searchTerm+"%");
        query.setParameter("txt", searchTerm);
        query.setParameter("ptxt", searchTerm + "%");
        query.setParameter("txtp", "%" +searchTerm);
        query.setParameter("active", active);
        return query.list();
    }

    public List<Venue> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Venue v where v.deleted = false ORDER BY v.id DESC")
                .list();
    }

    public List<Venue> getActiveVenuesByLocation(Integer locationId, Integer limit, Integer offset){
        Session session = this.getCurrentSession();
        session.enableFilter("activeVenues");
        Query query = session.createQuery("FROM Venue v WHERE v.location.id=:locationId ORDER BY v.id DESC");
        query.setParameter("locationId", locationId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Venue> venues = query.list();
        session.disableFilter("activeVenues");
        return venues;
    }

    public Integer getActiveVenueCountByLocation(Integer locationId){
        Session session = this.getCurrentSession();
        session.enableFilter("activeVenues");
        int count = ((Long) session.createQuery("SELECT DISTINCT COUNT(v) FROM Venue v WHERE v.location.id=:locationId")
                .setParameter("locationId", locationId).uniqueResult()).intValue();
        session.disableFilter("activeVenues");
        return count;
    }

    public Integer getActiveVenueCount(){
        Session session = this.getCurrentSession();
        session.enableFilter("activeVenues");
        int count = ((Long) session.createQuery("SELECT DISTINCT COUNT(v) FROM Venue v").uniqueResult()).intValue();
        session.disableFilter("activeVenues");
        return count;
    }

    public List<Venue> getActiveVenues(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Venue WHERE active=true ORDER BY id DESC")
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