package com.example.group_food_ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.group_food_ordering_core.order.OrderInteractors;
import com.example.group_food_ordering_core.order.dto.GroupFoodOrderDS;
import com.example.group_food_ordering_core.order.dto.OrderDS;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderInteractors orderInteractors;
	
	@PostMapping("/createGroupFoodOrder")
	public Long createGroupFoodOrder(@RequestBody GroupFoodOrderDS groupFoodOrderDS) {
		return orderInteractors.createGroupFoodOrder(groupFoodOrderDS);
	}
	
	@PostMapping("/createOrder/{groupFoodOrderID}")
	public Long createOrder(@PathVariable("groupFoodOrderID") Long groupFoodOrderID, @RequestBody OrderDS orderDS) {
		return orderInteractors.createOrder(groupFoodOrderID, orderDS);
	}
	
	@GetMapping("/{groupFoodOrderID}")
	public GroupFoodOrderDS readGroupFoodOrder(@PathVariable("groupFoodOrderID") Long groupFoodOrderID) {
		return orderInteractors.readGroupFoodOrder(groupFoodOrderID);
	}
	
	@GetMapping
	public List<GroupFoodOrderDS> readAllGroupFoodOrder() {
		return orderInteractors.readAllGroupFoodOrder();
	}

}
