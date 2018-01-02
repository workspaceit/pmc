package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.LocationDao;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.LocationBackgroundImage;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.validation.location.LocationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LocationService {
    private LocationDao locationDao;
    private StateService stateService;
    private FileService fileService;
    private  LocationBackgroundImageService locationBackgroundImageService;

    @Autowired
    protected void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Autowired
    protected void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    protected void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    protected void setLocationBackgroundImageService(LocationBackgroundImageService locationBackgroundImageService) {
        this.locationBackgroundImageService = locationBackgroundImageService;
    }
    @Transactional(rollbackFor = Exception.class)
    public List<Location> getAll(){
        return this.locationDao.getAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Location getById(int id){
        return this.locationDao.getById(id);
    }
    @Transactional(rollbackFor = Exception.class)
    public void insert(Location location){
        this.locationDao.insert(location);
    }

    @Transactional(rollbackFor = Exception.class)
    public Location create(LocationForm locationForm){
        Location location = new Location();
        Double breakTime = 0d;
        Double durationSpeed = 0d;
        Double fadeInTime = 0d;
        Double fadeOutTime = 0d;


        location.setName(locationForm.getName());
        location.setAddress(locationForm.getAddress());

        State state = this.stateService.getById(locationForm.getStateId());
        location.setState(state);

        location.setZip(locationForm.getZip());
        location.setPhone(locationForm.getPhone());
        location.setHasSlideshow(locationForm.getHasSlideshow());


        if(locationForm.getHasSlideshow()){
            breakTime = locationForm.getBreakTime();
            durationSpeed =  locationForm.getDurationSpeed();
            fadeInTime = locationForm.getFadeInTime();
            fadeOutTime = locationForm.getFadeOutTime();
        }

        location.setBreakTime(breakTime);
        location.setDurationSpeed(durationSpeed);
        location.setFadeInTime(fadeInTime);
        location.setFadeOutTime(fadeOutTime);

        int logoImgToken = locationForm.getLogoImgToken();

        int[] bgTokens = locationForm.getBgTokens();

        String logoImgName = this.fileService.copyFile(logoImgToken);
        location.setLocationLogo(logoImgName);
        this.create(location);

        try{
            this.locationBackgroundImageService.createFromToken(location,bgTokens);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return location;
    }
    public void create(Location location){
        this.locationDao.insert(location);
    }

}