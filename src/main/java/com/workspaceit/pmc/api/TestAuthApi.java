package com.workspaceit.pmc.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/api")
public class TestAuthApi {


    @RequestMapping("/test")
    public String test(){
        return "HELLO";
    }
}