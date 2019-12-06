package com.beyzatopcu.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.beyzatopcu.todolist.dto.UserAuthDto;
import com.beyzatopcu.todolist.entity.User;
import com.beyzatopcu.todolist.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public boolean save(UserAuthDto userDto) {
		if (!checkUserValidity(userDto)) {
			return false;
		}
		User user = new User();
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
