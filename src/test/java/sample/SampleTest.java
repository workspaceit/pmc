package sample;

import com.workspaceit.pmc.config.WebConfig;
import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.dao.AdvertiserDao;
import com.workspaceit.pmc.dao.GalleryAdDao;
import com.workspaceit.pmc.entity.AdvertisementPrices;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAdQuantityPrice;
import com.workspaceit.pmc.service.AdvertisementPricesService;
import com.workspaceit.pmc.service.AdvertiserService;
import com.workspaceit.pmc.service.LocationService;
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

        GalleryAd  list = this.galleryAdDao.getById(1);
        Map<GalleryAdsConstant,GalleryAdQuantityPrice> g = list.getGalleryQuantityPrice();
        if(g ==null){
            return;
        }
        Set<GalleryAdsConstant> keys = g.keySet();
        for(GalleryAdsConstant key: keys){

            System.out.println(g.get(key));
        }
        System.out.println("*******************");
        Map<GalleryAdsConstant,AdvertisementPrices> pricesMap = advertisementPricesService.getGalleryAddPrice();
        Set<GalleryAdsConstant> pkeys = pricesMap.keySet();
        for(GalleryAdsConstant key: pkeys){
            System.out.println(key);
            System.out.println(pricesMap.get(key));
        }

    }
}
