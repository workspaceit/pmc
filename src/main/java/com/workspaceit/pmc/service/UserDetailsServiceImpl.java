package com.workspaceit.pmc.service;

import com.workspaceit.pmc.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


/**
 * Created by anik on 12/22/17.
 */

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminService adminService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin = adminService.getAdminByEmail(s);
        System.out.println(admin);
        if(admin != null) {
            Collection<GrantedAuthority> authorities = admin.getAdminRoles().stream()
                    .map(adminRole -> new SimpleGrantedAuthority(adminRole.getRole()))
                    .collect(Collectors.toCollection(ArrayList::new));
            return new User(s, admin.getPassword(), true, true, true, true, authorities);
        }
        else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
