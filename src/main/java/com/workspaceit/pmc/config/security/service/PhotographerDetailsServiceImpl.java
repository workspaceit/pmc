package com.workspaceit.pmc.config.security.service;

import com.workspaceit.pmc.auth.PhotographerUserDetails;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("photographerDetailsService")
public class PhotographerDetailsServiceImpl implements UserDetailsService{

    private PhotographerService photographerService;


    @Autowired
    public void setPhotographerService(PhotographerService photographerService) {
        this.photographerService = photographerService;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrUserName) throws UsernameNotFoundException {
        Photographer photographer =  this.photographerService.getByEmailOrUserName(emailOrUserName);
        if(photographer==null){
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new PhotographerUserDetails(photographer);
    }
}
