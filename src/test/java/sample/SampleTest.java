package sample;

import com.workspaceit.pmc.config.WebConfig;
import com.workspaceit.pmc.constant.advertisement.*;
import com.workspaceit.pmc.dao.*;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.Section;
import com.workspaceit.pmc.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by mi_rafi on 1/4/18.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class SampleTest {


   private SectionDao sectionDao;
   private EventService eventService;
   private EventDao eventDao;

   @Autowired
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Test
    @Transactional
    public void test(){

     /* List<Section>  sections = this.sectionDao.getByAdTypeSectionTypeAndRotation(ADVERTISEMENT_TYPE.GALLERY,
              SECTION_TYPE.TOP_BANNER,
              ADVERTISEMENT_ROTATION_SETTINGS.STATIC);


      for(Section section :sections){
            System.out.println(section.getId());
        }*/

//    Section section = this.sectionDao.getById(1);
//    System.out.println(section.getAdvertisement().getAdType());
        List<Event> eventList = this.eventService.getByAdvertiserId(1);
        Event event = this.eventService.getById(9);





   }

    public static void main(String[] args) {
        Set<Integer> advertisers =  new HashSet<>();
        advertisers.add(1);
        advertisers.add(4);
        advertisers.add(3);
        advertisers.add(2);
        advertisers.removeIf(advertiser->advertiser==1);
        System.out.println(advertisers);
    }
}
