package com.example.group_food_ordering.data.entity;

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
@Table(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	@OneToMany(fetch=FetchType.EAGER, targetEntity= OrderItem.class,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE, CascadeType.REFRESH})
	@JoinColumns(value= {@JoinColumn(name="orders_id", referencedColumnName="id")})
	private Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
	
	public Orders() {}
	public Orders(String username) {
		this.username = username;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}
	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}
	
}
