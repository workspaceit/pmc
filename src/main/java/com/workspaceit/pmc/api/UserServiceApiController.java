package com.workspaceit.pmc.api;

import com.workspaceit.pmc.auth.AdminUserDetails;
import com.workspaceit.pmc.auth.PhotographerUserDetails;
import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.*;


@RestController
@CrossOrigin
@RequestMapping(ControllerUriPrefix.AUTH_API+"/user-service")
public class UserServiceApiController {

    private PhotographerService photographerService;

    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<?> getUserService(Authentication authentication){
        Object principle = authentication.getPrincipal();
        if( principle instanceof PhotographerUserDetails){
            return ResponseEntity.ok((PhotographerUserDetails)principle);
        }
        else if(principle instanceof AdminUserDetails){
            return ResponseEntity.ok((AdminUserDetails)principle);
        }
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorised");
    }

    @PostMapping("/login-as-photographer")
    public ResponseEntity<?> getToken(Authentication authentication, @RequestParam("photographerId") Integer photographerId){
        Object principal = authentication.getPrincipal();
        if(!(principal instanceof AdminUserDetails)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("You are not allowed to perform this operation");
        }
        Photographer photographer = this.photographerService.getById(photographerId);
        PhotographerUserDetails photographerUserDetails = new PhotographerUserDetails(photographer);
//        List<Role> roles = new ArrayList<>();
        List<String> scopes = new ArrayList<>();
        scopes.add("read");
        scopes.add("write");
        scopes.add("trust");
        UserDetails userDetails = photographerUserDetails;
        OAuth2AccessToken token = this.photographerService.generateOAuth2AccessToken(userDetails, scopes);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }



}