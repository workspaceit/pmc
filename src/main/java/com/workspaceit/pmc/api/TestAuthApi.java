package com.workspaceit.pmc.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api/")
@CrossOrigin
public class TestAuthApi {

    @RequestMapping("/test")
    public String test(){
        return "HELLO";
    }

}


