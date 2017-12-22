package com.workspaceit.pmc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.workspaceit.pmc.*" })
@Import({ SecurityConfig.class })

public class RootConfig {

}
