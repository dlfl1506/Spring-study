package com.cos.myjpa.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.myjpa.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User {
	
	@Id  // PK
	@GeneratedValue(strategy =GenerationType.IDENTITY )  // Table,Auto_increment,Sequence
	private Long id;
	private String username;
	private String password;
	private String email;
	
	// 자동으로 현재시간이 들어감
	@CreationTimestamp
	private LocalDateTime createDate;
	
	
	// 역방향 매핑
	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user",fetch=FetchType.LAZY)     // 나는 FK의 주인이 아니다. FK는 user 변수명이다. 
	private List<Post> post;

	
	
}
