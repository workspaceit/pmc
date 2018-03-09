package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.CommonDao;
import com.workspaceit.pmc.dao.EventDao;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.event.EventCreateForm;
import com.workspaceit.pmc.validation.event.EventUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private LocationService locationService;
    private FileService fileService;
    private CommonDao commonDao;


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
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @Transactional
    public Event getById(int id){
        return this.eventDao.getById(id);
    }
    @Transactional
    public Event getEvent(int id)throws EntityNotFound{
        Event event =  this.eventDao.getById(id);
        if(event==null)throw new EntityNotFound("Event not found by id :"+id);

        return event;
    }
    @Transactional
    public List<Event> getAll(){
        return this.eventDao.getAll();
    }

    @Transactional
    public List<Event> getByWatermarkId(int watermarkId){
        return this.eventDao.getByWatermarkId(watermarkId);
    }
    @Transactional
    public List<Event> getAll(int limit,int offset){
        return this.eventDao.getActiveEvents(limit,offset);
    }

    @Transactional
    public Integer getActiveEventCount(){
        return this.eventDao.getActiveEventCount();
    }

    @Transactional
    public Map<String,Object> getAllWithCountInMap(int limit,int offset){
        Map<String,Object> response = new HashMap<>();
        response.put("count",this.getActiveEventCount());
        response.put("events",this.getAll(limit,offset));
        return response;
    }


    @Transactional
    public List<Event> getActiveEvents(){
        return this.eventDao.getActiveEvents();
    }

    @Transactional
    public List<Event> getAll(Integer[] ids){
        return this.eventDao.getAll(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Event event){
        this.eventDao.insert(event);
    }

    public Event create(EventCreateForm eventForm) throws EntityNotFound {
        Event event = getEventFromEventForm(eventForm);
        create(event);
        return event;
    }

    public List<Event> getActiveEventsByCriteria(Integer locationId, Date filterDate,  Photographer photographer,
                                                 Integer limit, Integer offset) {
        return eventDao.getActiveEventsByCriteria(locationId, filterDate, photographer, limit, offset);
    }

    public Integer getActiveEventCountByCriteria(Integer locationId, Date date, Photographer photographer){
        return eventDao.getActiveEventCountByCriteria(locationId, date , photographer);
    }

    public Map<String, Object> getEventsByCriteriaWithCount(Integer locationId, Date filterDate, Photographer photographer,
                                                            Integer limit, Integer offset){
        Map<String,Object> eventData = new HashMap<>();
        eventData.put("count", this.getActiveEventCountByCriteria(locationId, filterDate , photographer));
        eventData.put("events", this.getActiveEventsByCriteria(locationId, filterDate, photographer, limit, offset));
        return eventData;
    }

    private Event getEventFromEventForm(EventCreateForm eventForm) throws EntityNotFound {
        Event event = new Event();

        //Venue venue =  venueService.getVenue(eventForm.getVenueId());

        Location location = this.locationService.getById(eventForm.getLocationId());
        List<Watermark> watermarks = watermarkService.getAll(eventForm.getWatermarkIds());
        List<Photographer> photographers = photographerService.getAll(eventForm.getPhotographerIds());
        List<Advertiser> advertisers = advertiserService.getAll(eventForm.getAdvertiserIds());
        Integer eventImageToken = eventForm.getImageToken();
        String eventImageName = this.fileService.copyFile(eventImageToken);

        Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        event.setName(eventForm.getEventName());
        event.setStartsAt(eventForm.getStartDate());
        event.setEndsAt(eventForm.getEndDate());
        event.setLocation(location);
        event.setIsAllAdvertiser(eventForm.getIsAllAdvertiserSelected());
        //event.setVenue(venue);


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
        event.setActive(true);
        event.setDeleted(false);
        return event;
    }

    @Transactional
    public Event update(Integer eventId, EventUpdateForm eventUpdateForm) throws EntityNotFound {
        Event event = eventDao.getById(eventId);
        //Venue venue =  venueService.getVenue(eventForm.getVenueId());
        Location location = this.locationService.getById(eventUpdateForm.getLocationId());
        List<Watermark> watermarks = watermarkService.getAll(eventUpdateForm.getWatermarkIds());
        List<Photographer> photographers = photographerService.getAll(eventUpdateForm.getPhotographerIds());
        List<Advertiser> advertisers = advertiserService.getAll(eventUpdateForm.getAdvertiserIds());
        Integer eventImageToken = eventUpdateForm.getImageToken();
        if(eventImageToken != 0 && eventImageToken != - 1){
            String eventImageName = this.fileService.copyFile(eventImageToken);
            event.setEventPhoto(eventImageName);
        }
        event.setName(eventUpdateForm.getEventName());
        event.setStartsAt(eventUpdateForm.getStartDate());
        event.setEndsAt(event.getEndsAt());
        //event.setVenue(venue);
        event.setLocation(location);
        event.setEventPrivate(eventUpdateForm.getIsPrivate());
        event.setIsAllAdvertiser(eventUpdateForm.getIsAllAdvertiserSelected());
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
    public List<Event> getByPhotographerId(int photographerId){
       return this.eventDao.getByPhotographerId(photographerId);
    }
    public List<Event> getByAdvertiserId(Integer advertiserId){
        return this.eventDao.getByAdvertiserId(advertiserId);
    }
    @Transactional
    public void update(Event event){
        this.eventDao.update(event);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeWatermarkFromEvent(int watermarkId) {
        List<Event> events =   this.getByWatermarkId(watermarkId);
        if(events==null){
            return;
        }

        this.eventDao.clearCurrentSessionFirstLevelCache();

        for(Event event:events){
            Event tmpEvent = this.getById(event.getId());
            Set<Watermark> watermarks =  tmpEvent.getWatermarks();
            watermarks.removeIf(watermark -> (watermark.getId() == watermarkId));
            this.update(event);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void removePhotographerFromEvent(int photographerId) {
        List<Event> events =   this.getByPhotographerId(photographerId);
        if(events==null){
            return;
        }

        this.eventDao.clearCurrentSessionFirstLevelCache();

        for(Event event:events){
            Event tmpEvent = this.getById(event.getId());
            Set<Photographer> photographers =  tmpEvent.getPhotographers();
            photographers.removeIf(photographer -> (photographer.getId() == photographerId));
            this.update(event);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void removeAdvertiserFromEvent(int advertiserId) {
        List<Event> events =   this.getByAdvertiserId(advertiserId);
        if(events==null){
            return;
        }
        this.eventDao.clearCurrentSessionFirstLevelCache();
        for(Event event:events){
            Event tmpEvent = this.getById(event.getId());
            Set<Advertiser> advertisers =  tmpEvent.getAdvertisers();
            advertisers.removeIf(advertiser -> (advertiser.getId() == advertiserId));
            this.update(tmpEvent);
        }
    }
}
