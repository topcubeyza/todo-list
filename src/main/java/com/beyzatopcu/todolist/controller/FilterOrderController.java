package com.beyzatopcu.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyzatopcu.todolist.dto.FilterAndOrderTypeDto;
import com.beyzatopcu.todolist.dto.FilterOrderDto;
import com.beyzatopcu.todolist.dto.FilterTypeDto;
import com.beyzatopcu.todolist.dto.OrderTypeDto;
import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.service.FilterOrderService;

@RestController
@RequestMapping("filter-order")
@Authenticate
public class FilterOrderController {
	
	@Autowired
	FilterOrderService filterOrderService;
	
	@GetMapping("/get-filter-order-types")
	public FilterAndOrderTypeDto getFilterAndOrderTypes() {
		List<FilterTypeDto> filterTypes = filterOrderService.getFilterTypes();
		List<OrderTypeDto> orderTypes = filterOrderService.getOrderTypes();
		FilterAndOrderTypeDto filtersAndOrders = new FilterAndOrderTypeDto();
		filtersAndOrders.setFilterTypes(filterTypes);
		filtersAndOrders.setOrderTypes(orderTypes);
		
		return filtersAndOrders;
	}
	
	@PostMapping("/filter-and-order")
	public List<TodoItemDto> filterAndOrder(@RequestBody FilterOrderDto filterOrderDto) {
		OrderTypeDto orderTypeDto;
		if (filterOrderDto.getOrderId() == null) {
			orderTypeDto = null;
		} else {
			orderTypeDto = new OrderTypeDto();
			orderTypeDto.setId(filterOrderDto.getOrderId());
			orderTypeDto.setAscending(filterOrderDto.isAscending());
		}
		return filterOrderService.filterAndOrder(filterOrderDto.getTodoListId(), filterOrderDto.getFilters(), orderTypeDto);
	}

}
