package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.dao.PhotographerDao;
import com.workspaceit.pmc.entity.Photographer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPhotographer(Authentication authentication){
        return "admin/photographer/add";
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ModelAndView allPhotographer(Authentication authentication){
        List<Photographer> photographers = photographerDao.getAll();
        ModelAndView model = new ModelAndView("admin/photographer/all");
        model.addObject("photographers",photographers);
        return model;
    }
}
