package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.LocationBackgroundImageDao;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.LocationBackgroundImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

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
        if(tmpFileTokens==null || tmpFileTokens.length==0){
            return;
        }
        for(int token:tmpFileTokens){
            String bgImg = this.fileService.copyFile(token);

            if(bgImg==null||bgImg.trim().equals(""))continue;

            LocationBackgroundImage locationBackgroundImage = new LocationBackgroundImage();
            locationBackgroundImage.setImage(bgImg);
            locationBackgroundImage.setLocationId(location.getId());

            this.create(locationBackgroundImage);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void remove(Location location,Integer[] ids){
        List<LocationBackgroundImage> bgImages = this.getById(ids,location);
        if(bgImages==null || bgImages.size()==0){
            System.out.println("No background image found by id"+ Arrays.toString(ids)+" Location id :"+location.getId());
            return;
        }
        for(LocationBackgroundImage bgImg:bgImages){
            this.delete(bgImg);
        }
    }
    public void create(LocationBackgroundImage locationBackgroundImage){
        this.locationBackgroundImageDao.insert(locationBackgroundImage);
    }
    public void delete(LocationBackgroundImage locationBackgroundImage){
        this.locationBackgroundImageDao.delete(locationBackgroundImage);
    }
    public LocationBackgroundImage getById(int id){
        return this.locationBackgroundImageDao.getById(id);
    }
    public List<LocationBackgroundImage> getById(Integer[] ids, Location location){
        return this.locationBackgroundImageDao.getById(ids,location);
    }
}