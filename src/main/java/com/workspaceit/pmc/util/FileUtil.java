package com.workspaceit.pmc.util;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.dao.TempFileDao;

/**
 * Created by mi on 1/11/17.
 */
@Component
public class FileUtil {

	Environment env;
	TempFileDao tempFileDao;


	@Autowired
	public void setEnv(Environment env) {
			this.env = env;
		}

	@Autowired
	public void setTempFileDao(TempFileDao tempFileDao) {
		this.tempFileDao = tempFileDao;
	}

	public String saveFileInFolder(byte[] fileByte, String fileExtention) throws IOException{
    		String fileName = System.nanoTime()+"."+fileExtention;
    		String filePath = this.env.getTmpFilePath()+"/"+fileName;
    		FileOutputStream fileOutPutStream = null;
    		try {
    			File file = new File(filePath);
    			fileOutPutStream = new FileOutputStream(file);
    			fileOutPutStream.write(fileByte);
    			fileOutPutStream.flush();
    			
    			
    		}catch (FileNotFoundException e) {
    			throw new FileNotFoundException("File not found in path "+filePath);
    		} catch (IOException e) {
    			throw new IOException("Unable to write byte "+filePath);
    		}finally {
    			if(fileOutPutStream!=null)fileOutPutStream.close();
    		} 
    		return filePath;
    	}

		public boolean deleteFile(String filePath){
			File file = new File(filePath);
			if(file.exists()){
				return file.delete();
			}
			return false;
		}

	public String copyPhotographerProfileFileFromTemp(String filePath) throws IOException {

		Path source = Paths.get(filePath);
		Path newPath = Paths.get(env.getPhotographerProfilePath());
		Path newFilePath = Files.copy(source, newPath.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
		return newFilePath.getFileName().toString();
	}
	public String copyFileFromTemp(String destinationPath,String filePath) throws IOException {

		Path source = Paths.get(filePath);
		Path newPath = Paths.get(destinationPath);
		Path newFilePath = Files.copy(source, newPath.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
		return newFilePath.getFileName().toString();
	}

}
