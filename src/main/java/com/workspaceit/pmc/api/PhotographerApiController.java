package com.workspaceit.pmc.api;

import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by anik on 5/8/18.
 */

@RestController
@RequestMapping("/auth/api/photographers")
@CrossOrigin
public class PhotographerApiController {

    private PhotographerService photographerService;

    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }

    @GetMapping("/")
    public ResponseEntity<?> get() throws InterruptedException {
        List<Photographer> photographers = photographerService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(photographers);
    }

}
