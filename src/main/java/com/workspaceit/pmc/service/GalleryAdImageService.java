package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.GalleryImageDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.entity.advertisement.galleryads.images.*;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi_rafi on 1/5/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GalleryAdImageService {
    private GalleryImageDao galleryImageDao;
    private FileService fileService;

    @Autowired
    public void setGalleryImageDao(GalleryImageDao galleryImageDao) {
        this.galleryImageDao = galleryImageDao;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public void create(GalleryAdsForm galleryAdsForm, GalleryAd galleryAd,Admin admin){
        List<Object> galleryAdsImageList = new ArrayList<>();

        Integer[] topBannerImgTokens = galleryAdsForm.getTopBannerImgTokens();
        Integer[] bottomBannerImgTokens = galleryAdsForm.getBottomBannerImgTokens();





        for(Integer topBannerImgToken : topBannerImgTokens){
            String fileName = this.fileService.copyFile(topBannerImgToken);

            GalleryAdsTopBannerImage galleryAdsTopBanner = new GalleryAdsTopBannerImage();
            galleryAdsTopBanner.setImage(fileName);
            galleryAdsTopBanner.setGalleryAd(galleryAd.getId());
            galleryAdsImageList.add(galleryAdsTopBanner);
        }
        for(Integer bottomBannerImgToken : bottomBannerImgTokens){
            String fileName = this.fileService.copyFile(bottomBannerImgToken);

            GalleryAdsBottomBannerImage galleryBottomBannerImage = new GalleryAdsBottomBannerImage();
            galleryBottomBannerImage.setImage(fileName);
            galleryBottomBannerImage.setGalleryAdId(galleryAd.getId());
            galleryAdsImageList.add(galleryBottomBannerImage);
        }

        this.create(galleryAdsImageList);

    }

    public void create(Object galleryAdsImage){
        this.galleryImageDao.insert(galleryAdsImage);
    }
    public void create(List<Object> galleryAdsImages){
        for(Object galleryAdsImage :galleryAdsImages){
            this.galleryImageDao.insert(galleryAdsImage);
        }
    }
}
