package com.beyzatopcu.todolist.dto;

public class TodoItemStatusUpdateDto {
	
	private Long id;
	private boolean status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
