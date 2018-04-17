package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.dao.PhotographerDao;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by mi_rafi on 12/26/17.
 */

@Controller
@RequestMapping(value = ControllerUriPrefix.ADMIN+"/photographer")
public class PhotographerController {
    PhotographerService photographerService;

    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ModelAndView allPhotographer(Authentication authentication){
        List<Photographer> photographers = this.photographerService.getAll();
        ModelAndView model = new ModelAndView("admin/photographer/all");
        model.addObject("photographers",photographers);
        return model;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPhotographer(Authentication authentication){
        return "admin/photographer/add";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public ModelAndView updatePhotographer(Authentication authentication, @PathVariable("id")Integer id){
        Photographer photographer = this.photographerService.getById(id);
        if(photographer==null){
            return new ModelAndView("redirect:"+"/admin/photographer/add");
        }
        ModelAndView model = new ModelAndView("admin/photographer/edit");
        model.addObject("photographer",photographer);
        return model;
    }

    @RequestMapping(value = "details/{id}", method = RequestMethod.GET)
    public ModelAndView photographerDetails(@PathVariable("id") Integer id){
        Photographer photographer = this.photographerService.getById(id);
        if(photographer==null){
            return new ModelAndView("redirect:"+"/admin/photographer/list");
        }
        ModelAndView model = new ModelAndView("admin/photographer/details");
        model.addObject("photographer", photographer);
        return model;
    }

}
