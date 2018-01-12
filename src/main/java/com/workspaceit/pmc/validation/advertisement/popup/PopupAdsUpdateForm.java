package com.workspaceit.pmc.validation.advertisement.popup;

import javax.validation.constraints.NotNull;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class PopupAdsUpdateForm extends PopupAdsForm {
    @Override
    @NotNull(message = "Email Id required")
    public Integer getSmsId() {
        return super.getSmsId();
    }

    @Override
    @NotNull(message = "Sms Id required")
    public Integer getEmailId() {
        return super.getEmailId();
    }
}