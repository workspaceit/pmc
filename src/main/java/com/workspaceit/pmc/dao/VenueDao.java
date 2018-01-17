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

    public List<Venue> getSuggestedVenues(String searchTerm){
        Session session = this.getCurrentSession();
        Query query = session.createQuery("SELECT DISTINCT(v) FROM Venue v where v.name LIKE :searchTerm ORDER BY " +
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
        return query.list();
    }

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