package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.Venue;
import com.workspaceit.pmc.service.EventService;
import com.workspaceit.pmc.service.LocationService;
import com.workspaceit.pmc.service.VenueService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private EventService eventService;

    private Environment env;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }
    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }
    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events/{id}")
    public Event getEventById(@PathVariable Integer id) {
        Event event = eventService.getById(id);
        System.out.println(event.getPhotographers());
        System.out.println(event.getWatermarks());
        return event;
    }

    //anik
//    @RequestMapping(value = "/images/{file_name}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
//    public @ResponseBody byte[] getImageAsByteArray(@PathVariable("file_name") String fileName) throws IOException {
//
//        String path = this.env.getEventImagePath() + "web/" + fileName +".jpg";
//
//            File initialFile = new File(path);
//            System.out.println(path);
//            InputStream is = new FileInputStream(initialFile);
//            return IOUtils.toByteArray(is);
//
//
//
//    }




    @RequestMapping(value = "/images/{file_name}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable("file_name") String fileName) throws IOException {
        String path = this.env.getEventImagePath() + "web/" + fileName;
        System.out.println(fileName);
        byte[] imageByte = new byte[0];
        HttpHeaders headers = new HttpHeaders();
        try {
            File initialFile = new File(path);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.setContentType(MediaType.IMAGE_JPEG);
//            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=k.jpg");
            InputStream is = new FileInputStream(initialFile);
            imageByte = IOUtils.toByteArray(is);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(imageByte);
        }
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(imageByte, headers, HttpStatus.OK);
        return responseEntity;

    }

}
