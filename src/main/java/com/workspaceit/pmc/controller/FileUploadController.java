package com.workspaceit.pmc.controller;
import java.util.Set;

import javax.mail.internet.ContentType;

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
@RequestMapping(value="/file-upload")
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
    @RequestMapping(value="/photographer-profile-image",headers="Content-Type=multipart/form-data",method=RequestMethod.POST)
    public ResponseEntity<?> uploadPhotographerProfilePicture(@RequestParam("profileImg") MultipartFile multipartFile) {
    	
    	String mimeType = FileHelper.getMimeType(multipartFile);
    	ServiceResponse serviceResponse = ServiceResponse.getInstance();
    	if(!this.photographerImgContentType.contains(mimeType)) {
    		serviceResponse.setValidationError("profileImg"," Mime Type "+ mimeType+" not allowed");
    		 ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceResponse.getFormError());
    	}
    	TempFile tempfile = null;
		try {
			byte[] fileByte = multipartFile.getBytes();
			tempfile = fileService.saveTempFile(fileByte,FileHelper.getExtension(multipartFile));
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body(tempfile);
    }
    
}