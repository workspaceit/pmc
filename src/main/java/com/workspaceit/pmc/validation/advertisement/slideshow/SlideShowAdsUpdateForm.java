package com.workspaceit.pmc.validation.advertisement.slideshow;

import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceUpdateForm;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class SlideShowAdsUpdateForm extends SlideShowAdsForm {



    private Integer[] removeBannerIds;
    private Map<SECTION_TYPE,SectionResourceUpdateForm[]> updateSectionResources;

    @Override
    @NotNull(message = "Id required")
    public Integer getId() {
        return super.getId();
    }

    public Integer[] getRemoveBannerIds() {
        return removeBannerIds;
    }

    public void setRemoveBannerIds(Integer[] removeBannerIds) {
        this.removeBannerIds = removeBannerIds;
    }

    public Map<SECTION_TYPE, SectionResourceUpdateForm[]> getUpdateSectionResources() {
        return updateSectionResources;
    }

    public void setUpdateSectionResources(Map<SECTION_TYPE, SectionResourceUpdateForm[]> updateSectionResources) {
        this.updateSectionResources = updateSectionResources;
    }
}
