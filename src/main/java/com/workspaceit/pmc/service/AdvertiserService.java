package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdvertiserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mi_rafi on 1/4/18.
 */
@Service
public class AdvertiserService {
    private AdvertiserDao advertiserDao;

    @Autowired
    public void setAdvertiserDao(AdvertiserDao advertiserDao) {
        this.advertiserDao = advertiserDao;
    }

    public void create(){

    }

}
