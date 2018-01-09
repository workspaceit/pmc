package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdminDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.helper.CypherHelper;
import com.workspaceit.pmc.validation.admin.AdminForm;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by anik on 12/19/17.
 */

@Service
public class AdminService {
    private FileService fileService;
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

    
    public Admin getAdminByEmail(String email){
        return adminDao.getByEmail(email);
    }
    public Admin getByUserName(String userName){
        return this.adminDao.getByUserName(userName);
    }
    public void create(Admin admin){
        this.adminDao.insert(admin);
    }
    @Transactional(rollbackFor = Exception.class)
    public Admin create(AdminForm adminForm){
        Admin admin = getAdminFromAdminForm(adminForm);
        Integer profilePictureToken = adminForm.getProfilePictureToken();


        String profileImageName = "";
        if(profilePictureToken!=null && profilePictureToken>0){
            profileImageName = this.fileService.copyFile(profilePictureToken);
        }

        admin.setImage(profileImageName);
        this.create(admin);


        return admin;
    }
    private Admin getAdminFromAdminForm(AdminForm adminForm){

        Admin admin = new Admin();




        admin.setName(adminForm.getFullName());
        admin.setPhoneNumber(adminForm.getPhoneNumber());
        admin.setUserName(adminForm.getUserName());
        admin.setEmail(adminForm.getEmail());
        admin.setPassword(adminForm.getPassword());



        return admin;
    }
    @Transactional(rollbackFor = Exception.class)
    public List<Admin> getAll(){
        return this.adminDao.getAll();
    }

}
