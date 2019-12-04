package com.beyzatopcu.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyzatopcu.todolist.dto.UserDto;
import com.beyzatopcu.todolist.service.SecurityService;
import com.beyzatopcu.todolist.service.UserService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SecurityService securityService;

	@PostMapping("/register")
	public boolean register(@RequestBody UserDto user) {
		boolean isSuccessful = userService.save(user);
		securityService.autoLogin(user.getUsername(), user.getPassword());
		return isSuccessful;
	}
	
	@PostMapping("/login")
	public boolean login(@RequestBody UserDto user) {
		return securityService.autoLogin(user.getUsername(), user.getPassword());
	}
	
	@GetMapping("/logout")
	public boolean logout() {
		return securityService.logout();
	}
	
}
