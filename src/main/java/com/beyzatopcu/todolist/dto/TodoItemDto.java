package com.beyzatopcu.todolist.dto;

import java.util.Date;

public class TodoItemDto {
	
	private Long id;
	private String name;
	private String description;
	private Date deadline;
	private boolean status;
	private Date createdOn;
	private Long todoListId;
	
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
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Long getTodoListId() {
		return todoListId;
	}
	public void setTodoListId(Long todoListId) {
		this.todoListId = todoListId;
	}
	
	
}
