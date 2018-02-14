package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.CityDao;
import com.workspaceit.pmc.entity.City;
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

    @Autowired
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
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
}