package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant;
import com.workspaceit.pmc.dao.SlideshowQuantityPriceDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowQuantityPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class SlideshowQuantityPriceService {
    private SlideshowQuantityPriceDao slideshowQuantityPriceDao;

    @Autowired
    public void setSlideshowQuantityPriceDao(SlideshowQuantityPriceDao slideshowQuantityPriceDao) {
        this.slideshowQuantityPriceDao = slideshowQuantityPriceDao;
    }
    @Transactional(rollbackFor = Exception.class)
    public SlideshowQuantityPrice create(int slideshowAdId,
                                         float price,
                                         int quantity,
                                         SlideshowAdsConstant slideshowAdsConstant,
                                         Admin admin){

        SlideshowQuantityPrice slideshowQuantityPrice = new SlideshowQuantityPrice();
        slideshowQuantityPrice.setSlideshowAdId(slideshowAdId);
        slideshowQuantityPrice.setPrice(price);
        slideshowQuantityPrice.setQuantity(quantity);
        slideshowQuantityPrice.setAdType(slideshowAdsConstant);
        slideshowQuantityPrice.setCreatedBy(admin);

        this.create(slideshowQuantityPrice);

        return slideshowQuantityPrice;
    }

    @Transactional(rollbackFor = Exception.class)
    public SlideshowQuantityPrice create(SlideshowQuantityPrice slideshowQuantityPrice){
        this.slideshowQuantityPriceDao.insert(slideshowQuantityPrice);
        return slideshowQuantityPrice;
    }
}