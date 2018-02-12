package com.workspaceit.pmc.api;

import com.workspaceit.pmc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test/api/events")
@CrossOrigin
public class EventApiController {
    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
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

    @GetMapping("/{limit}/{offset}")
    public ResponseEntity<?> getEventsByLocation(@RequestParam("locationId") Integer locationId,
                                                  @PathVariable Integer limit,
                                                 @PathVariable Integer offset) {
        Date d = new Date();
        Map<String, Object> responseData = eventService.getEventsByLocationWithCount(locationId, d, limit, offset);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

}