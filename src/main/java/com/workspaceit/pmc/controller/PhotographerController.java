package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.dao.PhotographerDao;
import com.workspaceit.pmc.entity.Photographer;
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
@RequestMapping(value = "/photographer")
public class PhotographerController {
    PhotographerDao photographerDao;

    @Autowired
    public void setPhotographerDao(PhotographerDao photographerDao) {
        this.photographerDao = photographerDao;
    }
    //@Secured("ROLE_ADMIN")


    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ModelAndView allPhotographer(Authentication authentication){
        List<Photographer> photographers = photographerDao.getAll();
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
        Photographer photographer = photographerDao.getById(id);
        if(photographer==null){
            return new ModelAndView("redirect:"+"/photographer/add");
        }
        ModelAndView model = new ModelAndView("admin/photographer/edit");
        model.addObject("photographer",photographer);
        return model;
    }
}
