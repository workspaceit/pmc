package com.workspaceit.pmc.api;

import com.workspaceit.pmc.auth.PhotographerUserDetails;
import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.FILE;
import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.helper.EmailHelper;
import com.workspaceit.pmc.helper.FileHelper;
import com.workspaceit.pmc.helper.SmsHelper;
import com.workspaceit.pmc.service.*;
import com.workspaceit.pmc.util.ServiceResponse;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class EventImageAuthApiController {

    private Set<String> imgAllowedMimeType;

    private EventImageService eventImageService;
    private WatermarkService watermarkService;
    private SentSlideShowService sentSlideShowService;
    private ReportImageService reportImageService;

    @Autowired
    public void setReportImageService(ReportImageService reportImageService) {
        this.reportImageService = reportImageService;
    }
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

    @PostMapping("/{limit}/{offset}")
    public ResponseEntity<?> getAllEventImagesByCriteria(@PathVariable("limit") Integer limit,
                                                      @PathVariable("offset") Integer offset,
                                                      @RequestParam(value = "eventId") Integer eventId){
        List<EventImage> eventImages = eventImageService.getEventImagesByCriteria(eventId, limit, offset, false);
        return ResponseEntity.status(HttpStatus.OK).body(eventImages);
    }

    @PostMapping("/{limit}/{offset}/in-slideshow")
    public ResponseEntity<?> getEventImagesInSlideshow(@PathVariable("limit") Integer limit,
                                                      @PathVariable("offset") Integer offset,
                                                      @RequestParam(value = "eventId") Integer eventId){
        List<EventImage> eventImages = eventImageService.getEventImagesByCriteria(eventId, limit, offset, true);
        return ResponseEntity.status(HttpStatus.OK).body(eventImages);
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<?> uploadEventPhotos(@PathVariable("eventId") Integer eventId,
                                               @RequestParam("file") MultipartFile multipartFile,
                                               Authentication authentication){

        Object principle = authentication.getPrincipal();
        Photographer photographer = (PhotographerUserDetails) principle;
        long fileSizeLimit = FileHelper.getMBtoByte(30);
        Set<String> imgContentType = this.imgAllowedMimeType;
        return validateAndProcessMultipartFile("file",fileSizeLimit,multipartFile,imgContentType,photographer,eventId);
    }

    private ResponseEntity<?> validateAndProcessMultipartFile(String param,long fileSizeLimit,MultipartFile file,
                                                              Set<String> imgContentTypes,Photographer photographer,
                                                              Integer eventId){
        String mimeType = FileHelper.getMimeType(file);
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        if(!imgContentTypes.contains(mimeType)) {
            serviceResponse.setValidationError(param," Mime Type "+ mimeType+" not allowed");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        if(file==null || file.getSize()==0){
            serviceResponse.setValidationError(param,"No file receive");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        if(file.getSize()>fileSizeLimit){
            serviceResponse.setValidationError(param,"File size exceeds. Max size "+FileHelper.getByteToMb(fileSizeLimit));
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        Map<FILE,String> fileInfo;
        EventImage eventImage = new EventImage();
        try {
            fileInfo = eventImageService.saveEventImageFile(file);
            String fileName = fileInfo.get(FILE.NAME);
            eventImage.setActive(true);
            eventImage.setImage(fileName);
            eventImage.setCreatedBy(photographer);
            Event event = eventService.getById(eventId);
            eventImage.setEvent(event);
            eventImage.setInSlideshow(false);
            eventImage.setDeleted(false);
            eventImageService.saveEventImage(eventImage);

        } catch(IOException e) {
            serviceResponse.setValidationError(param,"Internal server error : "+e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.OK).body(eventImage);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> removeEventImages(@RequestParam("imageIds") int[] imageIds,Authentication authentication){
        Object principle = authentication.getPrincipal();
        Photographer photographer = (PhotographerUserDetails) principle;
        try {
            boolean ownership = eventImageService.checkOwnerShipOfImages(imageIds,photographer);
            if(!ownership){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("You don't have access to delete some of the images");
            }
            boolean result = eventImageService.deleteEventImages(imageIds);
            if(!result){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
            }
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch (EntityNotFound entityNotFound){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Entity Not found");
        }
    }

    @PostMapping("/add-watermark")
    public ResponseEntity<?> addWatermark(@RequestParam("imageIds") List<Integer> imageIds,
                                               @RequestParam("watermarkId") Integer watermarkId,
                                               Authentication authentication){
        Object principle = authentication.getPrincipal();
        Photographer photographer = (PhotographerUserDetails) principle;
        Watermark watermark = watermarkService.getById(watermarkId);
        try {
            if(watermark == null){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("No watermark found");
            }
            boolean ownership = eventImageService.photographerAssignedOnEvent(imageIds, photographer);
            if(!ownership){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("You don't have permission to do this action");
            }
            boolean result = eventImageService.addWatermark(imageIds, watermark);
            if(!result){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
            }
            List<EventImage> eventImages = eventImageService.getImagesByIds(imageIds);
            return ResponseEntity.status(HttpStatus.OK).body(eventImages);
        }catch (EntityNotFound entityNotFound){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Entity Not found");
        }
    }

    @PostMapping("/remove-watermark")
    public ResponseEntity<?> removeWatermark(@RequestParam("imageIds") List<Integer> imageIds,
                                               Authentication authentication){
        Object principle = authentication.getPrincipal();
        Photographer photographer = (PhotographerUserDetails) principle;
        try {
            boolean ownership = eventImageService.photographerAssignedOnEvent(imageIds, photographer);
            if(!ownership){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("You don't have permission to do this action");
            }
            boolean result = eventImageService.removeWatermark(imageIds);
            if(!result){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
            }
            List<EventImage> eventImages = eventImageService.getImagesByIds(imageIds);
            return ResponseEntity.status(HttpStatus.OK).body(eventImages);
        }catch (EntityNotFound entityNotFound){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Entity Not found");
        }
    }
    @PostMapping("/send-to-slideshow")
    public ResponseEntity<?> sendImagesToSlideShow(@RequestParam("imageIds") List<Integer> imageIds){
        boolean result = eventImageService.sendImagesToSlideShow(imageIds);
        if(!result){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
        }
        List<EventImage> eventImages = eventImageService.getImagesByIds(imageIds);
        return ResponseEntity.status(HttpStatus.OK).body(eventImages);
    }

    @PostMapping("/remove-from-slideshow")
    public ResponseEntity<?> removeImagesFromSlideShow(@RequestParam("imageIds") List<Integer> imageIds){
        boolean result = eventImageService.removeImagesFromSlideShow(imageIds);
        if(!result){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
        }
        List<EventImage> eventImages = eventImageService.getImagesByIds(imageIds);
        return ResponseEntity.status(HttpStatus.OK).body(eventImages);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendImagesViaEmail(@RequestParam("imageIds") int[] imageIds,
                                                @RequestParam("customerName") String customerName,
                                                @RequestParam("email") String email,
                                                @RequestParam("phoneNumber") String phoneNumber,
                                                @RequestParam("message") String message,
                                                @RequestParam("eventId") int eventId,Authentication authentication){
        Object principle = authentication.getPrincipal();
        Photographer photographer = (PhotographerUserDetails) principle;
        Event event = eventService.getById(eventId);
        SentSlideshow sentSlideshowEmail = null;
        SentSlideshow sentSlideshowSms = null;
        Boolean resultEmail = false;
        Boolean resultSms = false;
        if(email.isEmpty() && phoneNumber.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Please provide email or phone number");
        }
        if(!email.equals("")) {
            sentSlideshowEmail = sentSlideShowService.saveByEmail(email, message, imageIds, photographer, event);
            resultEmail = emailHelper.sendImagesViaEmail(customerName, email, message, sentSlideshowEmail.getIdentifier());
        }
        if(!phoneNumber.equals("")){
            sentSlideshowSms = sentSlideShowService.saveBySms(phoneNumber, message, imageIds, photographer, event);
            resultSms = smsHelper.sendMessage(customerName, phoneNumber, sentSlideshowSms.getIdentifier(), message);
        }
        if(!resultEmail && !resultSms) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping("/send-via-sms")
    public ResponseEntity<?> sendImagesViaSms(@RequestParam("imageIds") int[] imageIds,
                                              @RequestParam("customerName") String customerName,
                                              @RequestParam("phoneNum") String phoneNum,
                                              @RequestParam("message") String message,
                                              @RequestParam("eventId") int eventId,Authentication authentication){
        Object principle = authentication.getPrincipal();
        Photographer photographer = (PhotographerUserDetails) principle;
        Event event = eventService.getById(eventId);
        SentSlideshow sentSlideshow = sentSlideShowService.saveBySms(phoneNum,message,imageIds,photographer,event);
        if(sentSlideshow==null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
        }
        Boolean result =smsHelper.sendMessage(customerName,phoneNum,sentSlideshow.getIdentifier(),message);
        if(!result){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");

        }
        return ResponseEntity.status(HttpStatus.OK).body(sentSlideshow);
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

}
