package com.workspaceit.pmc.validation.advertisement.popup;

import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class PopupAdsForm {
    private Integer smsId;
    private Integer emailId;
    private Integer advertiserId;
    private Integer[] smsPopupBanner;
    private Integer smsPopupVideo;
    private Integer[] emailPopupBanner;
    private Integer emailPopupVideo;
    private Integer emailPopupVideoDuration;
    private Integer smsPopupVideoDuration;
    private Date smsExpiryDate;
    private Date emailExpiryDate;

    private AdvertiseRotationSettings smsRotation;
    private AdvertiseRotationSettings emailRotation;

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

    public Integer[] getSmsPopupBanner() {
        return smsPopupBanner;
    }

    public void setSmsPopupBanner(Integer[] smsPopupBanner) {
        this.smsPopupBanner = smsPopupBanner;
    }

    public Integer getSmsPopupVideo() {
        return smsPopupVideo;
    }

    public void setSmsPopupVideo(Integer smsPopupVideo) {
        this.smsPopupVideo = smsPopupVideo;
    }

    public Integer[] getEmailPopupBanner() {
        return emailPopupBanner;
    }

    public void setEmailPopupBanner(Integer[] emailPopupBanner) {
        this.emailPopupBanner = emailPopupBanner;
    }

    public Integer getEmailPopupVideo() {
        return emailPopupVideo;
    }

    public void setEmailPopupVideo(Integer emailPopupVideo) {
        this.emailPopupVideo = emailPopupVideo;
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

    public AdvertiseRotationSettings getSmsRotation() {
        return smsRotation;
    }

    public void setSmsRotation(AdvertiseRotationSettings smsRotation) {
        this.smsRotation = smsRotation;
    }

    public AdvertiseRotationSettings getEmailRotation() {
        return emailRotation;
    }

    public void setEmailRotation(AdvertiseRotationSettings emailRotation) {
        this.emailRotation = emailRotation;
    }

    @Override
    public String toString() {
        return "PopupAdsForm{" +
                "smsId=" + smsId +
                ", emailId=" + emailId +
                ", advertiserId=" + advertiserId +
                ", smsPopupBanner=" + Arrays.toString(smsPopupBanner) +
                ", smsPopupVideo=" + smsPopupVideo +
                ", emailPopupBanner=" + Arrays.toString(emailPopupBanner) +
                ", emailPopupVideo=" + emailPopupVideo +
                ", emailPopupVideoDuration=" + emailPopupVideoDuration +
                ", smsPopupVideoDuration=" + smsPopupVideoDuration +
                ", smsExpiryDate=" + smsExpiryDate +
                ", emailExpiryDate=" + emailExpiryDate +
                ", smsRotation=" + smsRotation +
                ", emailRotation=" + emailRotation +
                '}';
    }
}