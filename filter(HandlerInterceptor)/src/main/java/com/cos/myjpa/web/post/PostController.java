  
package com.cos.myjpa.web.post;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.service.PostService;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;
import com.cos.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;
/**
 * 
 * ORM = Object Relation Mapping (자바 오브젝트 먼저 만들고 DB에 테이블을 자동 생성, 오브젝트로 연관관계를 맺을 수 있음 )
 * JPA는 자바 오브젝트를 영구적으로 저장하기 위한 인터페이스(함수)
 */

@RequiredArgsConstructor
@RestController
public class PostController {
	private final PostRepository postRepository;
	private final HttpSession session;
	private final EntityManager em;
	private final PostService postService;

	// 인증만 필요
	@PostMapping("/post")
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) {

		User principal = (User) session.getAttribute("principal");

		if (principal == null) {
			return new CommonRespDto<>(-1, "실패", null);
		}

		// 실패 => Exception 을 탄다.
		return new CommonRespDto<>(1, "성공", postService.글쓰기(postSaveReqDto,principal));
	}
	
	// 인증만 필요
	// http://localhost:8080/post/1
	@GetMapping("/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id){
		
		return new CommonRespDto<>(1,"성공",postService.글찾기(id)); // MessageConverter가 모든 getter를 다 호출해서 JSON으로 만들어준다.
	}
	
	// 인증만 필요
	@GetMapping("/post")
	public CommonRespDto<?> findAll(){
		
		return new CommonRespDto<>(1,"성공",postService.글전체보기());
		
	}
	
	// 인증(Authentication) + 권한(Authorization)
	@PutMapping("/post/{id}")
	public CommonRespDto<?> update(@PathVariable Long id, @RequestBody PostUpdateReqDto postUpdateReqDto){
		
		return new CommonRespDto<>(1,"성공",postService.글수정(postUpdateReqDto, id));
	}
	
	// 인증(Authentication) + 권한(Authorization)
	@DeleteMapping("/post/{id}")
	public CommonRespDto<?> deleteById(@PathVariable Long id){ // 인셉션 만들기
		
		postService.글삭제(id);
		return new CommonRespDto<>(1,"성공",null);
	}

}