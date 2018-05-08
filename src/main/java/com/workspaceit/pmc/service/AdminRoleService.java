package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.AdminRoleDao;
import com.workspaceit.pmc.entity.AdminRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by anik on 5/8/18.
 */

@Service
@Transactional
public class AdminRoleService {

    private AdminRoleDao adminRoleDao;

    @Autowired
    public void setAdminRoleDao(AdminRoleDao adminRoleDao) {
        this.adminRoleDao = adminRoleDao;
    }

    @Transactional
    public AdminRole getRoleByName(String role){
        return adminRoleDao.getByName(role);
    }

}
