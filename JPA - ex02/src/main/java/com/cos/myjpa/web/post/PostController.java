package com.cos.myjpa.web.post;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

/**
 * ORM = Object Relation Mapping ( 자바 오브젝트 먼저 만들고 DB에 테이블을 자동생성 , 자바 오브젝트로 연관관계를
 * 맺을 수 있음 ) JPA = Java 오브젝트를 영구적으로 저장하기 위한 인터페이스(함수)
 * 
 *
 */

@RequiredArgsConstructor
@RestController
public class PostController {
	private final PostRepository postRepository;
	private final HttpSession session;

	@PostMapping("/post")
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) {

		User principal = (User) session.getAttribute("principal");

		if (principal == null) {
			return new CommonRespDto<>(-1, "실패", null);
		}

		Post post = postSaveReqDto.toEntity();
		post.setUser(principal);
		Post postEntity = postRepository.save(post);

		// 실패 => Exception 을 탄다.
		return new CommonRespDto<>(1, "성공", postEntity);
	}
	
	@GetMapping("/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id){
		Post  postsEntity =postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을수 없습니다.");
					});
		
		return new CommonRespDto<>(1,"성공",postsEntity);
	}

}
