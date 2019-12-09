package com.beyzatopcu.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TestController {
	
	@PostMapping("/login")
	public String test1(@RequestBody String a) {
		System.out.println(a);
		return "okay";
	}
	
	@GetMapping("/test")
	public String test2() {
		return "okay";
	}
}
