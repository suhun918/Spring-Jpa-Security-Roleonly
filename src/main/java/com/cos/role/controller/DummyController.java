package com.cos.role.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.role.common.RoleType;
import com.cos.role.model.Role;
import com.cos.role.model.User;
import com.cos.role.model.UserRole;
import com.cos.role.repository.UserRepository;
import com.cos.role.repository.UserRoleRepository;

// DATA를 INSERT 하기
@RestController
public class DummyController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserRoleRepository userRoleRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/dummy/user/insert")
	public User dummyUserInsert(@RequestParam String username, @RequestParam String role1, @RequestParam String role2) {
		User u1 = new User();
		String encPassword = passwordEncoder.encode("1234");
		u1.setPassword(encPassword);
		u1.setUsername(username);
		u1.setEmail(username + "@nate.com");

		System.out.println("====영속화 전====");
		System.out.println("id : " + u1.getId());
		System.out.println("username : " + u1.getUsername());
		System.out.println("password : " + u1.getPassword());
		System.out.println("email : " + u1.getEmail());

		userRepo.save(u1);

		System.out.println("====영속화 후====");
		System.out.println("id : " + u1.getId());
		System.out.println("username : " + u1.getUsername());
		System.out.println("password : " + u1.getPassword());
		System.out.println("email : " + u1.getEmail());

		UserRole ur1 = new UserRole();
		ur1.setRole(new Role(RoleType.valueOf(role1).ID, role1));
		ur1.setUser(u1);

		userRoleRepo.save(ur1);

		UserRole ur2 = new UserRole();
		ur2.setRole(new Role(RoleType.valueOf(role2).ID, role2));
		ur2.setUser(u1);

		userRoleRepo.save(ur2);

		u1.addRole(ur1);
		u1.addRole(ur2);

		return u1;
	}

	@GetMapping("/dummy/user/{id}")
	public User getUser(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		return user.get();
	}

}
