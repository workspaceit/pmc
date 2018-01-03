package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.City;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.service.CityService;
import com.workspaceit.pmc.service.EventService;
import com.workspaceit.pmc.service.LocationService;
import com.workspaceit.pmc.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by mi_rafi on 1/3/18.
 */
@Controller
@RequestMapping(ControllerUriPrefix.ADMIN+"/advertiser")
public class AdvertiserController {
    private LocationService locationService;
    private StateService stateService;
    private CityService cityService;
    private EventService eventService;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        List<Location> locations = this.locationService.getAll();
        List<State> states = this.stateService.getAll();
        List<City> cities = this.cityService.getAllNameAcs();
        List<Event> events = this.eventService.getAll();

        ModelAndView model = new ModelAndView("admin/advertiser/add");

        model.addObject("events",events);
        model.addObject("locations",locations);
        model.addObject("states",states);
        model.addObject("cities",cities);

        return model;
    }

}