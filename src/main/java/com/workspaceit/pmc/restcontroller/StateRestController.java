package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.service.StateService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.state.StateForm;
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
 * Created by anik on 5/17/18.
 */

@RestController
@RequestMapping(ControllerUriPrefix.API+"/state")
public class StateRestController {
    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @PostMapping("/create")
    public ResponseEntity<?> create(Authentication authentication, @Valid StateForm stateForm, BindingResult bindingResult) {
        Admin currentUser = (Admin)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        State state = this.stateService.create(stateForm);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(state);
    }

}
