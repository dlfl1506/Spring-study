package com.cos.mycontroller;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// localhost:8080 /
// 디스패쳐가 PostController를 찾음.
// 모든 메서드를 다 검색해서 /를 찾는다 !!

@RestController
public class PostController {
	
	@GetMapping("/")
	public String home() {
		return "데이터";
	}
	
	// 연습 :(1) GET : / test/post -> 응답 "포스트"
	@GetMapping("/test/post")
	public String post() {
		return "포스트";
	}
	
	// 1. 쿼리스트링=주소?username=ssar&password=1234 
	@GetMapping("/test/post/data")
	public String testPostData(String username,String password) {
		return "username:"+username+" password : "+password;
	}

	// Get 방식은 데이터를 전송하는데에 목적이 있는것이 아니라 데이터 요구(select)에 목적이 있음.
	// 데이터 전송(insert)이 목적이면 Post를 사용
	@PostMapping("/test/post")
	public String testPost2(String username,String password) {
		return "username:"+username+" password : "+password;
	}
	
	@PostMapping("/test/post/object")
	public User testPost2(User user) {       // x-www-form-urlencoded
		return user;    // MessageConverter 낚아챔 -> json으로 변환해서 리턴(RestController 일때만)
	}
	
	// @RequestBody 을 사용하면 json 데이터를 받을 수 있음.
	@PostMapping("/test/post/json")
	public User testPostJson(@RequestBody User user) {       // json을 전송
		System.out.println(user);
		return user;
	}
	
	// @Pathvariable
	@GetMapping("/test/post/{id}") // test/post/3
	public Integer testPath(@PathVariable int id) {
		return id;
	}
}

