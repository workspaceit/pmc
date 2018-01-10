package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdvertisementPricesDao;
import com.workspaceit.pmc.entity.AdvertisementPrices;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.advertisement.price.AdvertisementPricesForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tomal on 1/10/2018.
 */
@Service
public class AdvertisementPricesService {

    private AdvertisementPricesDao advertisementPricesDao;

    @Autowired
    public void setAdvertisementPricesDao(AdvertisementPricesDao advertisementPricesDao) {
        this.advertisementPricesDao = advertisementPricesDao;
    }

    @Transactional public List<AdvertisementPrices> getAll(){
        return this.advertisementPricesDao.getAll();
    }

    public AdvertisementPrices getById(int id){
        return this.advertisementPricesDao.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(AdvertisementPrices advertisementPrices){
        this.advertisementPricesDao.insert(advertisementPrices);
    }



    @Transactional(rollbackFor = Exception.class)
    public String update(int id,AdvertisementPricesForm advertisementPricesForm)throws EntityNotFound {

        //advertisementPrices1.setPrice(advertisementPricesForm.getPrice());
        int[] ids = advertisementPricesForm.getIds();
        double[] prices = advertisementPricesForm.getPrice();

        for(int i=0;i<ids.length;i++){
            AdvertisementPrices advertisementPrices1 = this.getAdvertisementPrice(ids[i]);
            advertisementPrices1.setPrice(prices[i]);
            this.update(advertisementPrices1);
        }
        return "success";
    }
    public void update(AdvertisementPrices advertisementPrices){
        this.advertisementPricesDao.update(advertisementPrices);
    }


    public AdvertisementPrices getAdvertisementPrice(int id)throws EntityNotFound{
        AdvertisementPrices advertisementPrices = this.advertisementPricesDao.getById(id);
        if(advertisementPrices==null){
            throw new EntityNotFound("Location not found by id :"+id);
        }

        return advertisementPrices;
    }


}
