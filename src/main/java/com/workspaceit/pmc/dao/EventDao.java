package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Event;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 1/3/18.
 */
@Repository
public class EventDao extends BaseDao {

    public List<Event> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Event ORDER BY id DESC")
                .list();
    }
    public List<Event> getActiveEvents(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Event WHERE acitve=true ORDER BY id DESC")
                .list();
    }
    public List<Event> getAll(Integer[] ids){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Event where id in :ids")
                .setParameter("ids", Arrays.asList(ids))
                .list();
    }
    public Event getById(int id) {
        Session session = this.getCurrentSession();
        return (Event)session.createQuery("FROM Event where id=:id ")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
}
