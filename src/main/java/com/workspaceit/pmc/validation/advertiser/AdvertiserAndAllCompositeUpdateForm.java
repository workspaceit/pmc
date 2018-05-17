package com.workspaceit.pmc.validation.advertiser;

import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceUpdateForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsUpdateForm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by mi_rafi on 1/8/18.
 */
public class AdvertiserAndAllCompositeUpdateForm {
    @NotNull
    @Valid
    private AdvertiserUpdateForm advertiser;

    @NotNull
    @Valid
    private GalleryAdsUpdateForm galleryAds;

    @NotNull
    @Valid
    private PopupAdsUpdateForm popupAds;

    @NotNull
    @Valid
    private SlideShowAdsUpdateForm slideShowAds;

    private SectionResourceUpdateForm[] urlsUpdate;


    public AdvertiserUpdateForm getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(AdvertiserUpdateForm advertiser) {
        this.advertiser = advertiser;
    }

    public GalleryAdsUpdateForm getGalleryAds() {
        return galleryAds;
    }

    public void setGalleryAds(GalleryAdsUpdateForm galleryAds) {
        this.galleryAds = galleryAds;
    }

    public PopupAdsUpdateForm getPopupAds() {
        return popupAds;
    }

    public void setPopupAds(PopupAdsUpdateForm popupAds) {
        this.popupAds = popupAds;
    }

    public SlideShowAdsUpdateForm getSlideShowAds() {
        return slideShowAds;
    }

    public void setSlideShowAds(SlideShowAdsUpdateForm slideShowAds) {
        this.slideShowAds = slideShowAds;
    }

    public SectionResourceUpdateForm[] getUrlsUpdate() {
        return urlsUpdate;
    }

    public void setUrlsUpdate(SectionResourceUpdateForm[] urlsUpdate) {
        this.urlsUpdate = urlsUpdate;
    }
}
