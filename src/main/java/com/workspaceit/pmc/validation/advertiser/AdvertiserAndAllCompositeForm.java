package com.workspaceit.pmc.validation.advertiser;

import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsForm;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsForm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by mi_rafi on 1/8/18.
 */
public class AdvertiserAndAllCompositeForm {
    @NotNull
    @Valid
    AdvertiserForm advertiser;

    @NotNull
    @Valid
    GalleryAdsForm galleryAds;

    @NotNull
    @Valid
    PopupAdsForm popupAds;

    @NotNull
    @Valid
    SlideShowAdsForm slideShowAds;

    public AdvertiserForm getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(AdvertiserForm advertiser) {
        this.advertiser = advertiser;
    }

    public GalleryAdsForm getGalleryAds() {
        return galleryAds;
    }

    public void setGalleryAds(GalleryAdsForm galleryAds) {
        this.galleryAds = galleryAds;
    }

    public PopupAdsForm getPopupAds() {
        return popupAds;
    }

    public void setPopupAds(PopupAdsForm popupAds) {
        this.popupAds = popupAds;
    }

    public SlideShowAdsForm getSlideShowAds() {
        return slideShowAds;
    }

    public void setSlideShowAds(SlideShowAdsForm slideShowAds) {
        this.slideShowAds = slideShowAds;
    }
}
