package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by anik on 1/26/18.
 */

@Service
public class CommonService {
    private CommonDao commonDao;
    private EventService eventService;
    private EventImageService eventImageService;

    @Autowired
    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setEventImageService(EventImageService eventImageService) {
        this.eventImageService = eventImageService;
    }

    @Transactional
    public Boolean activateEntity(Integer id, String type){
        String entityClassName = getEntityClassName(type);
        if(entityClassName.equals("")){
            return false;
        }
        return commonDao.activate(entityClassName, id);
    }

    @Transactional
    public Boolean deActivateEntity(Integer id, String type){
        String entityClassName = getEntityClassName(type);
        if(entityClassName.equals("")){
            return false;
        }
        return commonDao.deActivate(entityClassName, id);
    }

    @Transactional(rollbackFor =Exception.class)
    public Boolean delete(Integer id, String type){
        String entityClassName = getEntityClassName(type);
        if(entityClassName.equals("")){
            return false;
        }
        boolean flag = commonDao.delete(entityClassName, id);
        this.afterDelete(entityClassName,id);
        return flag;
    }
    @Transactional(rollbackFor =Exception.class)
    public void afterDelete(String entityClassName,int id){
        switch(entityClassName){
            case "Watermark":
                this.eventService.removeWatermarkFromEvent(id);
                this.eventImageService.makeWatermarkNull(id);
                break;
            case "Photographer":
                this.eventService.removePhotographerFromEvent(id);
                break;
            case "Advertiser":
                this.eventService.removeAdvertiserFromEvent(id);
                break;
        }
    }
    private String getEntityClassName(String type){
        String entityClassName = "";
        switch (type){
            case "event":
                entityClassName = "Event";
                break;
            case "photographer":
                entityClassName = "Photographer";
                break;
            case "watermark":
                entityClassName = "Watermark";
                break;
            case "location":
                entityClassName = "Location";
                break;
            case "advertiser":
                entityClassName = "Advertiser";
                break;
            case "venue":
                entityClassName = "Venue";
                break;
            case "user":
                entityClassName = "Admin";
                break;
            default:
                break;
        }
        return entityClassName;
    }

}
