package com.beyzatopcu.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beyzatopcu.todolist.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	boolean existsByUsername(String username);
	
}
