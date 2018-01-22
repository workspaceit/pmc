package sample;

import com.workspaceit.pmc.config.WebConfig;
import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant;
import com.workspaceit.pmc.dao.AdvertiserDao;
import com.workspaceit.pmc.dao.GalleryAdDao;
import com.workspaceit.pmc.entity.AdvertisementPrices;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAdQuantityPrice;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowAd;
import com.workspaceit.pmc.entity.advertisement.slideshow.SlideshowQuantityPrice;
import com.workspaceit.pmc.service.AdvertisementPricesService;
import com.workspaceit.pmc.service.AdvertiserService;
import com.workspaceit.pmc.service.LocationService;
import com.workspaceit.pmc.service.SlideShowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

/**
 * Created by mi_rafi on 1/4/18.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class SampleTest {

    private AdvertisementPricesService advertisementPricesService;
    private LocationService locationService;
    private AdvertiserDao advertiserDao;
    private GalleryAdDao galleryAdDao;
    private SlideShowService slideShowService;
    private AdvertiserService advertiserService;

    @Autowired
    public void setAdvertisementPricesService(AdvertisementPricesService advertisementPricesService) {
        this.advertisementPricesService = advertisementPricesService;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Autowired
    public void setAdvertiserDao(AdvertiserDao advertiserDao) {
        this.advertiserDao = advertiserDao;
    }

    @Autowired
    public void setSlideShowService(SlideShowService slideShowService) {
        this.slideShowService = slideShowService;
    }

    @Autowired
    public void setGalleryAdDao(GalleryAdDao galleryAdDao) {
        this.galleryAdDao = galleryAdDao;
    }
    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }



    @Test
    @Transactional
    public void test() {
        System.out.println("*******************");

        SlideshowAd ssa = this.slideShowService.getByAdvertiserId(4);
        Map<SlideshowAdsConstant,SlideshowQuantityPrice> g = ssa.getQuantityPrice();
        if(g ==null){
            return;
        }
        Set<SlideshowAdsConstant> keys = g.keySet();
        for(SlideshowAdsConstant key: keys){

            System.out.println(g.get(key));
        }
        System.out.println("*******************");

    }
}
