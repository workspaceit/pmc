package com.workspaceit.pmc.validation.advertisement.popup;


import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.service.AdvertiserService;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by mi_rafi on 12/28/17.
 */
@Component
public class PopUpAdsValidator implements Validator {

    AdvertiserService advertiserService;

    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PopupAdsForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PopupAdsForm  popupAdsForm = (PopupAdsForm)obj;



    }
    public void validate(Object obj, Errors errors,String... params) {
        PopupAdsForm popupAdsForm = (PopupAdsForm)obj;

        for(String param : params) {
            switch (param) {
                case "advertiserId":
                    this.checkValidAdvertiserId(popupAdsForm.getAdvertiserId(), errors);
                    break;
                case "sms-banner-or-video":
                    SectionResourceForm smsVideoToken =  popupAdsForm.getSmsPopupVideoResource();
                    SectionResourceForm[] smsBannerTokens =  popupAdsForm.getSmsPopupBannerResources();
                    this.checkSmsBannerOrVideo(smsVideoToken,smsBannerTokens,errors);
                    break;
                case "email-banner-or-video":
                    SectionResourceForm emailVideoToken =  popupAdsForm.getEmailPopupVideoResource();
                    SectionResourceForm[] emailBannerTokens =  popupAdsForm.getEmailPopupBannerResources();
                    this.checkEmailBannerOrVideo(emailVideoToken,emailBannerTokens,errors);
                    break;
            }
        }
    }
    public void checkSmsBannerOrVideo(SectionResourceForm smsVideoSecRes,SectionResourceForm[] smsBannerSecRes,Errors errors){

        if(smsVideoSecRes.getToken()==null && (smsBannerSecRes==null ||smsBannerSecRes.length==0)){
            errors.rejectValue("smsPopupBanner","Video or banner required");
        }
    }
    public void checkEmailBannerOrVideo(SectionResourceForm emailVideoToken,SectionResourceForm[] emailBannerTokens,Errors errors){

        if(emailVideoToken.getToken()==null && (emailBannerTokens==null || emailBannerTokens.length==0)){
            errors.rejectValue("emailPopupBanner","Video or banner required");
        }
    }
    public void validateUpdate(Object obj, Errors errors){
        PopupAdsUpdateForm popupAdsUpdateForm = (PopupAdsUpdateForm)obj;
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