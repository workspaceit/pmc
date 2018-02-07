package com.workspaceit.pmc.service;

import com.workspaceit.pmc.auth.AdminUserDetails;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.AdminRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;



/**
 * Created by anik on 12/22/17.
 */

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {


    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public UserDetails loadUserByUsername(final String s)  throws UsernameNotFoundException{
        Admin admin = this.adminService.getAdminByEmailOrUserName(s);
        if(admin==null){
            throw new UsernameNotFoundException("User not found");
        }


        return new AdminUserDetails(admin);
    }

}
