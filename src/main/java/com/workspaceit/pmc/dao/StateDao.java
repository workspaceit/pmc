package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.State;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Repository
public class StateDao extends BaseDao{
    public State getById(int id){
        Session session = this.getCurrentSession();
        try{
            return (State)session.createQuery("FROM State WHERE id=:id")
                    .setParameter("id",id)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            if(session!=null)session.close();
        }
    }
}