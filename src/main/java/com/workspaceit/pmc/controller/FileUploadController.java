package com.workspaceit.pmc.controller;
import java.util.Set;

import java.io.IOException;
import java.util.HashSet;

import com.workspaceit.pmc.constant.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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

@Controller
@RequestMapping(value="/file")
public class FileUploadController{
	private FileService fileService;
	
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	private Set<String> imgContentType;
	   

    public FileUploadController(){
    	imgContentType = new HashSet<String>(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
                add("image/jpeg");
                add("image/pjpeg");
                add("image/jpeg");
                add("image/png");

            }
        };

    }

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
                default:
                    fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
                    imgContentType = this.imgContentType;
        }


        return validateAndProcessMultiPart(  "profileImg",fileSizeLimit,multipartFile,imgContentType);
    }


    @Secured({UserRole._SUPER_ADMIN})
    @RequestMapping(value="/upload/adv/{uploader}",headers="Content-Type=multipart/form-data",method=RequestMethod.POST)
    public ResponseEntity<?> uploadAdvertisePicture(@RequestParam("advImg") MultipartFile multipartFile,
                                           @PathVariable("uploader") String uploader) {
        long fileSizeLimit;
        Set<String> imgContentType;
        switch (uploader){

            default:
                fileSizeLimit =FileHelper.getMBtoByte(1) ;// 1 MB
                imgContentType = this.imgContentType;
        }

        return validateAndProcessMultiPart(  "advImg",fileSizeLimit,multipartFile,imgContentType);

    }
    private ResponseEntity<?> validateAndProcessMultiPart(  String param,
                                                            long fileSizeLimit,
                                                            MultipartFile multipartFile,
                                                            Set<String> imgContentType
                                                         ){
        String mimeType = FileHelper.getMimeType(multipartFile);
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