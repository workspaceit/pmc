package com.workspaceit.pmc.helper;

import com.workspaceit.pmc.validation.admin.AdminCreateForm;
import org.springframework.stereotype.Component;

@Component
public class TestHelper {
    public void test(AdminCreateForm admin){
        System.out.println("TEST FUNCTION");
    }
}
