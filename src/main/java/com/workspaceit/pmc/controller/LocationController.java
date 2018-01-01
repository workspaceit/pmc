package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Controller
@RequestMapping(value = "/location")
public class LocationController {
    private StateService stateService;


    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Secured("ROLE_superadmin")
    @RequestMapping(value = "/add")
    public ModelAndView add(){
        ModelAndView model = new ModelAndView("admin/location/add");
        model.addObject("states",this.stateService.getAll());
        return model;
    }
    @RequestMapping(value = "/all")
    public ModelAndView all(){
        ModelAndView model = new ModelAndView("admin/location/add");
        //model.addObject("photographers",photographers);
        return model;
    }

}
