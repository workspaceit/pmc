package com.workspaceit.pmc.restcontroller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.exception.ServiceException;
import com.workspaceit.pmc.service.WatermarkService;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.validation.watermark.WatermarkForm;
import com.workspaceit.pmc.validation.watermark.WatermarkValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mi_rafi on 1/1/18.
 */
@RestController
@RequestMapping(ControllerUriPrefix.API+"/watermark")
public class WatermarkRestController {

    private WatermarkService watermarkService;
    private WatermarkValidator watermarkValidator;

    @Autowired
    public void setWatermarkService(WatermarkService watermarkService){
        this.watermarkService=watermarkService;
    }
    @Autowired
    public void setWatermarkValidator(WatermarkValidator watermarkValidator) {
        this.watermarkValidator = watermarkValidator;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid WatermarkForm watermarkForm, BindingResult bindingResult) {
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         *  Conditional Basic Validation
         * */
        this.watermarkValidator.validate(watermarkForm, bindingResult);
        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        Watermark watermark = this.watermarkService.create(watermarkForm);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(watermark);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(Authentication authentication,@PathVariable("id") int id,
                                    @Valid WatermarkForm watermarkForm, BindingResult bindingResult) {
        Admin currentUser = (Admin)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /**
         * Conditional Basic Validation
         * And Business logic
         * */

        this.watermarkValidator.validateForUpdate(watermarkForm,bindingResult);

        if (bindingResult.hasErrors()) {
            serviceResponse.bindValidationError(bindingResult);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        try {
            this.watermarkService.update(id,watermarkForm,currentUser);
        } catch (EntityNotFound entityNotFound) {
            serviceResponse.setValidationError("id",entityNotFound.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getErrors());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    }

    @GetMapping("/auto-suggest")
    public ResponseEntity<?> getSuggestedWatermarks(@RequestParam("searchTerm") String searchTerm){
        try {
            List<Watermark> watermarks = watermarkService.getSuggestedWatermarks(searchTerm, true);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(watermarks);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Something went wrong");
        }
    }

}
