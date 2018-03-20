package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.entity.ReportedImage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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
    public Boolean isReported(EventImage eventImage){
        Boolean result = false;
        if(this.getByImageId(eventImage)!=null){
            result = true;
        }
        return result;
    }
}
