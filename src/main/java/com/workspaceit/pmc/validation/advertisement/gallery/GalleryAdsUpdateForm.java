package com.workspaceit.pmc.validation.advertisement.gallery;


import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class GalleryAdsUpdateForm extends GalleryAdsForm{

    @NotNull(message = "Gallery Id required")
    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }
}
