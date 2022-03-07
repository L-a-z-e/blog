package com.laze.blog.model;

import java.sql.Timestamp;
import java.util.List;

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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content;
	
	
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One, Eager 무조건 가져오기
	@JoinColumn(name="userId")
	private User user;
														 // Lazy 필요하면 가져오기
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아님, 컬럼만들지말라는 뜻, board는 Reply 에 있는 필드명임
	@JsonIgnoreProperties({"board"})
	private List<Reply> replys;
	
	
	@CreationTimestamp
	private Timestamp createDate;

}
