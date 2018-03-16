package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.PasswordResetDao;
import com.workspaceit.pmc.dao.PhotographerPasswordResetDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.PasswordResetToken;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.PhotographerPasswordResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class PhotographerPasswordResetService {

    @Autowired
    PhotographerPasswordResetDao passwordResetDao;

    @Transactional
    public PhotographerPasswordResetToken generatePasswordToken(Photographer photographer, String token){
        PhotographerPasswordResetToken passwordResetToken=new PhotographerPasswordResetToken();
        passwordResetToken.setPhotographer(photographer);
        passwordResetToken.setToken(token);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        passwordResetToken.setExpiryDate(dt);
        return this.passwordResetDao.insert(passwordResetToken);
    }

    @Transactional
    public String validatePasswordResetToken(int userId,String token){
        PhotographerPasswordResetToken passwordResetToken = this.passwordResetDao.findByToken(token);
        if((token==null)||(passwordResetToken.getPhotographer().getId()!=userId)){
            return "InvalidToken";
        }

        Calendar calendar = Calendar.getInstance();
        if((passwordResetToken.getExpiryDate().getTime()-calendar.getTime().getTime())<=0){
            return "Expired";
        }
        return null;
    }


}
