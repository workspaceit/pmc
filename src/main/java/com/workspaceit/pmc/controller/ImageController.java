package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.watermark.Size;
import com.workspaceit.pmc.constant.watermark.WatermarkType;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.FileService;
import com.workspaceit.pmc.service.WatermarkService;
import com.workspaceit.pmc.validation.form.WatermarkForm;
import com.workspaceit.pmc.validation.watermark.WatermarkValidator;
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
    private WatermarkValidator watermarkValidator;
    private FileService fileService;

    @Autowired
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }

    @Autowired
    public void setWatermarkValidator(WatermarkValidator watermarkValidator) {
        this.watermarkValidator = watermarkValidator;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @ResponseBody
    @RequestMapping(value = "/watermarked-preview",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public  ResponseEntity<byte[]> defaultSamplePreview( @Valid WatermarkForm watermarkForm,
                                                         BindingResult error){
        byte[] imageByte = null;




        try {
            this.watermarkValidator.validateForWatermarkPreview(watermarkForm,error);

            WatermarkType type = watermarkForm.getType();
            if(type!=null && type.equals(WatermarkType.image) && error.getFieldErrorCount("logoImgToken")>0){
                throw new IOException("Logo required");
            }
            imageByte = watermarkService.getImageWithWaterMark(watermarkForm); // Image or Text
            // imageByte = watermarkService.getImageWithWaterMark(watermarkForm,true); // Image and text both
        }catch (EntityNotFound ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new byte[]{});
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new byte[]{});
        }

        return ResponseEntity.status(HttpStatus.OK).body(imageByte);
    }
    @ResponseBody
    @RequestMapping(value = "/watermarked-preview/{watermarkId}",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public  ResponseEntity<byte[]> defaultSamplePreviewWhileEdit(@PathVariable(name = "watermarkId") Integer watermarkId,
                                                                 @Valid WatermarkForm watermarkForm,
                                                                 BindingResult error){
        byte[] imageByte = null;
        Watermark watermark = null;




        try {
            if(watermarkId!=null && watermarkId>0){
                watermark = this.watermarkService.getWatermark(watermarkId);
            }


            if(watermark==null){
                this.watermarkValidator.validateForWatermarkPreview(watermarkForm,error);

                WatermarkType type = watermarkForm.getType();
                if(type!=null && type.equals(WatermarkType.image) && error.getFieldErrorCount("logoImgToken")>0){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new byte[]{});
                }
            }
            imageByte = watermarkService.getImageWithWaterMark(watermark,watermarkForm); // Image or Text
            //imageByte = watermarkService.getImageWithWaterMark(watermark,true); // Image and text both
        }catch (EntityNotFound ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new byte[]{});
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new byte[]{});
        }

        return ResponseEntity.status(HttpStatus.OK).body(imageByte);
    }
    @ResponseBody
    @RequestMapping(value = "/watermarked-preview/{watermarkId}/{imageSize}",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public  ResponseEntity<byte[]> samplePreviewWhileEdit(@PathVariable(name = "watermarkId") Integer watermarkId,
                                                                 @PathVariable(name = "imageSize") Size imgSize,
                                                                 @Valid WatermarkForm watermarkForm, BindingResult error){
        byte[] imageByte = null;

        try {
            Watermark watermark = this.watermarkService.getWatermark(watermarkId);
            imageByte = watermarkService.getImageWithWaterMark(watermark,imgSize); // Image or Text
            //imageByte = watermarkService.getImageWithWaterMark(watermark,true); // Image and text both
        }catch (EntityNotFound ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new byte[]{});
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new byte[]{});
        }

        return ResponseEntity.status(HttpStatus.OK).body(imageByte);
    }
    @ResponseBody
    @RequestMapping(value = "/watermarked/{watermarkId}/{eventImageId}",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public  ResponseEntity<byte[]> watermark(@PathVariable("watermarkId") int watermarkId,
                                             @PathVariable("eventImageId") int eventImageId){
        byte[] imageByte = null;
        try {

            imageByte = watermarkService.getImageWithWaterMark(eventImageId,watermarkId);
        }catch (EntityNotFound ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new byte[]{});
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new byte[]{});
        }

        return ResponseEntity.status(HttpStatus.OK).body(imageByte);
    }


    @ResponseBody
    @RequestMapping(value = "/common/{fileName}/{size}",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public  ResponseEntity<byte[]> resizeCommonFile(@PathVariable("fileName") String fileName,
                                             @PathVariable("size")  Size imgSize){

        byte[] imageByte = new byte[0];
        try {
            imageByte = this.fileService.resizeCommonImage(fileName,imgSize);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(imageByte);
        }
        return ResponseEntity.status(HttpStatus.OK).body(imageByte);
    }

    @ResponseBody
    @RequestMapping(value = "/photographer-profile-img/{fileName}/{size}",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public  ResponseEntity<byte[]> resizePhotographerProfileImage(@PathVariable("fileName") String fileName,
                                             @PathVariable("size")  Size imgSize){

        byte[] imageByte = new byte[0];
        try {
            imageByte = this.fileService.resizePhotographerProfileImage(fileName,imgSize);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(imageByte);
        }
        return ResponseEntity.status(HttpStatus.OK).body(imageByte);
    }

}