package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.ENTITY_STATE;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.dao.AdvertisementDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<Advertisement> getByAdvertiserId(List<Integer> advertiserId){
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
    @Transactional
    public Advertisement getByAdvertiserIdAndType(int advertiserId,ADVERTISEMENT_TYPE adType){
        Advertisement advertisement  =  this.advertisementDao.getByAdvertiserIdAndType(advertiserId,adType);
        return advertisement;
    }
    @Transactional
    public List<Advertisement> getByAdvertiserIdAndType(List<Integer> advertiserIds,ADVERTISEMENT_TYPE adType){
        List<Advertisement> advertisements = new ArrayList<>();
        for(Integer advertiserId:advertiserIds){

            Advertisement advertisement = this.getByAdvertiserIdAndType(advertiserId,adType);
            advertisements.add(advertisement);
        }

        return advertisements;
    }
    @Transactional
    public List<Map<ADVERTISEMENT_TYPE,Advertisement>> getMapByAdvertiserId(List<Integer> advertiserIds,ADVERTISEMENT_TYPE adType){
        List<Map<ADVERTISEMENT_TYPE,Advertisement>> advertisements = new ArrayList<>();
        for(Integer advertiserId:advertiserIds){

            Map<ADVERTISEMENT_TYPE,Advertisement> advertisementMap = this.getMapByAdvertiserId(advertiserId);
            advertisements.add(advertisementMap);
        }

        return advertisements;
    }
    @Transactional
    public List<Map<ADVERTISEMENT_TYPE,Advertisement>> getMapByAdvertiserId(List<Integer> advertiserIds){
        List<Map<ADVERTISEMENT_TYPE,Advertisement>> advertisements = new ArrayList<>();
        for(Integer advertiserId:advertiserIds){

            Map<ADVERTISEMENT_TYPE,Advertisement> advertisementMap = this.getMapByAdvertiserId(advertiserId);
            advertisements.add(advertisementMap);
        }

        return advertisements;
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