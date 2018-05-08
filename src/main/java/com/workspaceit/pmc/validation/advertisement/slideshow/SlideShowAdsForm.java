package com.workspaceit.pmc.validation.advertisement.slideshow;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class SlideShowAdsForm {

    private Integer id;
    private Integer advertiserId;
    private String videoRotation;

    @Valid
    @NotNull
    private SectionResourceForm slideShowAdsVideoResources;

    @Valid
    @NotNull
    private SectionResourceForm[] slideShowAdsBannerResources;

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

    public String getVideoRotation() {
        return videoRotation;
    }

    public void setVideoRotation(String videoRotation) {
        this.videoRotation = videoRotation;
    }

    public SectionResourceForm[] getSlideShowAdsBannerResources() {
        return slideShowAdsBannerResources;
    }

    public void setSlideShowAdsBannerResources(SectionResourceForm[] slideShowAdsBannerResources) {
        this.slideShowAdsBannerResources = slideShowAdsBannerResources;
    }

    public SectionResourceForm getSlideShowAdsVideoResources() {
        return slideShowAdsVideoResources;
    }

    public void setSlideShowAdsVideoResources(SectionResourceForm slideShowAdsVideoResources) {
        this.slideShowAdsVideoResources = slideShowAdsVideoResources;
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
                ", slideShowAdsBannerResources=" + Arrays.toString(slideShowAdsBannerResources) +
                ", slideShowAdsVideoResources=" + slideShowAdsVideoResources +
                ", slideShowBannerDuration=" + slideShowBannerDuration +
                ", bannerExpiryDate=" + bannerExpiryDate +
                ", bannerRotation=" + bannerRotation +
                ", bannerPrice=" + bannerPrice +
                '}';
    }
}