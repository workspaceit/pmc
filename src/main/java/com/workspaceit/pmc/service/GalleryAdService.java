package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.GalleryAdDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GalleryAdService {
    GalleryAdDao galleryAdDao;
    GalleryAdImageService galleryAdService;
    FileService fileService;

    @Autowired
    public void setGalleryAdDao(GalleryAdDao galleryAdDao) {
        this.galleryAdDao = galleryAdDao;
    }
    @Autowired
    public void setGalleryAdService(GalleryAdImageService galleryAdService) {
        this.galleryAdService = galleryAdService;
    }
    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public GalleryAd create(Advertiser advertiser,GalleryAdsForm galleryAdsForm, Admin admin){
        Integer logoToken = galleryAdsForm.getLogoToken();
        Integer bgImgTokens = galleryAdsForm.getBgImgTokens();
        String logoFileName = this.fileService.copyFile(logoToken);
        String bgFileName = this.fileService.copyFile(bgImgTokens);
        Date topBannerExpiryDate =   galleryAdsForm.getTopBannerExpiryDate();
        Date bottomBannerExpiryDate = galleryAdsForm.getBottomBannerExpiryDate();

        GalleryAd galleryAd = new GalleryAd();
        galleryAd.setAdvertiserId(advertiser.getId());
        galleryAd.setLogo(logoFileName);
        galleryAd.setBackgroundImage(bgFileName);
        galleryAd.setTopBannerExpiryDate( topBannerExpiryDate);
        galleryAd.setBottomBannerExpiryDate(bottomBannerExpiryDate);
        galleryAd.setCreatedBy(admin);

        this.create(galleryAd);

        try{
            this.galleryAdService.create(galleryAdsForm,galleryAd,admin);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return galleryAd;
    }
    public void create(GalleryAd galleryAd){
        galleryAdDao.insert(galleryAd);
    }
    public GalleryAd getById(int id){
        return galleryAdDao.getById(id);
    }
}
