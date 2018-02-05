package sample;

import com.workspaceit.pmc.config.WebConfig;
import com.workspaceit.pmc.constant.advertisement.*;
import com.workspaceit.pmc.dao.AdvertiserDao;
import com.workspaceit.pmc.dao.CommonDao;
import com.workspaceit.pmc.dao.GalleryAdDao;
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

    private AdvertisementPricesService advertisementPricesService;
    private LocationService locationService;
    private AdvertiserDao advertiserDao;
    private GalleryAdDao galleryAdDao;
    private SlideShowService slideShowService;
    private AdvertiserService advertiserService;
    private PopUpAdsService popUpAdsService;
    private AdvertisementService advertisementService;

    private CommonDao commonDao;

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
    @Autowired
    public void setPopUpAdsService(PopUpAdsService popUpAdsService) {
        this.popUpAdsService = popUpAdsService;
    }

    @Autowired
    public void setAdvertisementService(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }
    @Autowired
    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @Test
    @Transactional
    public void advertimentTest(){
        List<Advertisement>  advertisements =  this.advertisementService.getByAdvertiserId(39);
        for (Advertisement advertisement:advertisements) {
            System.out.println(advertisement);
            System.out.println(advertisement.getSections().get(SECTION_TYPE.LOGO));
            System.out.println(advertisement.getSections().get(SECTION_TYPE.BACKGROUND));
            System.out.println(advertisement.getSections().get(SECTION_TYPE.BANNER));
            System.out.println(advertisement.getSections().get(SECTION_TYPE.TOP_BANNER));
            System.out.println(advertisement.getSections().get(SECTION_TYPE.BOTTOM_BANNER));
        }

    }

    //@Test
    @Transactional(rollbackFor = Exception.class)
    public void getAdvertiser(){
       /* Advertisement advertisement = this.advertisementService.getById(2);
        this.commonDao.delete(advertisement);*/
       /* boolean a = this.commonDao.delete("Advertisement",2);
        System.out.println(a);*/

        try {
            Date sDate = new Date();
            Date eDate =DateHelper.getStringToUtilDate("31/02/2018 00:00:00","dd/MM/yyyy HH:mm:ss");
            System.out.println(sDate.toString());
            System.out.println(eDate.toString());
            GalleryAd galleryAd =  this.galleryAdDao.getByAdvertiserId(1,sDate, eDate);
            if(galleryAd!=null)
                System.out.println(galleryAd.getId());
            System.out.println(galleryAd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public void printAdvertisement(List<Advertisement> advertisements){
       for(Advertisement advertisement : advertisements){
            System.out.println(advertisement.getAdType());
           printAdvertisementSection(advertisement,SECTION_TYPE.LOGO);
           printAdvertisementSection(advertisement,SECTION_TYPE.BACKGROUND);
           printAdvertisementSection(advertisement,SECTION_TYPE.TOP_BANNER);
           printAdvertisementSection(advertisement,SECTION_TYPE.BOTTOM_BANNER);
       }
    }
    public void printAdvertisementSection(Advertisement advertisement,SECTION_TYPE secType){
        System.out.println("******* "+secType+"**********");
        System.out.print(advertisement.getSections().get(secType).getId()+" ");
        System.out.print(advertisement.getSections().get(secType).getPrice()+" ");
        System.out.println(advertisement.getSections().get(secType).getQuantity());
    }
    @Test
    public void obtainAccessToken() {
        String clientId = "my-trusted-client";
        String username ="john";
        String password ="123456";
         Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);
        Response response = RestAssured.given().auth().preemptive()
                .basic(clientId, "secret").and().with().params(params).when()
                .post("http://localhost:8080/oauth/token");
       System.out.println(response.asString());
    }
  //  @Test
    public void create() {
        /****TEST section***/



        Advertisement advertisement = new Advertisement();

        advertisement.setAdType(ADVERTISEMENT_TYPE.GALLERY);
        advertisement.setAdvertiserId(4);
        advertisement.setSections(getSections());

        Advertisement advertisement1 = new Advertisement();

        advertisement1.setAdType(ADVERTISEMENT_TYPE.SLIDESHOW);
        advertisement1.setAdvertiserId(4);
        advertisement1.setSections(getSections());

        Advertisement advertisement2 = new Advertisement();

        advertisement2.setAdType(ADVERTISEMENT_TYPE.POPUP_SMS);
        advertisement2.setAdvertiserId(4);
        advertisement2.setSections(getSections());

        Advertisement advertisement3 = new Advertisement();

        advertisement3.setAdType(ADVERTISEMENT_TYPE.POPUP_EMAIL);
        advertisement3.setAdvertiserId(4);
        advertisement3.setSections(getSections());


        this.advertisementService.create(advertisement);
        this.advertisementService.create(advertisement1);
        this.advertisementService.create(advertisement2);
        this.advertisementService.create(advertisement3);

        /******************/


    }
    private  Map<SECTION_TYPE,Section> getSections(){
        Map<SECTION_TYPE,Section> sections = new HashMap<>();

        Section section = new Section();
        section.setPrice(1.0f);
        section.setQuantity(1);
        section.setRotation(AdvertiseRotationSettings.ROTATE);
        section.setSectionType(SECTION_TYPE.LOGO);
        section.setSectionResource(this.getSectionsResource());

        Section section1 = new Section();
        section1.setPrice(1.0f);
        section1.setQuantity(1);
        section1.setRotation(AdvertiseRotationSettings.ROTATE);
        section1.setSectionType(SECTION_TYPE.BACKGROUND);
        section1.setSectionResource(this.getSectionsResource());

        Section section2 = new Section();
        section2.setPrice(1.0f);
        section2.setQuantity(1);
        section2.setRotation(AdvertiseRotationSettings.ROTATE);
        section2.setSectionType(SECTION_TYPE.TOP_BANNER);
        section2.setSectionResource(this.getSectionsResource());

        Section section3 = new Section();
        section3.setPrice(1.0f);
        section3.setQuantity(1);
        section3.setRotation(AdvertiseRotationSettings.ROTATE);
        section3.setSectionType(SECTION_TYPE.BOTTOM_BANNER);
        section3.setSectionResource(this.getSectionsResource());

        sections.put(SECTION_TYPE.LOGO,section);
        sections.put(SECTION_TYPE.BACKGROUND,section1);
        sections.put(SECTION_TYPE.TOP_BANNER,section2);
        sections.put(SECTION_TYPE.BOTTOM_BANNER,section3);

        return sections;
    }
    private   List<SectionResource> getSectionsResource(){
        List<SectionResource> sectionResources  = new ArrayList<>();
        SectionResource sectionResource = new SectionResource();
        sectionResource.setFileName("12112.jgp");
        sectionResource.setFileType(FILE_TYPE.IMAGE);

        SectionResource sectionResource1 = new SectionResource();
        sectionResource.setFileName("12113.jgp");
        sectionResource.setFileType(FILE_TYPE.IMAGE);

        SectionResource sectionResource2 = new SectionResource();
        sectionResource.setFileName("12113.jgp");
        sectionResource.setFileType(FILE_TYPE.VIDEO);


        SectionResource sectionResource3 = new SectionResource();
        sectionResource.setFileName("12113.mp4");
        sectionResource.setFileType(FILE_TYPE.VIDEO);

        SectionResource sectionResource4 = new SectionResource();
        sectionResource.setFileName("12113.jgp");
        sectionResource.setFileType(FILE_TYPE.VIDEO);

        sectionResources.add(sectionResource);
        sectionResources.add(sectionResource1);
        sectionResources.add(sectionResource2);
        sectionResources.add(sectionResource3);
        sectionResources.add(sectionResource4);

        return sectionResources;
    }
}
