package com.beyzatopcu.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyzatopcu.todolist.dto.FilterOrderDto;
import com.beyzatopcu.todolist.dto.FilterTypeDto;
import com.beyzatopcu.todolist.dto.OrderTypeDto;
import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.service.FilterOrderService;

@RestController
@RequestMapping("filter-order")
public class FilterOrderController {
	
	@Autowired
	FilterOrderService filterOrderService;
	
	@GetMapping("get-filter-types")
	public List<FilterTypeDto> getFilterTypes() {
		return filterOrderService.getFilterTypes();
	}
	
	@GetMapping("get-order-types")
	public List<OrderTypeDto> getOrderTypes() {
		return filterOrderService.getOrderTypes();
	}
	
	@PostMapping("filter-and-order")
	public List<TodoItemDto> filterAndOrder(@RequestBody FilterOrderDto filterOrderDto) {
		return filterOrderService.filterAndOrder(filterOrderDto.getTodoListId(), filterOrderDto.getFilters(), filterOrderDto.getOrderTypeDto());
	}

}
