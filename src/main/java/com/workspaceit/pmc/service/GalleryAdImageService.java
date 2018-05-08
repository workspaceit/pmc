package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.GalleryAdsBottomBannerImageDao;
import com.workspaceit.pmc.dao.GalleryAdsTopBannerImageDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.entity.advertisement.galleryads.images.*;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsForm;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;
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
    private GalleryAdsTopBannerImageDao galleryAdsTopBannerImageDao;
    private GalleryAdsBottomBannerImageDao galleryAdsBottomBannerImageDao;
    private FileService fileService;

    @Autowired
    public void setGalleryAdsTopBannerImageDao(GalleryAdsTopBannerImageDao galleryAdsTopBannerImageDao) {
        this.galleryAdsTopBannerImageDao = galleryAdsTopBannerImageDao;
    }
    @Autowired
    public void setGalleryAdsBottomBannerImageDao(GalleryAdsBottomBannerImageDao galleryAdsBottomBannerImageDao) {
        this.galleryAdsBottomBannerImageDao = galleryAdsBottomBannerImageDao;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public void create(GalleryAdsForm galleryAdsForm, GalleryAd galleryAd, Admin admin){
        List<Object> galleryAdsImageList = new ArrayList<>();

        SectionResourceForm[] topSectionResources = galleryAdsForm.getTopSectionResource();
        SectionResourceForm[] bottomSectionResources = galleryAdsForm.getBottomSectionResource();

        if(topSectionResources==null)topSectionResources=new SectionResourceForm[0];
        if(bottomSectionResources==null)bottomSectionResources=new SectionResourceForm[0];


        for(SectionResourceForm sectionRes : topSectionResources){
            String fileName = this.fileService.copyFile(sectionRes.getToken());

            GalleryAdsTopBannerImage galleryAdsTopBanner = new GalleryAdsTopBannerImage();
            galleryAdsTopBanner.setImage(fileName);
            galleryAdsTopBanner.setGalleryAdId(galleryAd.getId());
            galleryAdsImageList.add(galleryAdsTopBanner);
        }
        for(SectionResourceForm sectionRes : bottomSectionResources){
            String fileName = this.fileService.copyFile(sectionRes.getToken());

            GalleryAdsBottomBannerImage galleryBottomBannerImage = new GalleryAdsBottomBannerImage();
            galleryBottomBannerImage.setImage(fileName);
            galleryBottomBannerImage.setGalleryAdId(galleryAd.getId());
            galleryAdsImageList.add(galleryBottomBannerImage);
        }

        this.create(galleryAdsImageList);

    }
    public void remove(GalleryAdsUpdateForm galleryAdsForm, GalleryAd galleryAd, Admin admin){
        Integer[] topRmId =  galleryAdsForm.getRemoveTopBannerIds();
        Integer[] btmRmId = galleryAdsForm.getRemoveBottomBannerIds();
        if(topRmId==null ||btmRmId==null ){
            return;
        }

        List<GalleryAdsTopBannerImage> galleryAdsTopBannerImages =  this.galleryAdsTopBannerImageDao.getById(topRmId,galleryAd.getId());
        List<GalleryAdsBottomBannerImage> galleryAdsBottomBannerImages =  this.galleryAdsBottomBannerImageDao.getById(btmRmId,galleryAd.getId());

        if(galleryAdsTopBannerImages!=null && galleryAdsTopBannerImages.size()>0 ){
            this.removeGalleryAdsTopBannerImage(galleryAdsTopBannerImages);
        }

        if(galleryAdsBottomBannerImages!=null && galleryAdsBottomBannerImages.size()>0 ){
            this.removeGalleryAdsBottomBannerImage(galleryAdsBottomBannerImages);
        }
    }

    public void create(Object galleryAdsImage){
        this.galleryAdsTopBannerImageDao.insert(galleryAdsImage);
    }
    public void create(List<Object> galleryAdsImages){
        for(Object galleryAdsImage :galleryAdsImages){
            this.galleryAdsTopBannerImageDao.insert(galleryAdsImage);
        }
    }
    public List<GalleryAdsTopBannerImage> getTopBannerImage(Integer[] ids,int galleryId){
        return this.galleryAdsTopBannerImageDao.getById(ids,galleryId);
    }
    public List<GalleryAdsBottomBannerImage> getBottomBannerImage(Integer[] ids,int galleryId){
        return this.galleryAdsBottomBannerImageDao.getById(ids,galleryId);
    }

    public void removeGalleryAdsTopBannerImage(List<GalleryAdsTopBannerImage> galleryAdsTopBannerImages){
        for(GalleryAdsTopBannerImage galleryAdsTopBannerImage:galleryAdsTopBannerImages){
            this.removeGalleryAdsTopBannerImage(galleryAdsTopBannerImage);
        }

    }
    public void removeGalleryAdsBottomBannerImage(List<GalleryAdsBottomBannerImage> galleryAdsBottomBannerImages){

        for(GalleryAdsBottomBannerImage galleryAdsBottomBannerImage:galleryAdsBottomBannerImages){
            this.removeGalleryAdsBottomBannerImage(galleryAdsBottomBannerImage);
        }
    }

    public void removeGalleryAdsTopBannerImage(GalleryAdsTopBannerImage galleryAdsTopBannerImage){
        this.galleryAdsTopBannerImageDao.delete(galleryAdsTopBannerImage);
    }
    public void removeGalleryAdsBottomBannerImage(GalleryAdsBottomBannerImage galleryAdsBottomBannerImage){
        this.galleryAdsBottomBannerImageDao.delete(galleryAdsBottomBannerImage);
    }
}