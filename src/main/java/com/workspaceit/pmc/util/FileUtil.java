package com.workspaceit.pmc.util;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.dao.TempFileDao;

/**
 * Created by mi on 1/11/17.
 */
@Component
public class FileUtil {
		@Autowired
		Environment env;
		
		public void setEnv(Environment env) {
			this.env = env;
		}
        @Autowired
        TempFileDao tempFileDao;

        public String saveFileInFolder(byte[] fileByte,String fileExtention) throws IOException{
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
}
