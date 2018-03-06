package com.workspaceit.pmc.config.security;

import com.workspaceit.pmc.config.security.handler.AuthSuccessHandler;
import com.workspaceit.pmc.config.security.provider.PhotographerAuthProvider;
import com.workspaceit.pmc.config.security.provider.AdminAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



/**
 * Created by anik on 12/22/17.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    private UserDetailsService userDetailsService;
    private AdminAuthProvider adminAuthProvider;
    private PhotographerAuthProvider photographerAuthProvider;

    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    @Qualifier("adminDetailsService")
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setAdminAuthProvider(AdminAuthProvider adminAuthProvider) {
        this.adminAuthProvider = adminAuthProvider;
    }

    @Autowired
    public void setPhotographerAuthProvider(PhotographerAuthProvider photographerAuthProvider) {
        this.photographerAuthProvider = photographerAuthProvider;
    }

    @Autowired
    public void setAuthSuccessHandler(AuthSuccessHandler authSuccessHandler) {
        this.authSuccessHandler = authSuccessHandler;
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(this.adminAuthProvider);
        auth.authenticationProvider(this.photographerAuthProvider);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/admin/**").access("hasRole('ROLE_superadmin')")
                    .antMatchers("/update-password*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .loginProcessingUrl("/j_spring_security_check")
                        .usernameParameter("email").passwordParameter("password")
                        .successHandler(authSuccessHandler)
                .and()
                    .authenticationProvider(this.adminAuthProvider)
                    .logout().logoutSuccessUrl("/login?logout")
                .and()
                    .exceptionHandling().accessDeniedPage("/403")
                .and()
                    .csrf().disable();


    }

    @Bean
    PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}