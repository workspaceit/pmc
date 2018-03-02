package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.EventDao;
import com.workspaceit.pmc.dao.EventImageDao;
import com.workspaceit.pmc.dataModel.RecentEvent;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.EventImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DashboardService {

    private EventImageDao eventImageDao;

    private EventDao eventDao;

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Autowired
    public void setEventImageDao(EventImageDao eventImageDao) {
        this.eventImageDao = eventImageDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public int getNumberOfEventPhotoUploaded(int days){
        if(days !=0){
            return this.eventImageDao.getAllImageCount(days);
        }else{
            return this.eventImageDao.getAllImageCount();
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public int getNumberOfEvents(int days){
        if(days !=0){
            return this.eventDao.getActiveEventCount(days);
        }else{
            return this.eventDao.getActiveEventCount();
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public List<RecentEvent> getTopActiveEvents(){
        List<RecentEvent> recentEvents = new ArrayList<>();
        List<Object[]> rows= this.eventDao.getActiveEventsWithImageCount();
        for (Object[] row:rows) {
            RecentEvent recentEvent = new RecentEvent();
            recentEvent.setId(Integer.parseInt(row[0].toString()));
            recentEvent.setName(row[1].toString());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date createDate = df.parse(row[2].toString());
                recentEvent.setCreateAt(createDate);
            }catch (ParseException e){
            }
            recentEvent.setStatus(Boolean.parseBoolean(row[3].toString()));
            recentEvent.setNoOfImages(Integer.parseInt(row[4].toString()));
            recentEvents.add(recentEvent);
        }
        return recentEvents;
    }
}
