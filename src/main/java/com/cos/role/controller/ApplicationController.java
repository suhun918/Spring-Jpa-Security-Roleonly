package com.cos.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.role.model.User;
import com.cos.role.repository.UserRepository;

@RestController
public class ApplicationController {

	@Autowired
	private UserRepository mRepo;

	@GetMapping("/authorize")
	public List<User> getAuthorize() {
		List<User> users = mRepo.findAll();
		return users;
	}

	// 인증
	@GetMapping("/user/test")
	public String userTest() {
		return "usertest";
	}
	
	//인증 없이 들어가지는 곳
	@GetMapping("/user/list")
	public String userList() {
		return "userList";
	}

	// admin or manager
	@GetMapping("/manager/test")
	public String managerTest() {
		return "managertest";
	}

	// admin
	@GetMapping("/admin/test")
	public String adminTest() {
		return "admintest";
	}

	// tester or admin
	@GetMapping("/tester/test")
	public String testerTest() {
		return "testertest";
	}
	
	//권한이 없어 거부됐을때 들어가는 곳
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "권한이 없습니다.";
	}
}
