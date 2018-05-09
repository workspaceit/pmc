package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsCreateForm;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsValidator;
import com.workspaceit.pmc.validation.advertisement.popup.PopUpAdsValidator;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsCreateForm;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsCreateForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by mi_rafi on 1/5/18.
 */
@RestController
@RequestMapping(ControllerUriPrefix.API+"/pmc-adv")
public class AdvertisementRestController {
    private GalleryAdsValidator galleryAdsValidator;
    private SlideShowAdsValidator slideShowAdsValidator;
    private AdminService adminService;

    private PopUpAdsValidator popupAdsValidator;


    @Autowired
    public void setGalleryAdsValidator(GalleryAdsValidator galleryAdsValidator) {
        this.galleryAdsValidator = galleryAdsValidator;
    }

    @Autowired
    public void setSlideShowAdsValidator(SlideShowAdsValidator slideShowAdsValidator) {
        this.slideShowAdsValidator = slideShowAdsValidator;
    }

    @Autowired
    public void setPopupAdsValidator(PopUpAdsValidator popupAdsValidator) {
        this.popupAdsValidator = popupAdsValidator;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }



    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @PostMapping(value = "/gallery-create-validation")
    public ResponseEntity<?> validateGalleryAdsCreate(Authentication authentication, @Valid @RequestBody  GalleryAdsCreateForm galleryAdsForm, BindingResult bindingResult){
        Admin currentUser = (Admin)authentication.getPrincipal();

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.galleryAdsValidator.validate(galleryAdsForm, bindingResult,"logoToken",
                                                                                "bgImgTokens",
                                                                                "topBannerImgTokens",
                                                                                "bottomBannerImgTokens",
                                                                                "topBannerExpiryDate",
                                                                                "bottomBannerExpiryDate");
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("No error found"));
    }

    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @PostMapping(value = "/popup-create-validation")
    public ResponseEntity<?> validatePopUpCreate(Authentication authentication, @Valid @RequestBody  PopupAdsCreateForm pupAdsCreateForm, BindingResult bindingResult){
        Admin currentUser = (Admin)authentication.getPrincipal();

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.popupAdsValidator.validate(pupAdsCreateForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("No error found"));
    }

    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @PostMapping(value = "/slideshow-create-validation")
    public ResponseEntity<?> validateSlideShowCreate(Authentication authentication, @Valid @RequestBody SlideShowAdsCreateForm slideShowAdsForm, BindingResult bindingResult){
        Admin currentUser = (Admin)authentication.getPrincipal();
        System.out.println(slideShowAdsForm);
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.slideShowAdsValidator.validate(slideShowAdsForm, bindingResult,"slideShowAdsBannerTokens","slideShowAdsVideoToken");
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("No error found"));
    }



/*



    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> galleryCreate(@PathVariable("id") int id){

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.galleryAdService.getById(id));
    }*/


    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @PostMapping(value = "/gallery-update-validation")
    public ResponseEntity<?> validateGalleryAdsUpdate(Authentication authentication, @Valid @RequestBody GalleryAdsUpdateForm galleryAdsUpdateForm, BindingResult bindingResult){
        Admin currentUser = (Admin)authentication.getPrincipal();

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.galleryAdsValidator.validateUpdate(galleryAdsUpdateForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("No error found"));
    }

    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @PostMapping(value = "/slideshow-update-validation")
    public ResponseEntity<?> validateSlideShowUpdate(Authentication authentication, @Valid @RequestBody SlideShowAdsUpdateForm slideShowAdsForm, BindingResult bindingResult){
        Admin currentUser = (Admin)authentication.getPrincipal();

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.slideShowAdsValidator.validateUpdate(slideShowAdsForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("No error found"));
    }

    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @PostMapping(value = "/popup-update-validation")
    public ResponseEntity<?> validatePopUpUpdate(Authentication authentication, @Valid @RequestBody PopupAdsUpdateForm popupAdsUpdateForm, BindingResult bindingResult){
        Admin currentUser = (Admin)authentication.getPrincipal();

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.popupAdsValidator.validateUpdate(popupAdsUpdateForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("No error found"));
    }

}