package com.example.group_food_ordering.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.group_food_ordering.data.entity.Orders;

public interface OrdersDAO extends JpaRepository<Orders, Long>{

}
