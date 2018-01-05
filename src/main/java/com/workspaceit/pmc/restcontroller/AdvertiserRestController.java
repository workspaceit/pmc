package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.AdvertiserService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.advertiser.AdvertiserForm;
import com.workspaceit.pmc.validation.advertiser.AdvertiserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by mi_rafi on 1/4/18.
 */
@RestController
@RequestMapping(ControllerUriPrefix.API+"/pmc-advsr")
public class AdvertiserRestController {
    AdvertiserValidator advertiserValidator;

    AdminService adminService;
    AdvertiserService advertiserService;

    @Autowired
    public void setAdvertiserValidator(AdvertiserValidator advertiserValidator) {
        this.advertiserValidator = advertiserValidator;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    private ResponseEntity<?> getValidated(AdvertiserForm advertiserForm, BindingResult bindingResult){

        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        this.advertiserValidator.basicFormValidate(advertiserForm,bindingResult);

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.advertiserValidator.validate(advertiserForm, bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("No error found"));
    }

    @Secured(UserRole._SUPER_ADMIN)
    @RequestMapping(value = "/validate-create")
    public ResponseEntity<?> validateCreate(Authentication authentication, @Valid AdvertiserForm advertiserForm, BindingResult bindingResult){
        return this.getValidated(advertiserForm,bindingResult);
    }

    @Secured(UserRole._SUPER_ADMIN)
    @RequestMapping(value = "/create")
    public ResponseEntity<?> create(Authentication authentication, @Valid AdvertiserForm advertiserForm, BindingResult bindingResult){
        Admin currentUser = this.adminService.getAdminByEmail(((User) authentication.getPrincipal()).getUsername());


        /**
         * Basic Validation and Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return this.getValidated(advertiserForm,bindingResult);
        }

        this.advertiserService.create(advertiserForm,currentUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("Successfully created"));
    }
}
