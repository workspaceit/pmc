package com.workspaceit.pmc.validation.advertisement.gallery;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class GalleryAdsForm {


    private Integer advertiserId;

    @NotNull(message = "Logo is required")
    private Integer logoToken;

    @NotNull(message = "Background Image required")
    private Integer bgImgTokens;

    @NotNull(message = "Top Banner Image required")
    private Integer[] topBannerImgTokens;

    @NotNull(message = "Bottom Banner Image required")
    private Integer[] bottomBannerImgTokens;


    @NotNull(message = "Top Banner Expiry Date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date topBannerExpiryDate;

    @NotNull(message = "Bottom Banner Expiry Date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date bottomBannerExpiryDate;


    public Integer getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Integer advertiserId) {
        this.advertiserId = advertiserId;
    }

    public Integer getLogoToken() {
        return logoToken;
    }

    public void setLogoToken(Integer logoToken) {
        this.logoToken = logoToken;
    }

    public Integer getBgImgTokens() {
        return bgImgTokens;
    }

    public void setBgImgTokens(Integer bgImgTokens) {
        this.bgImgTokens = bgImgTokens;
    }

    public Integer[] getTopBannerImgTokens() {
        return topBannerImgTokens;
    }

    public void setTopBannerImgTokens(Integer[] topBannerImgTokens) {
        this.topBannerImgTokens = topBannerImgTokens;
    }

    public Integer[] getBottomBannerImgTokens() {
        return bottomBannerImgTokens;
    }

    public void setBottomBannerImgTokens(Integer[] bottomBannerImgTokens) {
        this.bottomBannerImgTokens = bottomBannerImgTokens;
    }

    public Date getTopBannerExpiryDate() {
        return topBannerExpiryDate;
    }

    public void setTopBannerExpiryDate(Date topBannerExpiryDate) {
        this.topBannerExpiryDate = topBannerExpiryDate;
    }

    public Date getBottomBannerExpiryDate() {
        return bottomBannerExpiryDate;
    }

    public void setBottomBannerExpiryDate(Date bottomBannerExpiryDate) {
        this.bottomBannerExpiryDate = bottomBannerExpiryDate;
    }


}
