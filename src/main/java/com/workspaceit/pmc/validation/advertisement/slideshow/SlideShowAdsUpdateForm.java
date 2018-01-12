package com.workspaceit.pmc.validation.advertisement.slideshow;

import javax.validation.constraints.NotNull;

/**
 * Created by mi_rafi on 1/12/18.
 */
public class SlideShowAdsUpdateForm extends SlideShowAdsForm {

    @Override
    @NotNull(message = "Id required")
    public Integer getId() {
        return super.getId();
    }
}
