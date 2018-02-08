package com.workspaceit.pmc.api;

import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test/api/event")
@CrossOrigin
public class EventApiController {
    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping("/get-all/{limit}/{offset}")
    public ResponseEntity<?> getAll(@PathVariable("limit") int limit,@PathVariable("offset") int offset){
        List<Event> events = this.eventService.getAll(limit,offset);


        return ResponseEntity.status(HttpStatus.OK).body(events);

    }
}