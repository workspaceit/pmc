package com.workspaceit.pmc.controller;
import java.util.Set;

import java.io.IOException;
import java.util.HashSet;

import com.workspaceit.pmc.constant.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.workspaceit.pmc.entity.TempFile;
import com.workspaceit.pmc.helper.FileHelper;
import com.workspaceit.pmc.service.FileService;
import com.workspaceit.pmc.util.ServiceResponse;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping(value="/file")
public class FileUploadController{
	private FileService fileService;
	
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	private Set<String> imgAllowedMimeType;
    private Set<String> videoAllowedMimeType;


    @PostConstruct
    private void initConfiguration(){
        imgAllowedMimeType = new HashSet<>();
        videoAllowedMimeType = new HashSet<>();

        /**
         * Image mime type
         * */
        imgAllowedMimeType.add("image/jpeg");
        imgAllowedMimeType.add("image/pjpeg");
        imgAllowedMimeType.add("image/jpeg");
        imgAllowedMimeType.add("image/png");

        /**
         * Video mime type
         * */
        videoAllowedMimeType.add("video/ogg");
        videoAllowedMimeType.add("video/webm");
        videoAllowedMimeType.add("video/mp4");
    }

    public FileUploadController(){}

    @Secured({UserRole._SUPER_ADMIN})
    @RequestMapping(value="/upload/{uploader}",headers="Content-Type=multipart/form-data",method=RequestMethod.POST)
    public ResponseEntity<?> uploadPicture(@RequestParam("profileImg") MultipartFile multipartFile,
                                           @PathVariable("uploader") String uploader) {
        long fileSizeLimit;
        Set<String> imgContentType;
        switch (uploader){
            case "photographer-profile-image":
            case "venue-logo-image":
            case "venue-background-image":
            case "watermark-logo-image":





            case "admin-profile-image":
            default:
                fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
                imgContentType = this.imgAllowedMimeType;
        }


        return validateAndProcessMultiPart(  "profileImg",fileSizeLimit,multipartFile,imgContentType);
    }


    @Secured({UserRole._SUPER_ADMIN})
    @RequestMapping(value="/upload/adv/{uploader}",headers="Content-Type=multipart/form-data",method=RequestMethod.POST)
    public ResponseEntity<?> uploadAdvertisePicture(@RequestParam("advImg") MultipartFile multipartFile,
                                           @PathVariable("uploader") String uploader) {
        long fileSizeLimit=0;
        Set<String> imgContentType=this.imgAllowedMimeType;

         

        switch (uploader){
            case "other-images":
                fileSizeLimit = FileHelper.getMBtoByte(1) ;// 1 MB
                break;
            case "logo-image":
                fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
                break;
            case "background-image":
                fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
                break;
            case "top-banner":
                fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
                break;
            case "bottom-banner":
                fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
                break;
            case "slide-show-banner":
                fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
                break;
            case "slide-show-video":
                fileSizeLimit =FileHelper.getMBtoByte(3) ;// 3 MB
                imgContentType = this.videoAllowedMimeType;
                break;
            case "email-popup-banner":
                fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
                imgContentType = this.imgAllowedMimeType;
                break;
            case "email-popup-video":
                fileSizeLimit =FileHelper.getMBtoByte(3) ;// 3 MB
                imgContentType = this.videoAllowedMimeType;
                break;
            case "sms-popup-banner":
                fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
                imgContentType = this.imgAllowedMimeType;
                break;
            case "sms-popup-video":
                fileSizeLimit =FileHelper.getMBtoByte(3) ;// 3 MB
                imgContentType = this.videoAllowedMimeType;
                break;
            default:
                fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
        }

        return validateAndProcessMultiPart(  "advImg",fileSizeLimit,multipartFile,imgContentType);

    }
    private ResponseEntity<?> validateAndProcessMultiPart(  String param,
                                                            long fileSizeLimit,
                                                            MultipartFile multipartFile,
                                                            Set<String> imgContentType
                                                         ){
        String mimeType = FileHelper.getMimeType(multipartFile);
        System.out.println("mimeType "+mimeType);
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        if(!imgContentType.contains(mimeType)) {
            serviceResponse.setValidationError(param," Mime Type "+ mimeType+" not allowed");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        if(multipartFile==null || multipartFile.getSize()==0){
            serviceResponse.setValidationError(param,"No file receive");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        System.out.println(multipartFile.getSize());

        if(multipartFile.getSize()>fileSizeLimit){
            serviceResponse.setValidationError(param,"File size exceeds. Max size "+FileHelper.getByteToMb(fileSizeLimit));
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        TempFile tempfile;
        try {
            tempfile  = fileService.saveTempFile(multipartFile);

        } catch(IOException e) {
            // TODO Auto-generated catch block
            serviceResponse.setValidationError(param,"Internal server error : "+e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(tempfile);
    }

    @RequestMapping(value="/remove",method=RequestMethod.POST)
	public ResponseEntity<?> removePicture(@RequestParam("token") Integer token) {


		ServiceResponse serviceResponse = ServiceResponse.getInstance();
		if(token==null) {
			serviceResponse.setValidationError("token","Token Required");
			ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
		}
		this.fileService.removeTempFile(token);
        serviceResponse.setMsg("token",token);
        serviceResponse.setMsg("msg","Successfully removed");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getMsg());
	}
    
}