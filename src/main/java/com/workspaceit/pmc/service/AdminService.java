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

    @Transactional(rollbackFor = Exception.class)
    public Admin create(AdminForm photographerForm, Admin user){
        Admin admin = this.adminDao.getByEmail(user.getName());
        String profilePictureName = "";
        Integer fileToken = photographerForm.getProfilePictureToken();

        boolean removeTempFileRequired = false;
        if(fileToken!=null && fileToken>0){
            profilePictureName = fileService.copyFileToPhotographerProfilePicture(fileToken);
            removeTempFileRequired = true;
        }

        Admin admin1 = new Admin();
        photographer.setFullName(photographerForm.getFullName());
        photographer.setUserName(photographerForm.getUserName());
        photographer.setEmail(photographerForm.getEmail());
        photographer.setPassword(CypherHelper.getbCryptPassword(photographerForm.getPassword()));
        photographer.setPhoneNumber(photographerForm.getPhoneNumber());
        photographer.setProfilePhoto(profilePictureName);
        photographer.setCreatedBy(admin);
        adminDao.insert(photographer);
        /**
         * Remove temporary file after insert
         * */
        if(removeTempFileRequired)fileService.removeTempFile(fileToken);

        return photographer;
    }
    public Admin getAdminByEmail(String email){
        return adminDao.getByEmail(email);
    }
    public void create(Admin admin){
        this.adminDao.insert(admin);
    }


}
