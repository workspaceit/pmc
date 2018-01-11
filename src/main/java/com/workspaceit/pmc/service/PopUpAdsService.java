package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.PopupAdType;
import com.workspaceit.pmc.dao.PopUpAdsDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.PopupAd;
import com.workspaceit.pmc.helper.FileHelper;
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
        String smsVideoName = fileService.copyFile(popupAdsForm.getSmsPopupVideo());
        String emailVideoName = fileService.copyFile(popupAdsForm.getEmailPopupVideo());

        String emailVideoType = (emailVideoName==null ||emailVideoName.equals(""))?"":FileHelper.getMimeType(emailVideoName);
        String smsVideoType= (smsVideoName==null ||smsVideoName.equals(""))?"":FileHelper.getMimeType(smsVideoName);

        PopupAd smsPopupAdBanner =  new PopupAd();

        smsPopupAdBanner.setAdvertiserId(advertiser.getId());
        smsPopupAdBanner.setType(PopupAdType.SMS);
        smsPopupAdBanner.setDuration(popupAdsForm.getSmsPopupVideoDuration());
        smsPopupAdBanner.setVideo(smsVideoName);
        smsPopupAdBanner.setVideoType(smsVideoType);
        smsPopupAdBanner.setExpiryDate(popupAdsForm.getSmsExpiryDate());
        this.create(smsPopupAdBanner);
        this.popupBannerImageService.create(smsPopupAdBanner,popupAdsForm.getSmsPopupBanner(),admin);

        PopupAd emailAddBanner =  new PopupAd();

        emailAddBanner.setAdvertiserId(advertiser.getId());
        emailAddBanner.setType(PopupAdType.EMAIL);
        emailAddBanner.setDuration(popupAdsForm.getEmailPopupVideoDuration());
        emailAddBanner.setVideo(emailVideoName);
        smsPopupAdBanner.setVideoType(emailVideoType);
        emailAddBanner.setExpiryDate(popupAdsForm.getEmailExpiryDate());

        this.create(emailAddBanner);
        this.popupBannerImageService.create(emailAddBanner,popupAdsForm.getEmailPopupBanner(),admin);
    }

    @Transactional(rollbackFor = Exception.class)
    public void  create(PopupAd popupAd){
        this.popUpAdsDao.insert(popupAd);
    }

    @Transactional(readOnly = true)
    public PopupAd getByAdvertiserId(int advertiserId){
        return this.popUpAdsDao.getByAdvertiserId(advertiserId);
    }

    @Transactional(readOnly = true)
    public PopupAd getByAdvertiserId(int advertiserId,PopupAdType popupAdType){
        return this.popUpAdsDao.getByAdvertiserId(advertiserId,popupAdType);
    }

}