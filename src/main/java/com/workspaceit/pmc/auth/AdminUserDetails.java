package com.workspaceit.pmc.auth;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.AdminRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AdminUserDetails extends Admin implements UserDetails {

    public AdminUserDetails(Admin admin) {
        super();
        super.setId(admin.getId());
        super.setUserName(admin.getUserName());
        super.setEmail(admin.getEmail());
        super.setPassword(admin.getPassword());
        super.setName(admin.getName());
        super.setImage(admin.getImage());
        super.setPhoneNumber(admin.getPhoneNumber());
        super.setAdminRoles(admin.getAdminRoles());
        super.setCreatedBy("");
        super.setCreatedAt(admin.getCreatedAt());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        Set<AdminRole> adminRoles = this.getAdminRoles();
        for(AdminRole adminRole:adminRoles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+adminRole.getRole()));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}