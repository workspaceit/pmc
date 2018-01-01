package com.workspaceit.pmc.controller;

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
    @Secured("ROLE_superadmin")
    @RequestMapping(value = "/add")
    public ModelAndView add(){
        ModelAndView model = new ModelAndView("admin/location/add");
        //model.addObject("photographers",photographers);
        return model;
    }
    @RequestMapping(value = "/all")
    public ModelAndView all(){
        ModelAndView model = new ModelAndView("admin/location/add");
        //model.addObject("photographers",photographers);
        return model;
    }

}
