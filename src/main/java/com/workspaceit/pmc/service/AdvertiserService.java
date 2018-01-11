package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdvertiserDao;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.validation.advertiser.AdvertiserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

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
        advertiser.setAllLocations(advertiserForm.getIsAllLocationSelected());
        advertiser.setAllEvents(advertiserForm.getIsAllEventSelected());

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
    @Transactional(readOnly = true)
    public Advertiser getById(int id){
        return this.advertiserDao.getById(id);
    }
    @Transactional(rollbackFor = Exception.class)
    public void create(Advertiser advertiser){
        this.advertiserDao.insert(advertiser);
    }
    @Transactional(readOnly = true)
    public List<Advertiser> getAll(){
        return this.advertiserDao.getAll();
    }
}
