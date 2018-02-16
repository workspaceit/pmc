package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.EventImage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by anik on 2/15/18.
 */

@Repository
public class EventImageDao extends BaseDao {

    public List<EventImage> getEventImagesByCriteria(Integer eventId, Integer limit, Integer offset){
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EventImage> criteriaQuery = builder.createQuery(EventImage.class);
        Root<EventImage> eventImageRoot = criteriaQuery.from(EventImage.class);

        criteriaQuery.where(builder.equal(eventImageRoot.get("event").get("id"), eventId));
        Query query = session.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        List<EventImage> eventImages = query.getResultList();
        return  eventImages;
    }
    public EventImage getById(int id){
        Session session = this.getCurrentSession();
        return (EventImage)session.createQuery("FROM EventImage  WHERE id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }

}
