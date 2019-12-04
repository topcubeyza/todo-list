package com.beyzatopcu.todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.dto.TodoListDto;

@RestController
@RequestMapping("todolist")
public class TodoListController {
	
	@GetMapping("/get-all")
	public List<TodoListDto> getAll() {
		return null;
	}
	
	@GetMapping("/get-items-of-list/{id}")
	public List<TodoItemDto> getItemsOfList(@PathVariable Long id) {
		return null;
	}
	
	@PostMapping("/create")
	public TodoListDto createTodoList(@RequestBody TodoListDto todoListDto) {
		return null;
	}
	
	@PostMapping("/delete")
	public boolean deleteTodoItem(@RequestBody Long id) {
		return false;
	}

}
