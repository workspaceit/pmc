package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Advertiser;
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
        return session.createQuery("FROM Location l where l.deleted = false  ORDER BY  l.createdAt DESC")
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

    public List<Location> getActiveLocations(Integer limit, Integer offset) {
        Session session = this.getCurrentSession();
        session.enableFilter("activeLocations");
        Query query = session.createQuery("FROM Location l order by l.name");
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Location> locations = query.list();
        session.disableFilter("activeLocations");
        return locations;
    }

    public Integer getActiveLocationCount(){
        Session session = this.getCurrentSession();
        session.enableFilter("activeLocations");
        int count = ((Long) session.createQuery("SELECT DISTINCT COUNT(l) FROM Location l").uniqueResult()).intValue();
        session.disableFilter("activeLocations");
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

    public List<Location> getSuggestedLocations(String searchTerm, Boolean active){
        Session session = this.getCurrentSession();

        Query query = session.createQuery("SELECT DISTINCT(l) FROM Location l where l.active=:active and l.name LIKE :searchTerm ORDER BY " +
                "CASE " +
                "   WHEN l.name=:txt THEN 0" +
                "   WHEN l.name LIKE :ptxt THEN 1" +
                "   WHEN l.name LIKE :txtp THEN 3" +
                "   ELSE 2 " +
                "END, l.name ASC, id DESC");
        query.setParameter("searchTerm", "%"+searchTerm+"%");
        query.setParameter("txt", searchTerm);
        query.setParameter("ptxt", searchTerm + "%");
        query.setParameter("txtp", "%" +searchTerm);
        query.setParameter("active", active);
        return query.list();
    }
}