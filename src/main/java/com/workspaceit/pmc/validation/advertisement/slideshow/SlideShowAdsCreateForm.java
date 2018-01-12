package com.workspaceit.pmc.validation.advertisement.slideshow;

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
    @NotNull(message = "Slide Show Ads Banner required")
    public Integer[] getSlideShowAdsBannerTokens() {
        return super.getSlideShowAdsBannerTokens();
    }

    @Override
    @NotNull(message = "Slide Show Ads Video required")
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
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getVideoExpiryDate() {
        return super.getVideoExpiryDate();
    }

    @Override
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getBannerExpiryDate() {
        return super.getBannerExpiryDate();
    }
}
