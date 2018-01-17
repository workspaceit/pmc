package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.Watermark;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mi_rafi on 1/4/18.
 */
@Repository
public class AdvertiserDao extends BaseDao {
    public Advertiser getById(int id){
        Session session = this.getCurrentSession();
        return (Advertiser)session.createQuery("FROM Advertiser a " +
                                                        "LEFT JOIN FETCH a.otherImages " +
                                                        "LEFT JOIN FETCH a.events " +
                                                        "LEFT JOIN FETCH a.locations WHERE a.id=:id  ")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public List<Advertiser> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Advertiser ORDER BY id desc")
                .list();
    }

    public List<Advertiser> getSuggestedAdvertisers(String searchTerm){
        Session session = this.getCurrentSession();
        Query query = session.createQuery("SELECT DISTINCT(a) FROM Advertiser a where a.name LIKE :searchTerm ORDER BY " +
                "CASE " +
                "   WHEN a.name=:txt THEN 0" +
                "   WHEN a.name LIKE :ptxt THEN 1" +
                "   WHEN a.name LIKE :txtp THEN 3" +
                "   ELSE 2 " +
                "END, a.name ASC, id DESC");
        query.setParameter("searchTerm", "%"+searchTerm+"%");
        query.setParameter("txt", searchTerm);
        query.setParameter("ptxt", searchTerm + "%");
        query.setParameter("txtp", "%" +searchTerm);
        return query.list();
    }

}