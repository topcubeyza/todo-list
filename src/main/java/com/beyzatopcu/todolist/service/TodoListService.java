package com.beyzatopcu.todolist.service;

import java.util.List;

import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.dto.TodoListDto;

public interface TodoListService {
	
	public TodoListDto add(TodoListDto todoListDto, String username);
	public TodoListDto update(TodoListDto todoListDto);
	public boolean delete(Long id);
	public TodoListDto get(Long id);
	public List<TodoItemDto> getItems(Long id);

}
