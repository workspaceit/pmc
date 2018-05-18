package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.CityDao;
import com.workspaceit.pmc.entity.City;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.entity.Venue;
import com.workspaceit.pmc.validation.city.CityForm;
import com.workspaceit.pmc.validation.venue.VenueForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mi_rafi on 1/3/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CityService {
    private CityDao cityDao;
    private StateService stateService;

    @Autowired
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    public List<City> getAllNameAcs(){
        return this.cityDao.getAllNameAcs();
    }

    public City getById(int id){
        return this.cityDao.getById(id);
    }

    public List<City> getAllCityByZip(int state_id){
        return this.cityDao.getAllCityByZip(state_id);
    }

    @Transactional
    public void create(City city) {
        cityDao.insert(city);
    }

    @Transactional(rollbackFor = Exception.class)
    public City create(CityForm cityForm){
        City city = getCityFromCityForm(cityForm);
        this.create(city);
        return city;
    }

    private City getCityFromCityForm(CityForm cityForm){

        City city = new City();
        city.setName(cityForm.getCityName());
        State state = stateService.getById(cityForm.getCityStateId());
        city.setStateId(state.getId());
        return city;
    }

}