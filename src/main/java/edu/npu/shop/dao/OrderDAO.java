package edu.npu.shop.dao;
import java.util.List;

import edu.npu.shop.domain.Order;
import edu.npu.shop.domain.Customer;  

public interface OrderDAO {
	public int findOrderCount();
	public List<Order> findAllOrders();
	public Order findOrderById(int id);
	public List<Order> findOrderByCust(Customer cust);
	public void insertOrder(Order order);	
	//public List<Order> findOrdersByCustAndAmtGtrThan(Order order);
	public int deleteOrder(int id);
}