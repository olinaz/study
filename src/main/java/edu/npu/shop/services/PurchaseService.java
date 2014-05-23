package edu.npu.shop.services;

import edu.npu.shop.domain.Order;


public interface PurchaseService {
	public Order processCustomerPurchase(Order newOrder) throws Exception;
}
