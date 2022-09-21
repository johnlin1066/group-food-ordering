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
@Table(name = "menu")
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String storeName;
	private String storePhone;
	@OneToMany(fetch=FetchType.EAGER, targetEntity= MenuItem.class,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE, CascadeType.REFRESH})
	@JoinColumns(value= {@JoinColumn(name="menu_id", referencedColumnName="id")})
	private Set<MenuItem> menuItemSet = new HashSet<MenuItem>();
	
	public Menu() {}
	public Menu(String storeName, String storePhone) {
		this.storeName = storeName;
		this.storePhone = storePhone;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStorePhone() {
		return storePhone;
	}
	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	public Set<MenuItem> getMenuItemSet() {
		return menuItemSet;
	}
	public void setMenuItemSet(Set<MenuItem> menuItemSet) {
		this.menuItemSet = menuItemSet;
	}
}
