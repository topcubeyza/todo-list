package com.beyzatopcu.todolist.dto;

import java.util.Date;

public class FilterDto {
	
	private Long todoListId;
	private boolean status;
	private boolean isExpired;
	private String name;
	
	public Long getTodoListId() {
		return todoListId;
	}
	public void setTodoListId(Long todoListId) {
		this.todoListId = todoListId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isExpired() {
		return isExpired;
	}
	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
