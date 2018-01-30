package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.advertisement.Section;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class SectionDao extends BaseDao{
    public Section getById(int id){
        Session session = this.getCurrentSession();
        return (Section)session.createQuery("FROM Section  WHERE id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
}