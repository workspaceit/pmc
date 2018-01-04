package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.EventDao;
import com.workspaceit.pmc.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mi_rafi on 1/3/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EventService {
    private EventDao eventDao;

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public Event getById(int id){
        return this.eventDao.getById(id);
    }
    public List<Event> getAll(){
        return this.eventDao.getAll();
    }
}
