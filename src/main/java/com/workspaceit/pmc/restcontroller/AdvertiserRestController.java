package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.AdvertiserTransaction;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.Section;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.exception.ServiceException;
import com.workspaceit.pmc.service.*;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsValidator;
import com.workspaceit.pmc.validation.advertisement.popup.PopUpAdsValidator;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsValidator;
import com.workspaceit.pmc.validation.advertiser.*;
import com.workspaceit.pmc.validation.checkout.CheckoutCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    private AdvertiserService advertiserService;

    private AdvertiserTransactionService advertiserTransactionService;
    private AdvertisementService advertisementService;
    private SectionService sectionService;
    private SectionResourceService sectionResourceService;


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
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }


    @Autowired
    public void setAdvertiserTransactionService(AdvertiserTransactionService advertiserTransactionService) {
        this.advertiserTransactionService = advertiserTransactionService;
    }

    @Autowired
    public void setAdvertisementService(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }
    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }


    @Autowired
    public void setSectionResourceService(SectionResourceService sectionResourceService) {
        this.sectionResourceService = sectionResourceService;
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
    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @RequestMapping(value = "/validate-create")
    public ResponseEntity<?> validateCreate(Authentication authentication, @Valid @RequestBody AdvertiserCreateForm advertiserForm, BindingResult bindingResult){
        System.out.println(advertiserForm);
        return this.getValidated(advertiserForm,bindingResult);
    }

    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
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
    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @PostMapping(value = "/create-all")
    public ResponseEntity<?> advertiserCreate(Authentication authentication, @Valid @RequestBody  AdvertiserAndAllCompositeForm advertiserAndAllCompositeForm, BindingResult bindingResult){
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


        /** new Implementation */

        /**
         * Create Advertisement first
         * */

        Advertisement galleryAd =  this.advertisementService.create(advertiser.getId(), ADVERTISEMENT_TYPE.GALLERY,currentUser);
        Advertisement slideShowAd =  this.advertisementService.create(advertiser.getId(), ADVERTISEMENT_TYPE.SLIDESHOW,currentUser);
        Advertisement popupSmsAd =  this.advertisementService.create(advertiser.getId(), ADVERTISEMENT_TYPE.POPUP_SMS,currentUser);
        Advertisement popupEmailAd =  this.advertisementService.create(advertiser.getId(), ADVERTISEMENT_TYPE.POPUP_EMAIL,currentUser);

        /**
         * Create Sections after advertisement
         * */
        List<Section> gallerySection = this.sectionService.create(galleryAd,advertiserAndAllCompositeForm.getGalleryAds(),currentUser);
        List<Section> slideShowSection =  this.sectionService.create(slideShowAd,advertiserAndAllCompositeForm.getSlideShowAds(),currentUser);
        Map<ADVERTISEMENT_TYPE,Section> popUpSectionMap = this.sectionService.create(popupSmsAd,popupEmailAd,advertiserAndAllCompositeForm.getPopupAds(),currentUser);

        galleryAd.setSections(this.sectionService.getSectionInMap(gallerySection));
        slideShowAd.setSections(this.sectionService.getSectionInMap(slideShowSection));
        popupSmsAd.setSections(this.sectionService.getSectionInMap(popUpSectionMap.get(ADVERTISEMENT_TYPE.POPUP_SMS)));
        popupEmailAd.setSections(this.sectionService.getSectionInMap(popUpSectionMap.get(ADVERTISEMENT_TYPE.POPUP_EMAIL)));

        /**
         * Update Advertiment after setting created Sections
         * Other wise section advertisement_id will be null
         * * */
        this.advertisementService.update(galleryAd,currentUser);
        this.advertisementService.update(slideShowAd,currentUser);
        this.advertisementService.update(popupSmsAd,currentUser);
        this.advertisementService.update(popupEmailAd,currentUser);


        return ResponseEntity.status(HttpStatus.OK).body(advertiser);
    }

    @PostMapping(value = "/update-all/{id}")
    public ResponseEntity<?> advertiserUpdate(@PathVariable("id") int id,
                                              Authentication authentication,
                                              @Valid @RequestBody AdvertiserAndAllCompositeUpdateForm advertiserAndAllCompositeForm,
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
            Advertiser advertiser = this.advertiserService.update(id,advertiserAndAllCompositeForm.getAdvertiser(),currentUser);

            this.sectionService.update(advertiser.getId(),advertiserAndAllCompositeForm);
            this.sectionResourceService.update(advertiserAndAllCompositeForm);
            this.sectionResourceService.removeSectionResource(advertiserAndAllCompositeForm);
            this.sectionService.updateQuantity(advertiser.getId());


        } catch (EntityNotFound entityNotFound) {

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.setValidationError("id",entityNotFound.getMessage()).getFormError());

        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Advertiser successfully created");
    }

    @GetMapping("/auto-suggest")
    public ResponseEntity<?> getSuggestedWatermarks(@RequestParam("searchTerm") String searchTerm){
        try {
            List<Advertiser> advertisers = advertiserService.getSuggestedAdvertisers(searchTerm, true);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(advertisers);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
        }
    }
    @Secured({UserRole._SUPER_ADMIN, UserRole._ADMIN})
    @PostMapping(value = "/checkout/{transactionId}/{advertiserId}")
    public ResponseEntity<?> checkout(@PathVariable("transactionId") int transactionId,
                                      @PathVariable("advertiserId") int advertiserId,
                                      @Valid CheckoutCreateForm checkoutCreateForm,
                                      BindingResult bindingResult,
                                      Authentication authentication) {
        /**
         * Basic Validation and Business logic Validation
         * */
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        /**
         * Basic Validation
         * */
        Advertiser advertiser = this.advertiserService.getById(advertiserId);

        if (advertiser == null) {
            serviceResponse.setValidationError("id","No advertiser found");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        Admin currentUser = (Admin) authentication.getPrincipal();
        AdvertiserTransaction advertiserTransaction=null;
        if(transactionId>0){
            advertiserTransaction = this.advertiserTransactionService.getById(transactionId);
        }
        try {
            if(advertiserTransaction==null){
                this.advertiserTransactionService.create(advertiserId,checkoutCreateForm,currentUser);
            }else{
                this.advertiserTransactionService.update(advertiserId,transactionId,checkoutCreateForm,currentUser);
            }
        } catch (EntityNotFound entityNotFound) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ServiceResponse.getMsgInMap(entityNotFound.getMessage()));
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getErrors());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully checkout");
    }


}