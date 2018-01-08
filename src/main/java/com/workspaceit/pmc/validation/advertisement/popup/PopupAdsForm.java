package com.workspaceit.pmc.validation.advertisement.popup;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class PopupAdsForm {


    private Integer advertiserId;

    @NotNull(message = "Sms Popup Banner is required")
    private Integer[] smsPopupBanner;

    @NotNull(message = "Sms Popup Video is required")
    private Integer smsPopupVideo;

    @NotNull(message = "Email Popup Banner is required")
    private Integer[] emailPopupBanner;

    @NotNull(message = "Email Popup Video is required")
    private Integer emailPopupVideo;

    @NotNull(message = "Email Popup Video duration is required")
    private Integer emailPopupVideoDuration;

    @NotNull(message = "Email Popup Video duration is required")
    private Integer smsPopupVideoDuration;


    @NotNull(message = "Expiry Date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date smsExpiryDate;

    @NotNull(message = "Expiry Date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date emailExpiryDate;

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
}
