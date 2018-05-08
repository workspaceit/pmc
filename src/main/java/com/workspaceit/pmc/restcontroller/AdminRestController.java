package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    private AdminEditValidator adminEditValidator;
    private AdminService adminService;

    @Autowired
    public void setAdminEditValidator(AdminEditValidator adminEditValidator) {
        this.adminEditValidator = adminEditValidator;
    }

    @Autowired
    public void setAdminValidator(AdminValidator adminValidator) {
        this.adminValidator = adminValidator;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    @Secured({UserRole._SUPER_ADMIN})
    public ResponseEntity<?> create(Authentication authentication, @Valid AdminCreateForm adminCreateForm, BindingResult bindingResult) {
        Admin currentUser = (Admin)authentication.getPrincipal();

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.adminValidator.validate(adminCreateForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.adminService.create(adminCreateForm);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> create(Authentication authentication, @PathVariable("id")  Integer id){
        Admin admin = adminService.getById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(admin);
    }

    @RequestMapping(value = "/update/{id}")
    @Secured({UserRole._SUPER_ADMIN})
    public ResponseEntity<?> update(Authentication authentication,
                                    @PathVariable("id") int id,
                                    @Valid AdminEditForm adminEditForm, BindingResult bindingResult){
        Admin currentUser = (Admin)authentication.getPrincipal();

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.adminValidator.validateUpdate(adminEditForm,bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        try {
            this.adminService.update(id,adminEditForm,currentUser);
        } catch (EntityNotFound entityNotFound) {
            serviceResponse.setValidationError("id",entityNotFound.getMessage());
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("Admin updated"));
    }


    @RequestMapping(value = "/update-profile")
    public ResponseEntity<?> updateProfile(Authentication authentication,
                                           @Valid AdminProfileUpdateForm profileUpdateForm,
                                           BindingResult bindingResult){
        Admin currentUser = (Admin)authentication.getPrincipal();

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.adminValidator.validateProfileUpdate(profileUpdateForm, bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        try {
            this.adminService.updateProfile(currentUser.getId(),profileUpdateForm,currentUser);
        } catch (EntityNotFound entityNotFound) {
            serviceResponse.setValidationError("id",entityNotFound.getMessage());
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("Admin updated"));
    }
    
}