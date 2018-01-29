package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.ENTITY_STATE;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.dao.AdvertisementDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class AdvertisementService {
    private AdvertisementDao advertisementDao;

    @Autowired
    public void setAdvertisementDao(AdvertisementDao advertisementDao) {
        this.advertisementDao = advertisementDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public Advertisement create(Advertisement advertisement){
        this.advertisementDao.insert(advertisement);
        return advertisement;
    }

    @Transactional
    public Advertisement getById(int id){
        return this.advertisementDao.getById(id);
    }

    @Transactional
    public List<Advertisement> getByAdvertiserId(int advertiserId){
        return this.advertisementDao.getByAdvertiserId(advertiserId);
    }
    @Transactional
    public Map<ADVERTISEMENT_TYPE,Advertisement> getMapByAdvertiserId(int advertiserId){
        Map<ADVERTISEMENT_TYPE,Advertisement> advertisementMap = new HashMap<>();
        List<Advertisement> advertisements  =  this.advertisementDao.getByAdvertiserId(advertiserId);
        for (Advertisement advertisement:advertisements) {
            advertisementMap.put(advertisement.getAdType(),advertisement);
        }

        return advertisementMap;
    }

    @Transactional(rollbackFor = Exception.class)
    public Advertisement create(int advertiserId, ADVERTISEMENT_TYPE adType, Admin admin){
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertiserId(advertiserId);
        advertisement.setState(ENTITY_STATE.ACTIVE);
        advertisement.setAdType(adType);
        advertisement.setCreatedBy(admin);

        return this.create(advertisement);
    }

}