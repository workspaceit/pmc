package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.advertisement.SectionResource;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class SectionResourceDao  extends BaseDao{

    public List<SectionResource> getById(Integer[] id){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM SectionResource where id in :id")
                .setParameter("id", Arrays.asList(id))
                .list();
    }
    public List<SectionResource> getById(List<Integer> id){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM SectionResource where id in :id")
                .setParameter("id", Arrays.asList(id))
                .list();
    }
    public SectionResource getById(Integer id){
        Session session = this.getCurrentSession();
        return (SectionResource)session.createQuery("FROM SectionResource where id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .uniqueResult();
    }
}