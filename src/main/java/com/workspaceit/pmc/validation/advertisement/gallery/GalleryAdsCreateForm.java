package com.workspaceit.pmc.validation.advertisement.gallery;

import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class GalleryAdsCreateForm extends GalleryAdsForm{


    @Override
    @NotNull(message = "Expire date required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getTopBannerExpiryDate() {
        return topBannerExpiryDate;
    }

    @Override
    @NotNull(message = "Expire date required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getBottomBannerExpiryDate() {
        return bottomBannerExpiryDate;
    }


    @Override
    @NotNull(message = "Rotation settings required")
    public AdvertiseRotationSettings getTopBannerRotation() {
        return super.getTopBannerRotation();
    }

    @Override
    @NotNull(message = "Rotation settings required")
    public AdvertiseRotationSettings getBottomBannerRotation() {
        return super.getBottomBannerRotation();
    }


    @Override
    @Min(value = 1,message = "Price can't be zero or negative")
    @NotNull(message = "Price is required")
    public Float getBgPrice() {
        return super.getBgPrice();
    }

    @Override
    @Min(value = 1,message = "Price can't be zero or negative")
    @NotNull(message = "Price is required")
    public Float getTopBannerPrice() {
        return super.getTopBannerPrice();
    }

    @Override
    @Min(value = 1,message = "Price can't be zero or negative")
    @NotNull(message = "Price is required")
    public Float getBottomBannerPrice() {
        return super.getBottomBannerPrice();
    }
}