package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.entity.Admin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by anik on 11/13/17.
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/ss")
    public String home(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin)auth;
        return "admin/home";
    }
    
}
