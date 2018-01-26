package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdvertisementDao;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}