package com.beyzatopcu.todolist.service;

public interface SecurityService {
	
	public String findLoggedInUsername();
	
	public boolean autoLogin(String username, String password);
	
	public boolean logout();

}
