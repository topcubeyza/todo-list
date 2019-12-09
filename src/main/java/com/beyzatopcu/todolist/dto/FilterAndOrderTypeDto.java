package com.beyzatopcu.todolist.dto;

import java.util.List;

public class FilterAndOrderTypeDto {
	
	private List<FilterTypeDto> filterTypes;
	private List<OrderTypeDto> orderTypes;
	
	public List<FilterTypeDto> getFilterTypes() {
		return filterTypes;
	}
	public void setFilterTypes(List<FilterTypeDto> filterTypes) {
		this.filterTypes = filterTypes;
	}
	public List<OrderTypeDto> getOrderTypes() {
		return orderTypes;
	}
	public void setOrderTypes(List<OrderTypeDto> orderTypes) {
		this.orderTypes = orderTypes;
	}

}
