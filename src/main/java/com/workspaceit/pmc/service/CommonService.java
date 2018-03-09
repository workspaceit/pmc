package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.CommonDao;
import com.workspaceit.pmc.helper.EntityHelper;
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
    private EntityHelper entityHelper;

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

    @Autowired
    public void setEntityHelper(EntityHelper entityHelper) {
        this.entityHelper = entityHelper;
    }

    @Transactional
    public Boolean activateEntity(Integer id, String type){
        String entityClassName = entityHelper.getClassName(type);
        if(entityClassName.equals("")){
            return false;
        }
        return commonDao.activate(entityClassName, id);
    }

    @Transactional
    public Boolean deActivateEntity(Integer id, String type){
        String entityClassName = entityHelper.getClassName(type);
        if(entityClassName.equals("")){
            return false;
        }
        return commonDao.deActivate(entityClassName, id);
    }

    @Transactional(rollbackFor =Exception.class)
    public Boolean delete(Integer id, String type){
        String entityClassName = entityHelper.getClassName(type);
        if(entityClassName.equals("")){
            return false;
        }

        return  commonDao.delete(entityClassName, id);
    }



}
