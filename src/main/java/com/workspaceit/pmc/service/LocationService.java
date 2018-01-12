package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.LocationDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.util.FileUtil;
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
    private FileUtil fileUtil;

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

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Transactional
    public List<Location> getAll(){
        return this.locationDao.getAll();
    }

    @Transactional
    public List<Location> getAll(Integer[] ids){
        return this.locationDao.getAll(ids);
    }

    @Transactional
    public Location getById(int id){
        return this.locationDao.getById(id);
    }
    @Transactional
    public Location getLocation(int id)throws EntityNotFound{
        Location location = this.locationDao.getById(id);
        if(location==null){
            throw new EntityNotFound("Location not found by id :"+id);
        }

        return location;
    }
    @Transactional(rollbackFor = Exception.class)
    public void insert(Location location){
        this.locationDao.insert(location);
    }

    @Transactional(rollbackFor = Exception.class)
    public Location create(LocationForm locationForm){
        Location location = getLocationFromLocationForm(locationForm);

        Integer logoImgToken = locationForm.getLogoImgToken();
        int[] bgTokens = locationForm.getBgTokens();

        String logoImgName = "";
        if(logoImgToken!=null && logoImgToken>0){
            logoImgName = this.fileService.copyFile(logoImgToken);
        }

        location.setLocationLogo(logoImgName);
        this.create(location);

        try{
            this.locationBackgroundImageService.createFromToken(location,bgTokens);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return location;
    }
    @Transactional(rollbackFor = Exception.class)
    public void create(Location location){
        this.locationDao.insert(location);
    }
    @Transactional(rollbackFor = Exception.class)
    public Location update(int id,LocationForm locationForm,Admin admin)throws EntityNotFound{
        Location location = this.getLocation(id);
        Integer logoImgToken = locationForm.getLogoImgToken();
        int[] bgTokens = locationForm.getBgTokens();
        Integer[] bgIdsToRemove = locationForm.getRemoveBgIds();


        this.populateLocationByLocationForm(location,locationForm);

        /**
         * Update location logo
         * It replace previous image in database but
         * the physical image in folder explicitly
         * */
        if(logoImgToken!=null && logoImgToken>0){
            this.fileUtil.deleteFileInCommonFolder(location.getLocationLogo());
            String logoImgName =  this.fileService.copyFile(logoImgToken);
            location.setLocationLogo(logoImgName);
        }

        this.update(location);

        /**
         * Remove back ground images
         * */
        try{
            if(bgIdsToRemove!=null && bgIdsToRemove.length>0){
                this.locationBackgroundImageService.remove(location,bgIdsToRemove);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }


        /**
         * Add back ground images
         * */
        try{
            if(bgTokens==null || bgTokens.length>0){
                this.locationBackgroundImageService.createFromToken(location,bgTokens);
            }
        }catch (Exception ex){
            ex.printStackTrace();

        }

        return location;
    }
    private void populateLocationByLocationForm(Location location, LocationForm locationForm){

        location.setName(locationForm.getName());
        location.setAddress(locationForm.getAddress());
        State currentState = (location.getState()==null) ?new State():location.getState();

        if(currentState.getId() != locationForm.getStateId()){
            State state = this.stateService.getById(locationForm.getStateId());
            location.setState(state);
        }



        location.setZip(locationForm.getZip());
        location.setPhone(locationForm.getPhone());
        location.setHasSlideshow(locationForm.getHasSlideshow());
        /**
         * Populating slid show settings
        * */
        this.setUpdateSlidShowSettings(location,locationForm);

    }
    private Location getLocationFromLocationForm(LocationForm locationForm){

        Location location = new Location();




        location.setName(locationForm.getName());
        location.setAddress(locationForm.getAddress());

        State state = this.stateService.getById(locationForm.getStateId());
        location.setState(state);

        location.setZip(locationForm.getZip());
        location.setPhone(locationForm.getPhone());
        location.setHasSlideshow(locationForm.getHasSlideshow());

        this.setSlidShowSettings(location,locationForm);


        return location;
    }
    private void setSlidShowSettings(Location location,LocationForm locationForm){
        Double breakTime = 0d;
        Double durationSpeed = 0d;
        Double fadeInTime = 0d;
        Double fadeOutTime = 0d;

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
    }
    private void setUpdateSlidShowSettings(Location location,LocationForm locationForm){

        if(locationForm.getHasSlideshow()){
            Double breakTime = locationForm.getBreakTime();
            Double durationSpeed =  locationForm.getDurationSpeed();
            Double fadeInTime = locationForm.getFadeInTime();
            Double fadeOutTime = locationForm.getFadeOutTime();

            location.setBreakTime(breakTime);
            location.setDurationSpeed(durationSpeed);
            location.setFadeInTime(fadeInTime);
            location.setFadeOutTime(fadeOutTime);
        }


    }
    @Transactional(rollbackFor = Exception.class)
    public void update(Location location){
        this.locationDao.update(location);
    }

}