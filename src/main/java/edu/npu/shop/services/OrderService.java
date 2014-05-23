package edu.npu.shop.services;
import edu.npu.shop.domain.*;

public interface OrderService {
	public Order createNewCustOrder(String cusNum);
	public Order createNewCustOrder(String cusname, int prodId, int num);
	public void addProdIntoOrder(Order newOrder, int prodId, int num);
	public void updateProdInOrder(Order newOrder, int prodId, int newNum);
	//public void updateOrderSubtotal(Order newOrder);
	public String getOrderState(Order newOrder);
	public int getTotalOrders();
	public Order placeOrder(Order newOrder);
	public Order findOrderById(int id);
}
