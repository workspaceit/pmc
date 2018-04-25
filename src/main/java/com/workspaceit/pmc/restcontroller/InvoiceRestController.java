package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.SentSlideshow;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.*;
import com.workspaceit.pmc.util.MailUtil;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(ControllerUriPrefix.API+"/invoice")
@CrossOrigin
public class InvoiceRestController {

    private AdvertiserService advertiserService;
    private AdvertisementService advertisementService;
    private MailUtil mailUtil;

    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @Autowired
    public void setAdvertisementService(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @Autowired
    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    @RequestMapping(value = "/mail/{advertiserId}",method = RequestMethod.POST)
    public ResponseEntity<?> mailInvoice(@PathVariable("advertiserId")int advertiserId,
                                         @RequestParam("email") String email){

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        if(email==null || email.equals("")){
            serviceResponse.setValidationError("email","Email required");
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(serviceResponse.getFormError());
        }

        Advertiser advertiser =  this.advertiserService.getById(advertiserId);

        if(advertiser==null){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(serviceResponse.setMsg("advertiserId","Advertise not found by id :"+advertiserId)
                    .getFormError());
        }

        String emailBody = this.advertisementService.getInvoiceInHtml(advertiser);
        this.mailUtil.sendAdvertisementInvoice(email,emailBody);
        System.out.println(emailBody);
        return ResponseEntity.ok("Mail sent");
    }
}
