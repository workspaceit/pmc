package com.workspaceit.pmc.api;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anik on 2/12/18.
 */

@RestController
@RequestMapping(ControllerUriPrefix.PUBLIC_API+"/locations")
@CrossOrigin
public class LocationApiController {
    LocationService locationService;
    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/get/{locationId}")
    public ResponseEntity<?> getLocationById(@PathVariable Integer locationId){
        Location location;

        try {
            location = locationService.getLocation(locationId);
        } catch (EntityNotFound entityNotFound) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(entityNotFound.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(location);
    }



}
