package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.LocationDao;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.validation.location.LocationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LocationService {
    private LocationDao locationDao;
    private StateService stateService;

    @Autowired
    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(Location location){
        this.locationDao.insert(location);
    }
    @Transactional(rollbackFor = Exception.class)
    public Location create(LocationForm locationForm){
        Location location = new Location();
        location.setName(locationForm.getName());
        location.setAddress(locationForm.getAddress());

        State state = this.stateService.getById(locationForm.getStateId());
        location.setState(state);

        location.setZip(locationForm.getZip());
        location.setPhone(locationForm.getPhone());

        location.setLocationLogo(locationForm.getLocationLogo());

        location.setHasSlideshow(locationForm.getHasSlideshow());
        location.setBreakTime(locationForm.getBreakTime());
        location.setDurationSpeed(locationForm.getDurationSpeed());
        location.setFadeInTime(locationForm.getFadeInTime());
        location.setFadeOutTime(locationForm.getFadeOutTime());

        this.locationDao.insert(location);
        return location;
    }

}