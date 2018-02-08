package com.workspaceit.pmc.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workspaceit.pmc.entity.Photographer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class PhotographerUserDetails extends Photographer implements UserDetails{
    public PhotographerUserDetails(Photographer photographer) {

        super();
        super.setId(photographer.getId());
        super.setEmail(photographer.getEmail());
        super.setPassword(photographer.getPassword());
        super.setFullName(photographer.getFullName());
        super.setProfilePhoto(photographer.getProfilePhoto());
        super.setPhoneNumber(photographer.getPhoneNumber());
        super.setUserName(photographer.getUserName());
        super.setActive(photographer.getActive());
        super.setDeleted(photographer.getDeleted());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_photographer"));

        return authorities;
    }

    @JsonIgnore
    @Override
    public String getName() {
        return this.getUsername();
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return super.getEmail();
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
        return this.getActive();
    }
}
