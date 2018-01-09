package com.workspaceit.pmc.validation.admin;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Created by mi_rafi on 12/28/17.
 */
@Component
public class AdminValidator implements Validator {

    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    private void uniqueEmailCheck(String email,Errors errors){
        Admin admin = this.adminService.getAdminByEmail(email);
        if(admin!=null){
            errors.rejectValue("email", "Email already taken");
        }
    }

    private void uniqueUserNameCheck(String userName,Errors errors){
        Admin admin = this.adminService.getByUserName(userName);
        if(admin!=null){
            errors.rejectValue("userName", "User name already taken");
        }
    }
    private void passwordMatchCheck(String password,String conPassword,Errors errors){
        if((password!=null && conPassword!=null) && !password.equals(conPassword)){
            errors.rejectValue("password", "Password does not match with confirm password");
        }
    }
    
    @Override
    public boolean supports(Class<?> aClass) {
        return AdminForm.class.equals(aClass);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        AdminForm adminrForm = (AdminForm)obj;

        String email = adminrForm.getEmail();
        String userName = adminrForm.getUserName();
        String password = adminrForm.getPassword();
        String conPassword = adminrForm.getConfirmPassword();

        this.uniqueEmailCheck(email,errors);
        this.uniqueUserNameCheck(userName,errors);
        this.passwordMatchCheck(password,conPassword,errors);
    }

    
}