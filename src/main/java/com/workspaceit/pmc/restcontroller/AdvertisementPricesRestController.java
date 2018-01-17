package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.AdvertisementPricesService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.advertisement.price.AdvertisementPricesForm;
import com.workspaceit.pmc.validation.advertisement.price.AdvertisementPricesValidator;
import com.workspaceit.pmc.validation.venue.VenueForm;
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
 * Created by Tomal on 1/10/2018.
 */
@RestController
@RequestMapping(ControllerUriPrefix.API+"/advertisementPrices")
public class AdvertisementPricesRestController {

    private AdminService adminService;
    private AdvertisementPricesService advertisementPricesService;
    private AdvertisementPricesValidator advertisementPricesValidator;

    @Autowired
    public void setAdvertisementPricesValidator(AdvertisementPricesValidator advertisementPricesValidator) {
        this.advertisementPricesValidator = advertisementPricesValidator;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setAdvertisementPricesService(AdvertisementPricesService advertisementPricesService) {
        this.advertisementPricesService = advertisementPricesService;
    }

    @Secured(UserRole._SUPER_ADMIN)
    @PostMapping("/update")
    public ResponseEntity<?> update(Authentication authentication, @Valid AdvertisementPricesForm advertisementPricesForm, BindingResult bindingResult) throws EntityNotFound {
        Admin currentUser = (Admin)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        //System.out.println(currentUser.getId());
        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.advertisementPricesValidator.validate(advertisementPricesForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        advertisementPricesForm.setUserEmail(currentUser.getEmail());
        this.advertisementPricesService.update(0,advertisementPricesForm);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    }
}
