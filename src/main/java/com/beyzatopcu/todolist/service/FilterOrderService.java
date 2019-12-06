package com.beyzatopcu.todolist.service;

import java.util.List;
import java.util.Map;

import com.beyzatopcu.todolist.dto.FilterDto;
import com.beyzatopcu.todolist.dto.FilterTypeDto;
import com.beyzatopcu.todolist.dto.OrderTypeDto;
import com.beyzatopcu.todolist.dto.TodoItemDto;

public interface FilterOrderService {
	
	public List<FilterTypeDto> getFilterTypes();
	public List<OrderTypeDto> getOrderTypes();
	public List<TodoItemDto> filterAndOrder(List<TodoItemDto> todoItemList, Map<String, Object> filters, OrderTypeDto orderTypeDto);
	public List<TodoItemDto> filterAndOrder(Long todoListId, List<FilterDto> filters, OrderTypeDto orderTypeDto);

}
