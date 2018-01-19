package com.workspaceit.pmc.validation.event;


import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.service.AdvertiserService;
import com.workspaceit.pmc.service.PhotographerService;
import com.workspaceit.pmc.service.VenueService;
import com.workspaceit.pmc.service.WatermarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Created by anik on 1/17/18.
 */
@Component
public class EventValidator implements Validator {

    private PhotographerService photographerService;
    private WatermarkService watermarkService;
    private AdvertiserService advertiserService;
    private VenueService venueService;

    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }
    @Autowired
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }
    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }
    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return EventForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EventForm eventForm = (EventForm) o;
        this.checkAdvertiserExistence(eventForm.getAdvertiserIds(), errors);
        this.checkPhotographerExistence(eventForm.getPhotographerIds(), errors);
        this.checkWatermarkExistence(eventForm.getWatermarkIds(), errors);
        this.checkVenueExistence(eventForm.getVenueId(), errors);
        this.checkDates(eventForm.getStartDate(), eventForm.getEndDate(), errors);
    }

    private void checkPhotographerExistence(Integer[] photographerIds, Errors errors){
        for(int photographerId:photographerIds){
            Photographer photographer =  this.photographerService.getById(photographerId);
            if(photographer == null){
                errors.rejectValue("photographerIds","Photographer is not found by id : " + photographerId);
                return;
            }
        }
    }

    private void checkWatermarkExistence(Integer[] watermarkIds, Errors errors){
        for(int watermarkId: watermarkIds){
            Watermark watermark =  this.watermarkService.getById(watermarkId);
            if(watermark == null){
                errors.rejectValue("watermarkIds","Watermark is not found by id : " + watermarkId);
                return;
            }
        }
    }

    private void checkAdvertiserExistence(Integer[] advertiserIds, Errors errors){
        for(int advertiserId: advertiserIds){
            Advertiser advertiser =  this.advertiserService.getById(advertiserId);
            if(advertiser == null){
                errors.rejectValue("watermarkIds","Advertiser is not found by id : " + advertiserId);
                return;
            }
        }
    }

    private void checkVenueExistence(Integer venueId, Errors errors){
        Venue venue =  this.venueService.getById(venueId);
        if(venue == null){
            errors.rejectValue("watermarkIds","Advertiser is not found by id : " + venueId);
        }
    }

    private void checkDates(Date startDate, Date endDate, Errors errors){
        if(startDate.after(endDate) || startDate.equals(endDate)){
            errors.rejectValue("startDate","Start date should be before end date");
        }
    }

}
