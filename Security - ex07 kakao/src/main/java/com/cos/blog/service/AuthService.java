package com.cos.blog.service;



import javax.swing.plaf.basic.BasicTabbedPaneUI;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.user.RoleType;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	 private final UserRepository userRepository;
	 private final BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	  @Transactional
	   public void 회원가입(User user) {
	      String rawPassword = user.getPassword();
	      String encPassword = bCryptPasswordEncoder.encode(rawPassword);
	      user.setRole(RoleType.USER);
	      user.setPassword(encPassword);
	      userRepository.save(user);
	   }

}
