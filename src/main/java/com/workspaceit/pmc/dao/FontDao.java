package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Font;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FontDao extends BaseDao {
    public List<Font> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Font ")
                .list();
    }
    public Font getById(int id){
        Session session = this.getCurrentSession();
        return (Font)session.createQuery("FROM Font where id = :id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
}
