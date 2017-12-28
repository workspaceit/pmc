package com.workspaceit.pmc.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by mi_rafi on 12/28/17.
 */
public class CypherHelper {

    public static String getbCryptPassword(String password)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }
}
