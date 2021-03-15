package com.cos.blog.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	private final UserRepository userRepository;
	
	
	@Transactional
	public User 회원수정(int id,UserUpdateReqDto userUpdateReqDto) {
	
		// 영속화 
		User userEntity = userRepository.findById(id).get();
		String encPassword = bCryptPasswordEncoder.encode(userUpdateReqDto.getPassword());
		userEntity.setPassword(encPassword);
		userEntity.setEmail(userUpdateReqDto.getEmail());

		return userEntity;
	} // 더티체킹 - > 업데이트는 항상 더티체킹

}
