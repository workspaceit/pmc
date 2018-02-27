package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.Venue;
import com.workspaceit.pmc.service.EventService;
import com.workspaceit.pmc.service.LocationService;
import com.workspaceit.pmc.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anik on 2/6/18.
 */

@RestController
@RequestMapping("/test/api")
@CrossOrigin
public class TestRestController {

    private LocationService locationService;
    private VenueService venueService;
    private EventService eventService;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }
    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }
    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events/{id}")
    public Event getEventById(@PathVariable Integer id) {
        Event event = eventService.getById(id);
        System.out.println(event.getPhotographers());
        System.out.println(event.getWatermarks());
        return event;
    }

}
