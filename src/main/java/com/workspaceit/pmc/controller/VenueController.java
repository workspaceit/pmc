package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.Venue;
import com.workspaceit.pmc.service.LocationService;
import com.workspaceit.pmc.service.StateService;
import com.workspaceit.pmc.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Tomal on 1/6/2018.
 */
@Controller
@RequestMapping(value = ControllerUriPrefix.ADMIN+"/venue")
public class VenueController {
    private LocationService locationService;
    private VenueService venueService;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    @RequestMapping(value = "/add")
      public ModelAndView add(){
            List<Location> locations = this.locationService.getAll();
            ModelAndView model = new ModelAndView("admin/venue/add");
            model.addObject("locations",locations);
            return model;
    }

    @RequestMapping(value = "/all")
    public ModelAndView all(){
        List<Venue> venues = this.venueService.getAll();
        ModelAndView model = new ModelAndView("admin/venue/all");
        model.addObject("venues",venues);
        return model;
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable("id") int id){
        Venue venue  = this.venueService.getById(id);
        List<Location> locations = this.locationService.getAll();
        ModelAndView model = new ModelAndView("admin/venue/edit");
        model.addObject("venue",venue);
        model.addObject("locations",locations);
        return model;
    }
}
