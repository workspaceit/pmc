package com.workspaceit.pmc.validation.advertisement.popup;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
import com.workspaceit.pmc.entity.advertisement.SectionResource;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class PopupAdsForm {
    private Integer smsId;
    private Integer emailId;
    private Integer advertiserId;

    @Valid
    @NotNull
    private SectionResourceForm[] smsPopupBannerResources;

    @Valid
    @NotNull
    private SectionResourceForm smsPopupVideoResource;

    @Valid
    @NotNull
    private SectionResourceForm[] emailPopupBannerResources;

    @Valid
    @NotNull
    private SectionResourceForm emailPopupVideoResource;

    private Integer emailPopupVideoDuration;
    private Integer smsPopupVideoDuration;
    private Date smsExpiryDate;
    private Date emailExpiryDate;

    private ADVERTISEMENT_ROTATION_SETTINGS smsRotation;
    private ADVERTISEMENT_ROTATION_SETTINGS emailRotation;

    private Float smsAdPrice;
    private Float emailAdPrice;


    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    public Integer getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Integer advertiserId) {
        this.advertiserId = advertiserId;
    }

    public SectionResourceForm[] getSmsPopupBannerResources() {
        return smsPopupBannerResources;
    }

    public void setSmsPopupBannerResources(SectionResourceForm[] smsPopupBannerResources) {
        this.smsPopupBannerResources = smsPopupBannerResources;
    }

    public SectionResourceForm getSmsPopupVideoResource() {
        return smsPopupVideoResource;
    }

    public void setSmsPopupVideoResource(SectionResourceForm smsPopupVideoResource) {
        this.smsPopupVideoResource = smsPopupVideoResource;
    }

    public SectionResourceForm[] getEmailPopupBannerResources() {
        return emailPopupBannerResources;
    }

    public void setEmailPopupBannerResources(SectionResourceForm[] emailPopupBannerResources) {
        this.emailPopupBannerResources = emailPopupBannerResources;
    }

    public SectionResourceForm getEmailPopupVideoResource() {
        return emailPopupVideoResource;
    }

    public void setEmailPopupVideoResource(SectionResourceForm emailPopupVideoResource) {
        this.emailPopupVideoResource = emailPopupVideoResource;
    }

    public Integer getEmailPopupVideoDuration() {
        return emailPopupVideoDuration;
    }

    public void setEmailPopupVideoDuration(Integer emailPopupVideoDuration) {
        this.emailPopupVideoDuration = emailPopupVideoDuration;
    }

    public Integer getSmsPopupVideoDuration() {
        return smsPopupVideoDuration;
    }

    public void setSmsPopupVideoDuration(Integer smsPopupVideoDuration) {
        this.smsPopupVideoDuration = smsPopupVideoDuration;
    }

    public Date getSmsExpiryDate() {
        return smsExpiryDate;
    }

    public void setSmsExpiryDate(Date smsExpiryDate) {
        this.smsExpiryDate = smsExpiryDate;
    }

    public Date getEmailExpiryDate() {
        return emailExpiryDate;
    }

    public void setEmailExpiryDate(Date emailExpiryDate) {
        this.emailExpiryDate = emailExpiryDate;
    }

    public ADVERTISEMENT_ROTATION_SETTINGS getSmsRotation() {
        return smsRotation;
    }

    public void setSmsRotation(ADVERTISEMENT_ROTATION_SETTINGS smsRotation) {
        this.smsRotation = smsRotation;
    }

    public ADVERTISEMENT_ROTATION_SETTINGS getEmailRotation() {
        return emailRotation;
    }

    public void setEmailRotation(ADVERTISEMENT_ROTATION_SETTINGS emailRotation) {
        this.emailRotation = emailRotation;
    }

    public Float getSmsAdPrice() {
        return smsAdPrice;
    }

    public void setSmsAdPrice(Float smsAdPrice) {
        this.smsAdPrice = smsAdPrice;
    }

    public Float getEmailAdPrice() {
        return emailAdPrice;
    }

    public void setEmailAdPrice(Float emailAdPrice) {
        this.emailAdPrice = emailAdPrice;
    }


}