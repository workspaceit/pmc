package com.workspaceit.pmc.service;
import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workspaceit.pmc.dao.TempFileDao;
import com.workspaceit.pmc.entity.TempFile;
import com.workspaceit.pmc.helper.FileHelper;
import com.workspaceit.pmc.util.FileUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileService {
	FileUtil fileUtil;
	
	@Autowired
	TempFileDao tempFileDao;
	
	@Autowired
	public void setFileUtil(FileUtil fileUtil) {
		this.fileUtil = fileUtil;
	}
	@Transactional(rollbackFor = Exception.class)
	public TempFile saveTempFile(byte[] fileByte,String fileExtention) throws IOException{
		
		String filePath = this.fileUtil.saveFileInFolder(fileByte, fileExtention);
		
		 Random rnd = new Random();
	     int n = 1000000000 + rnd.nextInt(900000);
	        
	    TempFile tempFile = new TempFile();
		tempFile.setPath(filePath);
		tempFile.setToken(n);
		tempFileDao.insert(tempFile);
		return tempFile;
	}
}