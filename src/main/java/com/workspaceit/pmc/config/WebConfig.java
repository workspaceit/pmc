package com.workspaceit.pmc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("com.workspaceit.pmc")
@Import({ SecurityConfig.class })
public class WebConfig implements WebMvcConfigurer {

    Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Bean
    public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
        registry.addResourceHandler("/photographer-profile-img/**").addResourceLocations("file://"+this.env.getPhotographerProfilePath()+"/");
        registry.addResourceHandler("/common/**").addResourceLocations("file://"+this.env.getCommonFilePath()+"/");
    }
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }

    @Bean(name = "fadeInList")
    public List<Double> fadeInList(){
        List<Double> list = new ArrayList<>();
        for(int i=0;i<=4;i++){
            list.add((double)i);
        }
        return list;
    }

    @Bean(name = "fadeOutList")
    public List<Double> fadeOutList(){
        List<Double> list = new ArrayList<>();
        for(int i=0;i<=4;i++){
            list.add((double)i);
        }
        return list;
    }
}
