package com.beyzatopcu.todolist.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.dto.TodoListDto;
import com.beyzatopcu.todolist.service.TodoListService;
import com.beyzatopcu.todolist.service.UserService;

@RestController
@RequestMapping("todolist")
@Authenticate
public class TodoListController {
	
	@Autowired
	TodoListService todoListService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/get-items-of-list/{id}")
	public List<TodoItemDto> getItemsOfList(@PathVariable Long id) {
		return todoListService.getItems(id);
	}
	
	@PostMapping("/create")
	public TodoListDto createTodoList(@RequestBody TodoListDto todoListDto, HttpServletRequest req, HttpServletResponse res) {
		String username = "";
	    Cookie[] cookies = req.getCookies();
	    if (cookies == null) {
	    	res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    	return null;
	    }
	    for (Cookie cookie: cookies) {
	    	if (cookie.getName().equalsIgnoreCase("username") && cookie.getValue().length() > 0) {
	    		username = cookie.getValue();
	    	}
	    }
	    
		return todoListService.add(todoListDto, username);
	}
	
	@PostMapping("/delete")
	public Long deleteTodoList(@RequestBody TodoListDto todoListDto) {
		return todoListService.delete(todoListDto.getId()) ? todoListDto.getId() : null;
	}
	
	@GetMapping("/get-todo-lists-of-user")
	public List<TodoListDto> getTodoListOfUser(HttpServletRequest req, HttpServletResponse res) {
	    String username;
	    Cookie[] cookies = req.getCookies();
	    if (cookies == null) {
	    	res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    	return null;
	    }
	    for (Cookie cookie: cookies) {
	    	if (cookie.getName().equalsIgnoreCase("username") && cookie.getValue().length() > 0) {
	    		username = cookie.getValue();
	    		List<TodoListDto> lists = userService.getTodoListByUsername(username);
	    	    return lists;
	    	}
	    }

    	res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	return null;
	    
	}

}
