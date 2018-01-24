package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import com.workspaceit.pmc.dao.PopUpAdsQuantityPriceDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.popup.PopupAdQuantityPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PopUpAdsQuantityPriceService {
   private PopUpAdsQuantityPriceDao popUpAdsQuantityPriceDao;

    @Autowired
    public void setPopUpAdsQuantityPriceDao(PopUpAdsQuantityPriceDao popUpAdsQuantityPriceDao) {
        this.popUpAdsQuantityPriceDao = popUpAdsQuantityPriceDao;
    }
    @Transactional(rollbackFor = Exception.class)
    public PopupAdQuantityPrice create(PopupAdQuantityPrice popupAdQuantityPrice){
        this.popUpAdsQuantityPriceDao.insert(popupAdQuantityPrice);
        return popupAdQuantityPrice;
    }

    @Transactional(rollbackFor = Exception.class)
    public PopupAdQuantityPrice create(int popUpAdId,
           float price,
           int quantity,
           PopupAdConstant popupAdConstant,
           Admin admin){

        PopupAdQuantityPrice popupAdQuantityPrice = new PopupAdQuantityPrice();

        popupAdQuantityPrice.setAdType(popupAdConstant);
        popupAdQuantityPrice.setPopupAdId(popUpAdId);
        popupAdQuantityPrice.setPrice(price);
        popupAdQuantityPrice.setQuantity(quantity);
        popupAdQuantityPrice.setCreatedBy(admin);

        this.create(popupAdQuantityPrice);

        return popupAdQuantityPrice;
    }
}