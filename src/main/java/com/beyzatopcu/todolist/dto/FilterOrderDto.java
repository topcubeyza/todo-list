package com.beyzatopcu.todolist.dto;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

public class FilterOrderDto {
	
	private Long todoListId; 
	private List<FilterDto> filters; 
	private String orderId;
	private boolean ascending;
	
	public Long getTodoListId() {
		return todoListId;
	}
	public void setTodoListId(Long todoListId) {
		this.todoListId = todoListId;
	}
	public List<FilterDto> getFilters() {
		return filters;
	}
	public void setFilters(List<FilterDto> filters) {
		this.filters = filters;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public boolean isAscending() {
		return ascending;
	}
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
	
	

}
