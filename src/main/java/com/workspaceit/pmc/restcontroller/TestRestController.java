package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


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
    private Environment env;
    private EventImageService eventImageService;
    private WatermarkService watermarkService;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

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
    @Autowired
    public void setEventImageService(EventImageService eventImageService) {
        this.eventImageService = eventImageService;
    }
    @Autowired
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }

    @GetMapping("/events/{id}")
    public Event getEventById(@PathVariable Integer id) {
        Event event = eventService.getById(id);
        System.out.println(event.getPhotographers());
        System.out.println(event.getWatermarks());
        return event;
    }
    @RequestMapping(value = "/monthwise-event-image-count")
    private ResponseEntity<?> getMonthWiseEventImageCount(){
        List<Integer> monthdata = new ArrayList<Integer>();
        System.out.println("adfsdas---------d");
        System.out.println(this.eventImageService);
        monthdata = this.eventImageService.getMonthWiseEventImageCount();
        return ResponseEntity.status(HttpStatus.OK).body(monthdata);
    }


//    anik


}
