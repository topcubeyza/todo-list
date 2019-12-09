package com.beyzatopcu.todolist.dto;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

public class FilterOrderDto {
	
	private Long todoListId; 
	private List<FilterDto> filters; 
	private OrderTypeDto orderType;
	
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
	public OrderTypeDto getOrderTypeDto() {
		return orderType;
	}
	public void setOrderTypeDto(OrderTypeDto orderTypeDto) {
		this.orderType = orderTypeDto;
	}
	
	

}
