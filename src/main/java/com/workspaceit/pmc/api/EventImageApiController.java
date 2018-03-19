package com.workspaceit.pmc.api;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.entity.SentSlideshow;
import com.workspaceit.pmc.service.*;
import com.workspaceit.pmc.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by anik on 2/15/18.
 */

@RestController
@RequestMapping(ControllerUriPrefix.PUBLIC_API+"/event-images")
@CrossOrigin
public class EventImageApiController {


    private SentSlideShowService sentSlideShowService;
    private EventImageService eventImageService;
    private ReportImageService reportImageService;

    @Autowired
    public void setReportImageService(ReportImageService reportImageService) {
        this.reportImageService = reportImageService;
    }

    @Autowired
    public void setEventImageService(EventImageService eventImageService) {
        this.eventImageService = eventImageService;
    }

    @Autowired
    public void setSentSlideShowService(SentSlideShowService sentSlideShowService) {
        this.sentSlideShowService = sentSlideShowService;
    }



    @GetMapping("/get/{identifier}")
    public ResponseEntity<?> getAllEventImagesSentSlideShowIdentifier(@PathVariable("identifier") String identifier){
        SentSlideshow sentSlideShow =  this.sentSlideShowService.getByIdentifier(identifier);

        if(sentSlideShow==null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ServiceResponse.getInstance()
                    .setValidationError("identifier","No sent slide show found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(sentSlideShow.getEventImages());
    }

    @PostMapping("/report-image")
    public ResponseEntity<?> reportEventImage(@RequestParam("eventImageId") Integer eventImageId){
        EventImage eventImage = eventImageService.getById(eventImageId);
        if(eventImage == null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ServiceResponse.getInstance()
                    .setValidationError("image","Image not found"));
        }
        reportImageService.reportImage(eventImage);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }



}
