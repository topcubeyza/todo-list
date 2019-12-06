package com.beyzatopcu.todolist.dto;

import java.util.List;

public class TodoItemDetailDto {
	
	private TodoItemDto itemSummary;
	private DependedDto dependedItem;
	private List<DependentDto> dependentItemList;
	
	
	public TodoItemDto getItemSummary() {
		return itemSummary;
	}
	public void setItemSummary(TodoItemDto itemSummary) {
		this.itemSummary = itemSummary;
	}
	public DependedDto getDependedItem() {
		return dependedItem;
	}
	public void setDependedItem(DependedDto dependedItem) {
		this.dependedItem = dependedItem;
	}
	public List<DependentDto> getDependentItemList() {
		return dependentItemList;
	}
	public void setDependentItemList(List<DependentDto> dependentItemList) {
		this.dependentItemList = dependentItemList;
	}
	
	
}
