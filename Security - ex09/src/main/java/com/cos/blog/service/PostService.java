package com.cos.blog.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.post.PostRepository;
import com.cos.blog.domain.user.User;
import com.cos.blog.web.post.dto.PostSaveReqDto;
import com.cos.blog.web.post.dto.PostUpdateReqDto;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;
	

	@Transactional(readOnly = true)   //    영속성컨텍스트   - > 고립성, 변경감지 x ( 쓸데없는 연산을 줄여준다.)
	public Page<Post> 전체찾기(Pageable pageable){
	      return postRepository.findAll(pageable);
	   }
	
	
	@Transactional
	public Post 글쓰기(Post post) {
		return postRepository.save(post);
	}
	
	@Transactional(readOnly = true)
	public Post 상세보기(int id){
	      return postRepository.findById(id).get();
	   }
	
	@Transactional
	public void 글수정(int id,PostUpdateReqDto postUpdateReqDto) {
		// 영속화 
		Post  postEntity = postRepository.findById(id).get();
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());

	} // 더티체킹 - > 업데이트는 항상 더티체킹
	
	@Transactional
	public void 삭제하기(int id) {
		postRepository.deleteById(id);
	}
	
	
}
