package com.workspaceit.pmc.validation.advertisement.gallery;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class GalleryAdsForm {

    protected Integer id;
    protected Integer advertiserId;

    @Valid
    @NotNull
    protected SectionResourceForm logoSectionResource;

    @Valid
    @NotNull
    protected SectionResourceForm bgSectionResource;


    @Valid
    @NotNull
    protected SectionResourceForm[] topSectionResource;

    @Valid
    @NotNull
    protected SectionResourceForm[] bottomSectionResource;

    protected Date topBannerExpiryDate;
    protected Date bottomBannerExpiryDate;

    ADVERTISEMENT_ROTATION_SETTINGS topBannerRotation;
    ADVERTISEMENT_ROTATION_SETTINGS bottomBannerRotation;

    protected Float bgPrice;
    protected Float topBannerPrice;
    protected Float bottomBannerPrice;

    public Integer getId() {
        return id;
    }

    public Integer getAdvertiserId() {
        return advertiserId;
    }



    public SectionResourceForm[] getTopSectionResource() {
        return topSectionResource;
    }

    public SectionResourceForm[] getBottomSectionResource() {
        return bottomSectionResource;
    }

    public SectionResourceForm getLogoSectionResource() {
        return logoSectionResource;
    }

    public SectionResourceForm getBgSectionResource() {
        return bgSectionResource;
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

    public void setLogoSectionResource(SectionResourceForm logoSectionResource) {
        this.logoSectionResource = logoSectionResource;
    }

    public void setBgSectionResource(SectionResourceForm bgSectionResource) {
        this.bgSectionResource = bgSectionResource;
    }

    public void setTopSectionResource(SectionResourceForm[] topSectionResource) {
        this.topSectionResource = topSectionResource;
    }

    public void setBottomSectionResource(SectionResourceForm[] bottomSectionResource) {
        this.bottomSectionResource = bottomSectionResource;
    }

    public void setTopBannerExpiryDate(Date topBannerExpiryDate) {
        this.topBannerExpiryDate = topBannerExpiryDate;
    }

    public void setBottomBannerExpiryDate(Date bottomBannerExpiryDate) {
        this.bottomBannerExpiryDate = bottomBannerExpiryDate;
    }

    public ADVERTISEMENT_ROTATION_SETTINGS getTopBannerRotation() {
        return topBannerRotation;
    }

    public void setTopBannerRotation(ADVERTISEMENT_ROTATION_SETTINGS topBannerRotation) {
        this.topBannerRotation = topBannerRotation;
    }

    public ADVERTISEMENT_ROTATION_SETTINGS getBottomBannerRotation() {
        return bottomBannerRotation;
    }

    public void setBottomBannerRotation(ADVERTISEMENT_ROTATION_SETTINGS bottomBannerRotation) {
        this.bottomBannerRotation = bottomBannerRotation;
    }

    public Float getBgPrice() {
        return bgPrice;
    }

    public void setBgPrice(Float bgPrice) {
        this.bgPrice = bgPrice;
    }

    public Float getTopBannerPrice() {
        return topBannerPrice;
    }

    public void setTopBannerPrice(Float topBannerPrice) {
        this.topBannerPrice = topBannerPrice;
    }

    public Float getBottomBannerPrice() {
        return bottomBannerPrice;
    }

    public void setBottomBannerPrice(Float bottomBannerPrice) {
        this.bottomBannerPrice = bottomBannerPrice;
    }
}
