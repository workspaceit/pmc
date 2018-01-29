package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.Watermark;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 12/28/17.
 */
@Repository
public class WatermarkDao extends BaseDao {

    public List<Watermark> getSuggestedWatermarks(String searchTerm, Boolean active){
        Session session = this.getCurrentSession();
        Query query = session.createQuery("SELECT DISTINCT(w) FROM Watermark w where active=:active AND w.name LIKE :searchTerm ORDER BY " +
                "CASE " +
                "   WHEN w.name=:txt THEN 0" +
                "   WHEN w.name LIKE :ptxt THEN 1" +
                "   WHEN w.name LIKE :txtp THEN 3" +
                "   ELSE 2 " +
                "END, w.name ASC, id DESC");
        query.setParameter("searchTerm", "%"+searchTerm+"%");
        query.setParameter("txt", searchTerm);
        query.setParameter("ptxt", searchTerm + "%");
        query.setParameter("txtp", "%" +searchTerm);
        query.setParameter("active", active);
        return query.list();
    }

    public List<Watermark> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Watermark ORDER BY id DESC")
                .list();
    }

    public List<Watermark> getActiveWatermarks(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Watermark WHERE active=true ORDER BY id DESC")
                .list();
    }

    public List<Watermark> getAll(Integer[] ids){
        List<Watermark> watermarks = null;
        if(ids.length > 0) {
            Session session = this.getCurrentSession();
            watermarks = session.createQuery("FROM Watermark w where id in :ids")
                    .setParameterList("ids", ids)
                    .list();
        }
        return watermarks;
    }


    public Watermark getById(int id){
        Session session = this.getCurrentSession();
        return (Watermark)session.createQuery("FROM Watermark where id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }

}
