package com.cos.blog.domain.post;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
	
	@Id // Primary key 
	@GeneratedValue(strategy =GenerationType.IDENTITY )  // Table,Auto_increment,Sequence
	private Integer id;
	
	@Column(nullable =false ,length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content;
	
	@ColumnDefault("0")  // null 일시 자동으로 디폴트값 0 이 이들어감
	private int count;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	// mappedBy = 변수명 
	@OneToMany(mappedBy = "post" ,fetch =FetchType.LAZY,cascade = CascadeType.REMOVE )   // cacade : post 삭제시 댓글 다날라감.
	@JsonIgnoreProperties({"post"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp 
	private Timestamp createDate;

}
