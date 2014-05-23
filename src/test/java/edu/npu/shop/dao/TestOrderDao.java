package edu.npu.shop.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.shop.dao.OrderDAO;
import edu.npu.shop.domain.Customer;
import edu.npu.shop.domain.Order;

@ContextConfiguration("classpath:OrderApplication-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestOrderDao {

	@Autowired
	@Qualifier("orderDaoJdbc")
	private OrderDAO orderDAO;
	
	//test the retrieval of Order Obj. by order id 
	@Test
	public void testFindOrderById() {
		Order order = orderDAO.findOrderById(1);
		System.out.println("The order details: \n" + order);
	}
	//test the retrieval of Order Obj. by order id 
	@Test
	public void testFindOrderByCust() {
		Customer cust = new Customer("Tracy");
		List<Order> listOrder = orderDAO.findOrderByCust(cust);
		int numOrder = listOrder.size();
		assertEquals(2,numOrder);
	}
		
	//test to insert order
	@Test
	public void testInsertOrder() {
		Order newOrder = new Order();
		newOrder.setCusname("Tracy");
		newOrder.setDate(new Date());
		newOrder.setSubtotal(64);
		newOrder.setTax(5.8);
		newOrder.setTotal(69.8);
		
		int origCount = orderDAO.findOrderCount();
		orderDAO.insertOrder(newOrder);
		int newCount = orderDAO.findOrderCount();
		assertEquals(1, newCount-origCount);		
	}
	
	//test to delete the order by id 
	@Ignore
	@Test
	public void testDeleteOrder() {
		int origCount = orderDAO.findOrderCount();
		orderDAO.deleteOrder(3);
		int newCount = orderDAO.findOrderCount();	
		assertEquals(1, origCount-newCount);	
	}	
	
}
