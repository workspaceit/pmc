package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Notification;
import com.workspaceit.pmc.entity.PasswordResetToken;
import com.workspaceit.pmc.helper.EmailHelper;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.DashboardService;
import com.workspaceit.pmc.service.NotificationService;
import com.workspaceit.pmc.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * Created by anik on 12/22/17.
 */

@Controller
public class MainController {


    private DashboardService dashboardService;
    private PasswordResetService passwordResetService;
    private AdminService adminService;
    private NotificationService notificationService;
    private EmailHelper emailHelper;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setPasswordResetService(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @Autowired
    public void setDashboardService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setEmailHelper(EmailHelper emailHelper) {
        this.emailHelper = emailHelper;
    }

    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        return new ModelAndView("redirect:" + "admin/dashboard");
    }

    @RequestMapping(value = "/admin/**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
//        Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("home2");
        return model;

    }

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public ModelAndView adminDashboard() {
        List<Notification> notifications = this.notificationService.get(10,0);

        ModelAndView model = new ModelAndView();
        model.addObject("totalImages",this.dashboardService.getNumberOfEventPhotoUploaded(0));
        model.addObject("totalImagesInLastWeek",this.dashboardService.getNumberOfEventPhotoUploaded(7));
        model.addObject("totalEvents",this.dashboardService.getNumberOfEvents(0));
        model.addObject("totalEventsInLastWeek",this.dashboardService.getNumberOfEvents(7));
        model.addObject("topActiveEvents",this.dashboardService.getTopActiveEvents());
        model.addObject("notifications",notifications);
        model.setViewName("admin/home");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("admin/login");
        return model;

    }

    @RequestMapping(value = "/reset-password",method = RequestMethod.GET)
    public ModelAndView resetPassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/reset-password");
        return modelAndView;
    }

    @RequestMapping(value = "/submit-reset-password",method = RequestMethod.POST)
    public ModelAndView resetPasswordSubmit(@RequestParam("email") String email){

        ModelAndView modelAndView = new ModelAndView();

        Admin admin = adminService.getAdminByEmail(email);
        if(admin==null){
            modelAndView.addObject("error", "User doesn't exist");
        }
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken=this.passwordResetService.generatePasswordToken(admin,token);

        Boolean result =emailHelper.sendPasswordResettMail(admin.getId(),email,token);
        if(result){
            modelAndView.addObject("msg", "A mail has been sent to your email");
        }
        modelAndView.setViewName("admin/reset-password");
        return modelAndView;
    }

    @RequestMapping(value = "/reset-password-verify/{userId}/{token}",method = RequestMethod.GET)
    public ModelAndView resetPasswordVerify(@PathVariable("userId") String userId, @PathVariable("token") String token){
        ModelAndView modelAndView = new ModelAndView();
        int userID = Integer.parseInt(userId);
        String result = this.passwordResetService.validatePasswordResetToken(userID,token);
        if(result!=null){
            modelAndView.addObject("error", "Not Authorize to reset password");
            modelAndView.setViewName("admin/reset-password");
        }else{
            modelAndView.setViewName("redirect:"+"/update-password");
        }

        return modelAndView;
    }
    @RequestMapping(value = "/update-password",method = RequestMethod.GET)
    public ModelAndView updatePassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/update-password");
        return modelAndView;
    }

    @RequestMapping(value = "/submit-update-password",method = RequestMethod.POST)
    public ModelAndView submitUpdatePassword(@RequestParam("password") String password,@RequestParam("confirmPassword") String confirmPassword){
        ModelAndView modelAndView = new ModelAndView();

        Admin admin =
                (Admin) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        if(!password.equals(confirmPassword)){
            modelAndView.addObject("error", "Confirm Password doesnt match");
        }
        Boolean result =adminService.changePassword(admin,password);
        if(result){
            modelAndView.addObject("msg", "Password Changed Successfully");
        }
        modelAndView.setViewName("admin/update-password");
        return modelAndView;
    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user) {
        ModelAndView model = new ModelAndView();
        System.out.println(user);
        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }
        model.setViewName("403");
        return model;

    }
}