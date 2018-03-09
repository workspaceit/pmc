package com.workspaceit.pmc.aop.sevice;

import com.workspaceit.pmc.validation.advertiser.AdvertiserAndAllCompositeForm;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

@Aspect
@Component
public class FileServiceAop {

    @AfterReturning(value = "execution(* com.workspaceit.pmc.restcontroller.AdvertiserRestController.advertiserCreate(..))",returning = "result")
    public void cleanAdvertiserCreateFileToken(JoinPoint joinPoint,Object result) throws Exception{
        AdvertiserAndAllCompositeForm  allCompositeForm = null;
        for(Object arg : joinPoint.getArgs()){

            if( arg instanceof AdvertiserAndAllCompositeForm){
                allCompositeForm = (AdvertiserAndAllCompositeForm)arg;
                System.out.println(allCompositeForm.toString());
                throw new Exception();
            }

        }
        ResponseEntity<?> responseEntity =  (ResponseEntity)result;

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody().getClass());

    }
}
