package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.Venue;
import com.workspaceit.pmc.service.LocationService;
import com.workspaceit.pmc.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/location/{limit}/{offset}/")
    public Map<String, Object> get(@PathVariable int limit, @PathVariable int offset) {
        List<Location> locations = locationService.getAll(limit, offset);
        Map<String, Object> res = new HashMap<>();
        res.put("count", locationService.getLocationCount());
        res.put("locations", locations);
        return res;
    }

    @GetMapping("/venue/")
    public List<Venue> getVenuesByLocation(@RequestParam("locationId") Integer locationId) {
        List<Venue> venues = venueService.getActiveVenuesByLocation(locationId);
        return venues;
    }

}
