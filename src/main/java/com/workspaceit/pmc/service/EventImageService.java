package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.FILE;
import com.workspaceit.pmc.dao.EventImageDao;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.Watermark;
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
import java.util.*;

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
    public List<EventImage> getEventImagesByCriteria(Integer eventId, Integer limit, Integer offset, Boolean inSlideshow){
        return eventImageDao.getEventImagesByCriteria(eventId, limit, offset, inSlideshow);
    }

    @Transactional
    public List<EventImage> getEventImages(Integer eventId){
        return eventImageDao.getEventImagesByCriteria(eventId);
    }


    @Transactional
    public Integer getImageCountForEvent(Event event) {
        return eventImageDao.getImageCountForEvent(event);
    }

    @Transactional
    public Integer getSlideshowImageCountForEvent(Event event) {
        return eventImageDao.getSlideshowImageCountForEvent(event);
    }


    @Transactional
    public EventImage getById(Integer id){
        return eventImageDao.getById(id);
    }

    @Transactional
    public EventImage getByFileName(String fileName){
        return eventImageDao.getByFileName(fileName);
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

    public Map<FILE,String> saveEventLogoFile(MultipartFile multipartFile) throws IOException {
        Map<FILE,String> fileInfo = new HashMap<>();
        String fileExtension = FileHelper.getExtension(multipartFile);
        Map<FILE,String> fileInf =  this.fileUtil.saveEventLogoFile(multipartFile, fileExtension);
        String fileName = fileInf.get(FILE.NAME);
        String filePath = fileInf.get(FILE.PATH);
        fileInfo.put(FILE.NAME,fileName);
        fileInfo.put(FILE.PATH,filePath);
        return fileInf;
    }

    @Transactional
    public Boolean deleteEventImages(int[] imageIds)throws EntityNotFound{
        for(int imageId : imageIds){
            Boolean result = this.eventImageDao.deleteImage(imageId);
            if(!result){
                return false;
            }
        }
        return true;
    }

    @Transactional
    public Boolean sendImagesToSlideShow(List<Integer> imageIds){
        for(int imageId : imageIds){
            Boolean result = this.eventImageDao.sendToSlideShow(imageId);
            if(!result){
                return false;
            }
        }
        return true;
    }

    @Transactional
    public Boolean removeImagesFromSlideShow(List<Integer> imageIds){
        for(int imageId : imageIds){
            Boolean result = this.eventImageDao.removeFromSlideShow(imageId);
            if(!result){
                return false;
            }
        }
        return true;
    }

    @Transactional
    public Boolean sendViaEmail(int[] imageIds){
        for(int imageId : imageIds){
            Boolean result = this.eventImageDao.sendToSlideShow(imageId);
            if(!result){
                return false;
            }
        }
        return true;
    }

    @Transactional
    public Boolean checkOwnerShipOfImages(int[] imageIds, Photographer photographer){
        for(int imageId : imageIds){
            EventImage eventImage = this.eventImageDao.getById(imageId);
            if(eventImage.getCreatedBy().getId()!=photographer.getId()){
                return false;
            }
        }
        return true;
    }

    @Transactional
    public List<EventImage> getImagesByIds(List<Integer> imageIds){
        List<EventImage> eventImages = this.eventImageDao.getByIds(imageIds);
        return eventImages;
    }

    @Transactional
    public List<EventImage> getImagesByEventIdWhereInSlideshowTrue(int eventId){
        return this.eventImageDao.getByEventIdWhereInSlideshowTrue(eventId);
    }

    @Transactional
    public Boolean photographerAssignedOnEvent(List<Integer> imageIds, Photographer photographer){
        List<EventImage> eventImages = this.eventImageDao.getByIds(imageIds);
        Boolean exists = false;
        for(EventImage eventImage: eventImages){
            for(Photographer ePhotographer: eventImage.getEvent().getPhotographers()){
                if(ePhotographer.getId() == photographer.getId()){
                    exists = true;
                    break;
                }
            }
        }
        return exists;
    }

    @Transactional
    public Boolean addWatermark(List<Integer> imageIds, Watermark watermark)throws EntityNotFound{
        Boolean result = this.eventImageDao.addWatermarkToImages(imageIds, watermark);
        return result;
    }

    @Transactional
    public Boolean removeWatermark(List<Integer> imageIds)throws EntityNotFound{
        Boolean result = this.eventImageDao.removeWatermarkFromImages(imageIds);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Integer> getMonthWiseEventImageCount(){
        List<Object[]> rows= this.eventImageDao.getMonthWiseEventImageCount();
        List<Integer> monthdata = new ArrayList<Integer>();
        monthdata.add(0);
        monthdata.add(0);
        monthdata.add(0);
        monthdata.add(0);
        monthdata.add(0);
        monthdata.add(0);
        monthdata.add(0);
        monthdata.add(0);
        monthdata.add(0);
        monthdata.add(0);
        monthdata.add(0);
        monthdata.add(0);
        for (Object[] row:rows) {
            int index = Integer.parseInt(row[0].toString())-1;
            int data = Integer.parseInt(row[1].toString());
            System.out.println(index);
            System.out.println(data);
            monthdata.set(index,data);
        }
        return monthdata;
    }
    @Transactional
    public  List<EventImage> getByWatermarkId(int watermarkId){
        return this.eventImageDao.getByWatermarkId(watermarkId);
    }
    @Transactional(rollbackFor = Exception.class)
    public void makeWatermarkNull(int watermarkId) {
        List<EventImage> eventImages =   this.getByWatermarkId(watermarkId);
        if(eventImages==null){
            return;
        }
        for(EventImage eventImage:eventImages){
            eventImage.setWatermark(null);

            this.eventImageDao.update(eventImage);
        }

    }



}
