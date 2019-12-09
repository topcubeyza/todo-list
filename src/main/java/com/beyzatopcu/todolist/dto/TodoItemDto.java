package com.beyzatopcu.todolist.dto;

import java.util.Date;

public class TodoItemDto {
	
	private Long id;
	private String name;
	private String description;
	private String deadline;
	private boolean status;
	private String createdOn;
	private Long todoListId;
	private boolean expired;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public Long getTodoListId() {
		return todoListId;
	}
	public void setTodoListId(Long todoListId) {
		this.todoListId = todoListId;
	}
	public boolean getExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
	
}
