package com.workspaceit.pmc.api;

import com.workspaceit.pmc.auth.PhotographerUserDetails;
import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.FILE;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.helper.EmailHelper;
import com.workspaceit.pmc.helper.FileHelper;
import com.workspaceit.pmc.helper.SmsHelper;
import com.workspaceit.pmc.service.EventImageService;
import com.workspaceit.pmc.service.EventService;
import com.workspaceit.pmc.service.SentSlideShowService;
import com.workspaceit.pmc.service.WatermarkService;
import com.workspaceit.pmc.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by anik on 2/15/18.
 */

@RestController
@RequestMapping(ControllerUriPrefix.AUTH_API+"/event-images")
@CrossOrigin
public class EventImageApiController {

    private Set<String> imgAllowedMimeType;

    private EventImageService eventImageService;
    private WatermarkService watermarkService;
    private SentSlideShowService sentSlideShowService;
    @Autowired
    EmailHelper emailHelper;
    @Autowired
    SmsHelper smsHelper;
    @Autowired
    public void setSentSlideShowService(SentSlideShowService sentSlideShowService) {
        this.sentSlideShowService = sentSlideShowService;
    }
    @Autowired
    public void setEventImageService(EventImageService eventImageService) {
        this.eventImageService = eventImageService;
    }
    private EventService eventService;
    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
    @Autowired
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }

    @PostConstruct
    private void initConfiguration(){
        imgAllowedMimeType = new HashSet<>();
        /**
         * Image mime type
         * */
        imgAllowedMimeType.add("image/jpeg");
        imgAllowedMimeType.add("image/pjpeg");
        imgAllowedMimeType.add("image/jpeg");
        imgAllowedMimeType.add("image/png");
    }

   /* @PostMapping("/{identifier}")
    public ResponseEntity<?> getAllEventImagesByCriteria(@PathVariable("identifier") Integer identifier){
        List<EventImage> eventImages = eventImageService.getEventImagesByCriteria(eventId, limit, offset, false);
        return ResponseEntity.status(HttpStatus.OK).body(eventImages);
    }

    @PostMapping("/{limit}/{offset}/in-slideshow")
    public ResponseEntity<?> getEventImagesInSlideshow(@PathVariable("limit") Integer limit,
                                                      @PathVariable("offset") Integer offset,
                                                      @RequestParam(value = "eventId") Integer eventId){
        List<EventImage> eventImages = eventImageService.getEventImagesByCriteria(eventId, limit, offset, true);
        return ResponseEntity.status(HttpStatus.OK).body(eventImages);
    }*/


}
