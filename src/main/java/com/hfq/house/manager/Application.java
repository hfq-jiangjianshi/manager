package com.hfq.house.manager;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hfq.house.manager.interceptor.PageInterceptor;

@SpringBootApplication
//@EnableAsync
//@EnableTransactionManagement
//@EnableScheduling
//@PropertySource(value = {"classpath:application-dev.properties"})
public class Application extends WebMvcConfigurerAdapter{
	
	@Resource
	private PageInterceptor pageInterceptor;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(pageInterceptor);
	}
}
