package com.beyzatopcu.todolist.dto;

import java.util.List;

public class UserDetailDto {
	
	private String username;
	private List<TodoListDto> todoLists;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<TodoListDto> getTodoLists() {
		return todoLists;
	}
	public void setTodoLists(List<TodoListDto> todoLists) {
		this.todoLists = todoLists;
	}

}
