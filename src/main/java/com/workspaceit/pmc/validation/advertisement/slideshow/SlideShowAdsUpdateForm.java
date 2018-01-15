package com.workspaceit.pmc.validation.advertisement.slideshow;

import javax.validation.constraints.NotNull;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class SlideShowAdsUpdateForm extends SlideShowAdsForm {



    private Integer[] removeBannerIds;

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
}
