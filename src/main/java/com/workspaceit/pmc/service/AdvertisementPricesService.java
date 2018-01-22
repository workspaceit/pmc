package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.dao.AdvertisementPricesDao;
import com.workspaceit.pmc.entity.AdvertisementPrices;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAdQuantityPrice;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.advertisement.price.AdvertisementPricesForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomal on 1/10/2018.
 */
@Service
public class AdvertisementPricesService {

    private AdvertisementPricesDao advertisementPricesDao;

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
    public Map<GalleryAdsConstant,AdvertisementPrices> getGalleryAddPrice(){
        Map<GalleryAdsConstant,AdvertisementPrices> pricesMap = new HashMap<>();
        final String prefix = "GALLERY_";
        List<AdvertisementPrices> galleryAdQuantityPrices = this.advertisementPricesDao.getByType(
                            prefix+GalleryAdsConstant.BACKGROUND_IMAGE.name(),
                            prefix+GalleryAdsConstant.TOP_AD_BANNER.name(),
                            prefix+GalleryAdsConstant.BOTTOM_AD_BANNER.name()
                    );

        for(AdvertisementPrices advertisementPrice :galleryAdQuantityPrices){
            String adType = advertisementPrice.getType();
            System.out.println(adType.length()+" "+(prefix+GalleryAdsConstant.BOTTOM_AD_BANNER.name()).length());
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


}
