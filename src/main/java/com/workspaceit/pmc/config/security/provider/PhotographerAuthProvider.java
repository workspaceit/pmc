package com.workspaceit.pmc.config.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PhotographerAuthProvider implements AuthenticationProvider {


    private UserDetailsService photographerUserDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("photographerDetailsService")
    public void setPhotographerUserDetailsService(UserDetailsService photographerUserDetailsService) {
        this.photographerUserDetailsService = photographerUserDetailsService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Photographer AUTH PROVIDER");

        String userNameOrEmail = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails photographer  = this.photographerUserDetailsService.loadUserByUsername(userNameOrEmail);
        boolean passwordMatched = this.passwordEncoder.matches(password,photographer.getPassword());


        if(!passwordMatched){
            throw new BadCredentialsException("Invalid username or password");
        }
        if(!photographer.isEnabled()){
            throw new BadCredentialsException("Account is not active");
        }
        return new UsernamePasswordAuthenticationToken(photographer,null,photographer.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
