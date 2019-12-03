package com.beyzatopcu.todolist.service;

import com.beyzatopcu.todolist.entity.User;

public interface UserService {
	public boolean save(User user);
	public User findByUsername(String username);
}
