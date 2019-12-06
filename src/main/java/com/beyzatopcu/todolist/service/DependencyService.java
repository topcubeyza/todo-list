package com.beyzatopcu.todolist.service;

import java.util.List;

import com.beyzatopcu.todolist.dto.DependedDto;
import com.beyzatopcu.todolist.dto.DependencyDto;
import com.beyzatopcu.todolist.dto.DependentDto;

public interface DependencyService {
	
	public boolean addDependency(DependencyDto dependencyDto);
	public boolean removeDependency(DependencyDto dependencyDto);
}
