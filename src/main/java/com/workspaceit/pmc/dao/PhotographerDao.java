package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Photographer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mi_rafi on 12/28/17.
 */
@Repository
public class PhotographerDao extends BaseDao {

    public List<Photographer> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Photographer p where p.deleted = false ORDER BY p.createdAt DESC")
                .list();
    }

    public List<Photographer> getActivePhotographers(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Photographer WHERE active=true ORDER BY id DESC")
                .list();
    }

    public List<Photographer> getAll(Integer[] ids){
        List<Photographer> photographers = null;
        if(ids.length > 0) {
            Session session = this.getCurrentSession();
            photographers = session.createQuery("FROM Photographer where id in :ids")
                    .setParameterList("ids", Arrays.asList(ids))
                    .list();
        }
        return photographers;
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
    public Photographer getByEmailOrUserName(String emailOrUserName){
        Session session = this.getCurrentSession();
        return (Photographer)session.createQuery("FROM Photographer WHERE email=:emailOrUserName OR userName=:emailOrUserName")
                .setParameter("emailOrUserName",emailOrUserName)
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

    public List<Photographer> getSuggestedPhotographers(String searchTerm, Boolean active){
        Session session = this.getCurrentSession();
        Query query = session.createQuery("SELECT DISTINCT(p) FROM Photographer p WHERE active=:active AND " +
                "p.fullName LIKE :searchTerm ORDER BY " +
                "CASE " +
                "   WHEN p.fullName=:txt THEN 0" +
                "   WHEN p.fullName LIKE :ptxt THEN 1" +
                "   WHEN p.fullName LIKE :txtp THEN 3" +
                "   ELSE 2 " +
                "END, p.fullName ASC, id DESC");
        query.setParameter("searchTerm", "%"+searchTerm+"%");
        query.setParameter("txt", searchTerm);
        query.setParameter("ptxt", searchTerm + "%");
        query.setParameter("txtp", "%" +searchTerm);
        query.setParameter("active", active);
        return query.list();
    }
}
