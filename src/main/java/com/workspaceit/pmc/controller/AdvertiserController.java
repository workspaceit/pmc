package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAdQuantityPrice;
import com.workspaceit.pmc.entity.advertisement.popup.PopupAd;
import com.workspaceit.pmc.entity.advertisement.popup.PopupAdQuantityPrice;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowAd;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowQuantityPrice;
import com.workspaceit.pmc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.*;

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
    private AdvertisementPricesService advertisementPricesService;

    private GalleryAdService galleryAdService;
    private SlideShowService slideShowService;
    private PopUpAdsService popUpAdsService;

    private List<Double> fadeInList;
    private List<Double>fadeOutList;

    /*Value are inserted in @PostConstruct function*/
    private Set<Integer> durations;

    @PostConstruct
    private void initConfig(){
        Set<Integer> durations = new HashSet<>();
        for(int i=1;i<=5;i++){
            durations.add(i);
        }

        this.setDurations(durations);
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

    //Do not autowired check initConfig()
    public void setDurations(Set<Integer> durations) {
        this.durations = durations;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        List<Location> locations = this.locationService.getAll();
        List<State> states = this.stateService.getAll();
        List<City> cities = this.cityService.getAllNameAcs();
        List<Event> events = this.eventService.getAll();
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
        model.addObject("durations",durations);

          /*For location Modal Page*/
        model.addObject("fadeInList",this.fadeInList);
        model.addObject("fadeOutList",this.fadeOutList);

        return model;
    }
    @RequestMapping("/invoice/{id}")
    public ModelAndView invoice(@PathVariable("id") int advertiserId){

        Advertiser advertiser =  this.advertiserService.getById(advertiserId);

        if(advertiser==null){
            return new ModelAndView("redirect:"+"/admin/advertiser/all");
        }

        GalleryAd galleryAd = this.galleryAdService.getByAdvertiserId(advertiserId);
        SlideshowAd slideshowAd = this.slideShowService.getByAdvertiserId(advertiserId);
        PopupAd popupAdSms  = this.popUpAdsService.getByAdvertiserId(advertiserId, PopupAdConstant.SMS);
        PopupAd popupAdEmail  = this.popUpAdsService.getByAdvertiserId(advertiserId, PopupAdConstant.EMAIL);

        /* Price and quantity */
        Map<GalleryAdsConstant, GalleryAdQuantityPrice>  galleryQuantityPrice = galleryAd.getGalleryQuantityPrice();
        Map<SlideshowAdsConstant,SlideshowQuantityPrice> slideshowQuantityPrice = slideshowAd.getQuantityPrice();
        Map<PopupAdConstant,PopupAdQuantityPrice> popupAdSmsQuantityPrice = popupAdSms.getQuantityPrice();
        Map<PopupAdConstant,PopupAdQuantityPrice> popupAdEmailQuantityPrice = popupAdEmail.getQuantityPrice();

        Map<Object,Float> prices = new HashMap<>();
        Map<Object,Integer> quantities = new HashMap<>();
        float totalPrice = 0;
        float discount = 0;

        prices.put(GalleryAdsConstant.BACKGROUND_IMAGE,0f);
        prices.put(GalleryAdsConstant.TOP_AD_BANNER,0f);
        prices.put(GalleryAdsConstant.BOTTOM_AD_BANNER,0f);
        prices.put(SlideshowAdsConstant.BANNER,0f);
        prices.put(SlideshowAdsConstant.VIDEO,0f);
        prices.put(PopupAdConstant.SMS,0f);
        prices.put(PopupAdConstant.EMAIL,0f);



        quantities.put(GalleryAdsConstant.BACKGROUND_IMAGE,0);
        quantities.put(GalleryAdsConstant.TOP_AD_BANNER,0);
        quantities.put(GalleryAdsConstant.BOTTOM_AD_BANNER,0);

        quantities.put(SlideshowAdsConstant.BANNER,0);
        quantities.put(SlideshowAdsConstant.VIDEO,0);
        quantities.put(PopupAdConstant.SMS,0);
        quantities.put(PopupAdConstant.EMAIL,0);



        if(galleryQuantityPrice!=null){
            prices.put(GalleryAdsConstant.BACKGROUND_IMAGE,galleryQuantityPrice.get(GalleryAdsConstant.BACKGROUND_IMAGE).getPrice());
            prices.put(GalleryAdsConstant.TOP_AD_BANNER,galleryQuantityPrice.get(GalleryAdsConstant.TOP_AD_BANNER).getPrice());
            prices.put(GalleryAdsConstant.BOTTOM_AD_BANNER,galleryQuantityPrice.get(GalleryAdsConstant.BOTTOM_AD_BANNER).getPrice());

            quantities.put(GalleryAdsConstant.BACKGROUND_IMAGE,galleryQuantityPrice.get(GalleryAdsConstant.BACKGROUND_IMAGE).getQuantity());
            quantities.put(GalleryAdsConstant.TOP_AD_BANNER,galleryQuantityPrice.get(GalleryAdsConstant.TOP_AD_BANNER).getQuantity());
            quantities.put(GalleryAdsConstant.BOTTOM_AD_BANNER,galleryQuantityPrice.get(GalleryAdsConstant.BOTTOM_AD_BANNER).getQuantity());
        }



        if(slideshowQuantityPrice!=null){
            prices.put(SlideshowAdsConstant.BANNER,slideshowQuantityPrice.get(SlideshowAdsConstant.BANNER).getPrice());
            prices.put(SlideshowAdsConstant.VIDEO,slideshowQuantityPrice.get(SlideshowAdsConstant.VIDEO).getPrice());

            quantities.put(SlideshowAdsConstant.BANNER,slideshowQuantityPrice.get(SlideshowAdsConstant.BANNER).getQuantity());
            quantities.put(SlideshowAdsConstant.VIDEO,slideshowQuantityPrice.get(SlideshowAdsConstant.VIDEO).getQuantity());
        }
        if(popupAdSmsQuantityPrice!=null){
            prices.put(PopupAdConstant.SMS,popupAdSmsQuantityPrice.get(PopupAdConstant.SMS).getPrice());
            quantities.put(PopupAdConstant.SMS,popupAdSmsQuantityPrice.get(PopupAdConstant.SMS).getQuantity());
        }
        if(popupAdEmailQuantityPrice!=null){
            prices.put(PopupAdConstant.EMAIL,popupAdEmailQuantityPrice.get(PopupAdConstant.EMAIL).getPrice());
            quantities.put(PopupAdConstant.EMAIL,popupAdEmailQuantityPrice.get(PopupAdConstant.EMAIL).getQuantity());
        }


        for(GalleryAdsConstant key :GalleryAdsConstant.values()){
            totalPrice+=prices.get(key)*quantities.get(key);
        }
        for(SlideshowAdsConstant key :SlideshowAdsConstant.values()){
            totalPrice+=prices.get(key)*quantities.get(key);
        }
        for(PopupAdConstant key :PopupAdConstant.values()){
            totalPrice+=prices.get(key)*quantities.get(key);
        }

        ModelAndView model = new ModelAndView("admin/advertiser/invoice");


        model.addObject("advertiser",advertiser);
        model.addObject("galleryAd",galleryAd);
        model.addObject("slideshowAd",slideshowAd);
        model.addObject("popupAdSms",popupAdSms);
        model.addObject("popupAdEmail",popupAdEmail);

        /* Price and quantity */
        model.addObject("prices",prices);
        model.addObject("quantities",quantities);
        model.addObject("totalPrice",totalPrice);
        model.addObject("discount",discount);

        /* Number format settings values */
        model.addObject("currencyCode","USD");
        model.addObject("currencySymbol","$");
        model.addObject("maxFractionDigits",2);


        return model;
    }
}