package com.hfq.house.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 
 * @author jjs
 *
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {


    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward requests to /admin and /user to their index.html
//                registry.addViewController("/").setViewName(
//                        "forward:/login.html");
                registry.addViewController("/").setViewName(
                        "forward:/index");
            }
        };
    }
    
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false)
                   .setUseTrailingSlashMatch(false);
    }

}