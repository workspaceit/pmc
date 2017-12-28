package com.workspaceit.pmc.controller;
import java.util.Set;

import java.io.IOException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	
	private Set<String> photographerImgContentType;
	   

    public FileUploadController(){
    	photographerImgContentType = new HashSet<String>(){
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
    @RequestMapping(value="/upload/photographer-profile-image",headers="Content-Type=multipart/form-data",method=RequestMethod.POST)
    public ResponseEntity<?> uploadPhotographerProfilePicture(@RequestParam("profileImg") MultipartFile multipartFile) {
        long fileSizeLimit = 1 *(1024*1000); // 1 MB


    	String mimeType = FileHelper.getMimeType(multipartFile);
    	ServiceResponse serviceResponse = ServiceResponse.getInstance();
    	if(!this.photographerImgContentType.contains(mimeType)) {
    		serviceResponse.setValidationError("profileImg"," Mime Type "+ mimeType+" not allowed");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
    	}
    	System.out.println(multipartFile.getSize());
		if(multipartFile==null || multipartFile.getSize()==0){
			serviceResponse.setValidationError("profileImg","No file receive");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
		}


		if(multipartFile.getSize()>fileSizeLimit){
			serviceResponse.setValidationError("profileImg","File size exceeds. Max size 1 MB");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
		}

		TempFile tempfile;
		try {
			tempfile  = fileService.saveTempFile(multipartFile);

		} catch(IOException e) {
			// TODO Auto-generated catch block
			serviceResponse.setValidationError("profileImg","Internal server error : "+e.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
		}
    	
    	
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body(tempfile);
    }
	@RequestMapping(value="/remove/photographer-profile-image",method=RequestMethod.POST)
	public ResponseEntity<?> uploadPhotographerProfilePicture(@RequestParam("token") Integer token) {


		ServiceResponse serviceResponse = ServiceResponse.getInstance();
		if(token==null) {
			serviceResponse.setValidationError("token","Token Required");
			ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
		}
		this.fileService.removeTempFile(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServiceResponse.getMsgInMap("Successfully removed"));
	}
    
}