package com.workspaceit.pmc.config;

import com.workspaceit.pmc.aop.TestAop;
import com.workspaceit.pmc.aop.sevice.CommonServiceAop;
import com.workspaceit.pmc.aop.sevice.FileServiceAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@Configuration
public class AopConfig {
   // @Bean
    public TestAop testAop(){
        return new TestAop();
    }
    //@Bean
    public CommonServiceAop commonDaoAop(){
        return new CommonServiceAop();
    }
   // @Bean
    public FileServiceAop fileServiceAop(){
        return new FileServiceAop();
    }
}
