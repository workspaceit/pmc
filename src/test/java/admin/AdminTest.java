package admin;

import com.workspaceit.pmc.config.WebConfig;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.helper.FormToNameValuePair;
import com.workspaceit.pmc.validation.admin.AdminCreateForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class AdminTest {

    Admin admin;
    private MockMvc mockMvc;


    private WebApplicationContext wac;

    private FormToNameValuePair formToNameValuePair;

    @Autowired
    public void setWac(WebApplicationContext wac) {
        this.wac = wac;
    }
    @Autowired
    public void setFormToNameValuePair(FormToNameValuePair formToNameValuePair) {
        this.formToNameValuePair = formToNameValuePair;
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @Test
    public void createAdminUser() throws Exception {
        AdminCreateForm adminCreateForm = new AdminCreateForm();

        adminCreateForm.setUserName("admin");
        adminCreateForm.setEmail("admin@admin.com");
        adminCreateForm.setPassword("123456");
        adminCreateForm.setConfirmPassword("123456");
        adminCreateForm.setFullName("ADMIN ADMIN");
        adminCreateForm.setPhoneNumber("+8801764658987");

        MvcResult a = mockMvc.perform(
                post("/test/api/test")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(formToNameValuePair.getUrlEncodedFormEntityToString(adminCreateForm))
                        )
                .andExpect(status().isOk()).andReturn();
        String response  = a.getResponse().getContentAsString();
        System.out.println("HELLO");
        System.out.println(response);

        System.out.println( a.getRequest().getParameter("userName"));
    }



}
