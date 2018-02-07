package com.workspaceit.pmc.config.security.provider;

import com.workspaceit.pmc.entity.Admin;
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
public class AdminAuthProvider implements AuthenticationProvider {


    private UserDetailsService adminUserDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("userDetailsService")
    public void setAdminUserDetailsService(UserDetailsService adminUserDetailsService) {
        this.adminUserDetailsService = adminUserDetailsService;
    }



    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("ADMIN AUTH PROVIDER");
        String userNameOrEmail = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails admin  = this.adminUserDetailsService.loadUserByUsername(userNameOrEmail);
        boolean passwordMatched = this.passwordEncoder.matches(password,admin.getPassword());


        if(!passwordMatched){
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(admin,null,admin.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
