package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

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

    /** For Location modal*/
    private List<Double> fadeInList;
    private List<Double>fadeOutList;

    private Set<Integer> durations;


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

    @Autowired
    public void setFadeInList(List<Double> fadeInList) {
        this.fadeInList = fadeInList;
    }

    @Autowired
    public void setFadeOutList(List<Double> fadeOutList) {
        this.fadeOutList = fadeOutList;
    }

    @Autowired
    @Qualifier("durationList")
    public void setDurations(Set<Integer> durations) {
        this.durations = durations;
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

        /*For location Modal Page*/
        model.addObject("fadeInList",this.fadeInList);
        model.addObject("fadeOutList",this.fadeOutList);
        model.addObject("durations",durations);

        return model;
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable("id") int id){
        Event event = this.eventService.getById(id);
        List<Location> locations = this.locationService.getAll();
        ModelAndView model = new ModelAndView("admin/event/edit");
        model.addObject("event", event);
        model.addObject("locations",locations);

        /*For location Modal Page*/
        model.addObject("fadeInList",this.fadeInList);
        model.addObject("fadeOutList",this.fadeOutList);
        model.addObject("durations",durations);

        return model;
    }

}
