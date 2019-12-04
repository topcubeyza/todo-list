package com.beyzatopcu.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beyzatopcu.todolist.entity.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {

}
