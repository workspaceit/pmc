package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.AdvertisementDisplay;
import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant;
import com.workspaceit.pmc.dao.AdvertiserTransactionDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.AdvertiserTransaction;
import com.workspaceit.pmc.entity.AdvertiserTransactionDetails;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.checkout.CheckoutCreateForm;
import com.workspaceit.pmc.validation.checkout.CheckoutForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class AdvertiserTransactionService {
    private AdvertiserTransactionDao advertiserTransactionDao;
    private AdvertisementPriceAndQuantityService advertisementPriceAndQuantityService;
    private GalleryAdService galleryAdService;
    @Autowired
    public void setAdvertiserTransactionDao(AdvertiserTransactionDao advertiserTransactionDao) {
        this.advertiserTransactionDao = advertiserTransactionDao;
    }
    @Autowired
    public void setAdvertisementPriceAndQuantityService(AdvertisementPriceAndQuantityService advertisementPriceAndQuantityService) {
        this.advertisementPriceAndQuantityService = advertisementPriceAndQuantityService;
    }
    @Autowired
    public void setGalleryAdService(GalleryAdService galleryAdService) {
        this.galleryAdService = galleryAdService;
    }

    @Transactional(rollbackFor = Exception.class)
    public AdvertiserTransaction create(AdvertiserTransaction advertiserTransaction){

        this.advertiserTransactionDao.insert(advertiserTransaction);
        return advertiserTransaction;
    }
    @Transactional(rollbackFor = Exception.class)
    public AdvertiserTransaction update(AdvertiserTransaction advertiserTransaction){

        this.advertiserTransactionDao.update(advertiserTransaction);
        return advertiserTransaction;
    }
    @Transactional(rollbackFor = Exception.class)
    public AdvertiserTransaction create(Integer advertiserId, float subtotal, float total, float discount, Admin admin){

        AdvertiserTransaction advertiserTransaction = new AdvertiserTransaction();

        advertiserTransaction.setAdvertiserId(advertiserId);
        advertiserTransaction.setSubtotal(subtotal);
        advertiserTransaction.setTotal(total);
        advertiserTransaction.setDiscount(discount);
        advertiserTransaction.setCreatedBy(admin);


        return this.create(advertiserTransaction);
    }

    @Transactional(rollbackFor = Exception.class)
    public AdvertiserTransaction create(Integer advertiserId, CheckoutCreateForm checkoutCreateForm, Admin admin){
        Map<String,Object>   priceAndQuantity = this.advertisementPriceAndQuantityService.getSoldPriceAndQuantity(advertiserId);

        String priceMapKey = AdvertisementPriceAndQuantityService.priceMapKey;
        String quantityMapKey =AdvertisementPriceAndQuantityService.quantityMapKey;

        Map<Object,Float> prices = (Map<Object,Float>) priceAndQuantity.get(priceMapKey);
        Map<Object,Integer> quantities = (Map<Object,Integer>) priceAndQuantity.get(quantityMapKey);

        List<AdvertiserTransactionDetails>  transactionDetailsList = this.getAdvertiserTransactionDetails(prices,quantities);


        float total = this.advertisementPriceAndQuantityService.getTotal(advertiserId);
        float subtotal = total - checkoutCreateForm.getDiscount();


        AdvertiserTransaction advertiserTransaction = new AdvertiserTransaction();

        advertiserTransaction.setAdvertiserId(advertiserId);
        advertiserTransaction.setAdvertiserTransactionDetails(transactionDetailsList);
        advertiserTransaction.setSubtotal(subtotal);
        advertiserTransaction.setTotal(total);
        advertiserTransaction.setDiscount(checkoutCreateForm.getDiscount());
        advertiserTransaction.setTotalDue(checkoutCreateForm.getTotalDue());
        advertiserTransaction.setTotalPaid(checkoutCreateForm.getTotalPaidAmount());
        advertiserTransaction.setCreatedBy(admin);
        this.create(advertiserTransaction);

        return advertiserTransaction;
    }

    @Transactional(rollbackFor = Exception.class)
    public AdvertiserTransaction update(Integer advertiserId, Integer id, CheckoutForm checkoutForm, Admin admin)throws EntityNotFound{
        float total = this.advertisementPriceAndQuantityService.getTotal(advertiserId);
        float subtotal = total - checkoutForm.getDiscount();

        AdvertiserTransaction advertiserTransaction = this.getAdvertiserTransaction(id);

        advertiserTransaction.setSubtotal(subtotal);
        advertiserTransaction.setTotal(total);
        advertiserTransaction.setDiscount(checkoutForm.getDiscount());
        advertiserTransaction.setTotalDue(checkoutForm.getTotalDue());
        advertiserTransaction.setTotalPaid(checkoutForm.getTotalPaidAmount());
        advertiserTransaction.setCreatedBy(admin);

        this.update(advertiserTransaction);

        return advertiserTransaction;
    }

    @Transactional
    public AdvertiserTransaction getById(int id){
        return this.advertiserTransactionDao.getById(id);
    }

    @Transactional
    public AdvertiserTransaction getLastByAdvertiserId(int advertiserId){
        return this.advertiserTransactionDao.getLastByAdvertiserId(advertiserId);
    }
    private AdvertiserTransaction getAdvertiserTransaction(int id)throws EntityNotFound{
        AdvertiserTransaction advertiserTransaction =  this.advertiserTransactionDao.getById(id);
        if(advertiserTransaction==null){
            throw new EntityNotFound("AdvertiserTransaction entity not found");
        }
        return advertiserTransaction;
    }
    private List<AdvertiserTransactionDetails> getAdvertiserTransactionDetails(Map<Object,Float> prices, Map<Object,Integer> quantities){

        List<AdvertiserTransactionDetails> transactionDetailsList = new ArrayList<>();




        /* Calculating total */
        for(GalleryAdsConstant key :GalleryAdsConstant.values()){
            AdvertiserTransactionDetails transactionDetails = this.getAdvertiserTransactionDetails(key,
                    prices.get(key),
                    quantities.get(key));
            transactionDetailsList.add(transactionDetails);
        }
        for(SlideshowAdsConstant key :SlideshowAdsConstant.values()){

            AdvertiserTransactionDetails transactionDetails = this.getAdvertiserTransactionDetails(key,
                    prices.get(key),
                    quantities.get(key));
            transactionDetailsList.add(transactionDetails);
        }
        for(PopupAdConstant key :PopupAdConstant.values()){
            AdvertiserTransactionDetails transactionDetails = this.getAdvertiserTransactionDetails(key,
                    prices.get(key),
                    quantities.get(key));
            transactionDetailsList.add(transactionDetails);
        }

        return transactionDetailsList;

    }
    private AdvertiserTransactionDetails getAdvertiserTransactionDetails(AdvertisementDisplay ad, float price,int quantity){
        float totalPrice = price*quantity;

        AdvertiserTransactionDetails advertiserTransactionDetails = new AdvertiserTransactionDetails();
        advertiserTransactionDetails.setItem(ad.getDisplayText());
        advertiserTransactionDetails.setPrice(price);
        advertiserTransactionDetails.setQuantity(quantity);
        advertiserTransactionDetails.setTotal(totalPrice);
        return advertiserTransactionDetails;
    }
}