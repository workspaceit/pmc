package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by mi_rafi on 12/26/17.
 */

@Controller
@RequestMapping(value = ControllerUriPrefix.ADMIN+"/user")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/add")
    public ModelAndView add(){
        ModelAndView model = new ModelAndView("admin/admin-user-management/add");
        return model;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ModelAndView allAdmin(Authentication authentication){
        List<Admin> admins = this.adminService.getAll();
        ModelAndView model= new ModelAndView("admin/admin-user-management/all");
        model.addObject("admins",admins);
        return model;
    }

}
