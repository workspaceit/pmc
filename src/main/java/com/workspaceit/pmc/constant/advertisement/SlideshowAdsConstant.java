package com.workspaceit.pmc.constant.advertisement;

public enum SlideshowAdsConstant implements AdvertisementDisplay {
    BANNER("Slide show Ad Banner"),VIDEO("Slide show Ad Video");
    private String displayText;

    SlideshowAdsConstant(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}