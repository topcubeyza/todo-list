package com.beyzatopcu.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beyzatopcu.todolist.entity.OrderType;

public interface OrderTypeRepository extends JpaRepository<OrderType, String> {

}
