package com.cos.myjpa.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.handler.ex.MyAuthenticationException;

@Configuration // ioc에 등록 
public class WebMvcConfig implements WebMvcConfigurer { // View 컨트롤, Model 컨트롤, Controller
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new HandlerInterceptor() {
		
		
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				
				HttpSession session = request.getSession();
				User principal = (User) session.getAttribute("principal");
				
				if(principal == null) {
					throw new MyAuthenticationException();
				}else {
					return true;
				}
			}
		}).addPathPatterns("/user").addPathPatterns("/post"); //   /user일때 실행됨
				
	}
	
}
