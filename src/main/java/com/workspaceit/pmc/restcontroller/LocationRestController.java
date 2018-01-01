package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.form.PhotographerForm;
import com.workspaceit.pmc.validation.location.LocationForm;
import com.workspaceit.pmc.validation.location.LocationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by mi_rafi on 1/1/18.
 */
@RestController
@RequestMapping(ControllerUriPrefix.API+"/location")
public class LocationRestController {
    private LocationValidator locationValidator;
    private AdminService adminService;

    @Autowired
    public void setLocationValidator(LocationValidator locationValidator) {
        this.locationValidator = locationValidator;
    }
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }



    @PostMapping("/create")
    public ResponseEntity<?> create(Authentication authentication, @Valid LocationForm locationForm, BindingResult bindingResult) {
        Admin currentUser = this.adminService.getAdminByEmail(((User) authentication.getPrincipal()).getUsername());

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.locationValidator.validate(locationForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);
        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    }
}
