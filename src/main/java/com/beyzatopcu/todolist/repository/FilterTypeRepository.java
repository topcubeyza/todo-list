package com.beyzatopcu.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beyzatopcu.todolist.entity.FilterType;

public interface FilterTypeRepository extends JpaRepository<FilterType, String>{

}
