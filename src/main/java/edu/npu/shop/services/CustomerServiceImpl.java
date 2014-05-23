package edu.npu.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.shop.dao.CustomerDAO;
import edu.npu.shop.domain.Customer;



@Service("customerService")
@Transactional(readOnly=true)
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDAO customerDao;
	
	public boolean isCustomerNameExisted(String name) {
		Customer customerFound = customerDao.findCustByName(name);
		if(customerFound == null) {
			return false;
		}
		return true;
	}
	
	@Transactional(readOnly=false, rollbackFor=java.lang.Exception.class)
	public boolean registerNewCust(Customer customer) {
		try{
			customerDao.insertCust(customer);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
}
