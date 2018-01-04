package com.workspaceit.pmc.validation.advertiser;

import com.workspaceit.pmc.entity.City;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Created by mi_rafi on 12/28/17.
 */
@Component
public class AdvertiserValidator implements Validator {

    private PhotographerService photographerService;
    private LocationService locationService;
    private CityService cityService;
    private StateService stateService;
    private EventService eventService;


    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AdvertiserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        AdvertiserForm advertiserForm = (AdvertiserForm)obj;

        this.checkCityExistence(advertiserForm.getCityId(),errors);
        this.checkStateExistence(advertiserForm.getStateId(),errors);

        if(advertiserForm.getIsAllLocationSelected()){
            this.checkLocationExistence(advertiserForm.getLocationIds(),errors);
        }
        if(advertiserForm.getIsAllEventSelected()){
            this.checkEventExistence(advertiserForm.getEventIds(),errors);
        }

    }
    private void checkCityExistence(Integer cityId, Errors errors){
        if(cityId==null || cityId==-1){ // -1 Represents All
            return;
        }

        City city =  this.cityService.getById(cityId);
        if( city == null ){
            errors.rejectValue("city","City not found by id : "+cityId);
        }
    }
    private void checkStateExistence(Integer stateId, Errors errors){
        if(stateId==null || stateId==-1){
            return;
        }

        State state =  this.stateService.getById(stateId);
        if( state == null ){
            errors.rejectValue("state","State not found by id : "+stateId);
        }
    }
    private void checkLocationExistence(Integer[] locationIds, Errors errors){
        if(locationIds==null){
            return;
        }
        for(int locationId:locationIds){
            State state =  this.stateService.getById(locationId);
            if( state == null ){
                errors.rejectValue("location","State not found by id : "+locationId);
                return;
            }

        }

    }
    private void checkEventExistence(Integer[] eventIds, Errors errors){
        if(eventIds==null){
            return;
        }
        for(int eventId:eventIds){
            Event event =  this.eventService.getById(eventId);
            if( event == null ){
                errors.rejectValue("event","Event not found by id : "+eventId);
                return;
            }

        }

    }
}