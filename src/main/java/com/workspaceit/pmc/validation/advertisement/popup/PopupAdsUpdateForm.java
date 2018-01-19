package com.workspaceit.pmc.validation.advertisement.popup;

import javax.validation.constraints.NotNull;

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