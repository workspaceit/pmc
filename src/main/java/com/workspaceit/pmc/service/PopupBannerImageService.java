package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.PopupBannerImageDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.popup.PopupAd;
import com.workspaceit.pmc.entity.advertisement.popup.PopupBannerImage;
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
        if(tokens==null || tokens.length==0){
            return null;
        }
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
    @Transactional(rollbackFor = Exception.class)
    public void remove(PopupAd popupAd,Integer[] ids,Admin admin){
        if(ids==null || ids.length==0){
            return;
        }
        List<PopupBannerImage> popupBannerImages = this.getById(ids,popupAd.getId());
        this.remove(popupBannerImages);
    }
    @Transactional(rollbackFor = Exception.class)
    public void create( List<PopupBannerImage> popupBannerImages){
        for(PopupBannerImage popupBannerImage:popupBannerImages){
            this.create(popupBannerImage);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void create( PopupBannerImage popupBannerImage){
        this.popupBannerImageDao.insert(popupBannerImage);
    }
    @Transactional
    public List<PopupBannerImage> getById(Integer[] ids,int advertiserId){
        return this.popupBannerImageDao.getById(ids,advertiserId);
    }
    @Transactional(rollbackFor = Exception.class)
    public void remove( List<PopupBannerImage> popupBannerImages){
        for(PopupBannerImage popupBannerImage:popupBannerImages){
            this.remove(popupBannerImage);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void remove( PopupBannerImage popupBannerImages){
            this.popupBannerImageDao.delete(popupBannerImages);
    }
}