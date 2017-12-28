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

        /**
         * Email unique check
        * */
        Photographer photographer = photographerDao.getByEmail(photographerForm.getEmail());
        if(photographer!=null){
            errors.rejectValue("email", "Email already taken");
        }

        /**
         * Username unique check
         * */
        photographer = photographerDao.getByUserName(photographerForm.getUserName());
        if(photographer!=null){
            errors.rejectValue("userName", "User name already taken");
        }

        /**
         * Password mathing with confirm password
         * */
        if(!photographerForm.getPassword().equals(photographerForm.getConfirmPassword())){
            errors.rejectValue("password", "Password does not match with confirm password");
        }
    }
}
