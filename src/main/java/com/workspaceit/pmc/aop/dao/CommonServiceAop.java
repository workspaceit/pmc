package com.workspaceit.pmc.aop.dao;

import com.workspaceit.pmc.helper.EntityHelper;
import com.workspaceit.pmc.service.EventImageService;
import com.workspaceit.pmc.service.EventService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class CommonDaoAop {
    private EventService eventService;
    private EventImageService eventImageService;
    private EntityHelper entityHelper;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setEventImageService(EventImageService eventImageService) {
        this.eventImageService = eventImageService;
    }

    @Autowired
    public void setEntityHelper(EntityHelper entityHelper) {
        this.entityHelper = entityHelper;
    }

    @After("execution(* com.workspaceit.pmc.service.CommonService.delete(..))")
    public void afterDelete(JoinPoint joinPoint){
        System.out.println("*** AOP FOR COMMON DAO ****");
        String entityClassName = "";
        int id = 0;

        Object[] args =  joinPoint.getArgs();

        for(int i=0;i<args.length;i++){
            Object arg = args[i];
            System.out.println(arg.getClass());
            switch (i){
                case 0:
                    if (arg instanceof Integer)
                        id = (int)arg;
                    break;
                case 1:
                    if(arg instanceof String)
                        entityClassName = entityHelper.getClassName((String)arg);
                    break;

            }
        }
        System.out.println(entityClassName+" "+id);

        switch(entityClassName){
            case "Watermark":
                this.eventService.removeWatermarkFromEvent(id);
                this.eventImageService.makeWatermarkNull(id);
                break;
            case "Photographer":
                this.eventService.removePhotographerFromEvent(id);
                break;
            case "Advertiser":
                this.eventService.removeAdvertiserFromEvent(id);
                break;
        }

    }

    public static void main(String[] args) {
        Object arg = 10f;
        System.out.println (Integer.class.isInstance(arg));
        System.out.println (arg instanceof Integer);
    }


}