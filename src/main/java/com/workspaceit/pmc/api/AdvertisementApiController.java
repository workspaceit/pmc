package com.workspaceit.pmc.api;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.AdvertisementService;
import com.workspaceit.pmc.service.AdvertiserService;
import com.workspaceit.pmc.service.EventService;
import com.workspaceit.pmc.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/test"+ControllerUriPrefix.AUTH_API+"/pmv-adv")
public class AdvertisementApiController {

    private EventService eventService;
    private AdvertiserService advertiserService;
    private AdvertisementService advertisementService;

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

    @RequestMapping("/get/{eventId}")
    public ResponseEntity<?> getAdvertisement(@PathVariable("eventId")int eventId){
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        Event event;
        Location location;
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


        List<Advertiser> advertiserList = this.advertiserService.getByEventAndLocationId(event.getId(),locationId,true);

        List<Integer> advertiserIds = new ArrayList<>();
        if(advertiserList!=null){
            advertiserList.stream().forEach(advertiser -> advertiserIds.add(advertiser.getId()));
        }

        List<Map<ADVERTISEMENT_TYPE,Advertisement>> advertisementList = this.advertisementService.getMapByAdvertiserId(advertiserIds);
        return ResponseEntity.ok(advertisementList);
    }


}
