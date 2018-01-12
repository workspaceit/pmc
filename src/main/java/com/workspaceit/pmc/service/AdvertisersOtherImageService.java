package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdvertisersOtherImageDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.AdvertisersOtherImage;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi_rafi on 1/8/18.
 */
@Transactional
@Service
public class AdvertisersOtherImageService {

    private FileService fileService;
    private AdvertisersOtherImageDao advertisersOtherImageDao;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setAdvertisersOtherImageDao(AdvertisersOtherImageDao advertisersOtherImageDao) {
        this.advertisersOtherImageDao = advertisersOtherImageDao;
    }
    @Transactional(rollbackFor = Exception.class)
    public List<AdvertisersOtherImage> create(Advertiser advertiser, Integer[] tokens, Admin admin){
        List<AdvertisersOtherImage> advertisersOtherImages = new ArrayList<>();
        if(tokens == null){
            return advertisersOtherImages;
        }
        for(int token:tokens){
            String fileName = this.fileService.copyFile(token);

            AdvertisersOtherImage advertisersOtherImage = new AdvertisersOtherImage();
            advertisersOtherImage.setAdvertiserId(advertiser.getId());
            advertisersOtherImage.setImage(fileName);
            advertisersOtherImage.setCreatedBy(admin);
            advertisersOtherImages.add(advertisersOtherImage);
        }
        this.create(advertisersOtherImages);

        return advertisersOtherImages;
    }
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Advertiser advertiser, Integer[] ids){
        /**
         * Implementation is not done of
         * deleting physical image by using @TransactionalEventListener
         * */
        List<AdvertisersOtherImage> advertisersOtherImages = this.getById(advertiser.getId(),ids);
        this.delete(advertisersOtherImages);
    }
    public void create(List<AdvertisersOtherImage> advertisersOtherImages){
        for(AdvertisersOtherImage advertisersOtherImage:advertisersOtherImages){
            this.create(advertisersOtherImage);
        }
    }
    public void create(AdvertisersOtherImage advertisersOtherImage){
        this.advertisersOtherImageDao.insert(advertisersOtherImage);
    }
    public List<AdvertisersOtherImage> getById(int advertiserId,Integer[] ids){
       return this.advertisersOtherImageDao.getById(advertiserId,ids);
    }
    public void delete(List<AdvertisersOtherImage> advertisersOtherImages){
        for(AdvertisersOtherImage advertisersOtherImage:advertisersOtherImages){
            this.delete(advertisersOtherImage);
        }
    }
    public void delete(AdvertisersOtherImage advertisersOtherImage){
        this.advertisersOtherImageDao.delete(advertisersOtherImage);
    }
}