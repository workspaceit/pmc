package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.Venue;
import com.workspaceit.pmc.service.LocationService;
import com.workspaceit.pmc.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by anik on 2/6/18.
 */

@RestController
@RequestMapping("/test/api")
@CrossOrigin
public class TestRestController {

    private LocationService locationService;
    private VenueService venueService;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }
    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/location/")
    public List<Location> get() {
        List<Location> locations = locationService.getAll();
        return locations;
    }

    @GetMapping("/venue/")
    public List<Venue> getVenuesByLocation(@RequestParam("locationId") Integer locationId) {
        List<Venue> venues = venueService.getActiveVenuesByLocation(locationId);
        return venues;
    }

}
