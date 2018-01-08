package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdvertiserDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.City;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.validation.advertiser.AdvertiserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mi_rafi on 1/4/18.
 */
@Service
public class AdvertiserService {
    private AdvertiserDao advertiserDao;

    private StateService stateService;
    private CityService cityService;

    @Autowired
    public void setAdvertiserDao(AdvertiserDao advertiserDao) {
        this.advertiserDao = advertiserDao;
    }
    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }
    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
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

        this.create(advertiser);

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
