package com.beyzatopcu.todolist.service;

import com.beyzatopcu.todolist.dto.UserDto;

public interface UserService {
	public boolean save(UserDto user);
	public UserDto findByUsername(String username);
}
