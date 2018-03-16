package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import com.workspaceit.pmc.dao.SentSlideShowDao;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.SentSlideshow;
import com.workspaceit.pmc.exception.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class SentSlideShowService {

    private SentSlideShowDao sentSlideShowDao;


    private EventImageService eventImageService;

    @Autowired
    public void setEventImageService(EventImageService eventImageService) {
        this.eventImageService = eventImageService;
    }

    @Autowired
    public void setSentSlideShowDao(SentSlideShowDao sentSlideShowDao) {
        this.sentSlideShowDao = sentSlideShowDao;
    }

    /*public SentSlideshow*/

    @Transactional
    public SentSlideshow saveByEmail(String email,String message, int[] imageIDs, Photographer sentBy, Event event){
        SentSlideshow sentSlideshow = new SentSlideshow();
        sentSlideshow.setAddress(email);
        sentSlideshow.setType( PopupAdConstant.EMAIL);
        sentSlideshow.setPhotographer(sentBy);
        sentSlideshow.setEvent(event);
        sentSlideshow.setIdentifier(UUID.randomUUID().toString());
        sentSlideshow.setMessage(message);
        Set<EventImage> eventImageSet=new HashSet<EventImage>();
        for(int imageId:imageIDs){
            try {
                EventImage eventImage = eventImageService.getEventImage(imageId);
                eventImageSet.add(eventImage);
            } catch (EntityNotFound entityNotFound) {
                entityNotFound.printStackTrace();
            }
        }
        sentSlideshow.setEventImages(eventImageSet);
        sentSlideshow.setSeen(false);
        this.sentSlideShowDao.save(sentSlideshow);
        return sentSlideshow;
    }
    @Transactional
    public SentSlideshow saveBySms(String phoneNum,String message, int[] imageIDs, Photographer sentBy, Event event){
        SentSlideshow sentSlideshow = new SentSlideshow();
        sentSlideshow.setAddress(phoneNum);
        sentSlideshow.setType( PopupAdConstant.SMS);
        sentSlideshow.setPhotographer(sentBy);
        sentSlideshow.setEvent(event);
        sentSlideshow.setIdentifier(UUID.randomUUID().toString());
        sentSlideshow.setMessage(message);
        Set<EventImage> eventImageSet=new HashSet<EventImage>();
        for(int imageId:imageIDs){
            try {
                EventImage eventImage = eventImageService.getEventImage(imageId);
                eventImageSet.add(eventImage);
            } catch (EntityNotFound entityNotFound) {
                entityNotFound.printStackTrace();
            }
        }
        sentSlideshow.setEventImages(eventImageSet);
        sentSlideshow.setSeen(false);
        this.sentSlideShowDao.save(sentSlideshow);
        return sentSlideshow;
    }


}
