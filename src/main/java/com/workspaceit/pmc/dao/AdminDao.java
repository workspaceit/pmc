package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Photographer;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anik on 12/19/17.
 */

@Repository
public class AdminDao extends BaseDao {

    public Admin insert(Admin admin){
        Session session = this.sessionFactory.getCurrentSession();
        session.save(admin);
        return admin;
    }
    public Admin getByEmail(String email){
        Session session = this.openSession();
        try{
            return (Admin)session.createQuery("FROM Admin  WHERE email=:email")
                    .setParameter("email",email)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            if(session!=null)session.close();
        }
    }
    public Admin getByEmail(String email, Admin admin){
        Session session = this.openSession();
        try{
            return (Admin)session.createQuery("FROM Admin  WHERE email=:email AND id!=:id")
                    .setParameter("email",email)
                    .setParameter("id",admin.getId())
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            if(session!=null)session.close();
        }
    }
    public Admin getByUserName(String userName){
        Session session = this.openSession();
        return (Admin)session.createQuery("FROM Admin  WHERE userName=:userName")
                .setParameter("userName",userName)
                .setMaxResults(1)
                .uniqueResult();
    }
    public Admin getByUserName(String userName, Admin admin){
        Session session = this.openSession();
        return (Admin)session.createQuery("FROM Admin  WHERE userName=:userName AND id !=:id")
                .setParameter("userName",userName)
                .setParameter("id",admin.getId())
                .setMaxResults(1)
                .uniqueResult();
    }
    public List<Admin> getAll(){
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Admin ORDER BY id DESC")
                .list();
    }

    public Admin getById(int id){
        Session session = this.openSession();
        try {
            return (Admin) session.createQuery("FROM Admin where id=:id")
                    .setParameter("id", id)
                    .uniqueResult();
        }
        finally {
            if(session!=null)session.close();
        }
    }


//    public void update(Admin admin){
//        Session session = this.openSession();
//        session.beginTransaction();
//        try {
//            session.update(admin);
//        }
//        finally {
//            if(session!=null) {
//                session.close();
//                session.getTransaction().commit();
//            }
//        }
//
//    }

}
