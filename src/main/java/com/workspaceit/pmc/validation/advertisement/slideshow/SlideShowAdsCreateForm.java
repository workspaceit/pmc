package com.workspaceit.pmc.validation.advertisement.slideshow;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class SlideShowAdsCreateForm extends SlideShowAdsForm {



    @Override
    public Integer getAdvertiserId() {
        return super.getAdvertiserId();
    }


    @Override
    public SectionResourceForm[] getSlideShowAdsBannerResources() {
        return super.getSlideShowAdsBannerResources();
    }

    @Override
    public SectionResourceForm getSlideShowAdsVideoResources() {
        return super.getSlideShowAdsVideoResources();
    }

    @Override
    @NotNull(message = "Duration required")
    public Integer getSlideShowBannerDuration() {
        return super.getSlideShowBannerDuration();
    }

    @Override
    @NotNull(message = "Expire date required")
   // @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getBannerExpiryDate() {
        return super.getBannerExpiryDate();
    }

    @Override
    @NotNull(message = "Rotation settings required")
    public ADVERTISEMENT_ROTATION_SETTINGS getBannerRotation() {
        return super.getBannerRotation();
    }




}
