package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.*;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsValidator;
import com.workspaceit.pmc.validation.advertisement.popup.PopUpAdsValidator;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsValidator;
import com.workspaceit.pmc.validation.advertiser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mi_rafi on 1/4/18.
 */
@RestController
@RequestMapping(ControllerUriPrefix.API+"/pmc-advsr")
public class AdvertiserRestController {
    private AdvertiserValidator advertiserValidator;
    private GalleryAdsValidator galleryAdsValidator;
    private SlideShowAdsValidator slideShowAdsValidator;
    private PopUpAdsValidator popUpAdsValidator;

    private AdminService adminService;
    private AdvertiserService advertiserService;
    private GalleryAdService galleryAdService;
    private SlideShowService slideShowService;
    private PopUpAdsService popUpAdsService;

    @Autowired
    public void setAdvertiserValidator(AdvertiserValidator advertiserValidator) {
        this.advertiserValidator = advertiserValidator;
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
    public void setPopUpAdsValidator(PopUpAdsValidator popUpAdsValidator) {
        this.popUpAdsValidator = popUpAdsValidator;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @Autowired
    public void setGalleryAdService(GalleryAdService galleryAdService) {
        this.galleryAdService = galleryAdService;
    }

    @Autowired
    public void setSlideShowService(SlideShowService slideShowService) {
        this.slideShowService = slideShowService;
    }

    @Autowired
    public void setPopUpAdsService(PopUpAdsService popUpAdsService) {
        this.popUpAdsService = popUpAdsService;
    }

    private ResponseEntity<?> getValidated(AdvertiserForm advertiserForm, BindingResult bindingResult){

        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        this.advertiserValidator.basicFormValidate(advertiserForm,bindingResult);

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        this.advertiserValidator.validate(advertiserForm, bindingResult);

        /**
         * Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("No error found"));
    }

    @RequestMapping(value = "/get/{id}")
    public ResponseEntity<?> getById(Authentication authentication, @PathVariable("id") int id ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.advertiserService.getById(id));
    }
    @Secured(UserRole._SUPER_ADMIN)
    @RequestMapping(value = "/validate-create")
    public ResponseEntity<?> validateCreate(Authentication authentication, @Valid AdvertiserCreateForm advertiserForm, BindingResult bindingResult){
        return this.getValidated(advertiserForm,bindingResult);
    }

    @Secured(UserRole._SUPER_ADMIN)
    @RequestMapping(value = "/create")
    public ResponseEntity<?> create(Authentication authentication, @Valid AdvertiserForm advertiserForm, BindingResult bindingResult){
        Admin currentUser = (Admin)authentication.getPrincipal();


        /**
         * Basic Validation and Business logic Validation
         * */
        if (bindingResult.hasErrors()) {
            return this.getValidated(advertiserForm,bindingResult);
        }

        Advertiser advertiser = this.advertiserService.create(advertiserForm,currentUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(advertiser);
    }
    @Secured({UserRole._SUPER_ADMIN})
    @PostMapping(value = "/create-all")
    public ResponseEntity<?> advertiserCreate(Authentication authentication, @Valid AdvertiserAndAllCompositeForm advertiserAndAllCompositeForm, BindingResult bindingResult){
        /**
         * Validation should be done separately from Client before submitting
         * Below Validation not meant for client end error showing
         * and it would be difficult to show
         * */

        /**
         * Basic Validation and Business logic Validation
         * */
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        Admin currentUser = (Admin)authentication.getPrincipal();


        this.advertiserValidator.validate(advertiserAndAllCompositeForm.getAdvertiser(), bindingResult);
        this.galleryAdsValidator.validate(advertiserAndAllCompositeForm.getGalleryAds(),
                                            bindingResult,"logoToken",
                                            "bgImgTokens",
                                            "topBannerImgTokens",
                                            "bottomBannerImgTokens",
                                            "topBannerExpiryDate",
                                            "bottomBannerExpiryDate");
        this.slideShowAdsValidator.validate(advertiserAndAllCompositeForm.getSlideShowAds(),
                                            bindingResult,"slideShowAdsBannerTokens","slideShowAdsVideoToken");
        this.popUpAdsValidator.validate(advertiserAndAllCompositeForm.getPopupAds(),
                                            bindingResult,"smsPopupBanner",
                                                            "smsPopupVideo",
                                                            "emailPopupBanner",
                                                            "emailPopupVideo");

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        Advertiser advertiser = this.advertiserService.create(advertiserAndAllCompositeForm.getAdvertiser(),currentUser);

        this.galleryAdService.create(advertiser,advertiserAndAllCompositeForm.getGalleryAds(),currentUser);
        this.slideShowService.create(advertiser,advertiserAndAllCompositeForm.getSlideShowAds(),currentUser);
        this.popUpAdsService.create(advertiser,advertiserAndAllCompositeForm.getPopupAds(),currentUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(advertiser);
    }

    @PostMapping(value = "/update-all/{id}")
    public ResponseEntity<?> advertiserUpdate(@PathVariable("id") int id,
                                              Authentication authentication,
                                              @Valid AdvertiserAndAllCompositeUpdateForm advertiserAndAllCompositeForm,
                                              BindingResult bindingResult){
        /**
         * Validation should be done separately from Client before submitting
         * Below Validation not meant for client end error showing
         * and it would be difficult to show
         * */

        /**
         * Basic Validation and Business logic Validation
         * */
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        Admin currentUser = (Admin)authentication.getPrincipal();


        this.advertiserValidator.validate(advertiserAndAllCompositeForm.getAdvertiser(), bindingResult);
        this.galleryAdsValidator.validateUpdate(advertiserAndAllCompositeForm.getGalleryAds(),bindingResult);
        this.slideShowAdsValidator.validateUpdate(advertiserAndAllCompositeForm.getSlideShowAds(),bindingResult);
        this.popUpAdsValidator.validateUpdate(advertiserAndAllCompositeForm.getPopupAds(),bindingResult);

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        try {
            int galleryAdId = advertiserAndAllCompositeForm.getGalleryAds().getId();
            int slideShowAdId = advertiserAndAllCompositeForm.getSlideShowAds().getId();
            int smsPopUpAdId = advertiserAndAllCompositeForm.getPopupAds().getSmsId();
            int emailPopUpAdId = advertiserAndAllCompositeForm.getPopupAds().getEmailId();

            Advertiser advertiser = this.advertiserService.update(id,advertiserAndAllCompositeForm.getAdvertiser(),currentUser);
            this.galleryAdService.update(galleryAdId,
                                        advertiser,
                                        advertiserAndAllCompositeForm.getGalleryAds(),
                                        currentUser);

            this.slideShowService.update(slideShowAdId,advertiser,
                                        advertiserAndAllCompositeForm.getSlideShowAds(),
                                        currentUser);

            this.popUpAdsService.update(smsPopUpAdId,
                                            emailPopUpAdId,
                                            advertiser,
                                            advertiserAndAllCompositeForm.getPopupAds(),
                                            currentUser);

        } catch (EntityNotFound entityNotFound) {

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.setMsg("id",entityNotFound.getMessage()).getFormError());

        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Advertiser successfully created");
    }

    @GetMapping("/auto-suggest")
    public ResponseEntity<?> getSuggestedWatermarks(@RequestParam("searchTerm") String searchTerm){
        try {
            List<Advertiser> advertisers = advertiserService.getSuggestedAdvertisers(searchTerm);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(advertisers);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
        }
    }

}