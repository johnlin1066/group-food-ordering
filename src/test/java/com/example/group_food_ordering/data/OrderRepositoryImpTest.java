package com.example.group_food_ordering.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.group_food_ordering_core.order.dao.OrderRepository;
import com.example.group_food_ordering_core.order.dto.GroupFoodOrderDS;
import com.example.group_food_ordering_core.order.dto.OrderDS;
import com.example.group_food_ordering_core.order.dto.OrderItemDS;

@SpringBootTest
public class OrderRepositoryImpTest {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	@Disabled
	public void createGroupFoodOrder() {
		GroupFoodOrderDS groupFoodOrderDS = new GroupFoodOrderDS(null, 1L, LocalDateTime.now(), new ArrayList<OrderDS>());
		
		Long groupFoodOrderDSID = orderRepository.createGroupFoodOrder(groupFoodOrderDS);
		
		assertEquals(true, (groupFoodOrderDSID>0));
	}
	
	@Test
	@Disabled
	public void createOrder() {
		Long groupFoodOrderID = 1L;
		List<OrderItemDS> orderItemDSList = new ArrayList<OrderItemDS>();
		orderItemDSList.add(new OrderItemDS(1L, "milk", 10, 1));
		OrderDS orderDS = new OrderDS(1L, "user1", orderItemDSList);
		
		Long orderDSID = orderRepository.createOrder(groupFoodOrderID, orderDS);
		
		assertEquals(true, (orderDSID>0));
	}
	
	@Test
	@Disabled
	public void readGroupFoodOrder() {
		Long groupFoodOrderID = 1L;
		
		GroupFoodOrderDS groupFoodOrderDS = orderRepository.readGroupFoodOrder(groupFoodOrderID);
		
		assertEquals(true, (groupFoodOrderDS!=null));
	}
	
	@Test
	public void readAllGroupFoodOrder() {
		
		List<GroupFoodOrderDS> groupFoodOrderDSList = orderRepository.readAllGroupFoodOrder();
		
		assertEquals(true, (groupFoodOrderDSList!=null));
	}

}
