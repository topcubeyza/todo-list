package com.beyzatopcu.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyzatopcu.todolist.dto.DependedDto;
import com.beyzatopcu.todolist.dto.DependencyDto;
import com.beyzatopcu.todolist.dto.DependentDto;
import com.beyzatopcu.todolist.dto.TodoItemDetailDto;
import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.dto.TodoItemStatusUpdateDto;
import com.beyzatopcu.todolist.service.DependencyService;
import com.beyzatopcu.todolist.service.TodoItemService;

@RestController
@RequestMapping("todoitem")
@Authenticate
public class TodoItemController {
	
	@Autowired
	TodoItemService todoItemService;
	
	@Autowired
	DependencyService dependencyService;
	
	@PostMapping("/add")
	public TodoItemDto addTodoItem(@RequestBody TodoItemDto todoItemDto) {
		return todoItemService.add(todoItemDto);
	}
	
	@PostMapping("/delete")
	public boolean deleteTodoItem(@RequestBody TodoItemDto todoItemDto) {
		return todoItemService.delete(todoItemDto.getId());
	}
	
	@PostMapping("/update-status")
	public boolean updateStatusOfTodoItem(@RequestBody TodoItemStatusUpdateDto todoItemStatusUpdateDto) {
		if (todoItemStatusUpdateDto.getStatus() == true) {
			return todoItemService.markComplete(todoItemStatusUpdateDto.getId());
		} else {
			return todoItemService.markIncomplete(todoItemStatusUpdateDto.getId());
		}
	}
	
	@PostMapping("/update")
	public boolean updateTodoItem(@RequestBody TodoItemDetailDto todoItemDetailDto) {
		return false;
	}
	
	@GetMapping("/get/{id}")
	public TodoItemDetailDto getTodoItem(@PathVariable Long id) {
		TodoItemDto todoItemDto = todoItemService.getById(id);
		DependedDto dependedDto = todoItemService.getDependedDto(id);
		List<DependentDto> dependentDtoList = todoItemService.getDependents(id);
		
		TodoItemDetailDto todoItemDetailDto = new TodoItemDetailDto();
		todoItemDetailDto.setDependedItem(dependedDto);
		todoItemDetailDto.setDependentItemList(dependentDtoList);
		todoItemDetailDto.setItemSummary(todoItemDto);
		
		return todoItemDetailDto;
	}
	
	@PostMapping("/addDependency")
	public boolean addDependency(@RequestBody DependencyDto dependencyDto) {
		return dependencyService.addDependency(dependencyDto);
	}
	
	@PostMapping("/removeDependency")
	public boolean removeDependency(@RequestBody DependencyDto dependencyDto) {
		return dependencyService.removeDependency(dependencyDto);
	}

}
