package com.beyzatopcu.todolist.service;

import com.beyzatopcu.todolist.dto.UserAuthDto;

public interface UserService {
	public boolean save(UserAuthDto user);
	public UserAuthDto findByUsername(String username);
}
