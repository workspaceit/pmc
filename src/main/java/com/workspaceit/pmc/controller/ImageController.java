package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.WatermarkService;
import com.workspaceit.pmc.validation.form.WatermarkForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by mi_rafi on 12/26/17.
 */

@Controller
@RequestMapping(value = "/img")
public class ImageController {

    private WatermarkService watermarkService;

    @Autowired
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }

        /*
        http://localhost:8080/img/watermarked-preview?name=asd&type=image&logoImgToken=0&logoName=asd&placement=tl&size=thumb&fade=25
        * */
    @ResponseBody
    @RequestMapping(value = "/watermarked-preview",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public  ResponseEntity<byte[]> defaultSamplePreview(@Valid WatermarkForm watermarkForm, BindingResult error) throws IOException, EntityNotFound {
        byte[] imageByte = null;
        try {

            imageByte = watermarkService.getImageWithWaterMark(watermarkForm);
        }catch (EntityNotFound ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new byte[]{});
        }

        return ResponseEntity.status(HttpStatus.OK).body(imageByte);
    }

    @ResponseBody
    @RequestMapping(value = "/watermarked/{watermarkId}/{eventImageId}",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public  ResponseEntity<byte[]> watermark(@PathVariable("watermarkId") int watermarkId,
                                             @PathVariable("eventImageId") int eventImageId) throws IOException, EntityNotFound {
        byte[] imageByte = null;
        try {

            imageByte = watermarkService.getImageWithWaterMark(eventImageId,watermarkId);
        }catch (EntityNotFound ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new byte[]{});
        }

        return ResponseEntity.status(HttpStatus.OK).body(imageByte);
    }


}
