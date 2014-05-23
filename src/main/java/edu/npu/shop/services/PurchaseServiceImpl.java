package edu.npu.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.shop.domain.Order;
import edu.npu.shop.domain.OrderItem;

@Transactional
@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired 
	OrderService orderService;
	@Autowired
	ProductService productService;
	@Autowired
	OrderItemService orderItemService;
	
	@Transactional(rollbackFor=java.lang.Exception.class)
	public Order processCustomerPurchase(Order newOrder) throws Exception {
		//place the order to get the orderId created from database
		newOrder = orderService.placeOrder(newOrder);

		//For each product ordered, check whether enough inventory, roll back at any exceptions
		int numProducts = newOrder.getNumProd();
		int orderId = newOrder.getId();
		OrderItem eachItem;
		for(int i=0;i<numProducts;i++) {
			eachItem = newOrder.getOrderItemAt(i);
			eachItem.setOrderId(orderId);
			int eachProdId = eachItem.getProdId();
			int eachProdNum = eachItem.getNumOfProdOrdered();
			productService.productSale(eachProdId, eachProdNum);
			
			orderItemService.purchaseOrderItem(eachItem);
		}		
		return newOrder;
	}
}
