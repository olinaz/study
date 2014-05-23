package edu.npu.shop.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.shop.dao.OrderDAO;
import edu.npu.shop.dao.ProductDAO;
import edu.npu.shop.domain.Order;

@ContextConfiguration("classpath:OrderApplication-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPurchaseService {
	//final static double DELTA=1e-15;
	@Autowired
	@Qualifier("purchaseService")
	private PurchaseService purchaseService;
	@Autowired
	@Qualifier("productDaoJdbc")
	private ProductDAO productDAO;
	@Autowired
	@Qualifier("orderDaoJdbc")
	private OrderDAO orderDAO;
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	@Test
	public void testProcessPurchase() {
		Order order = new Order();
		order.setCusname("Rosemary");
		
		//OrderItem orderItem1 = new OrderItem(4,1);
		//OrderItem orderItem2 = new OrderItem(3,2);
		//order.addItem(orderItem1);
		//order.addItem(orderItem2);
		orderService.addProdIntoOrder(order, 3, 2);
		orderService.addProdIntoOrder(order, 6, 1);
		
		int origNumOrder = orderDAO.findOrderCount();
		int origItem1 = productDAO.findInvtQuantityById(3);
		int origItem2 = productDAO.findInvtQuantityById(6);
		
		try{
			order = purchaseService.processCustomerPurchase(order);
			//purchase processed successfully, order table and product invt should be updated
			int currNumOrder = orderDAO.findOrderCount();
			int currItem1 = productDAO.findInvtQuantityById(3);
			int currItem2 = productDAO.findInvtQuantityById(6);
			
			assertEquals(1, currNumOrder-origNumOrder);
			assertEquals(2, origItem1-currItem1);
			assertEquals(1, origItem2-currItem2);
		} catch (Exception ex) {	
			//exception should be rolled-back, order table & prod. invt not changed
			System.out.println("testProcessPurchase Exception: " + ex.getMessage());
			int currNumOrder = orderDAO.findOrderCount();
			int currItem1 = productDAO.findInvtQuantityById(3);
			int currItem2 = productDAO.findInvtQuantityById(6);
			assertEquals(currNumOrder,origNumOrder);
			assertEquals(origItem1,currItem1);
			assertEquals(origItem2,currItem2);
		}
	}
}
