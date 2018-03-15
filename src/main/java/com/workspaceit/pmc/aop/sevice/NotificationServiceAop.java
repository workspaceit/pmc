package com.workspaceit.pmc.aop.sevice;

import com.workspaceit.pmc.constant.DAB_OPERATION;
import com.workspaceit.pmc.constant.ENTITY_CARDINALITY;
import com.workspaceit.pmc.entity.Notification;
import com.workspaceit.pmc.service.NotificationService;
import com.workspaceit.pmc.util.NotificationUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotificationServiceAop {
    private NotificationService notificationService;
    private NotificationUtil notificationUtil;

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setNotificationUtil(NotificationUtil notificationUtil) {
        this.notificationUtil = notificationUtil;
    }

    @AfterReturning(value = "execution(* com.workspaceit.pmc.dao.*.insert(..))")
    public void create(JoinPoint joinPoint){

        Object entity = null;
        if(joinPoint.getArgs().length>0){
            entity = joinPoint.getArgs()[0];
        }
        System.out.println(entity.getClass());

        /**
         * Recursive Loop breaker
         * */
        if(Notification.class.equals(entity.getClass())){
            return;
        }
        /**
         * Getting specific notification message
         * */
        String details = notificationUtil.getDetails(entity.getClass(), ENTITY_CARDINALITY.ONE, DAB_OPERATION.CREATE);
        if(details==null){
            return;
        }


        Notification notification = new Notification();
        notification.setDetails(details);

        this.notificationService.create(notification);
    }
}