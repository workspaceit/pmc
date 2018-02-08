package com.workspaceit.pmc.config.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomTokenEndpointAuthenticationFilter extends TokenEndpointAuthenticationFilter {

    public CustomTokenEndpointAuthenticationFilter(AuthenticationManager authenticationManager, OAuth2RequestFactory oAuth2RequestFactory) {

        super(authenticationManager, oAuth2RequestFactory);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {

                /* on successful authentication do stuff here */

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
                /* before authentication check for condition if true then process to authenticate */
        HttpServletResponse httpServletResponse = (HttpServletResponse)res;
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("CustomTokenEndpointAuthenticationFilter -> doFilter");

        super.doFilter(req, res, chain);
    }
}