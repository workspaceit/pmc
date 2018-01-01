package com.workspaceit.pmc.service;

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

    @Autowired
    AdminService adminService;

//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        Admin admin = adminService.getAdminByEmail(s);
//        System.out.println(admin);
//        if(admin != null) {
//            Collection<GrantedAuthority> authorities = admin.getAdminRoles().stream()
//                    .map(adminRole -> new SimpleGrantedAuthority(adminRole.getRole()))
//                    .collect(Collectors.toCollection(ArrayList::new));
//            return new User(s, admin.getPassword(), true, true, true, true, authorities);
//        }
//        else {
//            throw new UsernameNotFoundException("User Not Found");
//        }
//    }
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(final String s)
            throws UsernameNotFoundException {

        Admin admin = adminService.getAdminByEmail(s);
        Set<AdminRole> roles = admin.getAdminRoles();
//        for(AdminRole adminRole: roles){
//            System.out.println(adminRole.toString());
//        }
        List<GrantedAuthority> authorities =
                buildUserAuthority(admin.getAdminRoles());
        return buildUserForAuthentication(admin, authorities);

    }

    // Converts Admin user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(Admin admin, List<GrantedAuthority> authorities) {
        return new User(admin.getEmail(), admin.getPassword(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<AdminRole> adminRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        // Build user's authorities
        for (AdminRole adminRole: adminRoles) {
            setAuths.add(new SimpleGrantedAuthority("ROLE_" + adminRole.getRole()));
        }
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        for(GrantedAuthority authority: result){
            System.out.println(authority.getAuthority());
        }
        return result;
    }
}
