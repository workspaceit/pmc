package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.NotificationDao;
import com.workspaceit.pmc.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationService {
    private NotificationDao notificationDao;

    @Autowired
    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Notification notification){
        this.notificationDao.insert(notification);
    }
}