package com.workspaceit.pmc.validation.advertisement.popup;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class PopupAdsCreateForm extends PopupAdsForm {


    @Override
    public Integer getAdvertiserId() {
        return super.getAdvertiserId();
    }

    @Override
    @NotNull(message = "Sms Popup Banner is required")
    public Integer[] getSmsPopupBanner() {
        return super.getSmsPopupBanner();
    }

    @Override
    @NotNull(message = "Sms Popup Video is required")
    public Integer getSmsPopupVideo() {
        return super.getSmsPopupVideo();
    }

    @Override
    @NotNull(message = "Email Popup Video is required")
    public Integer[] getEmailPopupBanner() {
        return super.getEmailPopupBanner();
    }

    @Override
    @NotNull(message = "Email Popup Video duration is required")
    public Integer getEmailPopupVideo() {
        return super.getEmailPopupVideo();
    }

    @Override
    @NotNull(message = "Email Popup Video duration is required")
    public Integer getEmailPopupVideoDuration() {
        return super.getEmailPopupVideoDuration();
    }

    @Override
    @NotNull(message = "Sms Popup Video duration is required")
    public Integer getSmsPopupVideoDuration() {
        return super.getSmsPopupVideoDuration();
    }

    @Override
    @NotNull(message = "Expiry Date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getSmsExpiryDate() {
        return super.getSmsExpiryDate();
    }

    @Override
    @NotNull(message = "Expiry Date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getEmailExpiryDate() {
        return super.getEmailExpiryDate();
    }
}
