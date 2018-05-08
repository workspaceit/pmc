package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant;
import com.workspaceit.pmc.dao.SlideshowAdDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowAd;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsCreateForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mi_rafi on 1/8/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SlideShowService {
   /* private FileService fileService;
    private SlideshowBannerImageService slideshowBannerImageService;
    private SlideshowQuantityPriceService quantityPriceService;

    private SlideshowAdDao slideshowAdDao;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setSlideshowBannerImageService(SlideshowBannerImageService slideshowBannerImageService) {
        this.slideshowBannerImageService = slideshowBannerImageService;
    }
    @Autowired
    public void setQuantityPriceService(SlideshowQuantityPriceService quantityPriceService) {
        this.quantityPriceService = quantityPriceService;
    }

    @Autowired
    public void setSlideshowAdDao(SlideshowAdDao slideshowAdDao) {
        this.slideshowAdDao = slideshowAdDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public SlideshowAd create(Advertiser advertiser , SlideShowAdsCreateForm slideShowAdsForm, Admin admin){
        Integer videoToken = slideShowAdsForm.getSlideShowAdsVideoToken();
        Integer[] bannerTokens = slideShowAdsForm.getSlideShowAdsBannerTokens();
        String videoType = this.fileService.getMimeTypeByToken(videoToken);
        String videoName = this.fileService.copyFile(videoToken);

        float bannerPrice = slideShowAdsForm.getBannerPrice();
        int bannerQuantity = bannerTokens!=null?bannerTokens.length:0;
        int videoQuantity = (videoToken!=null && videoToken>0)?1:0;


        SlideshowAd slideshowAd = new SlideshowAd();

        slideshowAd.setAdvertiserId(advertiser.getId());
        slideshowAd.setVideo(videoName);
        slideshowAd.setVideoType(videoType);
        slideshowAd.setBannerExpiryDate(slideShowAdsForm.getBannerExpiryDate());
        slideshowAd.setBannerDuration(slideShowAdsForm.getSlideShowBannerDuration());
        slideshowAd.setBannerRotate(slideShowAdsForm.getBannerRotation());
        slideshowAd.setCreatedBy(admin);

        this.create(slideshowAd);

        this.quantityPriceService.create(slideshowAd.getId(),bannerPrice,bannerQuantity, SlideshowAdsConstant.BANNER,admin);

        this.slideshowBannerImageService.create(slideshowAd,bannerTokens,admin);

        return slideshowAd;
    }
    @Transactional(rollbackFor = Exception.class)
    public SlideshowAd update(int id,Advertiser advertiser , SlideShowAdsUpdateForm slideShowAdsForm, Admin admin)throws EntityNotFound{
        SectionResourceForm videoToken = slideShowAdsForm.getSlideShowAdsVideoResources();
        SectionResourceForm[] bannerTokens = slideShowAdsForm.getSlideShowAdsBannerResources();

        Integer[] removeBannerIds = slideShowAdsForm.getRemoveBannerIds();
        SlideshowAd slideshowAd = this.getSlideshowAd(id,advertiser.getId());

        if(videoToken.getToken()!=null && videoToken.getToken()>0){
            String videoType = this.fileService.getMimeTypeByToken(videoToken.getToken());
            String videoName = this.fileService.copyFile(videoToken.getToken());
            slideshowAd.setVideo(videoName);
            slideshowAd.setVideoType(videoType);
        }

        slideshowAd.setBannerExpiryDate(slideShowAdsForm.getBannerExpiryDate());
        slideshowAd.setBannerDuration(slideShowAdsForm.getSlideShowBannerDuration());
        slideshowAd.setBannerRotate(slideShowAdsForm.getBannerRotation());

        this.update(slideshowAd);

        if(bannerTokens!=null && bannerTokens.length>0){
            this.slideshowBannerImageService.create(slideshowAd,slideShowAdsForm.getSlideShowAdsBannerResources(),admin);
        }

        if(removeBannerIds!=null && removeBannerIds.length>0){
            this.slideshowBannerImageService.remove(slideshowAd,slideShowAdsForm.getRemoveBannerIds(),admin);
        }

        return slideshowAd;
    }
    @Transactional(readOnly = true)
    public SlideshowAd getByAdvertiserId(int advertiserId){
       return this.slideshowAdDao.getByAdvertiserId(advertiserId);
    }
    @Transactional(readOnly = true)
    public SlideshowAd getSlideshowAd(int id)throws EntityNotFound{
        SlideshowAd slideshowAd =  this.slideshowAdDao.getById(id);
        if(slideshowAd==null){
            throw new EntityNotFound("No slideshowAd entity found with id :"+id);
        }
        return slideshowAd;
    }
    @Transactional(readOnly = true)
    public SlideshowAd getSlideshowAd(int id,int advertiserId)throws EntityNotFound{
        SlideshowAd slideshowAd =  this.slideshowAdDao.getById(id,advertiserId);
        if(slideshowAd==null){
            throw new EntityNotFound("No slideshowAd entity found with id :"+id);
        }
        return slideshowAd;
    }
    @Transactional(rollbackFor = Exception.class)
    public void create(SlideshowAd slideshowAd){
         this.slideshowAdDao.insert(slideshowAd);
    }
    @Transactional(rollbackFor = Exception.class)
    public void update(SlideshowAd slideshowAd){
        this.slideshowAdDao.update(slideshowAd);
    }*/
}