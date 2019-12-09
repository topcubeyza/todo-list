package com.beyzatopcu.todolist.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyzatopcu.todolist.constants.FilterIds;
import com.beyzatopcu.todolist.constants.OrderTypeIds;
import com.beyzatopcu.todolist.dto.FilterDto;
import com.beyzatopcu.todolist.dto.FilterTypeDto;
import com.beyzatopcu.todolist.dto.OrderTypeDto;
import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.entity.FilterType;
import com.beyzatopcu.todolist.entity.OrderType;
import com.beyzatopcu.todolist.entity.TodoItem;
import com.beyzatopcu.todolist.repository.FilterTypeRepository;
import com.beyzatopcu.todolist.repository.OrderTypeRepository;
import com.beyzatopcu.todolist.repository.TodoListRepository;

@Service
public class FilterOrderServiceImpl implements FilterOrderService {

	@Autowired
	FilterTypeRepository filterTypeRepository;

	@Autowired
	OrderTypeRepository orderTypeRepository;

	@Autowired
	TodoListRepository todoListRepository;
	
	@Autowired
	DateHelper dateHelper;

	@Override
	public List<FilterTypeDto> getFilterTypes() {
		List<FilterType> filterTypes = filterTypeRepository.findAll();
		List<FilterTypeDto> filterTypeDtoList = new ArrayList<FilterTypeDto>();

		FilterTypeDto filterTypeDto;
		for (FilterType filterType : filterTypes) {
			filterTypeDto = new FilterTypeDto();
			filterTypeDto.setId(filterType.getId());
			filterTypeDto.setName(filterType.getName());
			filterTypeDto.setType(filterType.getObjectType());

			filterTypeDtoList.add(filterTypeDto);
		}

		return filterTypeDtoList;
	}

	@Override
	public List<OrderTypeDto> getOrderTypes() {
		List<OrderType> orderTypes = orderTypeRepository.findAll();
		List<OrderTypeDto> orderTypeDtoList = new ArrayList<OrderTypeDto>();

		OrderTypeDto orderTypeDto;
		for (OrderType orderType : orderTypes) {
			orderTypeDto = new OrderTypeDto();
			orderTypeDto.setId(orderType.getId());
			orderTypeDto.setName(orderType.getName());
			orderTypeDto.setAscending(true);

			orderTypeDtoList.add(orderTypeDto);
		}

		return orderTypeDtoList;
	}

	@Override
	public List<TodoItemDto> filterAndOrder(Long todoListId, List<FilterDto> filters, OrderTypeDto orderTypeDto) {
		if (todoListRepository.existsById(todoListId)) {
			List<TodoItem> todoItemList = todoListRepository.getOne(todoListId).getTodoItemList();
			List<TodoItemDto> todoItemDtoList = new ArrayList<TodoItemDto>();

			TodoItemDto todoItemDto;
			for (TodoItem todoItem : todoItemList) {
				todoItemDto = new TodoItemDto();
				todoItemDto.setCreatedOn(dateHelper.convertDateToString(todoItem.getCreatedOn()));
				todoItemDto.setDeadline(dateHelper.convertDateToString(todoItem.getDeadline()));
				todoItemDto.setDescription(todoItem.getDescription());
				todoItemDto.setId(todoItem.getId());
				todoItemDto.setName(todoItem.getName());
				todoItemDto.setStatus(todoItem.getStatus());
				todoItemDto.setTodoListId(todoItem.getTodoList().getId());
				
				String todayStr = dateHelper.convertDateToString(new Date());
				Date today = dateHelper.convertStringToDate(todayStr);
				todoItemDto.setExpired(todoItem.getDeadline().before(today));

				todoItemDtoList.add(todoItemDto);
			}

			Map<String, Object> filterValues = new HashMap<String, Object>();
			for (FilterDto filter : filters) {
				filterValues.put(filter.getId(), filter.getValue());
			}

			return filterAndOrder(todoItemDtoList, filterValues, orderTypeDto);
		}

		return null;
	}

	@Override
	public List<TodoItemDto> filterAndOrder(List<TodoItemDto> todoItemList, Map<String, Object> filters,
			OrderTypeDto orderTypeDto) {
		List<TodoItemDto> filteredList = filter(todoItemList, filters);
		List<TodoItemDto> filteredAndOrderedList = order(filteredList, orderTypeDto);

		return filteredAndOrderedList;
	}

