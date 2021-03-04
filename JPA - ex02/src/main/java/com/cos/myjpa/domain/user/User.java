package com.cos.myjpa.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

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

}
