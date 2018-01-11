package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.SlideshowAdDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.SlideshowAd;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mi_rafi on 1/8/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SlideShowService {
    private FileService fileService;
    private SlideshowBannerImageService slideshowBannerImageService;

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
    public void setSlideshowAdDao(SlideshowAdDao slideshowAdDao) {
        this.slideshowAdDao = slideshowAdDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public SlideshowAd create(Advertiser advertiser , SlideShowAdsForm slideShowAdsForm, Admin admin){
        Integer videoToken = slideShowAdsForm.getSlideShowAdsVideoToken();
        String videoName = this.fileService.copyFile(videoToken);

        SlideshowAd slideshowAd = new SlideshowAd();
        slideshowAd.setAdvertiserId(advertiser.getId());
        slideshowAd.setVideo(videoName);
        slideshowAd.setBannerDuration(slideShowAdsForm.getSlideShowBannerDuration());
        slideshowAd.setVideoDuration(slideShowAdsForm.getSlideShowVideoDuration());
        slideshowAd.setCreatedBy(admin);

        slideshowBannerImageService.create(slideshowAd,slideShowAdsForm.getSlideShowAdsBannerTokens(),admin);

        return slideshowAd;
    }
    @Transactional(readOnly = true)
    public SlideshowAd getByAdvertiserId(int advertiserId){
       return this.slideshowAdDao.getByAdvertiserId(advertiserId);
    }
}