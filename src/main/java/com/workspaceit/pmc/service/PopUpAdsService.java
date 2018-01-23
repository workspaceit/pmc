package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import com.workspaceit.pmc.dao.PopUpAdsDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.advertisement.popup.PopupAd;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.helper.FileHelper;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsForm;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        smsPopupAdBanner.setType(PopupAdConstant.SMS);
        smsPopupAdBanner.setDuration(popupAdsForm.getSmsPopupVideoDuration());
        smsPopupAdBanner.setVideo(smsVideoName);
        smsPopupAdBanner.setVideoType(smsVideoType);
        smsPopupAdBanner.setExpiryDate(popupAdsForm.getSmsExpiryDate());
        smsPopupAdBanner.setAdRotate(popupAdsForm.getSmsRotation());

        this.create(smsPopupAdBanner);
        this.popupBannerImageService.create(smsPopupAdBanner,popupAdsForm.getSmsPopupBanner(),admin);

        PopupAd emailAddBanner =  new PopupAd();

        emailAddBanner.setAdvertiserId(advertiser.getId());
        emailAddBanner.setType(PopupAdConstant.EMAIL);
        emailAddBanner.setDuration(popupAdsForm.getEmailPopupVideoDuration());
        emailAddBanner.setVideo(emailVideoName);
        smsPopupAdBanner.setVideoType(emailVideoType);
        emailAddBanner.setExpiryDate(popupAdsForm.getEmailExpiryDate());
        emailAddBanner.setAdRotate(popupAdsForm.getEmailRotation());

        this.create(emailAddBanner);
        this.popupBannerImageService.create(emailAddBanner,popupAdsForm.getEmailPopupBanner(),admin);
    }
    @Transactional(rollbackFor = Exception.class)
    public void update(int smsPopupAdBannerId,
                       int emailAddBannerId,
                       Advertiser advertiser,
                       PopupAdsUpdateForm popupAdsForm,
                       Admin admin) throws EntityNotFound {
        Integer smsVideoToken = popupAdsForm.getSmsPopupVideo();
        Integer emailVideoToken = popupAdsForm.getEmailPopupVideo();
        System.out.println(popupAdsForm);

        PopupAd smsPopupAdBanner =  this.getPopupAd(smsPopupAdBannerId,advertiser.getId());
        smsPopupAdBanner.setDuration(popupAdsForm.getSmsPopupVideoDuration());

        this.processVideo(smsPopupAdBanner,smsVideoToken);

        smsPopupAdBanner.setExpiryDate(popupAdsForm.getSmsExpiryDate());
        smsPopupAdBanner.setAdRotate(popupAdsForm.getSmsRotation());

        this.update(smsPopupAdBanner);
        this.popupBannerImageService.create(smsPopupAdBanner,popupAdsForm.getSmsPopupBanner(),admin);

        if(popupAdsForm.getRemoveSmsBannerIds()!=null && popupAdsForm.getRemoveSmsBannerIds().length>0){
            this.popupBannerImageService.remove(smsPopupAdBanner, popupAdsForm.getRemoveSmsBannerIds(),admin);
        }

        PopupAd emailAddBanner =  this.getPopupAd(emailAddBannerId,advertiser.getId());
        emailAddBanner.setDuration(popupAdsForm.getEmailPopupVideoDuration());

        this.processVideo(emailAddBanner,emailVideoToken);

        emailAddBanner.setExpiryDate(popupAdsForm.getEmailExpiryDate());
        emailAddBanner.setAdRotate(popupAdsForm.getEmailRotation());

        this.update(emailAddBanner);
        this.popupBannerImageService.create(emailAddBanner,popupAdsForm.getEmailPopupBanner(),admin);

        if(popupAdsForm.getRemoveEmailBannerIds()!=null && popupAdsForm.getRemoveEmailBannerIds().length>0){
            this.popupBannerImageService.remove(emailAddBanner, popupAdsForm.getRemoveEmailBannerIds(),admin);
        }


    }
    private void processVideo( PopupAd popupAdBanner,Integer videoToken){
        if(videoToken!=null && videoToken>0){
            String videoName = fileService.copyFile(videoToken);
            String videoType= (videoName==null ||videoName.equals(""))?"":FileHelper.getMimeType(videoName);
            popupAdBanner.setVideo(videoName);
            popupAdBanner.setVideoType(videoType);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void  create(PopupAd popupAd){
        this.popUpAdsDao.insert(popupAd);
    }
    @Transactional(rollbackFor = Exception.class)
    public void  update(PopupAd popupAd){
        this.popUpAdsDao.update(popupAd);
    }
    @Transactional(readOnly = true)
    public List<PopupAd> getByAdvertiserId(int advertiserId){
        return this.popUpAdsDao.getByAdvertiserId(advertiserId);
    }

    @Transactional(readOnly = true)
    public PopupAd getByAdvertiserId(int advertiserId,PopupAdConstant popupAdType){
        return this.popUpAdsDao.getByAdvertiserId(advertiserId,popupAdType);
    }
    @Transactional
    public PopupAd getById(int id){
        return this.popUpAdsDao.getById(id);
    }
    @Transactional
    public PopupAd getById(int id,int advertiserId){
        return this.popUpAdsDao.getById(id,advertiserId);
    }
    @Transactional(readOnly = true)
    public PopupAd getPopupAd(int id)throws EntityNotFound{
        PopupAd popupAd =  this.getById(id);
        if(popupAd==null)throw new EntityNotFound("No Popup ad found by id :"+id);
        return popupAd;
    }
    @Transactional(readOnly = true)
    public PopupAd getPopupAd(int id,int advertiserId)throws EntityNotFound{
        PopupAd popupAd =  this.getById(id,advertiserId);
        if(popupAd==null)throw new EntityNotFound("No Popup ad found by id :"+id);
        return popupAd;
    }
}