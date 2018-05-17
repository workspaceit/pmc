package com.workspaceit.pmc.validation.advertisement.gallery;


import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceUpdateForm;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class GalleryAdsUpdateForm extends GalleryAdsForm{

    private Integer[] removeTopBannerIds;
    private Integer[] removeBottomBannerIds;

    private Map<SECTION_TYPE,SectionResourceUpdateForm[]> updateSectionResources;


    @NotNull(message = "Gallery Id required")
    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }


    public Integer[] getRemoveTopBannerIds() {
        return removeTopBannerIds;
    }

    public void setRemoveTopBannerIds(Integer[] removeTopBannerIds) {
        this.removeTopBannerIds = removeTopBannerIds;
    }

    public Integer[] getRemoveBottomBannerIds() {
        return removeBottomBannerIds;
    }

    public void setRemoveBottomBannerIds(Integer[] removeBottomBannerIds) {
        this.removeBottomBannerIds = removeBottomBannerIds;
    }

    public Map<SECTION_TYPE, SectionResourceUpdateForm[]> getUpdateSectionResources() {
        return updateSectionResources;
    }

    public void setUpdateSectionResources(Map<SECTION_TYPE, SectionResourceUpdateForm[]> updateSectionResources) {
        this.updateSectionResources = updateSectionResources;
    }

    @Override
    public String toString() {
        return "GalleryAdsUpdateForm{" +
                "removeTopBannerIds=" + Arrays.toString(removeTopBannerIds) +
                ", removeBottomBannerIds=" + Arrays.toString(removeBottomBannerIds) +
                ", updateSectionResources=" + updateSectionResources +
                '}';
    }
}
