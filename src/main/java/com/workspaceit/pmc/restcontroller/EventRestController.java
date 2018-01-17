package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.event.EventForm;
import com.workspaceit.pmc.validation.location.LocationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by anik on 1/17/18.
 */

@RestController
@RequestMapping(ControllerUriPrefix.API+"/event")
public class EventRestController {

    AdminService adminService;
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Secured(UserRole._SUPER_ADMIN)
    @PostMapping("/create")
    public ResponseEntity<?> create(Authentication authentication, @Valid EventForm eventForm, BindingResult bindingResult){
        Admin currentUser = this.adminService.getAdminByEmail(((User) authentication.getPrincipal()).getUsername());
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse);
    }

}
