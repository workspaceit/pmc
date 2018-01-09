package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.Watermark;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mi_rafi on 12/28/17.
 */
@Repository
public class WatermarkDao extends BaseDao {

    public List<Watermark> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Watermark ORDER BY id DESC")
                .list();
    }


    public Watermark getById(int id){
        Session session = this.getCurrentSession();
        return (Watermark)session.createQuery("FROM Watermark where id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }

}
