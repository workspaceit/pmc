package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.SentSlideshow;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class SentSlideShowDao extends BaseDao{
    public SentSlideshow getById(String id){
        Session session = this.getCurrentSession();
        return (SentSlideshow)session.createQuery("FROM SentSlideshow  where id = :id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public SentSlideshow getByIdentifier(String identifier){
        Session session = this.getCurrentSession();
        return (SentSlideshow)session.createQuery("FROM SentSlideshow  where identifier = :identifier")
                .setParameter("identifier",identifier)
                .setMaxResults(1)
                .uniqueResult();
    }
    public void save(SentSlideshow sentSlideshow){
        this.insert(sentSlideshow);
    }
}
