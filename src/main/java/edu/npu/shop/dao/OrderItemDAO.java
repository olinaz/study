package edu.npu.shop.dao;

import java.util.List;

import edu.npu.shop.domain.OrderItem;

public interface OrderItemDAO {
	public void insertOrderItem(OrderItem orderItem);
	public List<OrderItem> findOrderItemsByOrderId(int orderId);
}
