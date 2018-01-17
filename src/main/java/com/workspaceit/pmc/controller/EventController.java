package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.Venue;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.service.PhotographerService;
import com.workspaceit.pmc.service.VenueService;
import com.workspaceit.pmc.service.WatermarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by anik on 1/15/18.
 */

@Controller
@RequestMapping(value = ControllerUriPrefix.ADMIN+"/event")
public class EventController {

    @Autowired
    VenueService venueService;
    @Autowired
    PhotographerService photographerService;
    @Autowired
    WatermarkService watermarkService;

    @RequestMapping(value = "/add")
    public ModelAndView add(){
        List<Venue> venues = venueService.getAll();
        List<Photographer> photographers = photographerService.getAll();
        List<Watermark> watermarks = watermarkService.getAll();
        ModelAndView model = new ModelAndView("admin/event/add");
        model.addObject("venues", venues);
        model.addObject("photographers", photographers);
        model.addObject("watermarks", watermarks);
        return model;
    }

}
