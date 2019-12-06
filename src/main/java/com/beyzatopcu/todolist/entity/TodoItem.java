package com.beyzatopcu.todolist.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TodoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Date deadline;
	private boolean status;
	private Date createdOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "todo_list_id")
	private TodoList todoList;

	@ManyToOne
	@JoinColumn(name = "depends_on_id")
	private TodoItem dependsOn;

	@OneToMany(
	        mappedBy = "dependsOn",
	        fetch = FetchType.LAZY
	    )
	private List<TodoItem> dependentList;
	
	public void addDependentTodoItem(TodoItem todoItem) {
		if (todoItem == null || this.dependentList.contains(todoItem)) return;
		this.dependentList.add(todoItem);
	}
	
	public void removeDependentTodoItem(TodoItem todoItem) {
		if (this.dependentList.contains(todoItem)) {
			this.dependentList.remove(todoItem);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public TodoList getTodoList() {
		return todoList;
	}

	public void setTodoList(TodoList todoList) {
		this.todoList = todoList;
	}

	public List<TodoItem> getDependentList() {
		return dependentList;
	}

	public void setDependentList(List<TodoItem> dependentList) {
		this.dependentList = dependentList;
	}

	public TodoItem getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(TodoItem dependsOn) {
		this.dependsOn = dependsOn;
	}

}
