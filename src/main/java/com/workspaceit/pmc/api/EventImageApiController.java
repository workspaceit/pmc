package com.workspaceit.pmc.api;

import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.service.EventImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by anik on 2/15/18.
 */

@RestController
@RequestMapping("/test/api/event-images")
@CrossOrigin
public class EventImageApiController {

    private EventImageService eventImageService;
    @Autowired
    public void setEventImageService(EventImageService eventImageService) {
        this.eventImageService = eventImageService;
    }

    @PostMapping("/{limit}/{offset}")
    public ResponseEntity<?> getEventImagesByCriteria(@PathVariable("limit") Integer limit,
                                                      @PathVariable("offset") Integer offset,
                                                      @RequestParam(value = "eventId") Integer eventId){
        List<EventImage> eventImages = eventImageService.getEventImagesByCriteria(eventId, limit, offset);
        return ResponseEntity.status(HttpStatus.OK).body(eventImages);
    }

}
