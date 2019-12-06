package com.beyzatopcu.todolist.service;

import java.util.List;

import com.beyzatopcu.todolist.dto.DependedDto;
import com.beyzatopcu.todolist.dto.DependentDto;
import com.beyzatopcu.todolist.dto.TodoItemDto;

public interface TodoItemService {
	
	public TodoItemDto add(TodoItemDto todoItemDto);
	public TodoItemDto update(TodoItemDto todoItemDto);
	public boolean delete(Long id);
	public TodoItemDto getById(Long id);
	public boolean markComplete(Long id);
	public boolean markIncomplete(Long id);
	public DependedDto getDependedDto(Long todoItemId);
	public List<DependentDto> getDependents(Long todoItemId);
}
