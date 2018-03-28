package com.workspaceit.pmc.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.workspaceit.pmc.config.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebMvc
@EnableScheduling
@EnableAspectJAutoProxy
@ComponentScan("com.workspaceit.pmc")
//@Import({ SecurityConfig.class })
public class WebConfig implements WebMvcConfigurer {

    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }




    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Here we add our custom-configured HttpMessageConverter
        converters.add(jacksonMessageConverter());
        converters.add(byteArrayHttpMessageConverter());
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
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
        registry.addResourceHandler("/event-images/**").addResourceLocations("file://"+this.env.getEventImagePath()+"/");
    }
    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
        return arrayHttpMessageConverter;
    }

    private List<MediaType> getSupportedMediaTypes() {
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.IMAGE_PNG);
        list.add(MediaType.IMAGE_JPEG);
        return list;
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


    @Bean(name="durationList")
    public  Set<Integer> Durations(){
        Set<Integer> durations = new HashSet<>();
        for(int i=1;i<=5;i++){
            durations.add(i);
        }

        return durations;
    }

    public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        //Registering Hibernate4Module to support lazy objects
        mapper.registerModule(new Hibernate5Module());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        messageConverter.setObjectMapper(mapper);

        return messageConverter;

    }
}
