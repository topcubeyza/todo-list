package com.beyzatopcu.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.beyzatopcu.todolist.entity.User;
import com.beyzatopcu.todolist.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public boolean save(User user) {
		if (!checkUserValidity(user)) {
			return false;
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);

		return true;
	}

	@Override
	public User findByUsername(String username) {
		
		if (userRepository.existsByUsername(username)) {
			return userRepository.findByUsername(username);
		}
		
		return null;
	}

	private boolean checkUserValidity(User user) {
		if (user.getUsername() == null || user.getUsername().trim().length() == 0) {
			return false;
		}
		if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
			return false;
		}

		if (userRepository.existsByUsername(user.getUsername())) {
			return false;
		}

		return true;
	}

}
