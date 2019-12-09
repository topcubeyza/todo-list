package com.beyzatopcu.todolist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.beyzatopcu.todolist.dto.TodoListDto;
import com.beyzatopcu.todolist.dto.UserAuthDto;
import com.beyzatopcu.todolist.entity.TodoList;
import com.beyzatopcu.todolist.entity.User;
import com.beyzatopcu.todolist.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean save(UserAuthDto userDto) {
		if (!checkUserValidity(userDto)) {
			return false;
		}
		User user = new User();
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		userRepository.save(user);

		return true;
	}

	@Override
	public UserAuthDto findByUsername(String username) {
		
		if (userRepository.existsByUsername(username)) {
			User user = userRepository.findByUsername(username);
			UserAuthDto userDto = new UserAuthDto();
			userDto.setPassword(user.getPassword());
			userDto.setUsername(user.getUsername());
			
			return userDto;
		}
		
		return null;
	}

	@Override
	public List<TodoListDto> getTodoListByUsername(String username) {
		if (userRepository.existsByUsername(username)) {
			User user = userRepository.findByUsername(username);
			List<TodoListDto> todoListDtoList = new ArrayList<TodoListDto>();
			TodoListDto todoListDto;
			for (TodoList todoList: user.getTodoListList()) {
				todoListDto = new TodoListDto();
				todoListDto.setId(todoList.getId());
				todoListDto.setName(todoList.getName());
				
				todoListDtoList.add(todoListDto);
			}
			
			return todoListDtoList;
		}
		
		return null;
	}

	@Override
	public boolean canLogIn(UserAuthDto userAuthDto) {
		if (userRepository.existsByUsername(userAuthDto.getUsername())) {
			User user = userRepository.findByUsername(userAuthDto.getUsername());
			if (user.getPassword().equalsIgnoreCase(userAuthDto.getPassword())) {
				return true;
			}
		}
		
		return false;
	}

	private boolean checkUserValidity(UserAuthDto user) {
		if (user.getUsername() == null || user.getUsername().trim().length() == 0) {
			return false;
		}
		if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
			return false;
		}
		if (user.getPasswordConfirm() == null || user.getPasswordConfirm().trim().length() == 0) {
			return false;
		}
		if (!user.getPassword().equals(user.getPasswordConfirm())) {
			return false;
		}

		if (userRepository.existsByUsername(user.getUsername())) {
			return false;
		}

		return true;
	}

}
