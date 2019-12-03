package com.cos.role.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// 중복방지
	@Column(unique = true)
	private String username;
	private String password;
	private String email;

	// mappedBy로 주인이 아님을 선언 후 DB에는 생성되지 않게 설정
	// 컬렉션이므로 기본전략 Lazy인데 User를 불러오는 목적 자체가 Role을 보는 것
	// EAGER로 변경하자
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	// new 해주는 이유는 아래 함수에서 null포인트 익셉션 막아주기 위해서
	private List<UserRole> roles = new ArrayList<>();
	
	// 이 함수를 호출해서 role을 담아줄 것이다.
	public void addRole(UserRole role) {
		roles.add(role);
	}
	
}
