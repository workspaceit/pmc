package com.workspaceit.pmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by anik on 12/21/17.
 */

@Controller
public class AuthenticationController {

    @RequestMapping(value = "/login")
    public String home(){
        return "admin/login";
    }

}
