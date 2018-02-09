package com.workspaceit.pmc.api;

import com.workspaceit.pmc.auth.PhotographerUserDetails;
import com.workspaceit.pmc.entity.Photographer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/api/user-service")
@CrossOrigin
public class UserServiceApiController {
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<?> getUserService(Authentication authentication){
        Object principle = authentication.getPrincipal();
        if( principle instanceof PhotographerUserDetails){
            return ResponseEntity.ok((PhotographerUserDetails)principle);
        }

       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorised");
    }
}