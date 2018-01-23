package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by anik on 1/15/18.
 */

@Controller
@RequestMapping(value = ControllerUriPrefix.ADMIN+"/event")
public class EventController {


    private VenueService venueService;
    private PhotographerService photographerService;
    private WatermarkService watermarkService;
    private EventService eventService;
    private LocationService locationService;
    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }
    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }
    @Autowired
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }
    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(){
        List<Location> locations = this.locationService.getAll();
        ModelAndView model = new ModelAndView("admin/event/add");
        model.addObject("locations",locations);
        return model;
    }

    @RequestMapping(value = "/all")
    public ModelAndView all(){
        List<Event> events = this.eventService.getAll();
        ModelAndView model = new ModelAndView("admin/event/all");
        model.addObject("events", events);
        return model;
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable("id") int id){
        Event event = this.eventService.getById(id);
        ModelAndView model = new ModelAndView("admin/event/edit");
        model.addObject("event", event);
        return model;
    }

}
