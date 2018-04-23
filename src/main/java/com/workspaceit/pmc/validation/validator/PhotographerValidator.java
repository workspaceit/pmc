package com.workspaceit.pmc.validation.validator;

import com.workspaceit.pmc.dao.PhotographerDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.PhotographerService;
import com.workspaceit.pmc.validation.form.PhotographerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Created by mi_rafi on 12/28/17.
 */
@Component
public class PhotographerValidator implements Validator {

    private PhotographerService photographerService;
    private AdminService adminService;

    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PhotographerForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PhotographerForm photographerForm = (PhotographerForm)obj;

        String email = photographerForm.getEmail();
        String userName = photographerForm.getUserName();
        String password = photographerForm.getPassword();
        String conPassword = photographerForm.getConfirmPassword();

        this.uniqueEmailCheck(email,errors);
        this.uniqueUserNameCheck(userName,errors);

        this.passwordMatchCheck(password,conPassword,errors);
        this.passwordStrengthCheck(password, errors);
        this.checkWithAdmin(photographerForm,errors);


    }
    private void checkWithAdmin(PhotographerForm photographerForm, Errors errors){
        if(!errors.hasFieldErrors("email")){
            this.uniqueAdminEmailCheck(photographerForm.getEmail(),errors);
        }
        if(!errors.hasFieldErrors("userName")){
            this.uniqueAdminUserNameCheck(photographerForm.getUserName(),errors);
        }
    }
    public void validate(Object obj, Errors errors,String... params) {
        PhotographerForm photographerForm = (PhotographerForm)obj;

        String email = photographerForm.getEmail();
        String userName = photographerForm.getUserName();
        String password = photographerForm.getPassword();
        String conPassword = photographerForm.getConfirmPassword();

        for(String param : params){
            switch(param){
                case "email":
                    this.uniqueEmailCheck(email,errors);
                    if(!errors.hasFieldErrors("email")){
                        this.uniqueAdminEmailCheck(email,errors);
                    }
                    break;
                case "userName":
                    this.uniqueUserNameCheck(userName,errors);
                    if(!errors.hasFieldErrors("userName")){
                        this.uniqueAdminUserNameCheck(photographerForm.getUserName(),errors);
                    }
                    break;
                case "password":
                    this.passwordMatchCheck(password,conPassword,errors);
                    this.passwordStrengthCheck(password, errors);
                    break;
            }
        }
    }
    public void validateForUpdate(int id,Object obj, Errors errors,String... params) {
        PhotographerForm photographerForm = (PhotographerForm)obj;

        String email = photographerForm.getEmail();
        String userName = photographerForm.getUserName();
        String password = photographerForm.getPassword();
        String conPassword = photographerForm.getConfirmPassword();

        for(String param : params){
            switch(param){
                case "email":
                    this.emailUsedByOthersCheck(id,email,errors);
                    if(!errors.hasFieldErrors("email")){
                        this.uniqueAdminEmailCheck(email,errors);
                    }
                    break;
                case "userName":
                    this.userNameUsedByOthersCheck(id,userName,errors);
                    if(!errors.hasFieldErrors("userName")){
                        this.uniqueAdminUserNameCheck(photographerForm.getUserName(),errors);
                    }
                    break;
                case "password":
                    this.passwordMatchCheck(password,conPassword,errors);
                    break;
            }
        }
    }
    private void uniqueEmailCheck(String email,Errors errors){
        Photographer photographer = this.photographerService.getByEmail(email);
        if(photographer!=null ){
            String tmpMsg = "Email already taken";

            if(photographer.getDeleted()){
                tmpMsg+=" but account was deleted";
            }
            errors.rejectValue("email", tmpMsg);
        }
    }
    /** For login issue
     *  Oauth login and web login
     *  Same email and username can't be in both entity 'Photographer and Admin'
     * */
    private void uniqueAdminEmailCheck(String email,Errors errors){
        Admin admin = this.adminService.getAdminByEmail(email);

        if(admin!=null){
            String tmpMsg = "Email already taken by an Admin : '"+admin.getName()+"'";
            errors.rejectValue("email", tmpMsg);
        }
    }
    private void emailUsedByOthersCheck(int id,String email,Errors errors){
        Photographer photographer = this.photographerService.getByIdAndEmail(id,email);
        if(photographer!=null){
            errors.rejectValue("email", "Email already taken");
        }
    }
    private void uniqueUserNameCheck(String userName,Errors errors){
        Photographer photographer = this.photographerService.getByUserName(userName);
        if(photographer!=null){
            errors.rejectValue("userName", "User name already taken");
        }
    }
    /** For login issue
     *  Oauth login and web login
     *  Same email or username can't be in both entity 'Photographer and Admin'
     * */
    private void uniqueAdminUserNameCheck(String username,Errors errors){
        Admin admin = this.adminService.getByUserName(username);

        if(admin!=null){
            String tmpMsg = "Username already taken by an Admin : '"+admin.getName()+"'";
            errors.rejectValue("userName", tmpMsg);
        }
    }
    private void userNameUsedByOthersCheck(int id,String userName,Errors errors){
        Photographer photographer = this.photographerService.getByIdAndUserName(id,userName);
        if(photographer!=null){
            errors.rejectValue("userName", "User name already taken");
        }
    }
    private void passwordMatchCheck(String password,String conPassword,Errors errors){
        if((password!=null && conPassword!=null) && !password.equals(conPassword)){
            errors.rejectValue("password", "Password does not match with confirm password");
        }
    }

    private void passwordStrengthCheck(String password, Errors errors){
        boolean hasLetter = false;
        boolean hasDigit = false;
        if (password.length() >= 8) {
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
        } else {
            errors.rejectValue("password", "Password must be at least 8 characters");
        }
    }

}