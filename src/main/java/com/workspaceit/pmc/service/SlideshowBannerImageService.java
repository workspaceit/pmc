package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.SlideshowBannerImageDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.SectionResource;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowAd;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowBannerImage;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi_rafi on 1/8/18.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class SlideshowBannerImageService {
  /*  private FileService fileService;

    private SlideshowBannerImageDao slideshowBannerImageDao;

    @Autowired
    public void setSlideshowBannerImageDao(SlideshowBannerImageDao slideshowBannerImageDao) {
        this.slideshowBannerImageDao = slideshowBannerImageDao;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<SlideshowBannerImage> create(SlideshowAd slideshowAd , SectionResourceForm[] sectionResources, Admin admin){
        if(sectionResources==null ||sectionResources.length==0){
            return null;
        }
        List<SlideshowBannerImage> slideshowBannerImageList = new ArrayList<>();

        for(SectionResourceForm secRes:sectionResources){
            String fileName = this.fileService.copyFile(secRes.getToken());

            SlideshowBannerImage slideshowBannerImage = new SlideshowBannerImage();
            slideshowBannerImage.setSlideshowAdId(slideshowAd.getId());
            slideshowBannerImage.setImage(fileName);
            slideshowBannerImage.setCreatedBy(admin);

            slideshowBannerImageList.add(slideshowBannerImage);
        }


        this.create(slideshowBannerImageList);
        return slideshowBannerImageList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void remove(SlideshowAd slideshowAd , Integer[] ids,Admin admin){
        if(ids==null||ids.length==0){
            return;
        }
        List<SlideshowBannerImage> slideshowBannerImageList = this.getById(ids,slideshowAd.getId());
        this.remove(slideshowBannerImageList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(SlideshowBannerImage slideshowBannerImage){
       this.slideshowBannerImageDao.insert(slideshowBannerImage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(List<SlideshowBannerImage> slideshowBannerImages){
        for(SlideshowBannerImage slideshowBannerImage:slideshowBannerImages){

            this.slideshowBannerImageDao.insert(slideshowBannerImage);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(List<SlideshowBannerImage> slideshowBannerImages){
        for(SlideshowBannerImage slideshowBannerImage:slideshowBannerImages){

            this.slideshowBannerImageDao.update(slideshowBannerImage);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void update(SlideshowBannerImage slideshowBannerImage){
        this.slideshowBannerImageDao.update(slideshowBannerImage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void remove(List<SlideshowBannerImage> slideshowBannerImages){
        for(SlideshowBannerImage slideshowBannerImage:slideshowBannerImages){
            this.slideshowBannerImageDao.delete(slideshowBannerImage);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void remove(SlideshowBannerImage slideshowBannerImage){
        this.slideshowBannerImageDao.delete(slideshowBannerImage);
    }

    @Transactional
    public List<SlideshowBannerImage>  getById(Integer[] ids,int slideshowAdId){
        return this.slideshowBannerImageDao.getById(ids,slideshowAdId);
    }

*/
}