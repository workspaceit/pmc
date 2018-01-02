package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Photographer;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mi_rafi on 12/28/17.
 */
@Repository
public class PhotographerDao extends BaseDao {

    public List<Photographer> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Photographer ORDER BY id DESC")
                .list();
    }
    public Photographer getById(int id){
        Session session = this.getCurrentSession();
        return (Photographer)session.createQuery("FROM Photographer WHERE id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public Photographer getByEmail(String email){
        Session session = this.getCurrentSession();
        return (Photographer)session.createQuery("FROM Photographer WHERE email=:email")
                .setParameter("email",email)
                .setMaxResults(1)
                .uniqueResult();
    }
    public Photographer getByIdAndEmail(int id,String email){
        Session session = this.getCurrentSession();
        return (Photographer)session.createQuery("FROM Photographer WHERE email=:email AND id !=:id")
                .setParameter("email",email)
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public Photographer getByUserName(String userName){
        Session session = this.getCurrentSession();
        return (Photographer)session.createQuery("FROM Photographer  WHERE user_name=:userName")
                .setParameter("userName",userName)
                .setMaxResults(1)
                .uniqueResult();
    }
    public Photographer getByIdAndUserName(int id,String userName){
        Session session = this.getCurrentSession();
        return (Photographer)session.createQuery("FROM Photographer  WHERE user_name=:userName AND id!=:id")
                .setParameter("userName",userName)
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
}
