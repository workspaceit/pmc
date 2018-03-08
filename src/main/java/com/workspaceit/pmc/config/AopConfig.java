package com.workspaceit.pmc.config;

import com.workspaceit.pmc.aop.TestAop;
import com.workspaceit.pmc.aop.dao.CommonDaoAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    @Bean
    public TestAop testAop(){
        return new TestAop();
    }
    @Bean
    public CommonDaoAop commonDaoAop(){
        return new CommonDaoAop();
    }
}
