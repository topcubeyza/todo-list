package com.beyzatopcu.todolist.service;

import java.util.List;

import com.beyzatopcu.todolist.dto.TodoListDto;
import com.beyzatopcu.todolist.dto.UserAuthDto;

public interface UserService {
	public boolean save(UserAuthDto user);
	public UserAuthDto findByUsername(String username);
	public List<TodoListDto> getTodoListByUsername(String username);
	public boolean canLogIn(UserAuthDto user);
}
