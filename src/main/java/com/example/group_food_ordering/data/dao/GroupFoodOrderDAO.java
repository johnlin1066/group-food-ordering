package com.example.group_food_ordering.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.group_food_ordering.data.entity.GroupFoodOrder;

public interface GroupFoodOrderDAO extends JpaRepository<GroupFoodOrder, Long>{

}
