package com.cos.blog.domain.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

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
	private Integer id;
	
	@Column(nullable =false ,length = 100, unique=true)  //  nullable : null 허용 x , length : 글자길이 , unique : 중복허용 x 
	private String username;
	
	@Column(nullable =false ,length = 100)    // 123456 => 해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable =false ,length = 100)
	private String email;
	
	
	@Enumerated(EnumType.STRING)
	private RoleType role; // Admin ,User
	
	// 자동으로 현재시간이 들어감
	@CreationTimestamp
	private Timestamp createDate;
	
	
}
