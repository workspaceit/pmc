package com.workspaceit.pmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by anik on 11/13/17.
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(){
        return "admin/home";
    }
    
}
