package com.beyzatopcu.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beyzatopcu.todolist.entity.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}
