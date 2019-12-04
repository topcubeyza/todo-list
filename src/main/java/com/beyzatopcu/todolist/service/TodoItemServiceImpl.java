package com.beyzatopcu.todolist.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.entity.TodoItem;
import com.beyzatopcu.todolist.repository.TodoItemRepository;
import com.beyzatopcu.todolist.repository.TodoListRepository;

@Service
public class TodoItemServiceImpl implements TodoItemService {
	
	@Autowired
	TodoItemRepository todoItemRepository;
	
	@Autowired
	TodoListRepository todoListRepository;

	@Override
	public TodoItemDto add(TodoItemDto todoItemDto) {
		if (checkValidity(todoItemDto, false)) {
			TodoItem todoItem = new TodoItem();
			todoItem.setCreatedOn(new Date());
			todoItem.setDeadline(todoItemDto.getDeadline());
			todoItem.setDescription(todoItemDto.getDescription());
			todoItem.setName(todoItemDto.getName());
			todoItem.setStatus(false);
			todoItem.setTodoList(todoListRepository.getOne(todoItemDto.getTodoListId()));
			
			todoItemRepository.save(todoItem);
			
			todoItemDto.setId(todoItem.getId());
			todoItemDto.setCreatedOn(todoItem.getCreatedOn());
			todoItemDto.setStatus(todoItem.getStatus());
			
			return todoItemDto;
		}
		
		return null;
	}

	@Override
	public TodoItemDto update(TodoItemDto todoItemDto) {
		if (checkValidity(todoItemDto, true)) {
			TodoItem todoItem = todoItemRepository.getOne(todoItemDto.getId());
			todoItem.setDeadline(todoItemDto.getDeadline());
			todoItem.setDescription(todoItemDto.getDescription());
			todoItem.setName(todoItemDto.getName());
			
			todoItemRepository.save(todoItem);
			
			return todoItemDto;
		}
		
		return null;
	}
	
	@Override
	public boolean markComplete(Long id) {
		if (todoItemRepository.existsById(id)) {
			TodoItem todoItem = todoItemRepository.getOne(id);
			List<TodoItem> dependsOnList = todoItem.getDependsOnList();
			
			boolean canMarkComplete = true;
			for (TodoItem item: dependsOnList) {
				if (!item.getStatus()) {
					canMarkComplete = false;
					break;
				}
			}
			if (canMarkComplete) {
				todoItem.setStatus(true);
				todoItemRepository.save(todoItem);
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean markIncomplete(Long id) {
		if (todoItemRepository.existsById(id)) {
			TodoItem todoItem = todoItemRepository.getOne(id);
			List<TodoItem> dependentList = todoItem.getDependentList();
			
			boolean canMarkIncomplete = true;
			for (TodoItem item: dependentList) {
				if (item.getStatus()) {
					canMarkIncomplete = false;
					break;
				}
			}
			if (canMarkIncomplete) {
				todoItem.setStatus(false);
				todoItemRepository.save(todoItem);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if (todoItemRepository.existsById(id)) {
			TodoItem todoItem = todoItemRepository.getOne(id);
			List<TodoItem> dependentList = todoItem.getDependentList();
			if (dependentList.size() == 0) {
				todoItemRepository.delete(todoItem);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public TodoItemDto getById(Long id) {
		if (todoItemRepository.existsById(id)) {
			TodoItem todoItem = todoItemRepository.getOne(id);
			TodoItemDto todoItemDto = new TodoItemDto();
			todoItemDto.setCreatedOn(todoItem.getCreatedOn());
			todoItemDto.setDeadline(todoItem.getDeadline());
			todoItemDto.setDescription(todoItem.getDescription());
			todoItemDto.setId(todoItem.getId());
			todoItemDto.setName(todoItem.getName());
			todoItemDto.setStatus(todoItem.getStatus());
			todoItemDto.setTodoListId(todoItem.getTodoList().getId());
			
			return todoItemDto;
		}
		
		return null;
	}
	
	private boolean checkValidity(TodoItemDto todoItemDto, boolean forUpdate) {
		if (todoItemDto.getName() == null || todoItemDto.getName().trim().length() == 0) {
			return false;
		}
		if (todoItemDto.getDescription() == null || todoItemDto.getDescription().trim().length() == 0) {
			return false;
		}
		if (todoItemDto.getDeadline() == null) {
			return false;
		}
		if (!todoListRepository.existsById(todoItemDto.getTodoListId())) {
			return false;
		}
		if(forUpdate) {
			if (todoItemDto.getId() == null) {
				return false;
			}
			if(!todoItemRepository.existsById(todoItemDto.getId())) {
				return false;
			}
		}
		
		return true;
	}

}
