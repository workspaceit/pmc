package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Advertiser;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mi_rafi on 1/4/18.
 */
@Repository
public class AdvertiserDao extends BaseDao {
    public Advertiser getById(int id){
        Session session = this.getCurrentSession();
        return (Advertiser)session.createQuery("FROM Advertiser WHERE id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public List<Advertiser> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Advertiser ORDER BY id desc")
                .list();
    }
}