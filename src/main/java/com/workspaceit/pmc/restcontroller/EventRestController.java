package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.EventService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.event.EventForm;
import com.workspaceit.pmc.validation.event.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
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

    private AdminService adminService;
    private EventService eventService;
    private EventValidator eventValidator;
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
    @Autowired
    public void setEventValidator(EventValidator eventValidator) {
        this.eventValidator = eventValidator;
    }
    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Secured(UserRole._SUPER_ADMIN)
    @PostMapping("/create")
    public ResponseEntity<?> create(Authentication authentication, @Valid EventForm eventForm, BindingResult bindingResult) throws EntityNotFound {
        Admin admin = (Admin) authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        if(bindingResult.hasErrors()){
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /**
         * Business logic Validation
         * */
        this.eventValidator.validate(eventForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        Event event = this.eventService.create(eventForm);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    }

}
