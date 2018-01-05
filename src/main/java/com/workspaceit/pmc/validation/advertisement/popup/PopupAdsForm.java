package com.workspaceit.pmc.validation.advertisement.popup;

import javax.validation.constraints.NotNull;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class PopupAdsForm {


    private Integer advertiserId;

    @NotNull(message = "Sms Popup Banner is required")
    private Integer[] smsPopupBanner;

    @NotNull(message = "Sms Popup Video is required")
    private Integer[] smsPopupVideo;

    @NotNull(message = "Email Popup Banner is required")
    private Integer[] emailPopupBanner;

    @NotNull(message = "Email Popup Video is required")
    private Integer[] emailPopupVideo;

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

    public Integer[] getSmsPopupVideo() {
        return smsPopupVideo;
    }

    public void setSmsPopupVideo(Integer[] smsPopupVideo) {
        this.smsPopupVideo = smsPopupVideo;
    }

    public Integer[] getEmailPopupBanner() {
        return emailPopupBanner;
    }

    public void setEmailPopupBanner(Integer[] emailPopupBanner) {
        this.emailPopupBanner = emailPopupBanner;
    }

    public Integer[] getEmailPopupVideo() {
        return emailPopupVideo;
    }

    public void setEmailPopupVideo(Integer[] emailPopupVideo) {
        this.emailPopupVideo = emailPopupVideo;
    }
}
