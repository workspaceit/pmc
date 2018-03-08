package com.workspaceit.pmc.api;

import com.workspaceit.pmc.entity.Location;
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
@RequestMapping("/auth/api/locations")
@CrossOrigin
public class LocationAuthApiController {
    LocationService locationService;
    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{locationId}")
    public Location getLocationById(@PathVariable Integer locationId){
        return locationService.getById(locationId);
    }

    @GetMapping("/{limit}/{offset}/")
    public ResponseEntity<?> get(@PathVariable int limit, @PathVariable int offset) throws InterruptedException {
//        Thread.sleep(5000);
        List<Location> locations = locationService.getActiveLocations(limit, offset);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("count", locationService.getLocationCount());
        responseData.put("locations", locations);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

}
