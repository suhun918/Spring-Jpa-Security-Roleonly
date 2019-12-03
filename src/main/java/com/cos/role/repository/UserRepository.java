package com.cos.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.role.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
