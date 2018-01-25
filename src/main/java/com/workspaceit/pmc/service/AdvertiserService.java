package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdvertiserDao;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.advertiser.AdvertiserForm;
import com.workspaceit.pmc.validation.advertiser.AdvertiserUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by mi_rafi on 1/4/18.
 */
@Service
public class AdvertiserService {
    private AdvertiserDao advertiserDao;

    private AdvertisersOtherImageService advertisersOtherImageService;
    private StateService stateService;
    private CityService cityService;
    private EventService eventService;
    private LocationService locationService;

    @Autowired
    public void setAdvertiserDao(AdvertiserDao advertiserDao) {
        this.advertiserDao = advertiserDao;
    }

    @Autowired
    public void setAdvertisersOtherImageService(AdvertisersOtherImageService advertisersOtherImageService) {
        this.advertisersOtherImageService = advertisersOtherImageService;
    }

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }
    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }
    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }




    @Transactional(rollbackFor = Exception.class)
    public Advertiser create(AdvertiserForm advertiserForm, Admin admin){
        City city = this.cityService.getById(advertiserForm.getCityId());
        State state = this.stateService.getById(advertiserForm.getStateId());

        Advertiser advertiser = new Advertiser();
        advertiser.setName(advertiserForm.getName());
        advertiser.setAddress(advertiserForm.getAddress());

        advertiser.setCity(city);
        advertiser.setState(state);
        advertiser.setZip(advertiserForm.getZip());
        advertiser.setPhone(advertiserForm.getPhone());
        advertiser.setWebsite( advertiserForm.getWebsite());
        advertiser.setRuntimeStarts(advertiserForm.getRuntimeStarts());
        advertiser.setRuntimeEnds(advertiserForm.getRuntimeEnds());
        advertiser.setCreatedBy(admin);
        advertiser.setIsAllLocationSelected(advertiserForm.getIsAllLocationSelected());
        advertiser.setAllEventSelected(advertiserForm.getIsAllEventSelected());

        if(!advertiserForm.getIsAllLocationSelected()) {
            List<Location> locations = this.locationService.getAll(advertiserForm.getLocationIds());
            advertiser.setLocations(new HashSet<>(locations));
        }
        if(!advertiserForm.getIsAllEventSelected()){
            List<Event> events = this.eventService.getAll(advertiserForm.getEventIds());
            advertiser.setEvents(new HashSet<>(events));
        }

        this.create(advertiser);

        this.advertisersOtherImageService.create(advertiser,advertiserForm.getOtherImage(),admin);
        return advertiser;
    }

    @Transactional(rollbackFor = Exception.class)
    public Advertiser update(int id, AdvertiserUpdateForm advertiserForm, Admin admin)throws EntityNotFound{
        City city = this.cityService.getById(advertiserForm.getCityId());
        State state = this.stateService.getById(advertiserForm.getStateId());

        Advertiser advertiser = this.getById(id);

        advertiser.setName(advertiserForm.getName());
        advertiser.setAddress(advertiserForm.getAddress());
        advertiser.setCity(city);
        advertiser.setState(state);
        advertiser.setZip(advertiserForm.getZip());
        advertiser.setPhone(advertiserForm.getPhone());
        advertiser.setWebsite( advertiserForm.getWebsite());
        advertiser.setRuntimeStarts(advertiserForm.getRuntimeStarts());
        advertiser.setRuntimeEnds(advertiserForm.getRuntimeEnds());
        advertiser.setIsAllLocationSelected(advertiserForm.getIsAllLocationSelected());
        advertiser.setAllEventSelected(advertiserForm.getIsAllEventSelected());

        if(!advertiserForm.getIsAllLocationSelected()) {
            List<Location> locations = this.locationService.getAll(advertiserForm.getLocationIds());
            advertiser.setLocations(new HashSet<>(locations));
        }else {
            Set<Location> locations = advertiser.getLocations();
            if(locations!=null && locations.size()>0)locations.clear();
        }
        if(!advertiserForm.getIsAllEventSelected()){
            List<Event> events = this.eventService.getAll(advertiserForm.getEventIds());
            advertiser.setEvents(new HashSet<>(events));
        }else {
            Set<Event> events = advertiser.getEvents();
            if(events!=null && events.size()>0)events.clear();
        }

        this.update(advertiser);

        if(advertiserForm.getRemoveOtherImageIds()!=null && advertiserForm.getRemoveOtherImageIds().length>0){
            this.advertisersOtherImageService.deleteById(advertiser,advertiserForm.getRemoveOtherImageIds());
        }

        if(advertiserForm.getOtherImage()!=null && advertiserForm.getOtherImage().length>0){
            this.advertisersOtherImageService.create(advertiser,advertiserForm.getOtherImage(),admin);
        }
        return advertiser;
    }


    @Transactional(readOnly = true)
    public Advertiser getById(int id){
        return this.advertiserDao.getById(id);
    }

    @Transactional(readOnly = true)
    private Advertiser getAdvertiser(int id) throws EntityNotFound{
        Advertiser advertiser =  this.advertiserDao.getById(id);
        if(advertiser==null){
            throw new EntityNotFound("No advertiser found by id :"+id);
        }
        return advertiser;
    }
    @Transactional(rollbackFor = Exception.class)
    public void create(Advertiser advertiser){
        this.advertiserDao.insert(advertiser);
    }
    @Transactional(rollbackFor = Exception.class)
    public void update(Advertiser advertiser){
        this.advertiserDao.update(advertiser);
    }
    @Transactional(readOnly = true)
    public List<Advertiser> getAll(){
        return this.advertiserDao.getAll();
    }

    @Transactional
    public List<Advertiser> getSuggestedAdvertisers(String searchTerm){
        return this.advertiserDao.getSuggestedAdvertisers(searchTerm);
    }


    @Transactional
    public List<Advertiser> getAll(Integer[] ids){
        return this.advertiserDao.getAll(ids);
    }


    @Transactional
    public List<Advertiser> getByEventId(int eventId){
        return this.advertiserDao.getByEventId(eventId);
    }
    @Transactional
    public List<Integer> getIdByEventId(int eventId){
        List<Advertiser> advertisers =  this.getByEventId(eventId);
        if(advertisers==null || advertisers.size()==0){
            return new ArrayList<>(0);
        }
        return advertisers.stream().map(Advertiser::getId).collect(Collectors.toList());
    }
    @Transactional
    public List<Advertiser> getByLocationId(int locationId){
        return this.advertiserDao.getByLocationId(locationId);
    }
    @Transactional
    public List<Integer> getIdByLocationId(int locationId){
        List<Advertiser> advertisers =  this.getByLocationId(locationId);
        if(advertisers==null || advertisers.size()==0){
            return new ArrayList<>(0);
        }
        return advertisers.stream().map(Advertiser::getId).collect(Collectors.toList());
    }

}
