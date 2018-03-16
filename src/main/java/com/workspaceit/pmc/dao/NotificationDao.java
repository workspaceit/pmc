package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Notification;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationDao extends BaseDao {
    public List<Notification> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Notification ORDER BY id DESC")
                .list();
    }
    public List<Notification> get(int limit,int offset){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Notification ORDER BY id DESC")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }
}