package com.workspaceit.pmc.validation.advertisement.gallery;


import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.service.AdvertisementService;
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
public class GalleryAdsValidator implements Validator {

    private AdvertiserService advertiserService;
    private AdvertisementService advertisementService;

    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }
    @Autowired
    public void setAdvertisementService(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return GalleryAdsForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        GalleryAdsUpdateForm galleryAdsForm = (GalleryAdsUpdateForm)obj;



    }
    public void validate(Object obj, Errors errors,String... params) {
        GalleryAdsForm galleryAdsForm = (GalleryAdsForm)obj;

        for(String param : params) {
            switch (param) {
                case "advertiserId":
                    this.checkValidAdvertiserId(galleryAdsForm.getAdvertiserId(), errors);
            }
        }
    }

    public void validateUpdate(Object obj, Errors errors) {
        GalleryAdsForm galleryAdsForm = (GalleryAdsForm)obj;

        this.checkValidGallery(galleryAdsForm.getId(), errors);


    }
    void checkValidAdvertiserId(Integer advertiserId, Errors errors) {
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
    private void checkValidGallery(Integer id, Errors errors) {
        Advertisement galleryAd = this.advertisementService.getById(id);
        if(galleryAd==null || !galleryAd.getAdType().equals(ADVERTISEMENT_TYPE.GALLERY)){
            if(errors.getObjectName().equals("advertiserAndAllCompositeUpdateForm")){
                errors.rejectValue("galleryAds.id","GalleryAd not found by id :"+id);
            }else{
                errors.rejectValue("id","GalleryAd not found by id :"+id);
            }
        }

    }
}