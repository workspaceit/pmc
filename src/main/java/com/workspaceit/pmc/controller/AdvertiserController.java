package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mi_rafi on 1/3/18.
 */
@Controller
@RequestMapping(ControllerUriPrefix.ADMIN+"/advertiser")
public class AdvertiserController {
    private AdvertiserService advertiserService;
    private LocationService locationService;
    private StateService stateService;
    private CityService cityService;
    private EventService eventService;

    private GalleryAdService galleryAdService;
    private SlideShowService slideShowService;
    private PopUpAdsService popUpAdsService;

    private List<Double> fadeInList;
    private List<Double>fadeOutList;

    /*Value are inserted in @PostConstruct function*/
    private Set<Integer> durations;

    @PostConstruct
    private void initConfig(){
        durations = new HashSet<>();
        for(int i=1;i<=5;i++){
            durations.add(i);
        }

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
    public void setGalleryAdService(GalleryAdService galleryAdService) {
        this.galleryAdService = galleryAdService;
    }

    @Autowired
    public void setSlideShowService(SlideShowService slideShowService) {
        this.slideShowService = slideShowService;
    }

    @Autowired
    public void setPopUpAdsService(PopUpAdsService popUpAdsService) {
        this.popUpAdsService = popUpAdsService;
    }


    @Autowired
    public void setFadeInList(List<Double> fadeInList) {
        this.fadeInList = fadeInList;
    }

    @Autowired
    public void setFadeOutList(List<Double> fadeOutList) {
        this.fadeOutList = fadeOutList;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        List<Location> locations = this.locationService.getAll();
        List<State> states = this.stateService.getAll();
        List<City> cities = this.cityService.getAllNameAcs();
        List<Event> events = this.eventService.getAll();

        ModelAndView model = new ModelAndView("admin/advertiser/add");

        model.addObject("events",events);
        model.addObject("locations",locations);
        model.addObject("states",states);
        model.addObject("cities",cities);
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


        model.addObject("advertisers",advertisers);

        return model;
    }
    @RequestMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int advertiserId){

        Advertiser advertiser =  this.advertiserService.getById(advertiserId);

        if(advertiser==null){
            return new ModelAndView("redirect:"+"/admin/advertiser/all");
        }

        List<Location> locations = this.locationService.getAll();
        List<State> states = this.stateService.getAll();
        List<City> cities = this.cityService.getAllNameAcs();
        List<Event> events = this.eventService.getAll();


        GalleryAd galleryAd = this.galleryAdService.getByAdvertiserId(advertiserId);
        SlideshowAd slideshowAd = this.slideShowService.getByAdvertiserId(advertiserId);
        PopupAd popupAd  = this.popUpAdsService.getByAdvertiserId(advertiserId);

        ModelAndView model = new ModelAndView("admin/advertiser/edit");
        model.addObject("advertiser",advertiser);
        model.addObject("galleryAd",galleryAd);
        model.addObject("slideshowAd",slideshowAd);
        model.addObject("popupAd",popupAd);

        model.addObject("events",events);
        model.addObject("locations",locations);
        model.addObject("states",states);
        model.addObject("cities",cities);
        model.addObject("durations",durations);

        return model;
    }
}