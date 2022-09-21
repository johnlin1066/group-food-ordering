package com.example.group_food_ordering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.group_food_ordering.data.data.MenuRepositoryImp;
import com.example.group_food_ordering.data.data.OrderRepositoryImp;
import com.example.group_food_ordering_core.menu.MenuInteractors;
import com.example.group_food_ordering_core.menu.MenuInteractorsImp;
import com.example.group_food_ordering_core.menu.dao.MenuRepository;
import com.example.group_food_ordering_core.order.OrderInteractors;
import com.example.group_food_ordering_core.order.OrderInteractorsImp;
import com.example.group_food_ordering_core.order.dao.OrderRepository;

@SpringBootApplication
public class GroupFoodOrderingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupFoodOrderingApplication.class, args);
	}
	
	@Bean
	public MenuInteractors menuInteractors(MenuRepository menuRespository) {
		return new MenuInteractorsImp(menuRespository);
	}
	@Bean
	public MenuRepository menuRespository() {
		return new MenuRepositoryImp();
	}
	
	
	@Bean
	public OrderInteractors orderInteractors(OrderRepository orderRepository) {
		return new OrderInteractorsImp(orderRepository);
	}
	@Bean
	public OrderRepository orderRepository() {
		return new OrderRepositoryImp();
	}

}
