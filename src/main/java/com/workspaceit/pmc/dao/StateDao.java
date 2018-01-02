package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.State;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Repository
public class StateDao extends BaseDao{
    public State getById(int id){
        Session session = this.getCurrentSession();
        return (State)session.createQuery("FROM State WHERE id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public List<State> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM State")
                .getResultList();
    }
}