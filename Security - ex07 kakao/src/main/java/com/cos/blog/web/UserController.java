package com.cos.blog.web;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

@Controller
public class UserController {
   
	   
   @GetMapping("/user")
   public @ResponseBody String findAll(@AuthenticationPrincipal PrincipalDetails principalDetails ) { // @Controller + @ResponseBody = @RestController
//	 Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
//	PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal(); // UserDetails 를 다 가져옴
//	 System.out.println(principalDetails.getUser());
	   System.out.println(principalDetails.getUser());
      return "User";
   }
   
   
}