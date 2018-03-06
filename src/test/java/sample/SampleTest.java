package sample;

import com.workspaceit.pmc.config.WebConfig;
import com.workspaceit.pmc.constant.advertisement.*;
import com.workspaceit.pmc.dao.*;
import com.workspaceit.pmc.entity.advertisement.Section;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by mi_rafi on 1/4/18.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class SampleTest {


   private SectionDao sectionDao;

   @Autowired
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }


    @Test
    @Transactional
    public void test(){

      List<Section>  sections = this.sectionDao.getByAdTypeSectionTypeAndRotation(ADVERTISEMENT_TYPE.GALLERY,
              SECTION_TYPE.TOP_BANNER,
              ADVERTISEMENT_ROTATION_SETTINGS.STATIC);


      for(Section section :sections){
            System.out.println(section.getId());
        }

//    Section section = this.sectionDao.getById(1);
//    System.out.println(section.getAdvertisement().getAdType());

   }
}
