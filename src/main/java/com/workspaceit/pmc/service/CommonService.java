package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by anik on 1/26/18.
 */

@Service
public class CommonService {
    private CommonDao commonDao;
    @Autowired
    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
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
        return commonDao.delete(entityClassName, id);
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
