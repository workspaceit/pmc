package com.workspaceit.pmc.api;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.SentSlideshow;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.AdvertisementService;
import com.workspaceit.pmc.service.AdvertiserService;
import com.workspaceit.pmc.service.EventService;
import com.workspaceit.pmc.service.SentSlideShowService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(ControllerUriPrefix.PUBLIC_API+"/pmv-adv")
@CrossOrigin
public class AdvertisementApiController {

    private EventService eventService;
    private AdvertiserService advertiserService;
    private AdvertisementService advertisementService;
    private ValidationUtil validationUtil;
    private SentSlideShowService sentSlideShowService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @Autowired
    public void setAdvertisementService(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }


    @Autowired
    public void setValidationUtil(ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setSentSlideShowService(SentSlideShowService sentSlideShowService) {
        this.sentSlideShowService = sentSlideShowService;
    }

    @RequestMapping("/get/{eventId}/{limit}/{offset}")
    public ResponseEntity<?> getAdvertisement(@PathVariable("eventId")int eventId,
                                            @PathVariable("limit") int limit,
                                            @PathVariable("offset") int offset){
        Event event;
        Location location;
        ServiceResponse serviceResponse = this.validationUtil.limitOffsetValidation(limit,offset,10);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        int locationId = 0;
        try {
            event =  this.eventService.getEvent(eventId);

            location = event.getLocation();
            locationId = (location!=null)?location.getId():0;

        } catch (EntityNotFound entityNotFound) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(serviceResponse
                            .setMsg("eventId",entityNotFound.getMessage())
                            .getFormError());

        }


        List<Advertiser> advertiserList = this.advertiserService.getByEventAndLocationId(event.getId(),
                                                                                        locationId,true,
                                                                                        limit,offset);

        List<Integer> advertiserIds = new ArrayList<>();
        if(advertiserList!=null){
            advertiserList.stream().forEach(advertiser -> advertiserIds.add(advertiser.getId()));
        }

        List<Map<ADVERTISEMENT_TYPE,Advertisement>> advertisementList = this.advertisementService.getMapByAdvertiserId(advertiserIds);
        return ResponseEntity.ok(advertisementList);
    }

    @RequestMapping("/get-by-advertiser-id/{type}/{advertiserId}")
    public ResponseEntity<?> getAdvertisementByAdvertiserId(@PathVariable("advertiserId") int advertiserId,
                                                            @PathVariable("type") String type){

        ADVERTISEMENT_TYPE adType = null;

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        try {
            adType = ADVERTISEMENT_TYPE.getFromString(type);

        } catch (EntityNotFound entityNotFound) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(serviceResponse
                            .setMsg("eventId",entityNotFound.getMessage())
                            .getFormError());

        }

        Advertisement advertisementList = this.advertisementService.getByAdvertiserIdAndType(advertiserId,adType);
        return ResponseEntity.ok(advertisementList);
    }
    @RequestMapping("/get/{type}/{identifier}/{limit}/{offset}")
    public ResponseEntity<?> getAdvertisementByEventIdAndType(@PathVariable("identifier")String identifier,
                                                              @PathVariable("type") String type,
                                                              @PathVariable("limit") int limit,
                                                              @PathVariable("offset") int offset){

        ADVERTISEMENT_TYPE adType = null;

        ServiceResponse serviceResponse = this.validationUtil.limitOffsetValidation(limit,offset,10);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        List<Advertiser> advertiserList;
        try {
            SentSlideshow sentSlideshow = this.sentSlideShowService.getByIdentifier(identifier);
            if(sentSlideshow == null){
                throw new EntityNotFound("No sent show found by identifier :"+identifier);
            }
            adType = ADVERTISEMENT_TYPE.getFromString(type);
            advertiserList = this.advertiserService.getByEventAndLocationId(sentSlideshow.getEvent().getId(),true,
                    limit,offset);

        } catch (EntityNotFound entityNotFound) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(serviceResponse
                            .setValidationError("eventId",entityNotFound.getMessage())
                            .getFormError());

        }

        List<Integer> advertiserIds = new ArrayList<>();
        if(advertiserList!=null && advertiserList.size()>0){
            advertiserList.stream().forEach(advertiser -> advertiserIds.add(advertiser.getId()));
        }

        List<Advertisement> advertisementList = this.advertisementService.getByAdvertiserIdAndType(advertiserIds,adType);

        return ResponseEntity.ok(advertisementList);
    }
    @RequestMapping("/get/{advertisementId}")
    public ResponseEntity<?> getAdvertisementById(@PathVariable("advertisementId")int advertisementId){


        ServiceResponse serviceResponse = ServiceResponse.getInstance();


        Advertisement advertisement = this.advertisementService.getById(advertisementId);
        if(advertisement==null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(serviceResponse
                            .setValidationError("id","No Advertisement found by id :"+advertisementId)
                            .getFormError());
        }


        return ResponseEntity.ok(advertisement);
    }
}
