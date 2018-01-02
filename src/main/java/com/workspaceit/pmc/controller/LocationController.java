package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.service.LocationService;
import com.workspaceit.pmc.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by mi_rafi on 1/1/18.
 */
@Controller
@RequestMapping(value = ControllerUriPrefix.ADMIN+"/location")
public class LocationController {
    private StateService stateService;
    private LocationService locationService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(){
        ModelAndView model = new ModelAndView("admin/location/add");
        model.addObject("states",this.stateService.getAll());
        return model;
    }
    @RequestMapping(value = "/all")
    public ModelAndView all(){
        List<Location> locations = this.locationService.getAll();
        ModelAndView model = new ModelAndView("admin/location/all");
        model.addObject("locations",locations);
        return model;
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable("id") int id){
        Location location = this.locationService.getById(id);
        if(location==null){
            return new ModelAndView("redirect:"+"/admin/location/all");
        }
        ModelAndView model = new ModelAndView("admin/location/edit");
        model.addObject("location",location);
        return model;
    }

}
