package com.beyzatopcu.todolist.dto;

public class DependencyDto {
	
	private Long dependentTodoItemId;
	private Long dependedTodoItemId;
	
	public Long getDependentTodoItemId() {
		return dependentTodoItemId;
	}
	public void setDependentTodoItemId(Long dependentTodoItemId) {
		this.dependentTodoItemId = dependentTodoItemId;
	}
	public Long getDependedTodoItemId() {
		return dependedTodoItemId;
	}
	public void setDependedTodoItemId(Long dependedTodoItemId) {
		this.dependedTodoItemId = dependedTodoItemId;
	}

}
