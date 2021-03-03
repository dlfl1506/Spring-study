package com.cos.myjpa.domain.post;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
public class Post {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )  // Table,Auto_increment,Sequence
	private Long id;
	@Column(length=60,nullable = false)
	private String title;
	@Lob // 대용량데이터
	private String content;
	
	
	// 누가 적었는지?
	@CreationTimestamp
	private LocalDateTime createDate;
}
