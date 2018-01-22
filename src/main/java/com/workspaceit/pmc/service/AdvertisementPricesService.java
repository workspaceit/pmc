package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant;
import com.workspaceit.pmc.dao.AdvertisementPricesDao;
import com.workspaceit.pmc.entity.AdvertisementPrices;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.advertisement.price.AdvertisementPricesForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomal on 1/10/2018.
 */
@Service
public class AdvertisementPricesService {

    private AdvertisementPricesDao advertisementPricesDao;
    private static String GALLERY_AD_PREFIX = "GALLERY_";
    private static String SLIDESHOW_AD_PREFIX = "SLIDESHOW_";
    private static String POPUP_AD_PREFIX = "POPUP_";
    @Autowired
    public void setAdvertisementPricesDao(AdvertisementPricesDao advertisementPricesDao) {
        this.advertisementPricesDao = advertisementPricesDao;
    }

    @Transactional public List<AdvertisementPrices> getAll(){
        return this.advertisementPricesDao.getAll();
    }

    public AdvertisementPrices getById(int id){
        return this.advertisementPricesDao.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(AdvertisementPrices advertisementPrices){
        this.advertisementPricesDao.insert(advertisementPrices);
    }



    @Transactional(rollbackFor = Exception.class)
    public String update(int id,AdvertisementPricesForm advertisementPricesForm)throws EntityNotFound {

        //advertisementPrices1.setPrice(advertisementPricesForm.getPrice());
        int[] ids = advertisementPricesForm.getIds();
        double[] prices = advertisementPricesForm.getPrice();

        for(int i=0;i<ids.length;i++){
            AdvertisementPrices advertisementPrices1 = this.getAdvertisementPrice(ids[i]);
            advertisementPrices1.setPrice(prices[i]);
            this.update(advertisementPrices1);
        }
        return "success";
    }
    public void update(AdvertisementPrices advertisementPrices){
        this.advertisementPricesDao.update(advertisementPrices);
    }


    public AdvertisementPrices getAdvertisementPrice(int id)throws EntityNotFound{
        AdvertisementPrices advertisementPrices = this.advertisementPricesDao.getById(id);
        if(advertisementPrices==null){
            throw new EntityNotFound("Location not found by id :"+id);
        }

        return advertisementPrices;
    }
    @Transactional
    public Map<GalleryAdsConstant,AdvertisementPrices> getGalleryAdPrice(){
        Map<GalleryAdsConstant,AdvertisementPrices> pricesMap = new HashMap<>();
        final String prefix = GALLERY_AD_PREFIX;
        List<AdvertisementPrices> galleryAdQuantityPrices = this.advertisementPricesDao.getByType(
                            prefix+GalleryAdsConstant.BACKGROUND_IMAGE.name(),
                            prefix+GalleryAdsConstant.TOP_AD_BANNER.name(),
                            prefix+GalleryAdsConstant.BOTTOM_AD_BANNER.name()
                    );

        for(AdvertisementPrices advertisementPrice :galleryAdQuantityPrices){
            String adType = advertisementPrice.getType();
            if (adType.equals(prefix+GalleryAdsConstant.BACKGROUND_IMAGE.name())){
                pricesMap.put(GalleryAdsConstant.BACKGROUND_IMAGE,advertisementPrice);
            }else if (adType.equals(prefix+GalleryAdsConstant.TOP_AD_BANNER.name())){
                pricesMap.put(GalleryAdsConstant.TOP_AD_BANNER,advertisementPrice);
            }else if (adType.equals(prefix+GalleryAdsConstant.BOTTOM_AD_BANNER.name())){
                pricesMap.put(GalleryAdsConstant.BOTTOM_AD_BANNER,advertisementPrice);

            }
        }
        return pricesMap;
    }
    @Transactional
    public Map<SlideshowAdsConstant,AdvertisementPrices> getSlideshowAdPrice(){
        Map<SlideshowAdsConstant,AdvertisementPrices> pricesMap = new HashMap<>();
        final String prefix = SLIDESHOW_AD_PREFIX;
        List<AdvertisementPrices> galleryAdQuantityPrices = this.advertisementPricesDao.getByType(
                prefix+SlideshowAdsConstant.BANNER.name(),
                prefix+SlideshowAdsConstant.VIDEO.name()
        );

        for(AdvertisementPrices advertisementPrice :galleryAdQuantityPrices){
            String adType = advertisementPrice.getType();
            if (adType.equals(prefix+SlideshowAdsConstant.BANNER.name())){
                pricesMap.put(SlideshowAdsConstant.BANNER,advertisementPrice);
            }else if (adType.equals(prefix+SlideshowAdsConstant.VIDEO.name())){
                pricesMap.put(SlideshowAdsConstant.VIDEO,advertisementPrice);
            }
        }
        return pricesMap;
    }

    @Transactional
    public Map<PopupAdConstant,AdvertisementPrices> getPopupAdPrice(){
        Map<PopupAdConstant,AdvertisementPrices> pricesMap = new HashMap<>();
        final String prefix = POPUP_AD_PREFIX;
        List<AdvertisementPrices> galleryAdQuantityPrices = this.advertisementPricesDao.getByType(
                prefix+ PopupAdConstant.SMS.name(),
                prefix+ PopupAdConstant.EMAIL.name()
        );

        for(AdvertisementPrices advertisementPrice :galleryAdQuantityPrices){
            String adType = advertisementPrice.getType();
            if (adType.equals(prefix+ PopupAdConstant.SMS.name())){
                pricesMap.put(PopupAdConstant.SMS,advertisementPrice);
            }else if (adType.equals(prefix+ PopupAdConstant.EMAIL.name())){
                pricesMap.put(PopupAdConstant.EMAIL,advertisementPrice);
            }
        }
        return pricesMap;
    }

}
