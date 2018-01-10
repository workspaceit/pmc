package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.LocationService;
import com.workspaceit.pmc.service.WatermarkService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.form.WatermarkForm;
import com.workspaceit.pmc.validation.location.LocationForm;
import com.workspaceit.pmc.validation.location.LocationValidator;
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

/**
 * Created by mi_rafi on 1/1/18.
 */
@RestController
@RequestMapping(ControllerUriPrefix.API+"/watermark")
public class WatermarkRestController {

    private WatermarkService watermarkService;
    private AdminService adminService;
    @Autowired
    public void setWatermarkService(WatermarkService watermarkService){
        this.watermarkService=watermarkService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid WatermarkForm watermarkForm, BindingResult bindingResult) {


        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


        this.watermarkService.create(watermarkForm);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    }



    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(Authentication authentication,@PathVariable("id") int id,@Valid WatermarkForm watermarkForm, BindingResult bindingResult) {

        Admin currentUser = this.adminService.getAdminByEmail(((User) authentication.getPrincipal()).getUsername());
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


        try {
            this.watermarkService.update(id,watermarkForm,currentUser);
        } catch (EntityNotFound entityNotFound) {
            serviceResponse.setValidationError("id",entityNotFound.getMessage());
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    }
}
