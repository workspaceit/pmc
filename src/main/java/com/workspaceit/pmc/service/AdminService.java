package com.workspaceit.pmc.service;

import com.workspaceit.pmc.auth.AdminUserDetails;
import com.workspaceit.pmc.dao.AdminDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.helper.CypherHelper;
import com.workspaceit.pmc.util.FileUtil;
import com.workspaceit.pmc.validation.admin.AdminEditForm;
import com.workspaceit.pmc.validation.admin.AdminCreateForm;
import com.workspaceit.pmc.validation.admin.AdminForm;
import com.workspaceit.pmc.validation.admin.AdminProfileUpdateForm;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by anik on 12/19/17.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminService {

    private FileService fileService;
    private FileUtil fileUtil;
    @Autowired
    AdminDao adminDao;

    @Autowired
    protected void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }
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

    public boolean isUniqueEmail(String email){
        Admin admin = this.getAdminByEmail(email);
        return (admin==null);
    }
    public Admin getAdminByEmail(String email){
        return adminDao.getByEmail(email);
    }
    public Admin getAdminByEmailOrUserName(String emailOrUserName){
        return adminDao.getByEmailOrUserName(emailOrUserName);
    }

    public Admin getAdminByEmail(String email, Admin admin){
        return adminDao.getByEmail(email, admin);
    }
    public Admin getByUserName(String userName){
        return this.adminDao.getByUserName(userName);
    }
    public Admin getByUserName(String userName, Admin admin){
        return this.adminDao.getByUserName(userName, admin);
    }
    public void create(Admin admin){
        this.adminDao.insert(admin);
    }
    public Admin update(int id, AdminForm adminEditForm, Admin currentUser)throws EntityNotFound {
        Admin admin = this.getById(id);
        Integer profileImgToken = adminEditForm.getProfilePictureToken();

        admin.setName(adminEditForm.getFullName());
        admin.setPhoneNumber(adminEditForm.getPhoneNumber());

        if(adminEditForm.getPassword()!=null && !adminEditForm.getPassword().trim().equals("")){
            admin.setPassword(CypherHelper.getbCryptPassword(adminEditForm.getPassword()));
        }

        /**
         * Update admin profile
         * It replace previous image in database but
         * the physical image in folder explicitly
         * */
        if(profileImgToken!=null && profileImgToken>0){
            //this.fileUtil.deleteFileInCommonFolder(admin.getImage());
            String profileImgName =  this.fileService.copyFile(profileImgToken);
            admin.setImage(profileImgName);
        }
        this.update(admin);

        return admin;
    }
    public void update(Admin admin){
        this.adminDao.update(admin);
    }
    @Transactional(rollbackFor = Exception.class)
    public Admin create(AdminCreateForm adminCreateForm){
        Admin admin = getAdminFromAdminForm(adminCreateForm);
        Integer profilePictureToken = adminCreateForm.getProfilePictureToken();


        String profileImageName = "";
        if(profilePictureToken!=null && profilePictureToken>0){
            profileImageName = this.fileService.copyFile(profilePictureToken);
        }

        admin.setImage(profileImageName);
        this.create(admin);


        return admin;
    }
    public Admin updateProfile(int id, AdminProfileUpdateForm profileUpdateForm, Admin currentUser)throws EntityNotFound {
        Admin admin = this.getById(id);
        Integer profileImgToken = profileUpdateForm.getProfilePictureToken();


        admin.setName(profileUpdateForm.getFullName());
        admin.setPhoneNumber(profileUpdateForm.getPhoneNumber());

        if(profileUpdateForm.getPassword()!=null && !profileUpdateForm.getPassword().trim().equals("")){
            admin.setPassword(CypherHelper.getbCryptPassword(profileUpdateForm.getPassword()));
        }

        /**
         * Update admin profile
         * It replace previous image in database but
         * the physical image in folder explicitly
         * */
        if(profileImgToken!=null && profileImgToken>0){
            this.fileUtil.deleteFileInCommonFolder(admin.getImage());
            String profileImgName =  this.fileService.copyFile(profileImgToken);
            admin.setImage(profileImgName);
        }
        this.update(admin);

        /**
         * Update Spring authentication information
         * with new admin object
         * */
        /*******************************************/
        AdminUserDetails adminUserDetails = new AdminUserDetails(admin);
        Authentication authentication = new UsernamePasswordAuthenticationToken(adminUserDetails, admin.getPassword(), adminUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        /*******************************************/


        return admin;
    }
    private Admin getAdminFromAdminForm(AdminCreateForm adminCreateForm){
        Admin admin = new Admin();
        admin.setName(adminCreateForm.getFullName());
        admin.setPhoneNumber(adminCreateForm.getPhoneNumber());
        admin.setUserName(adminCreateForm.getUserName());
        admin.setEmail(adminCreateForm.getEmail());
        admin.setPassword(CypherHelper.getbCryptPassword(adminCreateForm.getPassword()));
        admin.setActive(true);
        return admin;
    }
    private void populateAdminByAdminForm(Admin admin, AdminCreateForm adminCreateForm){
        admin.setName(adminCreateForm.getFullName());
        admin.setPhoneNumber(adminCreateForm.getPhoneNumber());
        admin.setUserName(adminCreateForm.getUserName());
        admin.setEmail(adminCreateForm.getEmail());
        admin.setActive(true);
        admin.setPassword(CypherHelper.getbCryptPassword(adminCreateForm.getPassword()));
    }
    @Transactional(rollbackFor = Exception.class)
    public List<Admin> getAll(){
        return this.adminDao.getAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Admin getById(int id){
        return this.adminDao.getById(id);
    }

}
