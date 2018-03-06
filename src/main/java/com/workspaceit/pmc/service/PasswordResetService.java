package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.PasswordResetDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.PasswordResetToken;
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
public class PasswordResetService {

    @Autowired
    PasswordResetDao passwordResetDao;

    @Transactional
    public PasswordResetToken generatePasswordToken(Admin admin,String token){
        PasswordResetToken passwordResetToken=new PasswordResetToken();
        passwordResetToken.setAdmin(admin);
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
        PasswordResetToken passwordResetToken = this.passwordResetDao.findByToken(token);
        if((token==null)||(passwordResetToken.getAdmin().getId()!=userId)){
            return "InvalidToken";
        }

        Calendar calendar = Calendar.getInstance();
        if((passwordResetToken.getExpiryDate().getTime()-calendar.getTime().getTime())<=0){
            return "Expired";
        }
        Admin admin = passwordResetToken.getAdmin();
        Authentication auth = new UsernamePasswordAuthenticationToken(
                admin, null, Arrays.asList(
                new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
    }


}
