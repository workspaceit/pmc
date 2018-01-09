package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.PopupBannerImageDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.PopupAd;
import com.workspaceit.pmc.entity.PopupBannerImage;
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
public class PopupBannerImageService {
    private FileService fileService;
    private PopupBannerImageDao popupBannerImageDao;


    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
    @Autowired
    public void setPopupBannerImageDao(PopupBannerImageDao popupBannerImageDao) {
        this.popupBannerImageDao = popupBannerImageDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<PopupBannerImage> create(PopupAd popupAd,Integer[] tokens,Admin admin){
        List<PopupBannerImage> popupBannerImages = new ArrayList<>();
        for(int token:tokens){
            String imageName = this.fileService.copyFile(token);
            PopupBannerImage popupBannerImage = new PopupBannerImage();
            popupBannerImage.setImage(imageName);
            popupBannerImage.setCreatedBy(admin);
            popupBannerImage.setPopupAdId(popupAd.getId());
            popupBannerImages.add(popupBannerImage);
        }

        this.create(popupBannerImages);

        return popupBannerImages;
    }
    public void create( List<PopupBannerImage> popupBannerImages){
        for(PopupBannerImage popupBannerImage:popupBannerImages){
            this.create(popupBannerImage);
        }
    }
    public void create( PopupBannerImage popupBannerImage){
        this.popupBannerImageDao.insert(popupBannerImage);
    }
}