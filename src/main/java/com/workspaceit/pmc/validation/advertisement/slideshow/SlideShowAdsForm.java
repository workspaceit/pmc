package com.workspaceit.pmc.validation.advertisement.slideshow;

import javax.validation.constraints.NotNull;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class SlideShowAdsForm {

    private Integer advertiserId;

    @NotNull(message = "Slide Show Ads Banner required")
    private Integer[] slideShowAdsBannerTokens;

    @NotNull(message = "Slide Show Ads Video required")
    private Integer[] slideShowAdsVideoToken;

    public Integer getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Integer advertiserId) {
        this.advertiserId = advertiserId;
    }

    public Integer[] getSlideShowAdsBannerTokens() {
        return slideShowAdsBannerTokens;
    }

    public void setSlideShowAdsBannerTokens(Integer[] slideShowAdsBannerTokens) {
        this.slideShowAdsBannerTokens = slideShowAdsBannerTokens;
    }

    public Integer[] getSlideShowAdsVideoToken() {
        return slideShowAdsVideoToken;
    }

    public void setSlideShowAdsVideoToken(Integer[] slideShowAdsVideoToken) {
        this.slideShowAdsVideoToken = slideShowAdsVideoToken;
    }
}
