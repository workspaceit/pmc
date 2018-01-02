package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.LocationBackgroundImageDao;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.LocationBackgroundImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mi_rafi on 1/2/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LocationBackgroundImageService {

    LocationBackgroundImageDao locationBackgroundImageDao;

    FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setLocationBackgroundImageDao(LocationBackgroundImageDao locationBackgroundImageDao) {
        this.locationBackgroundImageDao = locationBackgroundImageDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createFromToken(Location location,int[] tmpFileTokens){

        for(int token:tmpFileTokens){
            String bgImg = this.fileService.copyFile(token);

            if(bgImg==null||bgImg.trim().equals(""))continue;

            LocationBackgroundImage locationBackgroundImage = new LocationBackgroundImage();
            locationBackgroundImage.setImage(bgImg);
            locationBackgroundImage.setLocationId(location.getId());

            this.create(locationBackgroundImage);
        }
    }
    public void create(LocationBackgroundImage locationBackgroundImage){
        this.locationBackgroundImageDao.insert(locationBackgroundImage);
    }
}