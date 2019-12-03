package com.beyzatopcu.todolist.service;

public interface SecurityService {
	
	public String findLoggedInUsername();
	
	public void autoLogin(String username, String password);

}
