package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.ENTITY_STATE;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant;
import com.workspaceit.pmc.dao.AdvertisementDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.AdvertiserTransaction;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.util.VelocityUtil;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class AdvertisementService {
    private AdvertisementDao advertisementDao;
    private AdvertisementPriceAndQuantityService advertisementPriceAndQuantityService;
    private AdvertiserTransactionService advertiserTransactionService;
    private VelocityUtil velocityUtil;

    @Autowired
    public void setAdvertisementDao(AdvertisementDao advertisementDao) {
        this.advertisementDao = advertisementDao;
    }

    @Autowired
    public void setAdvertisementPriceAndQuantityService(AdvertisementPriceAndQuantityService advertisementPriceAndQuantityService) {
        this.advertisementPriceAndQuantityService = advertisementPriceAndQuantityService;
    }

    @Autowired
    public void setAdvertiserTransactionService(AdvertiserTransactionService advertiserTransactionService) {
        this.advertiserTransactionService = advertiserTransactionService;
    }

    @Autowired
    public void setVelocityUtil(VelocityUtil velocityUtil) {
        this.velocityUtil = velocityUtil;
    }

    @Transactional(rollbackFor = Exception.class)
    public Advertisement create(Advertisement advertisement){
        this.advertisementDao.insert(advertisement);
        return advertisement;
    }

    @Transactional
    public Advertisement getById(int id){
        return this.advertisementDao.getById(id);
    }

    @Transactional
    public List<Advertisement> getByAdvertiserId(int advertiserId){
        return this.advertisementDao.getByAdvertiserId(advertiserId);
    }

    @Transactional
    public List<Advertisement> getByAdvertiserId(List<Integer> advertiserId){
        return this.advertisementDao.getByAdvertiserId(advertiserId);
    }
    @Transactional
    public Map<ADVERTISEMENT_TYPE,Advertisement> getMapByAdvertiserId(int advertiserId){
        Map<ADVERTISEMENT_TYPE,Advertisement> advertisementMap = new HashMap<>();
        List<Advertisement> advertisements  =  this.advertisementDao.getByAdvertiserId(advertiserId);
        for (Advertisement advertisement:advertisements) {
            advertisementMap.put(advertisement.getAdType(),advertisement);
        }

        return advertisementMap;
    }

    @Transactional
    public Advertisement getByAdvertiserIdAndType(int advertiserId,ADVERTISEMENT_TYPE adType){
        Advertisement advertisement  =  this.advertisementDao.getByAdvertiserIdAndType(advertiserId,adType);
        return advertisement;
    }
    @Transactional
    public List<Advertisement> getByAdvertiserIdAndType(List<Integer> advertiserIds,ADVERTISEMENT_TYPE adType){
        List<Advertisement> advertisements = new ArrayList<>();
        for(Integer advertiserId:advertiserIds){

            Advertisement advertisement = this.getByAdvertiserIdAndType(advertiserId,adType);
            advertisements.add(advertisement);
        }

        return advertisements;
    }
    @Transactional
    public List<Map<ADVERTISEMENT_TYPE,Advertisement>> getMapByAdvertiserId(List<Integer> advertiserIds,ADVERTISEMENT_TYPE adType){
        List<Map<ADVERTISEMENT_TYPE,Advertisement>> advertisements = new ArrayList<>();
        for(Integer advertiserId:advertiserIds){

            Map<ADVERTISEMENT_TYPE,Advertisement> advertisementMap = this.getMapByAdvertiserId(advertiserId);
            advertisements.add(advertisementMap);
        }

        return advertisements;
    }
    @Transactional
    public List<Map<ADVERTISEMENT_TYPE,Advertisement>> getMapByAdvertiserId(List<Integer> advertiserIds){
        List<Map<ADVERTISEMENT_TYPE,Advertisement>> advertisements = new ArrayList<>();
        for(Integer advertiserId:advertiserIds){

            Map<ADVERTISEMENT_TYPE,Advertisement> advertisementMap = this.getMapByAdvertiserId(advertiserId);
            advertisements.add(advertisementMap);
        }

        return advertisements;
    }

    @Transactional(rollbackFor = Exception.class)
    public Advertisement create(int advertiserId, ADVERTISEMENT_TYPE adType, Admin admin){
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertiserId(advertiserId);
        advertisement.setState(ENTITY_STATE.ACTIVE);
        advertisement.setAdType(adType);
        advertisement.setCreatedBy(admin);

        return this.create(advertisement);
    }
    @Transactional(rollbackFor = Exception.class)
    public void update(Advertisement advertisement, Admin admin){
        this.advertisementDao.update(advertisement);
    }

    public String getInvoiceInHtml(Advertiser advertiser){


        AdvertiserTransaction advertiserTransaction =  this.advertiserTransactionService.getLastByAdvertiserId(advertiser.getId());


        /* Price and quantity */
        Map<String,Object>   priceAndQuantity = this.advertisementPriceAndQuantityService.getSoldPriceAndQuantity(advertiser.getId());

        String priceMapKey = AdvertisementPriceAndQuantityService.priceMapKey;
        String quantityMapKey =AdvertisementPriceAndQuantityService.quantityMapKey;

        Map<Object,Float> prices =(Map<Object,Float>) priceAndQuantity.get(priceMapKey);
        Map<Object,Integer> quantities =(Map<Object,Integer>) priceAndQuantity.get(quantityMapKey);

        int transactionId = (advertiserTransaction!=null)?advertiserTransaction.getId():0;
        float totalPrice = this.advertisementPriceAndQuantityService.calculateTotal(prices,quantities);
        float discount = (advertiserTransaction!=null)?advertiserTransaction.getDiscount():0;
        float totalPayedPrice =(advertiserTransaction!=null)?advertiserTransaction.getTotalPaid():0;
        float totalDuePrice =(advertiserTransaction!=null)?advertiserTransaction.getTotalDue():(totalPrice-discount)-(totalPayedPrice);
        float priceAfterDiscount = totalPrice-discount;
        float amountReturn = (totalPayedPrice>priceAfterDiscount)?(totalPayedPrice - priceAfterDiscount):0;




        VelocityContext context = new VelocityContext();
        context.put("link", "");


        context.put("transactionId",transactionId);
        context.put("advertiser",advertiser);

        /* Price and quantity */
        context.put("prices",prices);
        context.put("quantities",quantities);
        context.put("totalPrice",totalPrice);
        context.put("amountReturn",amountReturn);
        context.put("discount",discount);
        context.put("totalDuePrice",totalDuePrice);
        context.put("totalPayedPrice",totalPayedPrice);


        /* Number format settings values */
        context.put("currencyCode","USD");
        context.put("currencySymbol","$");
        context.put("maxFractionDigits",2);

        /** Enum */
        context.put("GalleryAdsConstantBACKGROUND_IMAGE", GalleryAdsConstant.BACKGROUND_IMAGE);
        context.put("GalleryAdsConstantTOP_AD_BANNER", GalleryAdsConstant.TOP_AD_BANNER);
        context.put("GalleryAdsConstantBOTTOM_AD_BANNER", GalleryAdsConstant.BOTTOM_AD_BANNER);

        context.put("SlideshowAdsConstantBANNER", SlideshowAdsConstant.BANNER);
        context.put("SlideshowAdsConstantVIDEO", SlideshowAdsConstant.BANNER);

        context.put("PopupAdConstantSMS", PopupAdConstant.SMS);
        context.put("PopupAdConstantEMAIL", PopupAdConstant.EMAIL);


        String emailHtmlBody = this.velocityUtil.getHtmlByTemplateAndContext("invoice.vm",context);

        return emailHtmlBody;

    }

}