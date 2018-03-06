package com.workspaceit.pmc.validation.advertisement.slideshow;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
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

    public Integer[] getSlideShowAdsBannerTokens() {
        return super.getSlideShowAdsBannerTokens();
    }

    @Override
    public Integer getSlideShowAdsVideoToken() {
        return super.getSlideShowAdsVideoToken();
    }

    @Override
    @NotNull(message = "Video Duration required")
    public Integer getSlideShowVideoDuration() {
        return super.getSlideShowVideoDuration();
    }

    @Override
    @NotNull(message = "Banner Duration required")
    public Integer getSlideShowBannerDuration() {
        return super.getSlideShowBannerDuration();
    }

    @Override
    @NotNull(message = "Expire date required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getVideoExpiryDate() {
        return super.getVideoExpiryDate();
    }

    @Override
    @NotNull(message = "Expire date required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getBannerExpiryDate() {
        return super.getBannerExpiryDate();
    }

    @Override
    @NotNull(message = "Rotation settings required")
    public ADVERTISEMENT_ROTATION_SETTINGS getBannerRotation() {
        return super.getBannerRotation();
    }

    @Override
    @NotNull(message = "Rotation settings required")
    public ADVERTISEMENT_ROTATION_SETTINGS getVideoRotation() {
        return super.getVideoRotation();
    }


}
