package com.workspaceit.pmc.api;

import com.workspaceit.pmc.auth.PhotographerUserDetails;
import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.EventImageService;
import com.workspaceit.pmc.service.EventService;
import com.workspaceit.pmc.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(ControllerUriPrefix.PUBLIC_API+"/events")
@CrossOrigin
public class EventApiController {
    private EventService eventService;
    private PhotographerService photographerService;
    private EventImageService eventImageService;


    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }
    @Autowired
    public void setEventImageService(EventImageService eventImageService) {
        this.eventImageService = eventImageService;
    }

    @GetMapping("/get/{eventId}")
    public ResponseEntity<?> getById(@PathVariable("eventId") Integer eventId){
        Event event;
        try {
            event = eventService.getEvent(eventId);
        } catch (EntityNotFound entityNotFound) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(entityNotFound.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(event);

    }


}