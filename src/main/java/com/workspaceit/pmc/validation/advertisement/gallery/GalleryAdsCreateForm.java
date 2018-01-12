package com.workspaceit.pmc.validation.advertisement.gallery;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class GalleryAdsCreateForm extends GalleryAdsForm{


    @Override
    public Integer getAdvertiserId() {
        return advertiserId;
    }

    @Override
    @NotNull(message = "Logo is required")
    public Integer getLogoToken() {
        return logoToken;
    }

    @Override
    @NotNull(message = "Background Image required")
    public Integer getBgImgTokens() {
        return bgImgTokens;
    }

    @Override
    @NotNull(message = "Top Banner Image required")
    public Integer[] getTopBannerImgTokens() {
        return topBannerImgTokens;
    }

    @Override
    @NotNull(message = "Bottom Banner Image required")
    public Integer[] getBottomBannerImgTokens() {
        return bottomBannerImgTokens;
    }

    @Override
    @NotNull(message = "Top Banner Expiry Date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getTopBannerExpiryDate() {
        return topBannerExpiryDate;
    }

    @Override
    @NotNull(message = "Bottom Banner Expiry Date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getBottomBannerExpiryDate() {
        return bottomBannerExpiryDate;
    }

    @Override
    public void setAdvertiserId(Integer advertiserId) {
        this.advertiserId = advertiserId;
    }

    @Override
    public void setLogoToken(Integer logoToken) {
        this.logoToken = logoToken;
    }

    @Override
    public void setBgImgTokens(Integer bgImgTokens) {
        this.bgImgTokens = bgImgTokens;
    }

    @Override
    public void setTopBannerImgTokens(Integer[] topBannerImgTokens) {
        this.topBannerImgTokens = topBannerImgTokens;
    }

    @Override
    public void setBottomBannerImgTokens(Integer[] bottomBannerImgTokens) {
        this.bottomBannerImgTokens = bottomBannerImgTokens;
    }

    @Override
    public void setTopBannerExpiryDate(Date topBannerExpiryDate) {
        this.topBannerExpiryDate = topBannerExpiryDate;
    }

    @Override
    public void setBottomBannerExpiryDate(Date bottomBannerExpiryDate) {
        this.bottomBannerExpiryDate = bottomBannerExpiryDate;
    }


}
