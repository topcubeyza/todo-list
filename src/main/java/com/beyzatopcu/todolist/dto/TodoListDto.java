package com.beyzatopcu.todolist.dto;

import java.util.List;

public class TodoListDto {
	
	private Long id;
	private String name;
	private List<TodoItemDto> todoItemList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TodoItemDto> getTodoItemList() {
		return todoItemList;
	}
	public void setTodoItemList(List<TodoItemDto> todoItemList) {
		this.todoItemList = todoItemList;
	}

}
