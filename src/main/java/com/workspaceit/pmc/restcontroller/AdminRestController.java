package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.admin.AdminForm;
import com.workspaceit.pmc.validation.admin.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by mi_rafi on 12/28/17.
 */

@RestController
@RequestMapping(ControllerUriPrefix.API+"/admin")
public class AdminRestController {

    private AdminValidator adminValidator;
    private AdminService adminService;

    @Autowired
    public void setAdminValidator(AdminValidator adminValidator) {
        this.adminValidator = adminValidator;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/update/profile-picture/{id}")
    public ResponseEntity<?> updateProfilePicture(Authentication authentication,
                                                  @RequestParam("profilePictureToken") Integer token,
                                                  @PathVariable("id")  Integer id){
        System.out.println(authentication.getPrincipal().getClass());
        User user =  (User)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        if(token == null || token==0){
            serviceResponse.setValidationError("token","Token required");
        }
        if(id==null || id==0){
            serviceResponse.setValidationError("id","Admin Id required");
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /** End OF Validation**/


        Admin admin = null;
        try {
            admin = this.adminService.updateProfilePicture(id,token,user);
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
                                                @Valid AdminForm adminForm,
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
        this.adminValidator.validate(adminForm,bindingResult,"password");
        serviceResponse.bindValidationError(bindingResult,"password","confirmPassword");

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /** End OF Validation**/


        Admin admin = null;
        try {
            admin = this.adminService.updatePassword(id,adminForm.getPassword(),user);
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
                                            @Valid AdminForm adminForm,
                                            BindingResult bindingResult,
                                            @PathVariable("id")  Integer id){
        User user =  (User)authentication.getPrincipal();
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
        this.adminValidator.validateForUpdate(id,adminForm,bindingResult,params);
        serviceResponse.bindValidationError(bindingResult,params);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /** End OF Validation**/


        Admin admin = null;
        try {
            admin = this.adminService.updateBasicInfo(id,adminForm,user);
        } catch (EntityNotFound entityNotFound) {
            serviceResponse.setValidationError("id",entityNotFound.getMessage());
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("Info successfully updated"));
    }
}