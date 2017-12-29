package com.workspaceit.pmc.validation.validator;

import com.workspaceit.pmc.dao.PhotographerDao;
import com.workspaceit.pmc.entity.Photographer;
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

    private PhotographerDao photographerDao;

    @Autowired
    public void setPhotographerDao(PhotographerDao photographerDao) {
        this.photographerDao = photographerDao;
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
                    break;
                case "userName":
                    this.uniqueUserNameCheck(userName,errors);
                    break;
                case "password":
                    this.passwordMatchCheck(password,conPassword,errors);
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
                    break;
                case "userName":
                    this.userNameUsedByOthersCheck(id,userName,errors);
                    break;
                case "password":
                    this.passwordMatchCheck(password,conPassword,errors);
                    break;
            }
        }
    }
    private void uniqueEmailCheck(String email,Errors errors){
        Photographer photographer = photographerDao.getByEmail(email);
        if(photographer!=null){
            errors.rejectValue("email", "Email already taken");
        }
    }
    private void emailUsedByOthersCheck(int id,String email,Errors errors){
        Photographer photographer = photographerDao.getByIdAndEmail(id,email);
        if(photographer!=null){
            errors.rejectValue("email", "Email already taken");
        }
    }
    private void uniqueUserNameCheck(String userName,Errors errors){
        Photographer photographer = photographerDao.getByUserName(userName);
        if(photographer!=null){
            errors.rejectValue("userName", "User name already taken");
        }
    }
    private void userNameUsedByOthersCheck(int id,String userName,Errors errors){
        Photographer photographer = photographerDao.getByByIdAndUserName(id,userName);
        if(photographer!=null){
            errors.rejectValue("userName", "User name already taken");
        }
    }
    private void passwordMatchCheck(String password,String conPassword,Errors errors){
        if((password!=null && conPassword!=null) && !password.equals(conPassword)){
            errors.rejectValue("password", "Password does not match with confirm password");
        }
    }
}