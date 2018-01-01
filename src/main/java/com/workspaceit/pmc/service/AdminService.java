package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdminDao;
import com.workspaceit.pmc.entity.Admin;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by anik on 12/19/17.
 */

@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void insert(){
        try {
            Admin admin = new Admin();
            admin.setName("Anik S.");
            admin.setEmail("anik.sarker17@gmail.com");
            admin.setPassword("123456");
            adminDao.insert(admin);
            throw new RuntimeException();
        }
        catch (Exception e){

        }
        finally {

        }
    }

    @Transactional
    public Admin getAdminByEmail(String email){
        return adminDao.getByEmail(email);
    }



}
