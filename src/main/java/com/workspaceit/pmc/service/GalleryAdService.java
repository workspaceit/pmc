package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;
import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.dao.GalleryAdDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.Section;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAdQuantityPrice;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsCreateForm;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mi_rafi on 1/5/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GalleryAdService {
    GalleryAdDao galleryAdDao;
    GalleryAdImageService galleryImagesAdService;
    FileService fileService;
    GalleryAdQuantityPriceService galleryAdQuantityPriceService;

    @Autowired
    public void setGalleryAdDao(GalleryAdDao galleryAdDao) {
        this.galleryAdDao = galleryAdDao;
    }
    @Autowired
    public void setGalleryImagesAdService(GalleryAdImageService galleryImagesAdService) {
        this.galleryImagesAdService = galleryImagesAdService;
    }
    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
    @Autowired
    public void setGalleryAdQuantityPriceService(GalleryAdQuantityPriceService galleryAdQuantityPriceService) {
        this.galleryAdQuantityPriceService = galleryAdQuantityPriceService;
    }

    public GalleryAd create(Advertiser advertiser, GalleryAdsCreateForm galleryAdsForm, Admin admin){
        Integer logoToken = galleryAdsForm.getLogoToken();
        Integer bgImgTokens = galleryAdsForm.getBgImgTokens();
        String logoFileName = this.fileService.copyFile(logoToken);
        String bgFileName = this.fileService.copyFile(bgImgTokens);
        Date topBannerExpiryDate =   galleryAdsForm.getTopBannerExpiryDate();
        Date bottomBannerExpiryDate = galleryAdsForm.getBottomBannerExpiryDate();

        Float bgPrice =  galleryAdsForm.getBgPrice();
        Float topBannerPrice =  galleryAdsForm.getTopBannerPrice();
        Float bottomPrice =  galleryAdsForm.getBottomBannerPrice();

        int bgQuantity = (galleryAdsForm.getBgImgTokens()!=null)?1:0;
        int topBannerQuantity = (galleryAdsForm.getTopBannerImgTokens()!=null)?galleryAdsForm.getTopBannerImgTokens().length:0;
        int bottomBannerQuantity = (galleryAdsForm.getBottomBannerImgTokens()!=null)?galleryAdsForm.getBottomBannerImgTokens().length:0;

        GalleryAd galleryAd = new GalleryAd();
        galleryAd.setAdvertiserId(advertiser.getId());
        galleryAd.setLogo(logoFileName);
        galleryAd.setBackgroundImage(bgFileName);
        galleryAd.setTopBannerExpiryDate( topBannerExpiryDate);
        galleryAd.setBottomBannerExpiryDate(bottomBannerExpiryDate);
        galleryAd.setTopBannerRotate(galleryAdsForm.getTopBannerRotation());
        galleryAd.setBottomBannerRotate(galleryAdsForm.getBottomBannerRotation());
        galleryAd.setCreatedBy(admin);


        this.create(galleryAd);

        /** Background,Top Banner,Bottom Banner Images price and quantity */
        this.galleryAdQuantityPriceService.create(galleryAd.getId(),bgPrice,bgQuantity,
                                                    GalleryAdsConstant.BACKGROUND_IMAGE,admin);
        this.galleryAdQuantityPriceService.create(galleryAd.getId(),topBannerPrice,topBannerQuantity,
                                                    GalleryAdsConstant.TOP_AD_BANNER,admin);
        this.galleryAdQuantityPriceService.create(galleryAd.getId(),bottomPrice,bottomBannerQuantity,
                                                    GalleryAdsConstant.BOTTOM_AD_BANNER,admin);

        /**
         * Background,Top Banner,Bottom Banner Images copy from temp folder to common
         * and save in database
        * */
        try{
            this.galleryImagesAdService.create(galleryAdsForm,galleryAd,admin);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return galleryAd;
    }
    public GalleryAd update(int id,Advertiser advertiser,GalleryAdsUpdateForm galleryAdsForm, Admin admin) throws EntityNotFound {
        Integer logoToken = galleryAdsForm.getLogoToken();
        Integer bgImgTokens = galleryAdsForm.getBgImgTokens();

        Date topBannerExpiryDate =   galleryAdsForm.getTopBannerExpiryDate();
        Date bottomBannerExpiryDate = galleryAdsForm.getBottomBannerExpiryDate();

        GalleryAd galleryAd = this.getGalleryAd(id,advertiser.getId());

        if(logoToken!=null && logoToken>0){
            String logoFileName = this.fileService.copyFile(logoToken);
            galleryAd.setLogo(logoFileName);
        }

        if(bgImgTokens!=null && bgImgTokens>0){
            String bgFileName = this.fileService.copyFile(bgImgTokens);
            galleryAd.setBackgroundImage(bgFileName);
        }

        galleryAd.setTopBannerExpiryDate( topBannerExpiryDate);
        galleryAd.setBottomBannerExpiryDate(bottomBannerExpiryDate);

        galleryAd.setTopBannerRotate(galleryAdsForm.getTopBannerRotation());
        galleryAd.setBottomBannerRotate(galleryAdsForm.getBottomBannerRotation());

        this.update(galleryAd);

        try{
            if(galleryAdsForm.getTopBannerImgTokens()!=null ||galleryAdsForm.getBottomBannerImgTokens()!=null){
                this.galleryImagesAdService.create(galleryAdsForm,galleryAd,admin);
            }
            /**
             * this.galleryImagesAdService.remove handles null checking
             * */
            this.galleryImagesAdService.remove(galleryAdsForm,galleryAd,admin);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return galleryAd;
    }
    public void create(GalleryAd galleryAd){
        galleryAdDao.insert(galleryAd);
    }
    public void update(GalleryAd galleryAd){
        galleryAdDao.update(galleryAd);
    }
    public GalleryAd getById(int id){
        return galleryAdDao.getById(id);
    }

    public GalleryAd getByAdvertiserId(int advertiserId){
        return galleryAdDao.getByAdvertiserId(advertiserId);
    }
    private GalleryAd getGalleryAd(int id)throws EntityNotFound{

        GalleryAd  galleryAd = galleryAdDao.getById(id);

        if(galleryAd==null){
            throw new EntityNotFound("No gallery found by id "+id);
        }
        return galleryAd;
    }
    private GalleryAd getGalleryAd(int id,int advertiserId)throws EntityNotFound{

        GalleryAd  galleryAd = galleryAdDao.getById(id,advertiserId);

        if(galleryAd==null){
            throw new EntityNotFound("No gallery found by id "+id);
        }
        return galleryAd;
    }
}
