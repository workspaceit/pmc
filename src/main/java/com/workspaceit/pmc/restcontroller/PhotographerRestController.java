package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.service.PhotographerService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.form.PhotographerForm;
import com.workspaceit.pmc.validation.validator.PhotographerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by mi_rafi on 12/28/17.
 */
@RestController
@RequestMapping(ControllerUriPrefix.API+"/photographer")
public class PhotographerRestController {

    private PhotographerValidator photographerValidator;
    private PhotographerService photographerService;

    @Autowired
    public void setPhotographerValidator(PhotographerValidator photographerValidator) {
        this.photographerValidator = photographerValidator;
    }

    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(Authentication authentication,@Valid PhotographerForm photographerForm, BindingResult bindingResult){
        System.out.println(authentication.getPrincipal().getClass());
        User user =  (User)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        serviceResponse.bindValidationError(bindingResult);
        /**
         * Basic Validation
         * */
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.photographerValidator.validate(photographerForm,bindingResult);
        serviceResponse.bindValidationError(bindingResult);
        /**
         * Business logic Validation
         * */
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /** End OF Validation**/
        Photographer photographer = photographerService.create(photographerForm,user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(photographer);
    }
}