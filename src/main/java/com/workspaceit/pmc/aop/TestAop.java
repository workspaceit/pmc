package com.workspaceit.pmc.aop;

import com.workspaceit.pmc.validation.admin.AdminCreateForm;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAop {
    @After("execution(* com.workspaceit.pmc.helper.TestHelper.test(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object[] objs = joinPoint.getArgs();
        for(Object obj:objs){
            System.out.println(obj instanceof AdminCreateForm);
            AdminCreateForm acf = (AdminCreateForm)obj;

            System.out.println(acf);
        }
        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");
    }
}
