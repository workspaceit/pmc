package com.workspaceit.pmc.validation.advertisement.gallery;


import javax.validation.constraints.NotNull;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class GalleryAdsUpdateForm extends GalleryAdsForm{

    Integer[] removeTopBannerIds;
    Integer[] removeBottomBannerIds;

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
}
