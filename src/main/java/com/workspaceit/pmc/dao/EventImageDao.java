package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.exception.EntityNotFound;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by anik on 2/15/18.
 */

@Repository
public class EventImageDao extends BaseDao {

    public List<EventImage> getEventImagesByCriteria(Integer eventId, Integer limit, Integer offset){
        Session session = this.getCurrentSession();
        session.enableFilter("activeImages");
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EventImage> criteriaQuery = builder.createQuery(EventImage.class);
        Root<EventImage> eventImageRoot = criteriaQuery.from(EventImage.class);

        criteriaQuery.where(builder.equal(eventImageRoot.get("event").get("id"), eventId));
        criteriaQuery.orderBy(builder.desc(eventImageRoot.get("id")));
        Query query = session.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        List<EventImage> eventImages = query.getResultList();
        session.disableFilter("activeImages");
        return  eventImages;
    }
    public List<EventImage> getEventImagesByCriteria(Integer eventId){
        Session session = this.getCurrentSession();
        session.enableFilter("activeImages");
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EventImage> criteriaQuery = builder.createQuery(EventImage.class);
        Root<EventImage> eventImageRoot = criteriaQuery.from(EventImage.class);

        criteriaQuery.where(builder.equal(eventImageRoot.get("event").get("id"), eventId));
        criteriaQuery.orderBy(builder.desc(eventImageRoot.get("id")));
        Query query = session.createQuery(criteriaQuery);
        List<EventImage> eventImages = query.getResultList();
        session.disableFilter("activeImages");
        return  eventImages;
    }
    public EventImage getById(int id){
        Session session = this.getCurrentSession();
        return (EventImage)session.createQuery("FROM EventImage  WHERE id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }

    public Integer getImageCountForEvent(Event event){
        Session session = this.getCurrentSession();
        Integer count = ((Long) session.createQuery("SELECT DISTINCT COUNT(ei.id) FROM EventImage ei WHERE " +
                "ei.event.id=:eventId and ei.isDeleted=0").setParameter("eventId" , event.getId()).uniqueResult()).intValue();
        return count;
    }

    public  Integer getAllImageCount(int days){
        Integer count=0;
        Session session = this.getCurrentSession();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, (-1)*days);
        Date d = c.getTime();
        count= ((Long) session.createQuery("SELECT DISTINCT COUNT(ei.id) FROM EventImage ei WHERE " +
                "ei.isDeleted=0 and ei.createdAt > :date")
                .setParameter("date",d)
                .uniqueResult()).intValue();

        return count;
    }

    public Integer getAllImageCount(){
        Integer count=0;
        Session session = this.getCurrentSession();
        count = ((Long) session.createQuery("SELECT DISTINCT COUNT(ei.id) FROM EventImage ei WHERE " +
                    "ei.isDeleted=0").uniqueResult()).intValue();

        return count;
    }

    public Boolean deleteImage(int id){
        EventImage eventImage = this.getById(id);
        eventImage.setDeleted(true);
        this.update(eventImage);
        return true;
    }

    public Boolean sendToSlideShow(int id){
        EventImage eventImage = this.getById(id);
        eventImage.setInSlideshow(true);
        this.update(eventImage);
        return true;
    }

}
