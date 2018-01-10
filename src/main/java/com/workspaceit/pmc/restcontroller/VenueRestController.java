package com.workspaceit.pmc.restcontroller;

/**
 * Created by Tomal on 1/8/2018.
 */

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.LocationService;
import com.workspaceit.pmc.service.VenueService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.venue.VenueForm;
import com.workspaceit.pmc.validation.venue.VenueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping(ControllerUriPrefix.API+"/venue")
public class VenueRestController {
    private VenueValidator venueValidator;
    private AdminService adminService;
    private VenueService venueService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }
    @Autowired
    public void setLocationValidator(VenueValidator venueValidator) {
        this.venueValidator = venueValidator;
    }

    @Secured(UserRole._SUPER_ADMIN)
    @PostMapping("/create")
    public ResponseEntity<?> create(Authentication authentication, @Valid VenueForm venueForm, BindingResult bindingResult) {
        Admin currentUser = this.adminService.getAdminByEmail(((User) authentication.getPrincipal()).getUsername());
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        //System.out.println(currentUser.getId());
        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.venueValidator.validate(venueForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        venueForm.setUserEmail(currentUser.getEmail());
        this.venueService.create(venueForm);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    }


    @Secured(UserRole._SUPER_ADMIN)
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(Authentication authentication,@PathVariable("id") int id, @Valid VenueForm venueForm, BindingResult bindingResult) throws EntityNotFound{
        Admin currentUser = this.adminService.getAdminByEmail(((User) authentication.getPrincipal()).getUsername());
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        //System.out.println(currentUser.getId());
        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.venueValidator.validate(venueForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        venueForm.setUserEmail(currentUser.getEmail());
        this.venueService.update(id,venueForm);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    }
}