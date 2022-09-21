package com.example.group_food_ordering.data.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.group_food_ordering.data.dao.GroupFoodOrderDAO;
import com.example.group_food_ordering.data.dao.OrdersDAO;
import com.example.group_food_ordering.data.entity.GroupFoodOrder;
import com.example.group_food_ordering.data.entity.Orders;
import com.example.group_food_ordering.data.entity.OrderItem;
import com.example.group_food_ordering_core.order.dao.OrderRepository;
import com.example.group_food_ordering_core.order.dto.GroupFoodOrderDS;
import com.example.group_food_ordering_core.order.dto.OrderDS;
import com.example.group_food_ordering_core.order.dto.OrderItemDS;

public class OrderRepositoryImp implements OrderRepository{
	
	@Autowired
	GroupFoodOrderDAO groupFoodOrderDAO;
	@Autowired
	OrdersDAO ordersDAO;

	@Override
	public Long createGroupFoodOrder(GroupFoodOrderDS groupFoodOrderDS) {
		GroupFoodOrder groupFoodOrder = convertToGroupFoodOrder(groupFoodOrderDS);
		groupFoodOrderDAO.save(groupFoodOrder);
		return groupFoodOrder.getId();
	}

	@Override
	public Long createOrder(Long groupFoodOrderID, OrderDS orderDS) {
		Orders order = convertToOrder(orderDS);
		for(OrderItemDS orderItemDS : orderDS.getOrderItemDSList()) {
			OrderItem orderItem = new OrderItem(orderItemDS.getName(), orderItemDS.getPrice(), orderItemDS.getAmount());
			order.getOrderItemSet().add(orderItem);
		}
		ordersDAO.save(order);
		GroupFoodOrder groupFoodOrder = groupFoodOrderDAO.findById(groupFoodOrderID).orElse(null);
		groupFoodOrder.getOrderSet().add(order);
		groupFoodOrderDAO.save(groupFoodOrder);
		return order.getId();
	}

	@Override
	public GroupFoodOrderDS readGroupFoodOrder(Long groupFoodOrderID) {
		GroupFoodOrder groupFoodOrder = groupFoodOrderDAO.findById(groupFoodOrderID).orElse(null);
		return convertToGroupFoodOrderDS(groupFoodOrder);
	}
	
	@Override
	public List<GroupFoodOrderDS> readAllGroupFoodOrder() {
		return convertToGroupFoodOrderDSList(groupFoodOrderDAO.findAll());
	}
	
	GroupFoodOrder convertToGroupFoodOrder(GroupFoodOrderDS groupFoodOrderDS) {
		return new GroupFoodOrder(groupFoodOrderDS.getMenuID(), groupFoodOrderDS.getEndTime());
	}
	
	Orders convertToOrder(OrderDS orderDS) {
		return new Orders(orderDS.getUsername());
	}
	
	List<GroupFoodOrderDS> convertToGroupFoodOrderDSList(List<GroupFoodOrder> groupFoodOrderList){
		List<GroupFoodOrderDS> groupFoodOrderDSList = new ArrayList<>();
		for(GroupFoodOrder groupFoodOrder : groupFoodOrderList)
			groupFoodOrderDSList.add(convertToGroupFoodOrderDS(groupFoodOrder));
		return groupFoodOrderDSList;
	}
	GroupFoodOrderDS convertToGroupFoodOrderDS(GroupFoodOrder groupFoodOrder) {
		List<Orders> ordersList  = new ArrayList<>(groupFoodOrder.getOrderSet());
		Collections.sort(ordersList, new Comparator<Orders>() {
			@Override
		    public int compare(Orders s1, Orders s2) {
		        return (int) (s1.getId()-s2.getId());
		    }
		});
		return new GroupFoodOrderDS(groupFoodOrder.getId(), groupFoodOrder.getMenuID(),
				groupFoodOrder.getEndTime(), convertToOrderDSList(ordersList));
	}
	List<OrderDS> convertToOrderDSList(List<Orders> ordersList){
		List<OrderDS> orderDSList = new ArrayList<>();
		for(Orders order : ordersList)
			orderDSList.add(convertToOrderDS(order));
		return orderDSList;
	}
	OrderDS convertToOrderDS(Orders order) {
		List<OrderItem> orderItemList  = new ArrayList<>(order.getOrderItemSet());
		Collections.sort(orderItemList, new Comparator<OrderItem>() {
			@Override
		    public int compare(OrderItem s1, OrderItem s2) {
		        return (int) (s1.getId()-s2.getId());
		    }
		});
		return new OrderDS(order.getId(), order.getUsername(), convertToOrderItemDSList(orderItemList));
	}
	List<OrderItemDS> convertToOrderItemDSList(List<OrderItem> orderItemList){
		List<OrderItemDS> orderItemDSList = new ArrayList<>();
		for(OrderItem orderItem : orderItemList)
			orderItemDSList.add(convertToOrderItemDS(orderItem));
		return orderItemDSList;
	}
	OrderItemDS convertToOrderItemDS(OrderItem orderItem) {
		return new OrderItemDS(orderItem.getId(), orderItem.getName(), orderItem.getPrice(), orderItem.getAmount());
	}

}
