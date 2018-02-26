package sample;

import com.workspaceit.pmc.config.WebConfig;
import com.workspaceit.pmc.constant.advertisement.*;
import com.workspaceit.pmc.dao.AdvertisementDao;
import com.workspaceit.pmc.dao.AdvertiserDao;
import com.workspaceit.pmc.dao.CommonDao;
import com.workspaceit.pmc.dao.GalleryAdDao;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.Section;
import com.workspaceit.pmc.entity.advertisement.SectionResource;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import com.workspaceit.pmc.helper.DateHelper;
import com.workspaceit.pmc.service.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * Created by mi_rafi on 1/4/18.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class SampleTest {


    private AdvertisementService advertisementService;
    private AdvertiserDao advertiserDao;
    private AdvertisementDao advertisementDao;



    @Autowired
    public void setAdvertisementService(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @Autowired
    public void setAdvertiserDao(AdvertiserDao advertiserDao) {
        this.advertiserDao = advertiserDao;
    }

    @Autowired
    public void setAdvertisementDao(AdvertisementDao advertisementDao) {
        this.advertisementDao = advertisementDao;
    }

    @Test
    @Transactional
    public void test(){
        List<Advertiser> list = advertiserDao.getByEventAndLocationId(10,3);
        List<Integer> advertiserIds = new ArrayList<>();
        for(Advertiser a:list){

            advertiserIds.add(a.getId());

            System.out.println("**********************************");
            System.out.println("Id : "+a.getId());
            if(!a.isAllEventSelected()){
                System.out.println("Event Id : "+a.getEvents().iterator().next().getId());
            }else {
                System.out.println("All event");

            }
            if(!a.isAllLocationSelected()){
                System.out.println("Location Id : "+a.getLocations().iterator().next().getId());

            }else {
                System.out.println("All event");

            }

        }

        List<Advertisement> advertisements = advertisementDao.getByAdvertiserId(advertiserIds);

        for(Advertisement a:advertisements){

            System.out.println("**********************************");
            System.out.println(a);

        }



   }
}
