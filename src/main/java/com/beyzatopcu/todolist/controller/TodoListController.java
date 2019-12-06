package com.beyzatopcu.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.dto.TodoListDto;
import com.beyzatopcu.todolist.service.SecurityService;
import com.beyzatopcu.todolist.service.TodoListService;
import com.beyzatopcu.todolist.service.UserService;

@RestController
@RequestMapping("todolist")
public class TodoListController {
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	TodoListService todoListService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/get-items-of-list/{id}")
	public List<TodoItemDto> getItemsOfList(@PathVariable Long id) {
		return todoListService.getItems(id);
	}
	
	@PostMapping("/create")
	public TodoListDto createTodoList(@RequestBody TodoListDto todoListDto) {
		return todoListService.add(todoListDto);
	}
	
	@PostMapping("/delete")
	public boolean deleteTodoItem(@RequestBody Long id) {
		return todoListService.delete(id);
	}
	
	@PostMapping("/get-todo-lists-of-user")
	public List<TodoListDto> getTodoListOfUser() {
		String username = securityService.findLoggedInUsername();
		return userService.getTodoListByUsername(username);
	}

}
