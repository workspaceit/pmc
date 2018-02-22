package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.watermark.WATERMARK_ATTR;
import com.workspaceit.pmc.dao.TempFileDao;
import com.workspaceit.pmc.entity.TempFile;
import com.workspaceit.pmc.exception.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TempFileService {

    private TempFileDao tempFileDao;

    @Autowired
    public void setTempFileDao(TempFileDao tempFileDao) {
        this.tempFileDao = tempFileDao;
    }

    @Transactional
    public TempFile getTempFile(int token) throws EntityNotFound{
        TempFile tempFile =  this.tempFileDao.getByToken(token);

        if(tempFile == null){
            throw new EntityNotFound("No Temp file found with token :"+token);
        }
        return tempFile;
    }
    @Transactional
    public TempFile getByToken(int token){
        return this.tempFileDao.getByToken(token);
    }


}
