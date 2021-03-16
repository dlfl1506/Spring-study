package com.cos.blog.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cos.blog.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Configuration // 설정, 메모리에 띄움 ioc에 등록
@EnableWebSecurity // 이제 커스터마이징한 시큐리티가 실행된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{ // 어댑터는 함수를 걸러줌, 강제성을 없애준다.
	
	
	private final OAuth2DetailsService auth2DetailsService;
	
	
	// IoC 등록만하면 AuthenticationManager 가 Bcrypt로 자동 검증해줌.
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable();   // 403 인증 에러방지
      http .authorizeRequests()
    //   /** 하면  내부까지 막음
         .antMatchers("/user/**","/post/**","/reply/**").access("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")  //  ROLE_는 강제성이 있음 .롤검증 시킴    USER 를  가지고있는 사람만 진입가능   
         .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")   // ADMIN 을 가지고있는 사람만 진입가능 
         .anyRequest().permitAll() // 나머지는 전부 허용 
         .and() // 여기서 끝
         .formLogin() // x-www-form-urlencoded , json으로 던지면 안된다. 결국 폼태그를 만들어야한다.
         .loginPage("/loginForm") // user, post 호출 시 로그인 페이지로 리다이렉션됨 
         .loginProcessingUrl("/login") // 스프링 시큐리티가 /login  (post방식) 으로 들어오면 낚아챔 
         .defaultSuccessUrl("/")// 로그인했을때 내가 원하는 페이지로 이동시켜줌
         .and()
         .oauth2Login()
         .userInfoEndpoint()
         .userService(auth2DetailsService);
        
      /*      .successHandler(new AuthenticationSuccessHandler() {
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			response.sendRedirect("/");
			
		}
	});*/   //  성공했을때 해당 페이지로간다 
   }
}