package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.SlideshowBannerImageDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.SlideshowAd;
import com.workspaceit.pmc.entity.SlideshowBannerImage;
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
    private FileService fileService;

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
    public List<SlideshowBannerImage> create(SlideshowAd slideshowAd , Integer[] tokens,Admin admin){
        List<SlideshowBannerImage> slideshowBannerImageList = new ArrayList<>();

        for(int token:tokens){
            String fileName = this.fileService.copyFile(token);

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
    public void create(SlideshowBannerImage slideshowBannerImage){
       this.slideshowBannerImageDao.insert(slideshowBannerImage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(List<SlideshowBannerImage> slideshowBannerImages){
        for(SlideshowBannerImage slideshowBannerImage:slideshowBannerImages){

            this.slideshowBannerImageDao.insert(slideshowBannerImage);
        }
    }
}