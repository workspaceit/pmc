package com.workspaceit.pmc.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.workspaceit.pmc.config.Environment;

public class FileHelper {
	
	
	public static String getMimeType(MultipartFile multipartFile){
        String mimeType = multipartFile.getContentType();

        if(mimeType==null || mimeType.equals("")){
            mimeType =  URLConnection.guessContentTypeFromName(multipartFile.getOriginalFilename());
        }
        if(mimeType==null || mimeType.equals("")){
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            mimeType = fileNameMap.getContentTypeFor(multipartFile.getOriginalFilename());
        }
        return mimeType;
    }
	public static String getExtension(MultipartFile multipartFile) {
		String oiginalFilename = multipartFile.getOriginalFilename();
		String fileExtensions= "";
		if(oiginalFilename!=null) {
			try {
				System.out.println(Arrays.toString(oiginalFilename.split(".")));
				fileExtensions = oiginalFilename.substring(oiginalFilename.indexOf(".")+1);
			}catch(StringIndexOutOfBoundsException ex) {
				System.out.println("Can't retrive extention from "+oiginalFilename);
				fileExtensions= "";
			}
			
		}
		return fileExtensions;
	}
	public static long getMBtoByte(int mb) {
		return mb *(1024*1000);
	}
	public static long getByteToMb(long bytes) {
		return bytes / (long)(1024*1000);
	}
}