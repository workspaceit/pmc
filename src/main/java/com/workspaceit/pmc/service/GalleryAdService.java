package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.GalleryAdDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.entity.advertisement.galleryads.images.GalleryAdsImage;
import com.workspaceit.pmc.helper.DateHelper;
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

    @Autowired
    public void setGalleryAdDao(GalleryAdDao galleryAdDao) {
        this.galleryAdDao = galleryAdDao;
    }
    @Autowired
    public void setGalleryAdService(GalleryAdImageService galleryAdService) {
        this.galleryAdService = galleryAdService;
    }

    public GalleryAd create(GalleryAdsForm galleryAdsForm, Admin admin){
        GalleryAd galleryAd = new GalleryAd();

        Date topBannerExpiryDate =   galleryAdsForm.getTopBannerExpiryDate();
        Date bottomBannerExpiryDate = galleryAdsForm.getBottomBannerExpiryDate();

        galleryAd.setTopBannerExpiryDate( topBannerExpiryDate);
        galleryAd.setBottomBannerExpiryDate(bottomBannerExpiryDate);
        galleryAd.setCreatedBy(admin);

        this.create(galleryAd);

        try{
            this.galleryAdService.create(galleryAdsForm,galleryAd,admin);
        }catch (Exception ex){

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
