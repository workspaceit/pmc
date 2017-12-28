package com.workspaceit.pmc.service;
import java.io.IOException;
import java.util.Random;

import com.workspaceit.pmc.helper.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workspaceit.pmc.dao.TempFileDao;
import com.workspaceit.pmc.entity.TempFile;
import com.workspaceit.pmc.helper.FileHelper;
import com.workspaceit.pmc.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;

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
	public TempFile saveTempFile(MultipartFile multipartFile) throws IOException{

		byte[] fileByte = multipartFile.getBytes();
		String fileExtension =FileHelper.getExtension(multipartFile);
		String filePath = this.fileUtil.saveFileInFolder(fileByte, fileExtension);
		
		int token = TokenGenerator.generateTempFileToken();

		TempFile tempFile = new TempFile();
		tempFile.setPath(filePath);
		tempFile.setToken(token);
		tempFileDao.insert(tempFile);
		return tempFile;
	}

	@Transactional(rollbackFor = Exception.class)
	public void removeTempFile(Integer token){
		TempFile tempFile = tempFileDao.getByToken(token);
		if(tempFile==null){
			return;
		}
		this.fileUtil.deleteFile(tempFile.getPath());
		this.tempFileDao.delete(tempFile);
	}
	@Transactional(rollbackFor = Exception.class)
	public String copyFileToPhotographerProfilePicture(Integer token){
		TempFile tempFile = tempFileDao.getByToken(token);
		String fileName = "";
		if(tempFile==null){
			return fileName;
		}
		try {
			fileName = this.fileUtil.copyPhotographerProfileFileFromTemp(tempFile.getPath());
		} catch (IOException e) {
			return fileName;
		}
		return fileName;
	}
}