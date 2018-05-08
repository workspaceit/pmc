package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.advertisement.*;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.Section;
import com.workspaceit.pmc.entity.advertisement.SectionResource;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.entity.advertisement.popup.PopupAd;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowAd;
import com.workspaceit.pmc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by mi_rafi on 1/3/18.
 */
@Controller
@RequestMapping(ControllerUriPrefix.ADMIN+"/advertiser")
public class AdvertiserController {
    private AdvertisementService advertisementService;
    private AdvertiserService advertiserService;
    private LocationService locationService;
    private StateService stateService;
    private CityService cityService;
    private EventService eventService;
    private AdvertisementPricesService advertisementPricesService;
    private AdvertisementPriceAndQuantityService advertisementPriceAndQuantityService;
    private AdvertiserTransactionService advertiserTransactionService;


    private Environment environment;

    private List<Double> fadeInList;
    private List<Double>fadeOutList;

    private Set<Integer> durations;



    @Autowired
    public void setAdvertisementService(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setAdvertisementPricesService(AdvertisementPricesService advertisementPricesService) {
        this.advertisementPricesService = advertisementPricesService;
    }


    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    public void setFadeInList(List<Double> fadeInList) {
        this.fadeInList = fadeInList;
    }

    @Autowired
    public void setFadeOutList(List<Double> fadeOutList) {
        this.fadeOutList = fadeOutList;
    }

    @Autowired
    public void setAdvertisementPriceAndQuantityService(AdvertisementPriceAndQuantityService advertisementPriceAndQuantityService) {
        this.advertisementPriceAndQuantityService = advertisementPriceAndQuantityService;
    }

    @Autowired
    public void setAdvertiserTransactionService(AdvertiserTransactionService advertiserTransactionService) {
        this.advertiserTransactionService = advertiserTransactionService;
    }

    @Autowired
    @Qualifier("durationList")
    public void setDurations(Set<Integer> durations) {
        this.durations = durations;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        List<Location> locations = this.locationService.getActiveLocations();
        List<State> states = this.stateService.getAll();
        List<City> cities = this.cityService.getAllNameAcs();
        List<Event> events = this.eventService.getActiveEvents();
        Map<GalleryAdsConstant,AdvertisementPrices> galleryAdsPrices = this.advertisementPricesService.getGalleryAdPrice();
        Map<SlideshowAdsConstant,AdvertisementPrices> slideshowAdPrice = this.advertisementPricesService.getSlideshowAdPrice();
        Map<PopupAdConstant,AdvertisementPrices> popupAdPrice = this.advertisementPricesService.getPopupAdPrice();


        ModelAndView model = new ModelAndView("admin/advertiser/add");

        model.addObject("events",events);
        model.addObject("locations",locations);
        model.addObject("states",states);
        model.addObject("cities",cities);
        model.addObject("galleryAdsPrices",galleryAdsPrices);
        model.addObject("slideshowAdPrice",slideshowAdPrice);
        model.addObject("popupAdPrice",popupAdPrice);
        model.addObject("durations",durations);

        /*For location Modal Page*/
        model.addObject("fadeInList",this.fadeInList);
        model.addObject("fadeOutList",this.fadeOutList);
        return model;
    }
    @RequestMapping("/all")
    public ModelAndView all(){
       List<Advertiser> advertisers =  this.advertiserService.getAll();
       ModelAndView model = new ModelAndView("admin/advertiser/all");
       model.addObject("advertisers", advertisers);
       return model;
    }

    @RequestMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int advertiserId){

        Advertiser advertiser =  this.advertiserService.getById(advertiserId);

        if(advertiser==null){
            return new ModelAndView("redirect:"+"/admin/advertiser/all");
        }

        Map<ADVERTISEMENT_TYPE,Advertisement>  advertisements =  this.advertisementService.getMapByAdvertiserId(advertiser.getId());

        List<Location> locations = this.locationService.getAll();
        List<State> states = this.stateService.getAll();
        List<Event> events = this.eventService.getAll();


        Advertisement galleryAd = advertisements.get(ADVERTISEMENT_TYPE.GALLERY);
        Advertisement slideshowAd = advertisements.get(ADVERTISEMENT_TYPE.SLIDESHOW);
        Advertisement popupAdSms = advertisements.get(ADVERTISEMENT_TYPE.POPUP_SMS);
        Advertisement popupAdEmail = advertisements.get(ADVERTISEMENT_TYPE.POPUP_EMAIL);


        ModelAndView model = new ModelAndView("admin/advertiser/edit");


        model.addObject("advertiser",advertiser);
        model.addObject("advertisements",advertisements);

        model.addObject("events",events);
        model.addObject("locations",locations);
        model.addObject("states",states);
        model.addObject("durations",durations);


        model.addObject("galleryAd",galleryAd);
        model.addObject("slideshowAd",slideshowAd);
        model.addObject("popupAdSms",popupAdSms);
        model.addObject("popupAdEmail",popupAdEmail);

        model.addObject("frontEndBaseUrl",environment.getFrontEndAppBaseUrl());


          /*For location Modal Page*/
        model.addObject("fadeInList",this.fadeInList);
        model.addObject("fadeOutList",this.fadeOutList);

         /* Number format settings values */
        model.addObject("currencyCode","USD");
        model.addObject("currencySymbol","$");
        model.addObject("maxFractionDigits",2);

        return model;
    }

   /* @RequestMapping("/update-old/{id}")
    public ModelAndView updateOld(@PathVariable("id") int advertiserId){

        Advertiser advertiser =  this.advertiserService.getById(advertiserId);

        if(advertiser==null){
            return new ModelAndView("redirect:"+"/admin/advertiser-old/all");
        }

        List<Location> locations = this.locationService.getAll();
        List<State> states = this.stateService.getAll();
        List<City> cities = this.cityService.getAllNameAcs();
        List<Event> events = this.eventService.getAll();


        GalleryAd galleryAd = this.galleryAdService.getByAdvertiserId(advertiserId);
        SlideshowAd slideshowAd = this.slideShowService.getByAdvertiserId(advertiserId);
        PopupAd popupAdSms  = this.popUpAdsService.getByAdvertiserId(advertiserId, PopupAdConstant.SMS);
        PopupAd popupAdEmail  = this.popUpAdsService.getByAdvertiserId(advertiserId, PopupAdConstant.EMAIL);

        ModelAndView model = new ModelAndView("admin/advertiser/edit");
        model.addObject("advertiser",advertiser);
        model.addObject("galleryAd",galleryAd);
        model.addObject("slideshowAd",slideshowAd);
        model.addObject("popupAdSms",popupAdSms);
        model.addObject("popupAdEmail",popupAdEmail);

        model.addObject("events",events);
        model.addObject("locations",locations);
        model.addObject("states",states);
        model.addObject("cities",cities);

          *//*For location Modal Page*//*
        model.addObject("fadeInList",this.fadeInList);
        model.addObject("fadeOutList",this.fadeOutList);
        model.addObject("durations",durations);

         *//* Number format settings values *//*
        model.addObject("currencyCode","USD");
        model.addObject("currencySymbol","$");
        model.addObject("maxFractionDigits",2);

        return model;
    }*/

}