package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.ENTITY_STATE;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.dao.AdvertisementDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional(rollbackFor = Exception.class)
    public Advertisement getById(Advertisement advertisement){
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