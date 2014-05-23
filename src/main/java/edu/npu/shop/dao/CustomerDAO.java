package edu.npu.shop.dao;

import edu.npu.shop.domain.Customer;

public interface CustomerDAO {
	public int findCustCount();
	public Customer findCustByName(String name);
	public void insertCust(Customer cust);
	public int updateCustAddr(int id, String addr);
	public int updateCustEmail(int id, String email);
	public int updateCustPhone(int id, String phone);
	public int deleteCust(int id);	

}
