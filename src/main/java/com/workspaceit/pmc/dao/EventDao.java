package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.*;
import java.util.*;

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
    public Integer getActiveEventCount(int days){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, (-1)*days);
        Date d = c.getTime();
        Session session = this.getCurrentSession();
        session.enableFilter("activeEvents");
        int count = ((Long) session.createQuery("SELECT DISTINCT COUNT(e.id) FROM Event e WHERE e.createdAt > :date").setParameter("date",d).uniqueResult()).intValue();
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

    public List<Object[]> getActiveEventsWithImageCount(){
        Session session = this.getCurrentSession();
        SQLQuery query = session.createSQLQuery("select id, name, created_at, active ,(select count(id) from event_images ei where e.id = ei.event_id and is_deleted=0) as no_of_images from events e order by e.id desc limit 5");
        List<Object[]> rows = query.list();
        return rows;
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
    public List<Event> getByWatermarkId(Integer watermarkId){
        Session session = this.getCurrentSession();
        return session.createQuery("select distinct  e FROM Event e " +
                "inner join fetch e.watermarks as watermark WHERE watermark.id = :watermarkId")
                .setParameter("watermarkId", watermarkId)
                .list();
    }
    public List<Event> getByPhotographerId(Integer photographerId){
        Session session = this.getCurrentSession();
        return session.createQuery("select distinct  e FROM Event e " +
                "inner join fetch e.photographers as photographer WHERE photographer.id = :photographerId")
                .setParameter("photographerId", photographerId)
                .list();
    }
    public List<Event> getByAdvertiserId(Integer advertiserId) {
        Session session = this.getCurrentSession();
        return session.createQuery("select distinct  e FROM Event e " +
                "inner join fetch e.advertisers as advertiser WHERE advertiser.id = :advertiserId")
                .setParameter("advertiserId", advertiserId)
                .list();
    }

    public Event getById(int id) {
        Session session = this.getCurrentSession();
        return (Event)session.createQuery("FROM Event where id=:id ")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }

    public List<Event> getActiveEventsByCriteria(Integer locationId, Date filterDate, Photographer photographer,
                                                 Integer limit, Integer offset) {
        Session session = this.getCurrentSession();
        session.enableFilter("activeEvents");

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = builder.createQuery(Event.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        Join<Event, Photographer> eventPhotographerJoin = eventRoot.join("photographers", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(eventPhotographerJoin.get("id"), photographer.getId()));
        if(locationId != null) {
            predicates.add(builder.equal(eventRoot.get("location").get("id"), locationId));
        }
        if(filterDate != null) {
            predicates.add(builder.lessThanOrEqualTo(eventRoot.get("startsAt"), filterDate));
            predicates.add(builder.greaterThanOrEqualTo(eventRoot.get("endsAt"), filterDate));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        Query<Event> query = session.createQuery(criteriaQuery);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Event> events = query.list();
        session.disableFilter("activeEvents");
        return events;
    }

    public Integer getActiveEventCountByCriteria(Integer locationId, Date filterDate, Photographer photographer){
        Session session = this.getCurrentSession();
        session.enableFilter("activeEvents");
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        Join<Event, Photographer> eventPhotographerJoin = eventRoot.join("photographers", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(eventPhotographerJoin.get("id"), photographer.getId()));
        if(locationId != null) {
            predicates.add(builder.equal(eventRoot.get("location").get("id"), locationId));
        }
        if(filterDate != null) {
            predicates.add(builder.lessThanOrEqualTo(eventRoot.get("startsAt"), filterDate));
            predicates.add(builder.greaterThanOrEqualTo(eventRoot.get("endsAt"), filterDate));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.select(builder.count(eventRoot.get("id")));
        Query<Long> query =session.createQuery(criteriaQuery);
        int count = query.getSingleResult().intValue();
        session.disableFilter("activeEvents");
        return count;
    }

}
