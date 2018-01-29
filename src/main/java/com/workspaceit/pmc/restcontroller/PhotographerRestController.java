package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.PhotographerService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.form.PhotographerForm;
import com.workspaceit.pmc.validation.validator.PhotographerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;

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
        Admin currentUser =  (Admin)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */
        if(bindingResult.hasErrors()){
            serviceResponse.bindValidationError(bindingResult);
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
        Photographer photographer = photographerService.create(photographerForm,currentUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(photographer);
    }
    @PostMapping("/update/profile-picture/{id}")
    public ResponseEntity<?> updateProfilePicture(Authentication authentication,
                                                  @RequestParam("profilePictureToken") Integer token,
                                                  @PathVariable("id")  Integer id){
        System.out.println(authentication.getPrincipal().getClass());
        Admin currentUser =  (Admin)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        if(token == null || token==0){
            serviceResponse.setValidationError("token","Token required");
        }
        if(id==null || id==0){
            serviceResponse.setValidationError("id","Photographer Id required");
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /** End OF Validation**/


        Photographer photographer = null;
        try {
            photographer = this.photographerService.updateProfilePicture(id,token,currentUser);
        } catch (EntityNotFound entityNotFound) {
            serviceResponse.setValidationError("id",entityNotFound.getMessage());
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("Profile picture successfully updated"));
    }
    @PostMapping("/update/profile-password/{id}")
    public ResponseEntity<?> updatePassword(Authentication authentication,
                                                @Valid PhotographerForm photographerForm,
                                                BindingResult bindingResult,
                                                @PathVariable("id")  Integer id){
        User user =  (User)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        int errorCount = bindingResult.getFieldErrorCount("password");
        errorCount += bindingResult.getFieldErrorCount("password");
        if(errorCount>0){
            serviceResponse.bindValidationError(bindingResult,"password","confirmPassword");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /**
         * Business logic Validation
         * */
        this.photographerValidator.validate(photographerForm,bindingResult,"password");
        serviceResponse.bindValidationError(bindingResult,"password","confirmPassword");

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /** End OF Validation**/


        Photographer photographer = null;
        try {
            photographer = this.photographerService.updatePassword(id,photographerForm.getPassword(),user);
        } catch (EntityNotFound entityNotFound) {
            serviceResponse.setValidationError("id",entityNotFound.getMessage());
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("Password successful updated"));
    }
    @PostMapping("/update/profile-info/{id}")
    public ResponseEntity<?> updateBasicInfo(Authentication authentication,
                                            @Valid PhotographerForm photographerForm,
                                            BindingResult bindingResult,
                                            @PathVariable("id")  Integer id){
        Admin currentUser =  (Admin)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation for specific parameter
         * */
        String[] params = {"fullName","userName","email","phoneNumber"};
        int errorCount =0;
        for(String param:params){
            errorCount += bindingResult.getFieldErrorCount(param);
        }

        if(errorCount>0){
            serviceResponse.bindValidationError(bindingResult,params);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /**
         * Business logic Validation for specific parameter
         * */
        this.photographerValidator.validateForUpdate(id,photographerForm,bindingResult,params);
        serviceResponse.bindValidationError(bindingResult,params);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /** End OF Validation**/


        Photographer photographer = null;
        try {
            photographer = this.photographerService.updateBasicInfo(id,photographerForm,currentUser);
        } catch (EntityNotFound entityNotFound) {
            serviceResponse.setValidationError("id",entityNotFound.getMessage());
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("Info successfully updated"));
    }

    @GetMapping("/auto-suggest")
    public ResponseEntity<?> getSuggestedPhotographers(@RequestParam("searchTerm") String searchTerm){
        try {
            List<Photographer> photographers = photographerService.getSuggestedPhotographers(searchTerm, true);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(photographers);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
        }
    }

}