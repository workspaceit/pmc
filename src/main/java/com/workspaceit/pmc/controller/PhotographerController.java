package com.workspaceit.pmc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mi_rafi on 12/26/17.
 */
@Controller
@RequestMapping(value = "/photographer")
public class PhotographerController {
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPhotographer(Authentication authentication){
        return "admin/photographer/add";
    }
}
