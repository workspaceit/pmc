package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdvertiserTransactionDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.AdvertiserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdvertiserTransactionService {
    private AdvertiserTransactionDao advertiserTransactionDao;
    private AdvertisementPriceAndQuantityService advertisementPriceAndQuantityService;
    @Autowired
    public void setAdvertiserTransactionDao(AdvertiserTransactionDao advertiserTransactionDao) {
        this.advertiserTransactionDao = advertiserTransactionDao;
    }
    @Autowired
    public void setAdvertisementPriceAndQuantityService(AdvertisementPriceAndQuantityService advertisementPriceAndQuantityService) {
        this.advertisementPriceAndQuantityService = advertisementPriceAndQuantityService;
    }

    public AdvertiserTransaction create(AdvertiserTransaction advertiserTransaction){
        this.advertiserTransactionDao.insert(advertiserTransaction);
        return advertiserTransaction;
    }
    public AdvertiserTransaction create(Integer advertiserId, float subtotal, float total, float discount, Admin admin){
        AdvertiserTransaction advertiserTransaction = new AdvertiserTransaction();

        advertiserTransaction.setAdvertiserId(advertiserId);
        advertiserTransaction.setSubtotal(subtotal);
        advertiserTransaction.setTotal(total);
        advertiserTransaction.setDiscount(discount);
        advertiserTransaction.setCreatedBy(admin);

        this.create(advertiserTransaction);

        return advertiserTransaction;
    }

    public AdvertiserTransaction create(Integer advertiserId,float discount, Admin admin){
        float total = this.advertisementPriceAndQuantityService.getTotal(advertiserId);
        float subtotal = total - discount;
        return this.create(advertiserId,subtotal,total,discount,admin);
    }
}