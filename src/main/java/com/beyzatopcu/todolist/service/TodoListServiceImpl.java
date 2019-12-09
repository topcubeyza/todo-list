package com.beyzatopcu.todolist.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyzatopcu.todolist.dto.TodoItemDto;
import com.beyzatopcu.todolist.dto.TodoListDto;
import com.beyzatopcu.todolist.entity.TodoItem;
import com.beyzatopcu.todolist.entity.TodoList;
import com.beyzatopcu.todolist.entity.User;
import com.beyzatopcu.todolist.repository.TodoListRepository;
import com.beyzatopcu.todolist.repository.UserRepository;

@Service
public class TodoListServiceImpl implements TodoListService {
	

	@Autowired
	TodoListRepository todoListRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DateHelper dateHelper;
	
	@Override
	public TodoListDto add(TodoListDto todoListDto, String username) {
		if (checkTodoList(todoListDto, false)) {
			TodoList todoList = new TodoList();
			todoList.setName(todoListDto.getName());
			User user = userRepository.findByUsername(username);
			if (user == null) return null;
			todoList.setUser(user);
			
			todoListRepository.save(todoList);
			
			todoListDto.setId(todoList.getId());
			
			return todoListDto;
			
		}
		
		return null;
	}

	@Override
	public TodoListDto update(TodoListDto todoListDto) {
		if (checkTodoList(todoListDto, true)) {
			TodoList todoList = todoListRepository.getOne(todoListDto.getId());
			todoList.setName(todoListDto.getName());
			
			todoListRepository.save(todoList);
			
			return todoListDto;
		}
		
		return null;
	}

	@Override
	public boolean delete(Long id) {
		if (todoListRepository.existsById(id)) {
			todoListRepository.deleteById(id);
			return true;
		}
		
		return false;
	}

	@Override
	public TodoListDto get(Long id) {
		if (id != null && todoListRepository.existsById(id)) {
			TodoList todoList = todoListRepository.getOne(id);
			TodoListDto todoListDto = new TodoListDto();
			todoListDto.setId(todoList.getId());
			todoListDto.setName(todoList.getName());
			
			return todoListDto;
		}
		
		return null;
	}

	@Override
	public List<TodoItemDto> getItems(Long id) {
		if (id != null && todoListRepository.existsById(id)) {
			TodoList todoList = todoListRepository.getOne(id);
			List<TodoItem> todoItems = todoList.getTodoItemList();
			List<TodoItemDto> todoItemDtos = new ArrayList<TodoItemDto>();
			TodoItemDto todoItemDto;
			for (TodoItem todoItem: todoItems) {
				todoItemDto = new TodoItemDto();
				todoItemDto.setCreatedOn(dateHelper.convertDateToString(todoItem.getCreatedOn()));
				todoItemDto.setDeadline(dateHelper.convertDateToString(todoItem.getDeadline()));
				todoItemDto.setDescription(todoItem.getDescription());
				todoItemDto.setId(todoItem.getId());
				todoItemDto.setName(todoItem.getName());
				todoItemDto.setStatus(todoItem.getStatus());
				todoItemDto.setTodoListId(id);
				
				String todayStr = dateHelper.convertDateToString(new Date());
				Date today = dateHelper.convertStringToDate(todayStr);
				todoItemDto.setExpired(todoItem.getDeadline().before(today));
				
				todoItemDtos.add(todoItemDto);
			}
			
			return todoItemDtos;
		}
		
		return null;
	}
	
	private boolean checkTodoList(TodoListDto todoListDto, boolean forUpdate) {
		if (todoListDto.getName() == null || todoListDto.getName().trim().length() == 0) {
			return false;
		}
		if (forUpdate) {
			if (todoListDto.getId() == null || !todoListRepository.existsById(todoListDto.getId())) {
				return false;
			}
		}
		
		return true;
	}

}
