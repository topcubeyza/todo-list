package com.beyzatopcu.todolist.service;

import com.beyzatopcu.todolist.dto.DependencyDto;

public interface DependencyService {
	
	public boolean addDependency(DependencyDto dependencyDto);
	public boolean removeDependency(DependencyDto dependencyDto);

}
