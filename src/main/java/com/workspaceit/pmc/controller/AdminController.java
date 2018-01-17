package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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

    private AdminService adminService;

    @Autowired
    public void setStateService(AdminService adminService) {
        this.adminService = adminService;
    }

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

    @RequestMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable("id") int id){
        Admin admin = this.adminService.getById(id);

        if(admin==null){
            return new ModelAndView("redirect:"+"/admin/admin-user-management/all");
        }
        ModelAndView model = new ModelAndView("admin/admin-user-management/edit");

        model.addObject("admin",admin);

        return model;
    }
    @RequestMapping(value = "/profile")
    public ModelAndView profileDetails(Authentication authentication){
        Admin currentUser = (Admin)authentication.getPrincipal();

        if(currentUser==null){
            return new ModelAndView("redirect:"+"/admin/admin-user-management/all");
        }
        ModelAndView model = new ModelAndView("admin/profile/details");

        model.addObject("user",currentUser);

        return model;
    }
    @RequestMapping(value = "/profile/edit")
    public ModelAndView profileUpdate(Authentication authentication){
        Admin currentUser = (Admin)authentication.getPrincipal();

        if(currentUser==null){
            return new ModelAndView("redirect:"+"/admin/admin-user-management/all");
        }
        ModelAndView model = new ModelAndView("admin/profile/edit");

        model.addObject("user",currentUser);

        return model;
    }

}
