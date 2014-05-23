package edu.npu.shop.services;

import edu.npu.shop.domain.Customer;

public interface CustomerService {
	public boolean isCustomerNameExisted(String name);
	public boolean registerNewCust(Customer customer);
}
