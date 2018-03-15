package com.workspaceit.pmc.validation.advertisement.slideshow;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class SlideShowAdsForm {

    private Integer id;
    private Integer advertiserId;
    private Integer[] slideShowAdsBannerTokens;
    private Integer slideShowAdsVideoToken;
    private Integer slideShowBannerDuration;
    private Date bannerExpiryDate;

    private ADVERTISEMENT_ROTATION_SETTINGS bannerRotation;

    private Float bannerPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getSlideShowBannerDuration() {
        return slideShowBannerDuration;
    }

    public void setSlideShowBannerDuration(Integer slideShowBannerDuration) {
        this.slideShowBannerDuration = slideShowBannerDuration;
    }



    public Date getBannerExpiryDate() {
        return bannerExpiryDate;
    }

    public void setBannerExpiryDate(Date bannerExpiryDate) {
        this.bannerExpiryDate = bannerExpiryDate;
    }

    public ADVERTISEMENT_ROTATION_SETTINGS getBannerRotation() {
        return bannerRotation;
    }

    public void setBannerRotation(ADVERTISEMENT_ROTATION_SETTINGS bannerRotation) {
        this.bannerRotation = bannerRotation;
    }

    public Float getBannerPrice() {
        return bannerPrice;
    }

    public void setBannerPrice(Float bannerPrice) {
        this.bannerPrice = bannerPrice;
    }


    @Override
    public String toString() {
        return "SlideShowAdsForm{" +
                "id=" + id +
                ", advertiserId=" + advertiserId +
                ", slideShowAdsBannerTokens=" + Arrays.toString(slideShowAdsBannerTokens) +
                ", slideShowAdsVideoToken=" + slideShowAdsVideoToken +
                ", slideShowBannerDuration=" + slideShowBannerDuration +
                ", bannerExpiryDate=" + bannerExpiryDate +
                ", bannerRotation=" + bannerRotation +
                ", bannerPrice=" + bannerPrice +
                '}';
    }
}