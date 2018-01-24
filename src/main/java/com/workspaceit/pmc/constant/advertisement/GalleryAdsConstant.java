package com.workspaceit.pmc.constant.advertisement;

public enum  GalleryAdsConstant implements AdvertisementDisplay {
    BACKGROUND_IMAGE("Gallery Background Image"),TOP_AD_BANNER("Gallery Top Ad Banner"),BOTTOM_AD_BANNER("Gallery Bottom Ad Banner");
    private String displayText;

    GalleryAdsConstant(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}