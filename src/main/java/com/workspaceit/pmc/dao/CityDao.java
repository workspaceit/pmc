package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.City;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by mi_rafi on 1/3/18.
 */
@Repository
public class CityDao extends BaseDao {
    public List<City> getAllNameAcs(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM City ORDER BY name asc")
                .list();
    }

    public City getById(int id){
        Session session = this.getCurrentSession();
        return (City)session.createQuery("FROM City where id=:id")
                .setMaxResults(1)
                .setParameter("id",id)
                .uniqueResult();
    }
}
