package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.GalleryAdImageService;
import com.workspaceit.pmc.service.GalleryAdService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsForm;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsValidator;
import com.workspaceit.pmc.validation.advertisement.popup.PopUpAdsValidator;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsValidator;
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
 * Created by mi_rafi on 1/5/18.
 */
@RestController
@RequestMapping(ControllerUriPrefix.API+"/pmc-adv")
public class AdvertisementRestController {
    GalleryAdsValidator galleryAdsValidator;
    SlideShowAdsValidator slideShowAdsValidator;
    PopUpAdsValidator popupAdsValidator;

    AdminService adminService;
    GalleryAdImageService galleryImageService;
    GalleryAdService galleryAdService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
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
    public void setGalleryImageService(GalleryAdImageService galleryImageService) {
        this.galleryImageService = galleryImageService;
    }
    @Autowired
    public void setGalleryAdService(GalleryAdService galleryAdService) {
        this.galleryAdService = galleryAdService;
    }

    @Secured(UserRole._SUPER_ADMIN)
    @PostMapping(value = "/gallery-create-validation")
    public ResponseEntity<?> validateGalleryAdsCreate(Authentication authentication, @Valid GalleryAdsForm galleryAdsForm, BindingResult bindingResult){
        Admin currentUser = this.adminService.getAdminByEmail(((User) authentication.getPrincipal()).getUsername());

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
    @Secured(UserRole._SUPER_ADMIN)

    @PostMapping(value = "/slideshow-create-validation")
    public ResponseEntity<?> validateSlideShowCreate(Authentication authentication, @Valid SlideShowAdsForm slideShowAdsForm, BindingResult bindingResult){
        Admin currentUser = this.adminService.getAdminByEmail(((User) authentication.getPrincipal()).getUsername());

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

    @PostMapping(value = "/popup-create-validation")
    public ResponseEntity<?> validatePopUpCreate(Authentication authentication, @Valid PopupAdsForm popupAdsForm, BindingResult bindingResult){
        Admin currentUser = this.adminService.getAdminByEmail(((User) authentication.getPrincipal()).getUsername());

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Basic Validation
         * */

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.popupAdsValidator.validate(popupAdsForm, bindingResult,"smsPopupBanner",
                                                                            "smsPopupVideo",
                                                                            "emailPopupBanner",
                                                                            "emailPopupVideo");
        serviceResponse.bindValidationError(bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("No error found"));
    }


    @PostMapping(value = "/gallery-create")
    public ResponseEntity<?> galleryCreate(Authentication authentication, @Valid GalleryAdsForm galleryAdsForm, BindingResult bindingResult){
         Admin currentUser = this.adminService.getAdminByEmail(((User) authentication.getPrincipal()).getUsername());

        GalleryAd galleryAd = this.galleryAdService.create(galleryAdsForm,currentUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.galleryAdService.getById(galleryAd.getId()));
    }



}
