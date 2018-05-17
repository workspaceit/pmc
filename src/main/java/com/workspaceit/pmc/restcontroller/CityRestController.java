package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.City;
import com.workspaceit.pmc.service.CityService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.city.CityForm;
import com.workspaceit.pmc.validation.city.CityValidator;
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
@RequestMapping(ControllerUriPrefix.API+"/city")
public class CityRestController {

    private CityValidator cityValidator;
    private CityService cityService;

    @Autowired
    public void setCityValidator(CityValidator cityValidator) {
        this.cityValidator = cityValidator;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @PostMapping("/create")
    public ResponseEntity<?> create(Authentication authentication, @Valid CityForm cityForm, BindingResult bindingResult) {
        Admin currentUser = (Admin)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.cityValidator.validate(cityForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        City city = this.cityService.create(cityForm);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(city);
    }

}
