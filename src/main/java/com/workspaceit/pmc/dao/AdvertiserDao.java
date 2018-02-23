package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Watermark;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 1/4/18.
 */
@Repository
public class AdvertiserDao extends BaseDao {
    public Advertiser getById(int id){
        Session session = this.getCurrentSession();
        return (Advertiser)session.createQuery("FROM Advertiser a " +
                                                        "LEFT JOIN FETCH a.otherImages " +
                                                        "LEFT JOIN FETCH a.events " +
                                                        "LEFT JOIN FETCH a.locations " +
                                                        "WHERE a.id=:id ")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public List<Advertiser> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Advertiser a " +
                                      "WHERE a.deleted=false  " +
                                      "ORDER BY id desc")
                .list();
    }
    public List<Advertiser> getActiveAdvertisers(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Advertiser WHERE active=true ORDER BY id desc")
                .list();
    }


    public List<Advertiser> getAll(Integer[] ids){
        List<Advertiser> advertisers = null;

        if(ids.length > 0) {
            Session session = this.getCurrentSession();
            advertisers = session.createQuery("FROM Advertiser where id in :ids")
                    .setParameterList("ids", Arrays.asList(ids))
                    .list();
        }
        return advertisers;
    }

    public List<Advertiser> getSuggestedAdvertisers(String searchTerm, Boolean active){
        Session session = this.getCurrentSession();

        Query query = session.createQuery("SELECT DISTINCT(a) FROM Advertiser a where a.active=:active and a.name LIKE :searchTerm ORDER BY " +
                "CASE " +
                "   WHEN a.name=:txt THEN 0" +
                "   WHEN a.name LIKE :ptxt THEN 1" +
                "   WHEN a.name LIKE :txtp THEN 3" +
                "   ELSE 2 " +
                "END, a.name ASC, id DESC");
        query.setParameter("searchTerm", "%"+searchTerm+"%");
        query.setParameter("txt", searchTerm);
        query.setParameter("ptxt", searchTerm + "%");
        query.setParameter("txtp", "%" +searchTerm);
        query.setParameter("active", active);
        return query.list();
    }
    public List<Advertiser> getByEventId(int eventId){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Advertiser a "+
                                      "LEFT JOIN FETCH a.events event " +
                                      "WHERE event.id=:eventId  ")
                .setParameter("eventId",eventId)
                .list();
    }
    public List<Advertiser> getByLocationId(int locationId){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Advertiser a "+
                                      "LEFT JOIN FETCH a.locations location " +
                                      "WHERE location.id=:locationId  ")
                .setParameter("locationId",locationId)
                .list();
    }
    public List<Advertiser> getByEventId(int eventId,boolean includeAllSelected){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Advertiser a "+
                                      "LEFT JOIN FETCH a.events event " +
                                      "WHERE event.id=:eventId or a")
                .setParameter("eventId",eventId)
                .list();
    }

    public List<Advertiser> getByEventAndLocationId(int locationId,int eventId){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Advertiser a "+
                "LEFT JOIN FETCH a.locations location " +
                "LEFT JOIN FETCH a.events event " +
                "WHERE ( location.id=:locationId or " +
                       " event.id=:eventId ) and a.active = true ")
                .setParameter("locationId",locationId)
                .setParameter("eventId",eventId)
                .list();
    }
    public List<Advertiser> getByEventAndLocationId(int eventId,int locationId,boolean includeAllSelected){
        Session session = this.getCurrentSession();

        return session.createQuery("FROM Advertiser a "+
                "LEFT JOIN FETCH a.locations location " +
                "LEFT JOIN FETCH a.events event " +
                "WHERE ( location.id=:locationId or " +
                        " event.id=:eventId or" +
                        " a.isAllLocationSelected =true or " +
                        " a.isAllEventSelected = true )" +
                        " and a.active = true ")
                .setParameter("locationId",locationId)
                .setParameter("eventId",eventId)
                .list();
    }
}