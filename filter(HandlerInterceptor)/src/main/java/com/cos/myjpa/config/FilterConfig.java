package com.cos.myjpa.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.myjpa.filter.MyAuthenticationFilter;

//web.xml

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<MyAuthenticationFilter> authenticationFilterRegister() {
		FilterRegistrationBean<MyAuthenticationFilter> bean =
				new FilterRegistrationBean<>(new MyAuthenticationFilter());
		
		bean.addUrlPatterns("/test");
		bean.setOrder(0);
		return bean;
	}

}
