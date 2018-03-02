package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.FILE;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.helper.FileHelper;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.DashboardService;
import com.workspaceit.pmc.service.EventImageService;
import com.workspaceit.pmc.service.EventService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.event.EventForm;
import com.workspaceit.pmc.validation.event.EventValidator;
import com.workspaceit.pmc.validation.location.LocationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;


/**
 * Created by anik on 1/17/18.
 */

@RestController
@RequestMapping(ControllerUriPrefix.API+"/event")
public class EventRestController {

    private AdminService adminService;
    private EventService eventService;
    private EventValidator eventValidator;
    private EventImageService eventImageService;
    @Autowired
    private DashboardService dashboardService;
    private Set<String> imgAllowedMimeType;



    @Autowired
    public void setEventImageService(EventImageService eventImageService) {
        this.eventImageService = eventImageService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
    @Autowired
    public void setEventValidator(EventValidator eventValidator) {
        this.eventValidator = eventValidator;
    }
    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
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

    @Secured(UserRole._SUPER_ADMIN)
    @PostMapping("/create")
    public ResponseEntity<?> create(Authentication authentication, @Valid EventForm eventForm, BindingResult bindingResult) throws EntityNotFound {
        Admin admin = (Admin) authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        if(bindingResult.hasErrors()){
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /**
         * Business logic Validation
         * */
        this.eventValidator.validate(eventForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        Event event = this.eventService.create(eventForm);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(event);
    }

    @Secured(UserRole._SUPER_ADMIN)
    @RequestMapping(value = "/update/{id}")
    public ResponseEntity<?> update(Authentication authentication,
                                    @PathVariable("id") int id,
                                    @Valid EventForm eventForm, BindingResult bindingResult) throws EntityNotFound {
        Admin admin = (Admin) authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Basic Validation
         * */
        if(bindingResult.hasErrors()){
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /**
         * Business logic Validation
         * */
        this.eventValidator.validate(eventForm, bindingResult);
        serviceResponse.bindValidationError(bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        Event event = eventService.update(id,eventForm);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    }

    @Secured(UserRole._SUPER_ADMIN)
    @RequestMapping(value = "/delete/images")
    public ResponseEntity<?>  deleteEventImages(@RequestParam("eventId") int eventId,@RequestParam("imageIds[]") String[] imageIds){

        boolean result = false;
        int[] images = new int[imageIds.length];
        int i=0;
        for (String id:imageIds) {
            images[i]=Integer.parseInt(id);
            i++;
        }
        System.out.println(images);
        try {
            result = this.eventImageService.deleteEventImages(images);
            Event event = this.eventService.getById(eventId);
            int numOfImages = this.eventImageService.getImageCountForEvent(event);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(numOfImages);
        } catch (EntityNotFound entityNotFound) {
            entityNotFound.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result);
    }

    @Secured(UserRole._SUPER_ADMIN)
    @RequestMapping(value = "/update-name-logo")
    public ResponseEntity<?> updateEventNameLogo(@RequestParam(name = "file",required = false) MultipartFile multipartFile,@RequestParam("eventId") String eventId,@RequestParam("eventName") String eventName){
        int eventID = Integer.parseInt(eventId);
        if(multipartFile!=null){
            long fileSizeLimit = FileHelper.getMBtoByte(30);
            Set<String> imgContentType = this.imgAllowedMimeType;

            return validateAndProcessMultipartFile("file",fileSizeLimit,multipartFile,imgContentType,eventID,eventName);
        }else{
            return UpdateEventName(eventID,eventName);
        }

    }

    private ResponseEntity<?> validateAndProcessMultipartFile(String param, long fileSizeLimit, MultipartFile file, Set<String> imgContentTypes,int eventId,String eventName){

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
        Event event = eventService.getById(eventId);
        try {
            fileInfo = eventImageService.saveEventLogoFile(file);
            String fileName = fileInfo.get(FILE.NAME);
            event.setName(eventName);
            event.setEventPhoto(fileName);
            eventService.update(event);

        } catch(IOException e) {
            serviceResponse.setValidationError(param,"Internal server error : "+e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    private ResponseEntity<?> UpdateEventName(int eventId,String eventName){
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        Event event = eventService.getById(eventId);
        try {
            event.setName(eventName);
            eventService.update(event);
        } catch(Exception e) {
            serviceResponse.setValidationError("name","Internal server error : "+e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }


    @RequestMapping(value = "/monthwise-event-image-count")
    public ResponseEntity<?> getMonthWiseEventImageCount(){
        List<Integer> monthdata = new ArrayList<Integer>();
        System.out.println("adfsdas---------d");
        System.out.println(this.eventImageService);
        monthdata = this.eventImageService.getMonthWiseEventImageCount();
        return ResponseEntity.status(HttpStatus.OK).body(monthdata);
    }

}
