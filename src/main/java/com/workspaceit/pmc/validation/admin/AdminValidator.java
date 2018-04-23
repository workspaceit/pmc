package com.workspaceit.pmc.validation.admin;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by mi_rafi on 12/28/17.
 */
@Component
public class AdminValidator implements Validator {

    private AdminService adminService;
    private PhotographerService photographerService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }

    private void uniqueEmailCheck(String email, Errors errors){
        Admin admin = this.adminService.getAdminByEmail(email);
        if(admin!=null){
            errors.rejectValue("email", "Email already taken");
        }
    }
    private void uniquePhotographerEmailCheck(String email,Errors errors){
        Photographer photographer = this.photographerService.getByEmail(email);
        if(photographer!=null ){
            String tmpMsg = "Email already taken by a Photographer : '"+photographer.getFullName()+"'";

            if(photographer.getDeleted()){
                tmpMsg+=" but account was deleted";
            }
            errors.rejectValue("email", tmpMsg);
        }
    }
    private void uniquePhotographerUserNameCheck(String userName,Errors errors){
        Photographer photographer = this.photographerService.getByUserName(userName);
        if(photographer!=null ){
            String tmpMsg = "Username already taken by a Photographer : '"+photographer.getFullName()+"'";

            if(photographer.getDeleted()){
                tmpMsg+=" but account was deleted";
            }
            errors.rejectValue("userName", tmpMsg);
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
        return AdminCreateForm.class.equals(aClass);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        AdminCreateForm adminForm = (AdminCreateForm) obj;

        String email = adminForm.getEmail();
        String userName = adminForm.getUserName();
        String password = adminForm.getPassword();
        String conPassword = adminForm.getConfirmPassword();

        this.uniqueEmailCheck(email, errors);
        this.uniqueUserNameCheck(userName, errors);
        this.passwordMatchCheck(password, conPassword, errors);
        this.passwordStrengthCheck(password, errors);
        this.checkWithPhotographer(adminForm , errors);
    }

    private void checkWithPhotographer(AdminCreateForm adminForm , Errors errors){
        if(!errors.hasFieldErrors("email")){
            this.uniquePhotographerEmailCheck(adminForm.getEmail(), errors);
        }
        if(!errors.hasFieldErrors("userName")) {
            this.uniquePhotographerUserNameCheck(adminForm.getUserName(), errors);
        }
    }
    public void validateUpdate(Object obj, Errors errors) {
        AdminForm adminForm = (AdminEditForm)obj;

        String password = adminForm.getPassword();
        String conPassword = adminForm.getConfirmPassword();

        this.passwordMatchCheck(password,conPassword,errors);
        this.passwordStrengthCheck(password, errors);
    }
    public void validateProfileUpdate(Object obj, Errors errors) {
        AdminProfileUpdateForm adminProfileUpdateForm = (AdminProfileUpdateForm)obj;

        String password = adminProfileUpdateForm.getPassword();
        String conPassword = adminProfileUpdateForm.getConfirmPassword();

        if( (password!=null && !password.trim().equals("")) || (conPassword!=null && !conPassword.trim().equals("")) )
            this.passwordBasicValidation(password,conPassword,errors);
            this.passwordStrengthCheck(password, errors);
    }
    private void passwordBasicValidation(String password,String conPassword,Errors errors){

        ValidationUtils.rejectIfEmpty(errors,"password","Password required");
        ValidationUtils.rejectIfEmpty(errors,"confirmPassword","Confirm Password required");
        if(errors.getFieldErrorCount("password")>0 || errors.getFieldErrorCount("confirmPassword")>0){
            return;
        }
        if(errors.getFieldErrorCount("password")>0){
            return;
        }
        this.passwordMatchCheck(password,conPassword,errors);
    }

    private void passwordStrengthCheck(String password, Errors errors){
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (int i = 0; i < password.length(); i++) {
            char x = password.charAt(i);
            if (Character.isLetter(x)) {
                hasLetter = true;
            }
            else if (Character.isDigit(x)) {
                hasDigit = true;
            }
            // no need to check further, break the loop
            if(hasLetter && hasDigit){
                break;
            }
        }
        if (hasLetter && hasDigit) {
            System.out.println("STRONG");
        } else {
            errors.rejectValue("password", "Password must contain both letter(s) and number(s)");
        }

    }

    
}