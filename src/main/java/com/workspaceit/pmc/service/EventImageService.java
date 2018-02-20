package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.EventImageDao;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.exception.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by anik on 2/15/18.
 */

@Service
public class EventImageService {

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

}
