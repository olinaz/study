package edu.npu.shop.services;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.shop.dao.OrderDAO;
import edu.npu.shop.domain.Order;

@ContextConfiguration("classpath:OrderApplication-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestOrderService {
	final static double DELTA=1e-15;
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	@Autowired
	@Qualifier("orderDaoJdbc")
	private OrderDAO orderDao;
	
	@Ignore
	@Test
	public void testGetOrderState() {
		Order order = orderDao.findOrderById(2);
		String state = orderService.getOrderState(order);
		
		assertEquals("CA", state);
	}
	@Ignore
	@Test
	public void testPlaceOrder() {
		Order order = new Order();
		order.setCusname("Tracy");
		order.setSubtotal(100);
		order = orderService.placeOrder(order);
		
		double orderTotal = order.getTotal();
		assertEquals(109.25,orderTotal,DELTA);
	}
	
	@Test
	public void testFindOrderById() {
		Order order = orderService.findOrderById(2);
		System.out.println("testFindOrderById results=" + order);
	}

}
