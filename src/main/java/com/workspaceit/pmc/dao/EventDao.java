package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Venue;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 1/3/18.
 */
@Repository
public class EventDao extends BaseDao {

    public List<Event> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Event e WHERE  e.deleted = false ORDER BY e.id DESC")
                .list();
    }
    public Integer getActiveEventCount(){
        Session session = this.getCurrentSession();
        session.enableFilter("activeEvents");
        int count = ((Long) session.createQuery("SELECT DISTINCT COUNT(e.id) FROM Event e").uniqueResult()).intValue();
        session.disableFilter("activeEvents");
        return count;
    }
    public List<Event> getActiveEvents(int limit,int offset){
        Session session = this.getCurrentSession();
        session.enableFilter("activeEvents");
        Query query = session.createQuery("FROM Event e WHERE  e.active = true ORDER BY e.id DESC");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        List<Event> events = query.list();
        session.disableFilter("activeEvents");
        return events;
    }
    public List<Event> getActiveEvents(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Event WHERE active=true ORDER BY id DESC")
                .list();
    }
    public List<Event> getAll(Integer[] ids){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Event WHERE id in :ids")
                .setParameter("ids", Arrays.asList(ids))
                .list();
    }
    public Event getById(int id) {
        Session session = this.getCurrentSession();
        return (Event)session.createQuery("FROM Event where id=:id ")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }

    public List<Event> getActiveEventsByLocation(Integer locationId, Integer limit, Integer offset) {
        Session session = this.getCurrentSession();
        session.enableFilter("activeEvents");
        Query query = session.createQuery("FROM Event e WHERE e.location.id=:locationId ORDER BY e.id DESC");
        query.setParameter("locationId", locationId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Event> events = query.list();
        session.disableFilter("activeEvents");
        return events;
    }

    public Integer getActiveEventCountByLocation(Integer locationId){
        Session session = this.getCurrentSession();
        session.enableFilter("activeEvents");
        int count = ((Long) session.createQuery("SELECT DISTINCT COUNT(e.id) FROM Event e WHERE e.location.id=:locationId")
                .setParameter("locationId", locationId).uniqueResult()).intValue();
        session.disableFilter("activeEvents");
        return count;
    }

}
