package com.workspaceit.pmc.service;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.constant.FILE;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.helper.TokenGenerator;
import com.workspaceit.pmc.helper.watermark.WatermarkHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workspaceit.pmc.dao.TempFileDao;
import com.workspaceit.pmc.entity.TempFile;
import com.workspaceit.pmc.helper.FileHelper;
import com.workspaceit.pmc.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.MapKey;
import javax.servlet.ServletContext;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileService {
	private ServletContext servletContext;
	private Environment environment;
	private FileUtil fileUtil;
	private TempFileDao tempFileDao;
	private TempFileService tempFileService;
	private Environment env;
	private WatermarkHelper watermarkHelper;


	@Autowired
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Autowired
    public void setTempFileDao(TempFileDao tempFileDao) {
        this.tempFileDao = tempFileDao;
    }

	@Autowired
	public void setTempFileService(TempFileService tempFileService) {
		this.tempFileService = tempFileService;
	}

	@Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }


    @Autowired
	public void setWatermarkHelper(WatermarkHelper watermarkHelper) {
		this.watermarkHelper = watermarkHelper;
	}

	@Transactional(rollbackFor = Exception.class)
	public TempFile saveTempFile(MultipartFile multipartFile) throws IOException{

		byte[] fileByte = multipartFile.getBytes();
		String fileExtension = FileHelper.getExtension(multipartFile);
		Map<FILE,String> fileInf =  this.fileUtil.saveFileInFolder(fileByte, fileExtension);
		String fileName = fileInf.get(FILE.NAME);
		String filePath = fileInf.get(FILE.PATH);

		int token = TokenGenerator.generateTempFileToken();

		TempFile tempFile = new TempFile();
		tempFile.setFileName(fileName);
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
		this.tempFileDao.delete(tempFile);
		this.fileUtil.deleteFile(tempFile.getPath());
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

	@Transactional(rollbackFor = Exception.class)
	public String copyFile(Integer token){
		String fileName = "";
		if(token==null){
			return "";
		}
		TempFile tempFile = tempFileDao.getByToken(token);
		if(tempFile==null){
			return fileName;
		}
		try {
			fileName = this.fileUtil.copyFile(env.getCommonFilePath(),tempFile.getPath());
		} catch (IOException e) {
			return fileName;
		}
		return fileName;
	}

	@Transactional(rollbackFor = Exception.class)
	public String copyFile(String fileAbsPath){
		String fileName = "";

		try {
			fileName = this.fileUtil.copyFile(env.getCommonFilePath()+System.nanoTime(),fileAbsPath);
		} catch (IOException e) {
			return fileName;
		}
		return fileName;
	}
	@Transactional
	public String getMimeTypeByToken(Integer token){
		String fileMimeType = "";
		if(token==null){
			return fileMimeType;
		}

		TempFile tempFile = tempFileDao.getByToken(token);

		if(tempFile==null){
			return fileMimeType;
		}
		try {
			File file = this.fileUtil.getTempFile(env.getTmpFilePath()+"/"+tempFile.getPath());
			fileMimeType =  FileHelper.getMimeType(file);
		} catch (IOException e) {
			return fileMimeType;
		}
		return fileMimeType;

	}

	public String getSampleImgPath(Integer sampleToken) throws EntityNotFound, IOException {
		String originalImgAbsPath = this.getSampleImgPathByToken(sampleToken);
		originalImgAbsPath = (originalImgAbsPath==null || originalImgAbsPath.trim().equals(""))?this.watermarkHelper.getDefaultWatermarkSampleImgPath():originalImgAbsPath;
		return originalImgAbsPath;
	}
	public String getSampleImgPathByToken(Integer sampleToken) throws EntityNotFound, IOException {
		String originalImgAbsPath="";
		if(sampleToken!=null && sampleToken>0){
			TempFile tempSampleFile =this.tempFileService.getByToken(sampleToken);
			if(tempSampleFile==null){
				throw new EntityNotFound("No Sample file found with token : "+sampleToken);
			}
			originalImgAbsPath = tempSampleFile.getPath();
		}

		return originalImgAbsPath;
	}

	public Map<FILE,String> makeNewFileFromDefaultWatermarkSampleImg(){
		String defaultPath = null;
		String fileName = System.nanoTime()+".png";
		String filePath = env.getCommonFilePath()+"/"+fileName;
		Map<FILE,String> fileInf = new HashMap<>();

		try {
			defaultPath = this.watermarkHelper.getDefaultWatermarkSampleImgPath();
			this.fileUtil.copyFileWithNewName(filePath,defaultPath);
		} catch (EntityNotFound entityNotFound) {
			System.out.println(entityNotFound.getMessage());
			fileName = filePath = "";
		} catch (IOException e) {
			System.out.println(e.getMessage());
			fileName = filePath = "";
		}



		fileInf.put(FILE.NAME,fileName);
		fileInf.put(FILE.PATH,filePath);
		return fileInf;
	}
}