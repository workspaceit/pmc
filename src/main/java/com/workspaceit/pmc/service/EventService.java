package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.EventDao;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.event.EventForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mi_rafi on 1/3/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EventService {

    private EventDao eventDao;
    private WatermarkService watermarkService;
    private AdvertiserService advertiserService;
    private PhotographerService photographerService;
    private VenueService venueService;
    private FileService fileService;

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
    @Autowired
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }
    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }
    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }
    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }
    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public Event getById(int id){
        return this.eventDao.getById(id);
    }
    public List<Event> getAll(){
        return this.eventDao.getAll();
    }

    @Transactional
    public List<Event> getAll(Integer[] ids){
        return this.eventDao.getAll(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Event event){
        this.eventDao.insert(event);
    }

    public Event create(EventForm eventForm) throws EntityNotFound {
        Event event = getEventFromEventForm(eventForm);
        create(event);
        return event;
    }

    private Event getEventFromEventForm(EventForm eventForm) throws EntityNotFound {
        Event event = new Event();

        Venue venue =  venueService.getVenue(eventForm.getVenueId());
        List<Watermark> watermarks = watermarkService.getAll(eventForm.getWatermarkIds());
        List<Photographer> photographers = photographerService.getAll(eventForm.getPhotographerIds());
        List<Advertiser> advertisers = advertiserService.getAll(eventForm.getAdvertiserIds());
        Integer eventImageToken = eventForm.getImageToken();
        String eventImageName = this.fileService.copyFile(eventImageToken);

        Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        event.setName(eventForm.getEventName());
        event.setStartsAt(eventForm.getStartDate());
        event.setEndsAt(eventForm.getEndDate());
        event.setVenue(venue);
        if(photographers != null) {
            event.setPhotographers(new HashSet<>(photographers));
        }
        if(watermarks != null) {
            event.setWatermarks(new HashSet<>(watermarks));
        }
        if(advertisers != null) {
            event.setAdvertisers(new HashSet<>(advertisers));
        }
        event.setEventPhoto(eventImageName);
        event.setEventPrivate(eventForm.getIsPrivate());
        event.setCreatedBy(admin);
        return event;
    }

    @Transactional
    public Event update(Integer eventId, EventForm eventForm) throws EntityNotFound {
        Event event = eventDao.getById(eventId);
        Venue venue =  venueService.getVenue(eventForm.getVenueId());
        List<Watermark> watermarks = watermarkService.getAll(eventForm.getWatermarkIds());
        List<Photographer> photographers = photographerService.getAll(eventForm.getPhotographerIds());
        List<Advertiser> advertisers = advertiserService.getAll(eventForm.getAdvertiserIds());
        Integer eventImageToken = eventForm.getImageToken();
        if(eventImageToken != 0){
            String eventImageName = this.fileService.copyFile(eventImageToken);
            event.setEventPhoto(eventImageName);
        }
        event.setName(eventForm.getEventName());
        event.setStartsAt(eventForm.getStartDate());
        event.setEndsAt(event.getEndsAt());
        event.setVenue(venue);
        event.setEventPrivate(eventForm.getIsPrivate());

        if(photographers != null) {
            event.setPhotographers(new HashSet<>(photographers));
        }
        else {
            event.setPhotographers(new HashSet<>());
        }
        if(advertisers != null) {
            event.setAdvertisers(new HashSet<>(advertisers));
        }
        else{
            event.setAdvertisers(new HashSet<>());
        }
        if(watermarks != null) {
            event.setWatermarks(new HashSet<>(watermarks));
        }
        else {
            event.setWatermarks(new HashSet<>());
        }
        eventDao.update(event);
        return event;
    }

}
