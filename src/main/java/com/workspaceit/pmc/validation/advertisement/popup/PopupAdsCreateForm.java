package com.workspaceit.pmc.validation.advertisement.popup;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class PopupAdsCreateForm extends PopupAdsForm {


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
    public Date getSmsExpiryDate() {
        return super.getSmsExpiryDate();
    }

    @Override
    @NotNull(message = "Expiry Date is required")
    public Date getEmailExpiryDate() {
        return super.getEmailExpiryDate();
    }


    @Override
    @NotNull(message = "Rotation settings required")
    public ADVERTISEMENT_ROTATION_SETTINGS getSmsRotation() {
        return super.getSmsRotation();
    }

    @Override
    @NotNull(message = "Rotation settings required")
    public ADVERTISEMENT_ROTATION_SETTINGS getEmailRotation() {
        return super.getEmailRotation();
    }
}
