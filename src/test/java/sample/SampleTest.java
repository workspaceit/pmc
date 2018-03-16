package sample;

import com.workspaceit.pmc.aop.TestAop;
import com.workspaceit.pmc.config.WebConfig;
import com.workspaceit.pmc.constant.advertisement.*;
import com.workspaceit.pmc.dao.*;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.Section;
import com.workspaceit.pmc.helper.TestHelper;
import com.workspaceit.pmc.service.EventService;
import com.workspaceit.pmc.validation.admin.AdminCreateForm;
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


    private TestAop testAop;
    private TestHelper testHelper;
    private HistoryDao historyDao;

    @Autowired
    public void setTestAop(TestAop testAop) {
        this.testAop = testAop;
    }

    @Autowired
    public void setTestHelper(TestHelper testHelper) {
        this.testHelper = testHelper;
    }

    @Autowired
    public void setHistoryDao(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    @Test
    @Transactional
    public void test(){
        AdminCreateForm adminCreateForm = new AdminCreateForm();

        adminCreateForm.setUserName("admin");
        adminCreateForm.setEmail("admin@admin.com");
        adminCreateForm.setPassword("123456");
        adminCreateForm.setConfirmPassword("123456");
        adminCreateForm.setFullName("ADMIN ADMIN");
        adminCreateForm.setPhoneNumber("+8801764658987");
        //testHelper.test(adminCreateForm);
        historyDao.getAuditReader();


   }


}