	private List<TodoItemDto> filter(List<TodoItemDto> todoItemList, Map<String, Object> filters) {
		if (filters == null)
			return todoItemList;

		List<TodoItemDto> todoItemListFiltered = new ArrayList<TodoItemDto>();

		boolean passedStatus, passedExpired, passedName = false;
		for (TodoItemDto todoItem : todoItemList) {
			passedStatus = filterByStatus(todoItem, filters.get(FilterIds.STATUS));
			passedExpired = filterByExpiration(todoItem, filters.get(FilterIds.EXPIRED));
			passedName = filterByName(todoItem, filters.get(FilterIds.NAME));

			if (passedExpired && passedName && passedStatus) {
				todoItemListFiltered.add(todoItem);
			}
		}

		return todoItemListFiltered;
	}

	private boolean filterByStatus(TodoItemDto todoItem, Object statusObj) {
		if (statusObj == null)
			return true;
		Boolean status;
		if (statusObj instanceof Boolean) {
			status = (Boolean) statusObj;
			return todoItem.getStatus() == status.booleanValue();
		} else {
			return true;
		}
	}

	private boolean filterByExpiration(TodoItemDto todoItem, Object expiredObj) {
		if (expiredObj == null)
			return true;
		Boolean expired;
		if (expiredObj instanceof Boolean) {
			expired = (Boolean) expiredObj;
			String todayStr = dateHelper.convertDateToString(new Date());
			Date today = dateHelper.convertStringToDate(todayStr);
			return (dateHelper.convertStringToDate(todoItem.getDeadline()).compareTo(today) < 0) == expired;
		} else {
			return true;
		}
	}

	private boolean filterByName(TodoItemDto todoItem, Object nameObj) {
		if (nameObj == null)
			return true;
		String name;
		if (nameObj instanceof String) {
			name = (String) nameObj;
			return (todoItem.getName().contains(name.trim()));
		} else {
			return true;
		}
	}

	private List<TodoItemDto> order(List<TodoItemDto> todoItemList, OrderTypeDto orderTypeDto) {
		if (orderTypeDto == null) {
			return todoItemList;
		}
		Comparator<TodoItemDto> comparator = null;
		switch (orderTypeDto.getId()) {
		case OrderTypeIds.CREATEDATE:
			comparator = new Comparator<TodoItemDto>() {
				@Override
				public int compare(TodoItemDto o1, TodoItemDto o2) {
					Date d1 = dateHelper.convertStringToDate(o1.getCreatedOn());
					Date d2 = dateHelper.convertStringToDate(o2.getCreatedOn());
					int result =  d1.compareTo(d2);
					if (!orderTypeDto.getAscending()) {
						result *= -1;
					}
					
					return result;
				}
			};
			break;
		case OrderTypeIds.DEADLINE:
			comparator = new Comparator<TodoItemDto>() {

				@Override
				public int compare(TodoItemDto o1, TodoItemDto o2) {
					Date d1 = dateHelper.convertStringToDate(o1.getDeadline());
					Date d2 = dateHelper.convertStringToDate(o2.getDeadline());
					int result = d1.compareTo(d2);
					if (!orderTypeDto.getAscending()) {
						result *= -1;
					}
					
					return result;
				}
			};
			break;
		case OrderTypeIds.NAME:
			comparator= new Comparator<TodoItemDto>() {
				
				@Override
				public int compare(TodoItemDto o1, TodoItemDto o2) {
					int result = o1.getName().compareTo(o2.getName());
					if (!orderTypeDto.getAscending()) {
						result *= -1;
					}
					
					return result;
				}
			};
			break;
		case OrderTypeIds.STATUS:
			comparator = new Comparator<TodoItemDto>() {

				@Override
				public int compare(TodoItemDto o1, TodoItemDto o2) {
					int result;
					if (o1.getStatus() && !o2.getStatus()) {
						result = 1;
					} else if (!o1.getStatus() && o2.getStatus()) {
						result = -1;
					}
					else {						
						result = 0;
					}
					if (!orderTypeDto.getAscending()) {
						result *= -1;
					}
					
					return result;
				}
			};
			break;
		default:
			break;
		}
		
		todoItemList.sort(comparator);
		
		return todoItemList;
	}

}
