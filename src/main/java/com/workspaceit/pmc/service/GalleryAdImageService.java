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
        List<GalleryAdsImage> galleryAdsImageList = new ArrayList<>();

        Integer logoToken = galleryAdsForm.getLogoToken();
        Integer bgImgTokens = galleryAdsForm.getBgImgTokens();
        Integer[] topBannerImgTokens = galleryAdsForm.getTopBannerImgTokens();
        Integer[] bottomBannerImgTokens = galleryAdsForm.getBottomBannerImgTokens();

        String logoFileName = this.fileService.copyFile(logoToken);
        String bgFileName = this.fileService.copyFile(bgImgTokens);

        GalleryAdsLogo galleryAdsLogo = new GalleryAdsLogo();
        GalleryAdsBackground galleryAdsBackground = new GalleryAdsBackground();

        galleryAdsLogo.setImageName(logoFileName);
        galleryAdsLogo.setGalleryAdId(galleryAd.getId());

        galleryAdsBackground.setImageName(bgFileName);
        galleryAdsBackground.setGalleryAdId(galleryAd.getId());

        galleryAdsImageList.add(galleryAdsLogo);
        galleryAdsImageList.add(galleryAdsBackground);

        for(Integer topBannerImgToken : topBannerImgTokens){
            String fileName = this.fileService.copyFile(topBannerImgToken);

            GalleryAdsTopBanner galleryAdsTopBanner = new GalleryAdsTopBanner();
            galleryAdsTopBanner.setImageName(fileName);
            galleryAdsTopBanner.setGalleryAdId(galleryAd.getId());
            galleryAdsImageList.add(galleryAdsTopBanner);
        }
        for(Integer bottomBannerImgToken : bottomBannerImgTokens){
            String fileName = this.fileService.copyFile(bottomBannerImgToken);

            GalleryAdsBottomBanner galleryAdsBottomBanner = new GalleryAdsBottomBanner();
            galleryAdsBottomBanner.setImageName(fileName);
            galleryAdsBottomBanner.setGalleryAdId(galleryAd.getId());
            galleryAdsImageList.add(galleryAdsBottomBanner);
        }

        this.create(galleryAdsImageList);

    }

    public void create(GalleryAdsImage galleryAdsImage){
        this.galleryImageDao.insert(galleryAdsImage);
    }
    public void create(List<GalleryAdsImage> galleryAdsImages){
        for(GalleryAdsImage galleryAdsImage :galleryAdsImages){
            this.galleryImageDao.insert(galleryAdsImage);
        }
    }
}
