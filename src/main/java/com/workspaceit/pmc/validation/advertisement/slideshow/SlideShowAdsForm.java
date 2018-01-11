package com.workspaceit.pmc.validation.advertisement.slideshow;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class SlideShowAdsForm {

    private Integer advertiserId;

    @NotNull(message = "Slide Show Ads Banner required")
    private Integer[] slideShowAdsBannerTokens;

    @NotNull(message = "Slide Show Ads Video required")
    private Integer slideShowAdsVideoToken;

    @NotNull(message = "Video Duration required")
    private Integer slideShowVideoDuration;

    @NotNull(message = "Banner Duration required")
    private Integer slideShowBannerDuration;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date videoExpiryDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date bannerExpiryDate;

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

    public Integer getSlideShowAdsVideoToken() {
        return slideShowAdsVideoToken;
    }

    public void setSlideShowAdsVideoToken(Integer slideShowAdsVideoToken) {
        this.slideShowAdsVideoToken = slideShowAdsVideoToken;
    }

    public Integer getSlideShowVideoDuration() {
        return slideShowVideoDuration;
    }

    public void setSlideShowVideoDuration(Integer slideShowVideoDuration) {
        this.slideShowVideoDuration = slideShowVideoDuration;
    }

    public Integer getSlideShowBannerDuration() {
        return slideShowBannerDuration;
    }

    public void setSlideShowBannerDuration(Integer slideShowBannerDuration) {
        this.slideShowBannerDuration = slideShowBannerDuration;
    }

    public Date getVideoExpiryDate() {
        return videoExpiryDate;
    }

    public void setVideoExpiryDate(Date videoExpiryDate) {
        this.videoExpiryDate = videoExpiryDate;
    }

    public Date getBannerExpiryDate() {
        return bannerExpiryDate;
    }

    public void setBannerExpiryDate(Date bannerExpiryDate) {
        this.bannerExpiryDate = bannerExpiryDate;
    }
}