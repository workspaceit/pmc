package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.FILE;
import com.workspaceit.pmc.dao.EventImageDao;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.helper.FileHelper;
import com.workspaceit.pmc.helper.ImageHelper;
import com.workspaceit.pmc.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anik on 2/15/18.
 */

@Service
public class EventImageService {

    private FileUtil fileUtil;


    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    private  EventImageDao eventImageDao;
    @Autowired
    public void setEventImageDao(EventImageDao eventImageDao) {
        this.eventImageDao = eventImageDao;
    }

    @Transactional
    public List<EventImage> getEventImagesByCriteria(Integer eventId, Integer limit, Integer offset){
        return eventImageDao.getEventImagesByCriteria(eventId, limit, offset);
    }

    @Transactional
    public Integer getImageCountForEvent(Event event) {
        return eventImageDao.getImageCountForEvent(event);
    }

    @Transactional
    public EventImage getById(Integer id){
        return eventImageDao.getById(id);
    }

    @Transactional
    public EventImage getEventImage(Integer id)throws EntityNotFound{
        EventImage eventImage = this.eventImageDao.getById(id);
        if(eventImage==null){
            throw new EntityNotFound("Entity not found by id : "+id);
        }
        return eventImage;
    }

    @Transactional
    public  void saveEventImage(EventImage eventImage){
        this.eventImageDao.insert(eventImage);
    }

    public Map<FILE,String> saveEventImageFile(MultipartFile multipartFile) throws IOException {
        Map<FILE,String> fileInfo = new HashMap<>();
        String fileExtension = FileHelper.getExtension(multipartFile);
        Map<FILE,String> fileInf =  this.fileUtil.saveEventImageFile(multipartFile, fileExtension);
        String fileName = fileInf.get(FILE.NAME);
        String filePath = fileInf.get(FILE.PATH);
        fileInfo.put(FILE.NAME,fileName);
        fileInfo.put(FILE.PATH,filePath);
        return fileInf;
    }

    @Transactional
    public Boolean deleteEventImages(int[] imageIds)throws EntityNotFound{
        for(Integer imageId : imageIds){
            Boolean result = this.eventImageDao.deleteImage(imageId);
            if(!result){
                return false;
            }
        }
        return true;
    }






}
