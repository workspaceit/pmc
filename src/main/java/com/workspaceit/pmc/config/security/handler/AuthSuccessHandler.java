package com.workspaceit.pmc.config.security.handler;

import com.workspaceit.pmc.entity.Photographer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler{
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        System.out.println("AuthSuccessHandler");
        Object loggedInUser = authentication.getPrincipal();

        if(loggedInUser instanceof Photographer){
            new SecurityContextLogoutHandler().logout(httpServletRequest, null, null);
            this.redirectToLogin(httpServletRequest, httpServletResponse);
        }else{
            this.redirectToPriorLogin(httpServletRequest, httpServletResponse);
        }



    }
    private void redirectToPriorLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String redirectUrl ="/";
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            SavedRequest savedRequest = (SavedRequest)session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
           if (savedRequest != null) {
                redirectUrl = savedRequest.getRedirectUrl();
           }
        }

        this.redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, redirectUrl);
    }
    private void redirectToLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String redirectUrl ="/login?error";
        this.redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, redirectUrl);
    }
}