package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.AdType;
import com.workspaceit.pmc.constant.advertisement.PopupAdType;
import com.workspaceit.pmc.dao.PopUpAdsDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.PopupAd;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mi_rafi on 1/8/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PopUpAdsService {
    private FileService fileService;
    private PopUpAdsDao popUpAdsDao;
    private PopupBannerImageService popupBannerImageService;
    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
    @Autowired
    public void setPopUpAdsDao(PopUpAdsDao popUpAdsDao) {
        this.popUpAdsDao = popUpAdsDao;
    }
    @Autowired
    public void setPopupBannerImageService(PopupBannerImageService popupBannerImageService) {
        this.popupBannerImageService = popupBannerImageService;
    }
    @Transactional(rollbackFor = Exception.class)
    public void create(Advertiser advertiser,PopupAdsForm popupAdsForm, Admin admin){

        this.createSmsPopUpVideo(advertiser,popupAdsForm,admin);
        this.createEmailPopUpVideo(advertiser,popupAdsForm,admin);

        PopupAd smsPopupAdBanner =  new PopupAd();

        smsPopupAdBanner.setAdvertiserId(advertiser.getId());
        smsPopupAdBanner.setType(PopupAdType.sms);
        smsPopupAdBanner.setAdType(AdType.banner);
        smsPopupAdBanner.setDuration(0);
        smsPopupAdBanner.setVideo("");
        smsPopupAdBanner.setExpiryDate(popupAdsForm.getSmsExpiryDate());
        this.create(smsPopupAdBanner);
        this.popupBannerImageService.create(smsPopupAdBanner,popupAdsForm.getSmsPopupBanner(),admin);

        PopupAd emailAddBanner =  new PopupAd();

        emailAddBanner.setAdvertiserId(advertiser.getId());
        emailAddBanner.setType(PopupAdType.email);
        emailAddBanner.setAdType(AdType.banner);
        emailAddBanner.setDuration(0);
        emailAddBanner.setVideo("");
        emailAddBanner.setExpiryDate(popupAdsForm.getEmailExpiryDate());

        this.create(emailAddBanner);
        this.popupBannerImageService.create(emailAddBanner,popupAdsForm.getEmailPopupBanner(),admin);
    }
    @Transactional(rollbackFor = Exception.class)
    private PopupAd createEmailPopUpVideo(Advertiser advertiser,PopupAdsForm popupAdsForm, Admin admin){
        String emailVideoName = fileService.copyFile(popupAdsForm.getEmailPopupVideo());


        PopupAd popupAd =  new PopupAd();

        popupAd.setAdvertiserId(advertiser.getId());
        popupAd.setType(PopupAdType.email);
        popupAd.setAdType(AdType.video);
        popupAd.setDuration(popupAdsForm.getEmailPopupVideoDuration());
        popupAd.setVideo(emailVideoName);
        popupAd.setCreatedBy(admin);
        popupAd.setExpiryDate(popupAdsForm.getEmailExpiryDate());

        this.create(popupAd);

        return popupAd;
    }
    @Transactional(rollbackFor = Exception.class)
    private PopupAd createSmsPopUpVideo(Advertiser advertiser,PopupAdsForm popupAdsForm, Admin admin){

        String smsVideoName = fileService.copyFile(popupAdsForm.getSmsPopupVideo());


        PopupAd popupAd =  new PopupAd();

        popupAd.setAdvertiserId(advertiser.getId());
        popupAd.setType(PopupAdType.sms);
        popupAd.setAdType(AdType.video);
        popupAd.setDuration(popupAdsForm.getSmsPopupVideoDuration());
        popupAd.setVideo(smsVideoName);
        popupAd.setCreatedBy(admin);
        popupAd.setExpiryDate(popupAdsForm.getSmsExpiryDate());

        this.create(popupAd);

        return popupAd;
    }
    @Transactional(rollbackFor = Exception.class)
    public void  create(PopupAd popupAd){
        this.popUpAdsDao.insert(popupAd);
    }
}