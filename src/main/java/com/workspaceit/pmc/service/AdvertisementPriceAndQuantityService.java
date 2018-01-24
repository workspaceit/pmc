package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAdQuantityPrice;
import com.workspaceit.pmc.entity.advertisement.popup.PopupAd;
import com.workspaceit.pmc.entity.advertisement.popup.PopupAdQuantityPrice;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowAd;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowQuantityPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdvertisementPriceAndQuantityService {
    public final static String priceMapKey = "prices";
    public final static String quantityMapKey = "quantities";
    private GalleryAdService galleryAdService;
    private SlideShowService slideShowService;
    private PopUpAdsService  popUpAdsService;

    @Autowired
    public void setGalleryAdService(GalleryAdService galleryAdService) {
        this.galleryAdService = galleryAdService;
    }
    @Autowired
    public void setSlideShowService(SlideShowService slideShowService) {
        this.slideShowService = slideShowService;
    }
    @Autowired
    public void setPopUpAdsService(PopUpAdsService popUpAdsService) {
        this.popUpAdsService = popUpAdsService;
    }

    @Transactional
    public Map<String,Object> getSoldPriceAndQuantity(int advertiserId){
        Map<String,Object> priceAndQuantity = new HashMap<>();

        GalleryAd galleryAd = this.galleryAdService.getByAdvertiserId(advertiserId);
        SlideshowAd slideshowAd = this.slideShowService.getByAdvertiserId(advertiserId);
        PopupAd popupAdSms  = this.popUpAdsService.getByAdvertiserId(advertiserId, PopupAdConstant.SMS);
        PopupAd popupAdEmail  = this.popUpAdsService.getByAdvertiserId(advertiserId, PopupAdConstant.EMAIL);

        /* Price and quantity */

        Map<GalleryAdsConstant, GalleryAdQuantityPrice>  galleryQuantityPrice = galleryAd.getGalleryQuantityPrice();
        Map<SlideshowAdsConstant,SlideshowQuantityPrice> slideshowQuantityPrice = slideshowAd.getQuantityPrice();
        Map<PopupAdConstant,PopupAdQuantityPrice> popupAdSmsQuantityPrice = popupAdSms.getQuantityPrice();
        Map<PopupAdConstant,PopupAdQuantityPrice> popupAdEmailQuantityPrice = popupAdEmail.getQuantityPrice();

        Map<Object,Float> prices = new HashMap<>();
        Map<Object,Integer> quantities = new HashMap<>();

        prices.put(GalleryAdsConstant.BACKGROUND_IMAGE,0f);
        prices.put(GalleryAdsConstant.TOP_AD_BANNER,0f);
        prices.put(GalleryAdsConstant.BOTTOM_AD_BANNER,0f);

        prices.put(SlideshowAdsConstant.BANNER,0f);
        prices.put(SlideshowAdsConstant.VIDEO,0f);

        prices.put(PopupAdConstant.SMS,0f);
        prices.put(PopupAdConstant.EMAIL,0f);


        quantities.put(GalleryAdsConstant.BACKGROUND_IMAGE,0);
        quantities.put(GalleryAdsConstant.TOP_AD_BANNER,0);
        quantities.put(GalleryAdsConstant.BOTTOM_AD_BANNER,0);

        quantities.put(SlideshowAdsConstant.BANNER,0);
        quantities.put(SlideshowAdsConstant.VIDEO,0);

        quantities.put(PopupAdConstant.SMS,0);
        quantities.put(PopupAdConstant.EMAIL,0);



        if(galleryQuantityPrice!=null){
            GalleryAdQuantityPrice bgQP = galleryQuantityPrice.get(GalleryAdsConstant.BACKGROUND_IMAGE);
            GalleryAdQuantityPrice topQP = galleryQuantityPrice.get(GalleryAdsConstant.TOP_AD_BANNER);
            GalleryAdQuantityPrice bottomQP = galleryQuantityPrice.get(GalleryAdsConstant.BOTTOM_AD_BANNER);

            prices.put(GalleryAdsConstant.BACKGROUND_IMAGE,(bgQP != null)?bgQP.getPrice():0);
            prices.put(GalleryAdsConstant.TOP_AD_BANNER,(topQP != null)?topQP.getPrice():0);
            prices.put(GalleryAdsConstant.BOTTOM_AD_BANNER,(bottomQP != null)?bottomQP.getPrice():0);

            quantities.put(GalleryAdsConstant.BACKGROUND_IMAGE,(bgQP != null)?bgQP.getQuantity():0);
            quantities.put(GalleryAdsConstant.TOP_AD_BANNER,(topQP != null)?topQP.getQuantity():0);
            quantities.put(GalleryAdsConstant.BOTTOM_AD_BANNER,(bottomQP != null)?bottomQP.getQuantity():0);
        }


        if(slideshowQuantityPrice!=null){
            SlideshowQuantityPrice bannerQP = slideshowQuantityPrice.get(SlideshowAdsConstant.BANNER);
            SlideshowQuantityPrice videoQP = slideshowQuantityPrice.get(SlideshowAdsConstant.VIDEO);


            prices.put(SlideshowAdsConstant.BANNER,(bannerQP != null)?bannerQP.getPrice():0);
            prices.put(SlideshowAdsConstant.VIDEO,(videoQP != null)?videoQP.getPrice():0);

            quantities.put(SlideshowAdsConstant.BANNER,(bannerQP != null)?bannerQP.getQuantity():0);
            quantities.put(SlideshowAdsConstant.VIDEO,(videoQP != null)?videoQP.getQuantity():0);
        }
        if(popupAdSmsQuantityPrice!=null){
            PopupAdQuantityPrice smsQP = popupAdSmsQuantityPrice.get(PopupAdConstant.SMS);
            prices.put(PopupAdConstant.SMS,(smsQP != null)?smsQP.getPrice():0);
            quantities.put(PopupAdConstant.SMS,(smsQP != null)?smsQP.getQuantity():0);
        }
        if(popupAdEmailQuantityPrice!=null){
            PopupAdQuantityPrice emailQP =  popupAdEmailQuantityPrice.get(PopupAdConstant.EMAIL);
            prices.put(PopupAdConstant.EMAIL,(emailQP != null)?emailQP.getPrice():0);
            quantities.put(PopupAdConstant.EMAIL,(emailQP != null)?emailQP.getQuantity():0);
        }



        priceAndQuantity.put(priceMapKey,prices);
        priceAndQuantity.put(quantityMapKey,quantities);

        return priceAndQuantity;

    }
    public Float calculateTotal(Map<Object,Float> prices, Map<Object,Integer> quantities){
        float totalPrice = 0;


        /* Calculating total */
        for(GalleryAdsConstant key :GalleryAdsConstant.values()){
            totalPrice+=prices.get(key)*quantities.get(key);
        }
        for(SlideshowAdsConstant key :SlideshowAdsConstant.values()){
            totalPrice+=prices.get(key)*quantities.get(key);
        }
        for(PopupAdConstant key :PopupAdConstant.values()){
            totalPrice+=prices.get(key)*quantities.get(key);
        }
        return totalPrice;
    }
    public Float getTotal(int advertiserId){

        Map<String,Object> priceAndQuantity = this.getSoldPriceAndQuantity(advertiserId);
        Map<Object,Float> prices = (Map<Object,Float>)priceAndQuantity.get(priceMapKey);
        Map<Object,Integer> quantities = (Map<Object,Integer>)priceAndQuantity.get(quantityMapKey);
        float totalPrice = this.calculateTotal(prices,quantities);
        return totalPrice;
    }
}