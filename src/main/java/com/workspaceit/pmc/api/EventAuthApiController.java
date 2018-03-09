package com.workspaceit.pmc.api;

import com.workspaceit.pmc.auth.PhotographerUserDetails;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Photographer;
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
@RequestMapping("/auth/api/events")
@CrossOrigin
public class EventAuthApiController {
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

    @GetMapping("/{eventId}/details")
    public ResponseEntity<?> getEventDetails(@PathVariable("eventId") Integer eventId){
        Event event = eventService.getById(eventId);
        if(event != null) {
            Map<String, Object> eventDetails = new HashMap<>();
            eventDetails.put("event", event);
            eventDetails.put("imageCount", eventImageService.getImageCountForEvent(event));
            return ResponseEntity.status(HttpStatus.OK).body(eventDetails);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }

    @RequestMapping("/{limit}/{offset}")
    public ResponseEntity<?> getAll(@PathVariable("limit") int limit,@PathVariable("offset") int offset){
        Map<String,Object> events = this.eventService.getAllWithCountInMap(limit,offset);
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @RequestMapping("/get-count")
    public ResponseEntity<?> getCount(){
        Integer count = this.eventService.getActiveEventCount();
        Map<String,Object> events = new HashMap<>();
        events.put("count",count);
        return ResponseEntity.status(HttpStatus.OK).body(events);

    }

    @PostMapping("/{limit}/{offset}")
    public ResponseEntity<?> getEventsByLocation(@PathVariable Integer limit, @PathVariable Integer offset,
                                                 @RequestParam("locationId") Integer locationId,
                                                 @RequestParam(value = "filterDate", required = false)
                                                     @DateTimeFormat(pattern="yyyy-MM-dd") Date filterDate,
                                                 Authentication authentication) {
        Object principle = authentication.getPrincipal();
        Photographer photographer = (PhotographerUserDetails) principle;
        Map<String, Object> responseData = eventService.getEventsByCriteriaWithCount(locationId, filterDate, photographer,
                limit, offset);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

}