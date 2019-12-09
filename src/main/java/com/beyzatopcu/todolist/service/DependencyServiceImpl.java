package com.beyzatopcu.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyzatopcu.todolist.dto.DependencyDto;
import com.beyzatopcu.todolist.entity.TodoItem;
import com.beyzatopcu.todolist.repository.TodoItemRepository;

@Service
public class DependencyServiceImpl implements DependencyService {
	
	@Autowired
	TodoItemRepository todoItemRepository;

	@Override
	public boolean addDependency(DependencyDto dependencyDto) {
		
		if (!checkForAddition(dependencyDto)) {
			return false;
		}

		Long dependentTodoItemId = dependencyDto.getDependentTodoItemId();
		Long dependedTodoItemId = dependencyDto.getDependedTodoItemId();
		
		TodoItem dependentTodoItem = todoItemRepository.getOne(dependentTodoItemId);
		TodoItem dependedTodoItem = todoItemRepository.getOne(dependedTodoItemId);
		
		dependentTodoItem.setDependsOn(dependedTodoItem);
		dependedTodoItem.addDependentTodoItem(dependentTodoItem);
		
		todoItemRepository.save(dependedTodoItem);
		todoItemRepository.save(dependentTodoItem);
		
		return true;
	}

	@Override
	public boolean removeDependency(DependencyDto dependencyDto) {
		Long dependentTodoItemId = dependencyDto.getDependentTodoItemId();
		Long dependedTodoItemId = dependencyDto.getDependedTodoItemId();
		
		if (!checkTodoItem(dependentTodoItemId) || !checkTodoItem(dependedTodoItemId)) {
			return false;
		}
		
		TodoItem dependentTodoItem = todoItemRepository.getOne(dependentTodoItemId);
		TodoItem dependedTodoItem = todoItemRepository.getOne(dependedTodoItemId);
		
		// if the dependent actually does not depend on the given item, return false
		if (dependentTodoItem.getDependsOn().getId().longValue() != dependedTodoItemId.longValue()) {
			return false;
		}
		
		dependentTodoItem.setDependsOn(null);
		dependedTodoItem.removeDependentTodoItem(dependentTodoItem);
		
		todoItemRepository.save(dependentTodoItem);
		todoItemRepository.save(dependedTodoItem);
		
		return true;
	}

	private boolean checkForAddition(DependencyDto dependencyDto) {
		Long dependentTodoItemId = dependencyDto.getDependentTodoItemId();
		Long dependedTodoItemId = dependencyDto.getDependedTodoItemId();
		
		if (!checkTodoItem(dependedTodoItemId) || !checkTodoItem(dependentTodoItemId)) {
			return false;
		}
		
		TodoItem dependentTodoItem = todoItemRepository.getOne(dependentTodoItemId);
		TodoItem dependedTodoItem = todoItemRepository.getOne(dependedTodoItemId);

		// check if dependency already exists
		if (dependentTodoItem.getDependsOn() != null) {

			if (dependentTodoItem.getDependsOn().getId().longValue() == dependedTodoItemId.longValue()) {
				return false;
			}
		}
		
		// check if two todo items belong to the same todo list
		if (dependentTodoItem.getTodoList().getId().longValue() != dependedTodoItem.getTodoList().getId().longValue()) {
			return false;
		}
		
		// check if there is any circular dependency
		if (!checkCircularDependency(dependentTodoItem, dependedTodoItemId)) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkTodoItem(Long todoItemId) {
		if (todoItemId == null || !todoItemRepository.existsById(todoItemId)) {
			return false;
		}
		
		return true;
	}
	
	// Items depending on each other would cause impossible completion of tasks
	private boolean checkCircularDependency(TodoItem dependentTodoItem, Long dependedTodoItemId) {
		if (dependentTodoItem.getDependentList().size() == 0) {
			return true;
		}
		
		boolean passed = true;
		for (TodoItem dependent: dependentTodoItem.getDependentList()) {
			if (dependent.getId().longValue() == dependedTodoItemId) {
				return false;
			}
			
			passed = checkCircularDependency(dependent, dependedTodoItemId);
			if (!passed) 
				return false;
		}
		
		return true;
	}
}
