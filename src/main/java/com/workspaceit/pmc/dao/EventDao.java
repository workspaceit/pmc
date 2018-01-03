package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Event;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mi_rafi on 1/3/18.
 */
@Repository
public class EventDao extends BaseDao{

    public List<Event> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Event")
                .list();
    }
}
