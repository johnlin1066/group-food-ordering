package com.example.group_food_ordering.data.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "group_food_order")
public class GroupFoodOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long menuID;
	private LocalDateTime endTime;
	@OneToMany(fetch=FetchType.EAGER, targetEntity= Orders.class,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE, CascadeType.REFRESH})
	@JoinColumns(value= {@JoinColumn(name="group_food_order_id", referencedColumnName="id")})
	private Set<Orders> orderSet = new HashSet<Orders>();
	
	public GroupFoodOrder() {}
	public GroupFoodOrder(Long menuID, LocalDateTime endTime) {
		this.menuID = menuID;
		this.endTime = endTime;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMenuID() {
		return menuID;
	}
	public void setMenuID(Long menuID) {
		this.menuID = menuID;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public Set<Orders> getOrderSet() {
		return orderSet;
	}
	public void setOrderSet(Set<Orders> orderSet) {
		this.orderSet = orderSet;
	}
}
