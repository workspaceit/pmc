package com.workspaceit.pmc.validation.advertisement.gallery;

import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
public class GalleryAdsCreateForm extends GalleryAdsForm{


    @Override
    public Integer getAdvertiserId() {
        return advertiserId;
    }

    @Override
    @NotNull(message = "Logo is required")
    public Integer getLogoToken() {
        return logoToken;
    }

    @Override
    @NotNull(message = "Background Image required")
    public Integer getBgImgTokens() {
        return bgImgTokens;
    }

    @Override
    @NotNull(message = "Top Banner Image required")
    public Integer[] getTopBannerImgTokens() {
        return topBannerImgTokens;
    }

    @Override
    @NotNull(message = "Bottom Banner Image required")
    public Integer[] getBottomBannerImgTokens() {
        return bottomBannerImgTokens;
    }

    @Override
    @NotNull(message = "Top Banner Expiry Date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getTopBannerExpiryDate() {
        return topBannerExpiryDate;
    }

    @Override
    @NotNull(message = "Bottom Banner Expiry Date is required")
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
}
