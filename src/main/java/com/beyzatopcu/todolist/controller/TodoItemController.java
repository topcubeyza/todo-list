package com.beyzatopcu.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.dto.TodoItemStatusUpdateDto;

@RestController
@RequestMapping("todoitem")
public class TodoItemController {
	
	@PostMapping("/add")
	public TodoItemDto addTodoItem(@RequestBody TodoItemDto todoItemDto) {
		return null;
	}
	
	@PostMapping("/delete")
	public boolean deleteTodoItem(@RequestBody Long todoItemId) {
		return false;
	}
	
	@PostMapping("/update-status")
	public boolean updateStatusOfTodoItem(@RequestBody TodoItemStatusUpdateDto todoItemStatusUpdateDto) {
		return false;
	}
	
	@GetMapping("/get/{id}")
	public TodoItemDto getTodoItem(@PathVariable Long id) {
		return null;
	}

}
