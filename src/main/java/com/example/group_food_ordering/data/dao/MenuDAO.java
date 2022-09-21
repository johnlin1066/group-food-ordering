package com.example.group_food_ordering.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.group_food_ordering.data.entity.Menu;

public interface MenuDAO extends JpaRepository<Menu, Long>{

}
