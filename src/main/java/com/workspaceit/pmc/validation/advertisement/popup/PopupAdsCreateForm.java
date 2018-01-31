package com.workspaceit.pmc.validation.advertisement.popup;

import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;
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

    public Integer[] getSmsPopupBanner() {
        return super.getSmsPopupBanner();
    }

    @Override
    public Integer getSmsPopupVideo() {
        return super.getSmsPopupVideo();
    }

    @Override
    public Integer[] getEmailPopupBanner() {
        return super.getEmailPopupBanner();
    }

    public Integer getEmailPopupVideo() {
        return super.getEmailPopupVideo();
    }

    @Override
    @NotNull(message = "Email Popup duration is required")
    public Integer getEmailPopupVideoDuration() {
        return super.getEmailPopupVideoDuration();
    }

    @Override
    @NotNull(message = "Sms Popup duration is required")
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


    @Override
    @NotNull(message = "Rotation settings required")
    public AdvertiseRotationSettings getSmsRotation() {
        return super.getSmsRotation();
    }

    @Override
    @NotNull(message = "Rotation settings required")
    public AdvertiseRotationSettings getEmailRotation() {
        return super.getEmailRotation();
    }
}
