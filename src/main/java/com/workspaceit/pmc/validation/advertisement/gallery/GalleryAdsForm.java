package com.workspaceit.pmc.validation.advertisement.gallery;

import java.util.Date;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class GalleryAdsForm {

    protected Integer id;
    protected Integer advertiserId;

    protected Integer logoToken;
    protected Integer bgImgTokens;
    protected Integer[] topBannerImgTokens;
    protected Integer[] bottomBannerImgTokens;
    protected Date topBannerExpiryDate;
    protected Date bottomBannerExpiryDate;

    public Integer getId() {
        return id;
    }

    public Integer getAdvertiserId() {
        return advertiserId;
    }

    public Integer getLogoToken() {
        return logoToken;
    }

    public Integer getBgImgTokens() {
        return bgImgTokens;
    }

    public Integer[] getTopBannerImgTokens() {
        return topBannerImgTokens;
    }

    public Integer[] getBottomBannerImgTokens() {
        return bottomBannerImgTokens;
    }

    public Date getTopBannerExpiryDate() {
        return topBannerExpiryDate;
    }

    public Date getBottomBannerExpiryDate() {
        return bottomBannerExpiryDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAdvertiserId(Integer advertiserId) {
        this.advertiserId = advertiserId;
    }

    public void setLogoToken(Integer logoToken) {
        this.logoToken = logoToken;
    }

    public void setBgImgTokens(Integer bgImgTokens) {
        this.bgImgTokens = bgImgTokens;
    }

    public void setTopBannerImgTokens(Integer[] topBannerImgTokens) {
        this.topBannerImgTokens = topBannerImgTokens;
    }

    public void setBottomBannerImgTokens(Integer[] bottomBannerImgTokens) {
        this.bottomBannerImgTokens = bottomBannerImgTokens;
    }

    public void setTopBannerExpiryDate(Date topBannerExpiryDate) {
        this.topBannerExpiryDate = topBannerExpiryDate;
    }

    public void setBottomBannerExpiryDate(Date bottomBannerExpiryDate) {
        this.bottomBannerExpiryDate = bottomBannerExpiryDate;
    }
}
