package com.workspaceit.pmc.aop.sevice;

import com.workspaceit.pmc.constant.DAB_OPERATION;
import com.workspaceit.pmc.constant.ENTITY_CARDINALITY;
import com.workspaceit.pmc.constant.NOTIFICATION_DETAILS_REPLACE_TAG;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.service.NotificationService;
import com.workspaceit.pmc.util.NotificationUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Aspect
@Component
public class NotificationServiceAop {
    private NotificationService notificationService;
    private NotificationUtil notificationUtil;
    private Set<Class> allowedClasses;
    @PostConstruct
    public void init(){
        allowedClasses = new HashSet<>();
        allowedClasses.add(Admin.class);
        allowedClasses.add(Photographer.class);
        allowedClasses.add(Location.class);
        allowedClasses.add(Event.class);
        allowedClasses.add(Watermark.class);
        allowedClasses.add(Advertiser.class);
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setNotificationUtil(NotificationUtil notificationUtil) {
        this.notificationUtil = notificationUtil;
    }
    @AfterReturning(value = "execution(* com.workspaceit.pmc.dao.*.insertAll(..))")
    public void createMany(JoinPoint joinPoint){
        System.out.println("AOP Create Many");
        Object entities = null;
        if(joinPoint.getArgs().length>0){
            entities = joinPoint.getArgs()[0];
        }
        boolean flag = entities instanceof Collection;
        if(!flag){
            return;
        }
        Collection entitiesCol = (Collection)entities;

        for(Object entity :entitiesCol){
            this.create(entity);
        }
    }
    @AfterReturning(value = "execution(* com.workspaceit.pmc.dao.*.insert(..))")
    public void afterCreate(JoinPoint joinPoint){

        Object entity = null;
        if(joinPoint.getArgs().length>0){
            entity = joinPoint.getArgs()[0];
        }
        System.out.println(entity.getClass());

        /**
         * Recursive Loop breaker
         * */
        if(isAllowedClass(entity.getClass())){
            return;
        }
        this.create(entity);

    }
    public void create(Object entity){
        /**
         * Getting specific notification message
         * */
        String details = notificationUtil.getDetails(entity.getClass(), ENTITY_CARDINALITY.ONE, DAB_OPERATION.CREATE);

        if(details==null){
            return;
        }

        String name = this.getName(entity);
        details= notificationUtil.fillTextInDetails(NOTIFICATION_DETAILS_REPLACE_TAG.NAME,details,name);

        Notification notification = new Notification();
        notification.setDetails(details);

        this.notificationService.create(notification);
    }
    @AfterReturning(value = "execution(* com.workspaceit.pmc.dao.*.update(..))")
    public void update(JoinPoint joinPoint){

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
        String details = notificationUtil.getDetails(entity.getClass(), ENTITY_CARDINALITY.ONE, DAB_OPERATION.UPDATE);

        if(details==null){
            return;
        }

        String name = this.getName(entity);
        details= notificationUtil.fillTextInDetails(NOTIFICATION_DETAILS_REPLACE_TAG.NAME,details,name);

        Notification notification = new Notification();
        notification.setDetails(details);

        this.notificationService.create(notification);
    }
    public String getName(Object entity){
        String name = "";
        if (entity instanceof Admin){
            name =  ((Admin)entity).getName();
        }else if (entity instanceof Watermark){
            name =  ((Watermark)entity).getName();
        }else if (entity instanceof Photographer){
            name =  ((Photographer)entity).getFullName();
        }else if (entity instanceof Event){
            name =  ((Event)entity).getName();
        }else if (entity instanceof Location){
            name =  ((Location)entity).getName();
        }else if (entity instanceof Advertiser){
            name =  ((Advertiser)entity).getName();
        }

        return name;
    }
    public boolean isAllowedClass(Class aClass){
        return this.allowedClasses.contains(aClass);
    }
    public static void main(String[] args) {
        Collection<Admin> entitiesCol = new ArrayList<>();
    }
}