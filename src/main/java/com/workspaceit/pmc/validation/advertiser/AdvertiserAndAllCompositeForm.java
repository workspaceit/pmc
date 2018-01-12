package com.workspaceit.pmc.validation.advertiser;

import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsCreateForm;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsCreateForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsCreateForm;

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
    GalleryAdsCreateForm galleryAds;

    @NotNull
    @Valid
    PopupAdsCreateForm popupAds;

    @NotNull
    @Valid
    SlideShowAdsCreateForm slideShowAds;

    public AdvertiserForm getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(AdvertiserForm advertiser) {
        this.advertiser = advertiser;
    }

    public GalleryAdsCreateForm getGalleryAds() {
        return galleryAds;
    }

    public void setGalleryAds(GalleryAdsCreateForm galleryAds) {
        this.galleryAds = galleryAds;
    }

    public PopupAdsCreateForm getPopupAds() {
        return popupAds;
    }

    public void setPopupAds(PopupAdsCreateForm popupAds) {
        this.popupAds = popupAds;
    }

    public SlideShowAdsCreateForm getSlideShowAds() {
        return slideShowAds;
    }

    public void setSlideShowAds(SlideShowAdsCreateForm slideShowAds) {
        this.slideShowAds = slideShowAds;
    }
}
