package com.workspaceit.pmc.validation.advertisement.slideshow;


import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.service.AdvertiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by mi_rafi on 12/28/17.
 */
@Component
public class SlideShowAdsValidator implements Validator {

    AdvertiserService advertiserService;

    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SlideShowAdsForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        SlideShowAdsForm slideShowAdsForm = (SlideShowAdsForm)obj;



    }
    public void validate(Object obj, Errors errors,String... params) {
        SlideShowAdsForm slideShowAdsForm = (SlideShowAdsForm)obj;

        for(String param : params) {
            switch (param) {
                case "advertiserId":
                    this.checkValidAdvertiserId(slideShowAdsForm.getAdvertiserId(), errors);
            }
        }
    }
    public void validateUpdate(Object obj, Errors errors) {
        SlideShowAdsUpdateForm slideShowAdsForm = (SlideShowAdsUpdateForm)obj;


    }
    private void checkValidAdvertiserId(Integer advertiserId, Errors errors) {
        if (advertiserId==null){
            ValidationUtils.rejectIfEmpty(errors,"advertiserId","Advertiser is required");
            return;
        }
        Advertiser advertiser = this.advertiserService.getById(advertiserId);
        if (advertiser==null){
            ValidationUtils.rejectIfEmpty(errors,"advertiserId","Advertiser not found by id "+advertiserId);
            return;
        }
    }

}