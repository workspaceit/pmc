package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.entity.ReportedImage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anik on 3/19/18.
 */

@Repository
public class ReportImageDao extends BaseDao {

    public ReportedImage getByImageId(EventImage eventImage){
        Session session=this.getCurrentSession();
        return (ReportedImage)session.createQuery("From ReportedImage where image_id=:id")
                .setParameter("id",eventImage.getId())
                .setMaxResults(1)
                .uniqueResult();
    }
    public ReportedImage getByImageId(int imageId){
        Session session=this.getCurrentSession();
        return (ReportedImage)session.createQuery("From ReportedImage where image_id=:id")
                .setParameter("id",imageId)
                .setMaxResults(1)
                .uniqueResult();
    }

    public List<ReportedImage> getAllByEventId(int eventId){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM ReportedImage a " +
                "WHERE a.actionTaken=0 and a.eventImage.event.id=:eventId" +
                " ORDER BY id desc").setParameter("eventId",eventId)
                .list();
    }

    public int getCountByEvent(Event event){
        Session session = this.getCurrentSession();

        int count = ((Long) session.createQuery("SELECT DISTINCT COUNT(ri.id) FROM ReportedImage ri " +
                "WHERE ri.actionTaken=0 and ri.eventImage.event.id=:eventId")
                .setParameter("eventId", event.getId()).uniqueResult()).intValue();

        return count;
    }

    public Boolean isReported(EventImage eventImage){
        Boolean result = false;
        if(this.getByImageId(eventImage)!=null){
            result = true;
        }
        return result;
    }
}
