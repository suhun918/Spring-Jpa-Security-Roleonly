package com.cos.role.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String role;

	public Role(int id, String role) {
		this.id = id;
		this.role = role;
	}
	
	// mappedBy로 주인이 아님을 선언 후 DB에는 생성되지 않게 설정
	// 컬렉션이므로 기본전략 Lazy인데 Role을 불러오는 목적 자체가 User를 보는 것
	// EAGER로 변경하자
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	private List<UserRole> users;
}
