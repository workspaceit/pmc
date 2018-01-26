package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.UserRole;
import com.workspaceit.pmc.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by anik on 1/26/18.
 */

@RestController
@RequestMapping(ControllerUriPrefix.API+"/common")
public class CommonRestController {
    private CommonService commonService;
    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    @Secured(UserRole._SUPER_ADMIN)
    @PostMapping("/activate-entity")
    public ResponseEntity<?> activateEntity(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        String type = request.getParameter("type");
        Boolean activated = commonService.activateEntity(id, type);
        if(activated){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("true");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Operation Unsuccessful");
        }

    }

    @Secured(UserRole._SUPER_ADMIN)
    @PostMapping("/deactivate-entity")
    public ResponseEntity<?> deActivateEntity(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        String type = request.getParameter("type");
        Boolean deActivated = commonService.deActivateEntity(id, type);
        if(deActivated){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("true");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Operation Unsuccessful");
        }

    }

}
