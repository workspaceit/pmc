package com.workspaceit.pmc.api;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.PhotographerPasswordResetToken;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.helper.EmailHelper;
import com.workspaceit.pmc.service.PhotographerPasswordResetService;
import com.workspaceit.pmc.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ControllerUriPrefix.PUBLIC_API+"/reset-password")
@CrossOrigin
public class ResetPasswordApiController {

    private PhotographerPasswordResetService photographerPasswordResetService;
    private PhotographerService photographerService;

    @Autowired
    private EmailHelper emailHelper;

    @Autowired
    public void setPhotographerPasswordResetService(PhotographerPasswordResetService photographerPasswordResetService) {
        this.photographerPasswordResetService = photographerPasswordResetService;
    }

    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email){
        Photographer photographer = this.photographerService.getByEmail(email);
        if(photographer == null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("not a user");
        }
        String token = UUID.randomUUID().toString();
        PhotographerPasswordResetToken photographerPasswordResetToken= this.photographerPasswordResetService
                .generatePasswordToken(photographer,token);

        new Thread(new Runnable() {
            public void run() {
                System.out.println("Inside Email sending");
                emailHelper.sendPasswordResetMailToPhotoGrapher(photographer.getId(),email,token);
                System.out.println("Inside Email sent");
            }
        }).start();
        return ResponseEntity.status(HttpStatus.OK).body(true);


    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam("photographerId") String photographerId,@RequestParam("token") String token) {
        int userId = Integer.parseInt(photographerId);
        String result = this.photographerPasswordResetService.validatePasswordResetToken(userId,token);
        if(result!=null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result);
        }
        Photographer photographer = this.photographerService.getById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(photographer);
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestParam("photographerId") String photographerId,
                                            @RequestParam("password") String password) {
        int userId = Integer.parseInt(photographerId);
        Photographer photographer = null;
        try {
            photographer = this.photographerService.updatePassword(userId,password,null);
        } catch (EntityNotFound entityNotFound) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("user not found");
        };
        return ResponseEntity.status(HttpStatus.OK).body(photographer);
    }


}
