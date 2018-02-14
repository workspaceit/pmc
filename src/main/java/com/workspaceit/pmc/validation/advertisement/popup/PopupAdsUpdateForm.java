package com.workspaceit.pmc.validation.advertisement.popup;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class PopupAdsUpdateForm extends PopupAdsForm {
    Integer[] removeSmsBannerIds;
    Integer[] removeEmailBannerIds;

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

    public Integer[] getRemoveSmsBannerIds() {
        return removeSmsBannerIds;
    }

    public void setRemoveSmsBannerIds(Integer[] removeSmsBannerIds) {
        this.removeSmsBannerIds = removeSmsBannerIds;
    }

    public Integer[] getRemoveEmailBannerIds() {
        return removeEmailBannerIds;
    }

    public void setRemoveEmailBannerIds(Integer[] removeEmailBannerIds) {
        this.removeEmailBannerIds = removeEmailBannerIds;
    }


}