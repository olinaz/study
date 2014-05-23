package edu.npu.shop.services;
import edu.npu.shop.domain.*;
import edu.npu.shop.dao.*;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	@Qualifier("orderDaoJdbc")
	private OrderDAO orderDao;
	@Autowired
	@Qualifier("productDaoJdbc")
	private ProductDAO productDao;
	@Autowired
	@Qualifier("customerDaoJdbc")
	private CustomerDAO customerDao;
	@Autowired
	@Qualifier("orderItemDaoJdbc")
	private OrderItemDAO orderItemDao;
	@Autowired
	private TaxService taxService;
	
	public int getTotalOrders() {
		return orderDao.findOrderCount();
	}
	
	public Order findOrderById(int id) {
		Order order = orderDao.findOrderById(id);
		List<OrderItem> orderItems = orderItemDao.findOrderItemsByOrderId(id);
		order.setItemsOrdered(orderItems);
		return order;
	}
	
	public Order createNewCustOrder(String cusname) {
		Order newOrder= new Order(cusname);		
		return newOrder;
	}

	public Order createNewCustOrder(String cusname, int prodId, int num) {
		Order newOrder = new Order(cusname, prodId, num );				
		return newOrder;
	}
	
	public void addProdIntoOrder(Order newOrder, int prodId, int num) {
		OrderItem orderItem = new OrderItem(prodId, num);
		newOrder.addItem(orderItem);
		updateOrderSubtotal(newOrder, prodId, num);
	}
	public void updateProdInOrder(Order newOrder, int prodId, int newNum) {
		newOrder.updateProduct(prodId, newNum);
		updateOrderSubtotal(newOrder, prodId, newNum);
	}
	public void updateOrderSubtotal(Order newOrder, int prodId, int num) {
		double subtotal=newOrder.getSubtotal();
		
		Product prod = productDao.findProductById(prodId);
		double newAmt = num * prod.getPrice();
		subtotal += newAmt;
		
		newOrder.setSubtotal(subtotal);
	}
	public String getOrderState(Order newOrder) {
		String cusname = newOrder.getCusname();		
		Customer cust = customerDao.findCustByName(cusname);
		String state = cust.getCustState();
		return state;
	}
	
	@Transactional(readOnly=false)
	public Order placeOrder(Order newOrder) {
		Date todaysDate = new Date();
		newOrder.setDate(todaysDate);
		
		//set the total amount for the order: call the taxservice here
		String state = getOrderState(newOrder);
		System.out.println("+++++++++++the state of customer is:" + state);
		double tax = taxService.computeTax(newOrder, state);
		newOrder.setTax(tax);
		newOrder.calcOrderTotal();
		
		orderDao.insertOrder(newOrder);		
		return newOrder;
	}
}
