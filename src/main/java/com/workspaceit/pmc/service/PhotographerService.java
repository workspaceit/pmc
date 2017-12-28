package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdminDao;
import com.workspaceit.pmc.dao.PhotographerDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.TempFile;
import com.workspaceit.pmc.helper.CypherHelper;
import com.workspaceit.pmc.validation.form.PhotographerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mi_rafi on 12/28/17.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PhotographerService {
    private PhotographerDao photographerDao;
    private AdminDao adminDao;

    private FileService fileService;

    @Autowired
    public void setPhotographerDao(PhotographerDao photographerDao) {
        this.photographerDao = photographerDao;
    }

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Transactional(rollbackFor = Exception.class)
    public Photographer create(PhotographerForm photographerForm,User user){
        Admin admin = this.adminDao.getByEmail(user.getUsername());
        String profilePictureName = "";
        Integer fileToken = photographerForm.getProfilePictureToken();
        if(fileToken!=null){
            profilePictureName = fileService.copyFileToPhotographerProfilePicture(fileToken);
        }

        Photographer photographer = new Photographer();
        photographer.setFullName(photographerForm.getFullName());
        photographer.setUserName(photographerForm.getUserName());
        photographer.setEmail(photographerForm.getEmail());
        photographer.setPassword(CypherHelper.getbCryptPassword(photographerForm.getPassword()));
        photographer.setPhoneNumber(photographerForm.getPhoneNumber());
        photographer.setProfilePhoto(profilePictureName);
        photographer.setCreatedBy(admin);
        photographerDao.insert(photographer);
        fileService.removeTempFile(fileToken);
        return photographer;
    }

}