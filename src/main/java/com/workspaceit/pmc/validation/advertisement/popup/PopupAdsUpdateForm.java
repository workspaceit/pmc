package com.workspaceit.pmc.validation.advertisement.popup;

import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceUpdateForm;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class PopupAdsUpdateForm extends PopupAdsForm {
    Integer[] removeSmsBannerIds;
    Integer[] removeEmailBannerIds;
    private Map<SECTION_TYPE,SectionResourceUpdateForm[]> updateSmsSectionResources;
    private Map<SECTION_TYPE,SectionResourceUpdateForm[]> updateEmailSectionResources;
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

    public Map<SECTION_TYPE, SectionResourceUpdateForm[]> getUpdateSmsSectionResources() {
        return updateSmsSectionResources;
    }

    public void setUpdateSmsSectionResources(Map<SECTION_TYPE, SectionResourceUpdateForm[]> updateSmsSectionResources) {
        this.updateSmsSectionResources = updateSmsSectionResources;
    }

    public Map<SECTION_TYPE, SectionResourceUpdateForm[]> getUpdateEmailSectionResources() {
        return updateEmailSectionResources;
    }

    public void setUpdateEmailSectionResources(Map<SECTION_TYPE, SectionResourceUpdateForm[]> updateEmailSectionResources) {
        this.updateEmailSectionResources = updateEmailSectionResources;
    }
}