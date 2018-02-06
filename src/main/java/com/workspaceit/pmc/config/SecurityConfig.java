package com.workspaceit.pmc.config;

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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;



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
    @Qualifier("userDetailsService")
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
        auth.authenticationProvider(this.adminAuthProvider);
        auth.authenticationProvider(this.photographerAuthProvider);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//.antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_superadmin')")
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .loginProcessingUrl("/j_spring_security_check")
                        .usernameParameter("email").passwordParameter("password")
                        .successHandler(authSuccessHandler)
                .and()
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
       // AuthenticationManager am = super.authenticationManagerBean();
       // System.out.println(am.);
        return super.authenticationManagerBean();
    }


    /*@Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }*/

//    @Bean
//    @Autowired
//    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
//        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
//        handler.setTokenStore(tokenStore);
//        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
//        handler.setClientDetailsService(clientDetailsService);
//        return handler;
//    }

//    @Bean
//    @Autowired
//    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
//        TokenApprovalStore store = new TokenApprovalStore();
//        store.setTokenStore(tokenStore);
//        return store;
//    }

}
