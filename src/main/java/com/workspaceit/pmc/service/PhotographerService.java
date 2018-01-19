package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdminDao;
import com.workspaceit.pmc.dao.PhotographerDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.helper.CypherHelper;
import com.workspaceit.pmc.validation.form.PhotographerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Photographer getByEmail(String email){
        return this.photographerDao.getByEmail(email);
    }
    public Photographer getByIdAndEmail(int id,String email){
        return this.photographerDao.getByIdAndEmail(id,email);
    }
    public Photographer getByUserName(String userName){
        return this.photographerDao.getByUserName(userName);
    }
    public Photographer getByIdAndUserName(int id,String userName){
        return this.photographerDao.getByIdAndUserName(id,userName);
    }
    @Transactional(rollbackFor = Exception.class)
    public Photographer getById(int id){
        return this.photographerDao.getById(id);
    }
    @Transactional(rollbackFor = Exception.class)
    public List<Photographer> getAll(){
        return this.photographerDao.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Photographer create(PhotographerForm photographerForm,Admin admin){
        String profilePictureName = "";
        Integer fileToken = photographerForm.getProfilePictureToken();

        boolean removeTempFileRequired = false;
        if(fileToken!=null && fileToken>0){
            profilePictureName = fileService.copyFileToPhotographerProfilePicture(fileToken);
            removeTempFileRequired = true;
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
        /**
         * Remove temporary file after insert
         * */
        if(removeTempFileRequired)fileService.removeTempFile(fileToken);

        return photographer;
    }
    @Transactional(rollbackFor = Exception.class)
    public Photographer updateBasicInfo(int id,PhotographerForm photographerForm,Admin admin) throws EntityNotFound {

        String profilePictureName = "";
        Integer fileToken = photographerForm.getProfilePictureToken();
        boolean removeTempFileRequired=false;

        if(fileToken!=null && fileToken>0){
            profilePictureName = fileService.copyFileToPhotographerProfilePicture(fileToken);
            removeTempFileRequired = true;
        }

        Photographer photographer = this.getPhotographer(id);
        photographer.setFullName(photographerForm.getFullName());
        photographer.setUserName(photographerForm.getUserName());
        photographer.setEmail(photographerForm.getEmail());
        photographer.setPhoneNumber(photographerForm.getPhoneNumber());

        if(profilePictureName!=null&&!profilePictureName.trim().equals(""))
            photographer.setProfilePhoto(profilePictureName);

        this.photographerDao.update(photographer);

        /**
         * Remove temporary file after update
         * */
        if(removeTempFileRequired)this.fileService.removeTempFile(fileToken);


        return photographer;
    }
    private Photographer getPhotographer(int id)throws EntityNotFound{
        Photographer photographer =  this.getById(id);
        if(photographer==null){
            throw new EntityNotFound("No photographer found");
        }
        return photographer;
    }
    @Transactional(rollbackFor = Exception.class)
    public Photographer updateProfilePicture(int id,int token,Admin user)throws EntityNotFound{
        Photographer photographer =  this.getPhotographer(id);
        return this.updateProfilePicture(photographer,token,user);
    }

    @Transactional(rollbackFor = Exception.class)
    Photographer updateProfilePicture(Photographer photographer,int token,Admin user){
        String fileName = this.fileService.copyFileToPhotographerProfilePicture(token);
        if(fileName==null || fileName.equals("")){
            return photographer;
        }

        photographer.setProfilePhoto(fileName);
        this.update(photographer);
        this.fileService.removeTempFile(token);
        return photographer;
    }
    @Transactional(rollbackFor = Exception.class)
    public Photographer updatePassword(int id,String password,User user)throws EntityNotFound{
        Photographer photographer =  this.getPhotographer(id);
        photographer.setPassword(CypherHelper.getbCryptPassword(password));
        this.update(photographer);
        photographer = this.getPhotographer(id);
        return photographer;
    }

    public void create(Photographer photographer){
        this.photographerDao.insert(photographer);
    }
    public void update(Photographer photographer){
        this.photographerDao.update(photographer);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Photographer> getSuggestedPhotographers(String searchTerm){
        return this.photographerDao.getSuggestedPhotographers(searchTerm);
    }
}