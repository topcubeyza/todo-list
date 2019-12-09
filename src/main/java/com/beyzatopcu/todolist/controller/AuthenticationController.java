package com.beyzatopcu.todolist.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyzatopcu.todolist.dto.TodoListDto;
import com.beyzatopcu.todolist.dto.UserAuthDto;
import com.beyzatopcu.todolist.service.UserService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public boolean register(@RequestBody UserAuthDto user, HttpServletResponse response) {
		boolean isSuccessful = userService.save(user);
		if (isSuccessful) {
			Cookie cookie = new Cookie("username", user.getUsername());
			cookie.setMaxAge(60*60*24*7);
			cookie.setPath("/");
			cookie.setHttpOnly(false);

			response.addCookie(cookie);
			return true;
		}
		return false;
	}

	@PostMapping("/login")
	public boolean login(@RequestBody UserAuthDto user, HttpServletResponse response, HttpSession session) {
		boolean isSuccessful = userService.canLogIn(user);
		if (isSuccessful) {
			Cookie cookie = new Cookie("username", user.getUsername());
			cookie.setMaxAge(60*60*24*7);
			cookie.setPath("/");
			cookie.setHttpOnly(false);

			response.addCookie(cookie);
			return true;
		}
		
		Cookie cookie = new Cookie("username", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setHttpOnly(false);

		response.addCookie(cookie);
		return false;
	}

	@GetMapping("/logout")
	public boolean logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("username", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setHttpOnly(false);

		response.addCookie(cookie);

		return true;

	}
	
	@GetMapping("/is-logged-in")
	public boolean isLoggedIn(HttpServletRequest req) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies == null) {
	    	return false;
	    }
	    for (Cookie cookie: cookies) {
	    	if (cookie.getName().equalsIgnoreCase("username") && cookie.getValue().length() > 0) {
	    	    return true;
	    	}
	    }

    	return false;
	}

}
