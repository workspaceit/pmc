package com.workspaceit.pmc.config.security;

import com.workspaceit.pmc.config.security.filter.CustomTokenEndpointAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
public class Oauth2Configuration {

    private static InMemoryTokenStore tokenStore = new InMemoryTokenStore();
    private static final String SERVER_RESOURCE_ID = "oauth2-server";






    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenStore(tokenStore).resourceId(SERVER_RESOURCE_ID);
        }
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/auth/api/**")
                    .authorizeRequests()
                    .antMatchers("/auth/api/**").access("hasRole('photographer')")
                    .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        }


    }
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthServer extends AuthorizationServerConfigurerAdapter {


        private OAuth2RequestFactory oAuth2RequestFactory;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        private AuthenticationManager authenticationManager;
        private UserDetailsService photographerDetailsService;

        @Autowired
        @Qualifier("authenticationManagerBean")
        public void setAuthenticationManager(AuthenticationManager authenticationManager) {
            this.authenticationManager = authenticationManager;
        }

        @Autowired
        @Qualifier("photographerDetailsService")
        public void setPhotographerDetailsService(UserDetailsService photographerDetailsService) {
            this.photographerDetailsService = photographerDetailsService;
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("pmc-app-client")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                    .authorities("ROLE_superadmin")
                    .scopes("read", "write", "trust")
                    .secret(passwordEncoder.encode("f6c3d96bc05036e738f0899ba149f447924b3a09"))
                    .accessTokenValiditySeconds(60*60*24)
                    .resourceIds(SERVER_RESOURCE_ID);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            this.oAuth2RequestFactory = endpoints.getOAuth2RequestFactory();
            endpoints.tokenStore(tokenStore)
                    .authenticationManager(authenticationManager)
                    .userDetailsService(photographerDetailsService);

        }



        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

            oauthServer.allowFormAuthenticationForClients()
                    .passwordEncoder(passwordEncoder)
                    .addTokenEndpointAuthenticationFilter(new CustomTokenEndpointAuthenticationFilter(this.authenticationManager,this.oAuth2RequestFactory ));
        }


    }

}