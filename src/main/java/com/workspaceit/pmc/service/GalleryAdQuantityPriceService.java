package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.dao.GalleryAdQuantityPriceDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAdQuantityPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class GalleryAdQuantityPriceService {
    private GalleryAdQuantityPriceDao galleryAdQuantityPriceDao;

    @Autowired
    public void setGalleryAdQuantityPriceDao(GalleryAdQuantityPriceDao galleryAdQuantityPriceDao) {
        this.galleryAdQuantityPriceDao = galleryAdQuantityPriceDao;
    }
    @Transactional(rollbackFor = Exception.class)
    public GalleryAdQuantityPrice create(int galleryId,
                                         float price,
                                         int quantity,
                                         GalleryAdsConstant galleryAdsConstant,
                                         Admin admin){
        GalleryAdQuantityPrice galleryAdQuantityPrice = new GalleryAdQuantityPrice();

        galleryAdQuantityPrice.setGalleryAdId(galleryId);
        galleryAdQuantityPrice.setPrice(price);
        galleryAdQuantityPrice.setQuantity(quantity);
        galleryAdQuantityPrice.setAdType(galleryAdsConstant);
        galleryAdQuantityPrice.setCreatedBy(admin);

        this.create(galleryAdQuantityPrice);
        return galleryAdQuantityPrice;
    }
    @Transactional(rollbackFor = Exception.class)
    public GalleryAdQuantityPrice create(GalleryAdQuantityPrice galleryAdQuantityPrice){
        this.galleryAdQuantityPriceDao.insert(galleryAdQuantityPrice);
        return galleryAdQuantityPrice;
    }
}