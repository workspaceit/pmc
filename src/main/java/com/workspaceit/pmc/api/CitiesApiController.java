package com.workspaceit.pmc.api;

import com.workspaceit.pmc.entity.City;
import com.workspaceit.pmc.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anik on 2/12/18.
 */

@RestController
@RequestMapping("/api/cities")
@CrossOrigin
public class CitiesApiController {
    CityService cityService;

    @Autowired
    public void setEventService(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getByZip/{zipId}")
   public List<City> getCitiesByZipCode(@PathVariable Integer zipId){
        return cityService.getAllCityByZip(zipId);
    }

}
