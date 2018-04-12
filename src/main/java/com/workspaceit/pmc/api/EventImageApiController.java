package com.workspaceit.pmc.api;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.entity.ReportedImage;
import com.workspaceit.pmc.entity.SentSlideshow;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.*;
import com.workspaceit.pmc.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if(reportImageService.isReported(eventImage)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ServiceResponse.getInstance()
                    .setValidationError("image","Image already been reported"));
        }
        reportImageService.reportImage(eventImage);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping("/reported-image/{eventId}")
    public ResponseEntity<?> reportedImage(@PathVariable("eventId") Integer eventId){
        List<ReportedImage> list =reportImageService.getAllByEventId(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/report-image-action")
    public ResponseEntity<?> reportedImageAction(@RequestParam("type") String type,@RequestParam("imageIds") int[] imageIds){
        boolean result=false;
        if(type.equals("ignore")){
            result=reportImageService.takeAction(imageIds);
        }else if(type.equals("delete")){
            try {
                Boolean res = eventImageService.deleteEventImages(imageIds);
            } catch (EntityNotFound entityNotFound) {
                entityNotFound.printStackTrace();
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(false);

            }
            result=reportImageService.takeAction(imageIds);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/get-by-event-id-where-is-sent-slide-show-true/{eventId}")
    public ResponseEntity<?> getAllEventImagesSentSlideShowByEventId(@PathVariable("eventId") int eventId){
        List<EventImage> eventImages =  this.eventImageService.getImagesByEventIdWhereInSlideshowTrue(eventId);
        System.out.println("total images:" + eventImages.size());
        return ResponseEntity.status(HttpStatus.OK).body(eventImages);
    }


}
