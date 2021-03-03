package com.cos.myjpa.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;


// @Repositoy 생략가능함. 내부적으로 IoC에 등록됨.
public interface PostRepository extends JpaRepository<Post, Long> {    // <모델이름,프라이머리키의 데이터타입>

}
