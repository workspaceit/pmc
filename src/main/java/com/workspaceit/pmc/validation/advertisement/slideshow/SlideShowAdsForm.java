package com.workspaceit.pmc.validation.advertisement.slideshow;

import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
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
    private Integer slideShowVideoDuration;
    private Integer slideShowBannerDuration;
    private Date videoExpiryDate;
    private Date bannerExpiryDate;

    private AdvertiseRotationSettings bannerRotation;
    private AdvertiseRotationSettings videoRotation;

    private Float bannerPrice;
    private Float videoPrice;

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

    public AdvertiseRotationSettings getBannerRotation() {
        return bannerRotation;
    }

    public void setBannerRotation(AdvertiseRotationSettings bannerRotation) {
        this.bannerRotation = bannerRotation;
    }

    public AdvertiseRotationSettings getVideoRotation() {
        return videoRotation;
    }

    public void setVideoRotation(AdvertiseRotationSettings videoRotation) {
        this.videoRotation = videoRotation;
    }

    public Float getBannerPrice() {
        return bannerPrice;
    }

    public void setBannerPrice(Float bannerPrice) {
        this.bannerPrice = bannerPrice;
    }

    public Float getVideoPrice() {
        return videoPrice;
    }

    public void setVideoPrice(Float videoPrice) {
        this.videoPrice = videoPrice;
    }

    @Override
    public String toString() {
        return "SlideShowAdsForm{" +
                "id=" + id +
                ", advertiserId=" + advertiserId +
                ", slideShowAdsBannerTokens=" + Arrays.toString(slideShowAdsBannerTokens) +
                ", slideShowAdsVideoToken=" + slideShowAdsVideoToken +
                ", slideShowVideoDuration=" + slideShowVideoDuration +
                ", slideShowBannerDuration=" + slideShowBannerDuration +
                ", videoExpiryDate=" + videoExpiryDate +
                ", bannerExpiryDate=" + bannerExpiryDate +
                ", bannerRotation=" + bannerRotation +
                ", videoRotation=" + videoRotation +
                ", bannerPrice=" + bannerPrice +
                ", videoPrice=" + videoPrice +
                '}';
    }
}