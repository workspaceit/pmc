package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.AdvertisementPrices;
import com.workspaceit.pmc.service.AdvertisementPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Tomal on 1/10/2018.
 */
@Controller
@RequestMapping(ControllerUriPrefix.ADMIN+"/advertisementPrices")
public class AdvertisementPricesController {

    private AdvertisementPricesService advertisementPricesService;

    @Autowired
    public void setAdvertisementPricesService(AdvertisementPricesService advertisementPricesService) {
        this.advertisementPricesService = advertisementPricesService;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(){

        List<AdvertisementPrices> pricesList = this.advertisementPricesService.getAll();
        ModelAndView model = new ModelAndView("admin/advertisementPrices/edit");
        for(AdvertisementPrices ad:pricesList)
        {
            System.out.println(ad.getDescription());
        }
        model.addObject("pricesList",pricesList);
        return model;
    }
}
