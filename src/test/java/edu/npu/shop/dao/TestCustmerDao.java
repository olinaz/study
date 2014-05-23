package edu.npu.shop.dao;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.shop.dao.CustomerDAO;
import edu.npu.shop.domain.Customer;

@ContextConfiguration("classpath:OrderApplication-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCustmerDao {
	@Autowired
	@Qualifier("customerDaoJdbc")
	private CustomerDAO custmerDAO;
	
	//test the retrieval of Customer Obj. by customer name 
	@Test
	public void testGetCustByName() {
		Customer cust = custmerDAO.findCustByName("Tracy");
		System.out.println("The customer details of Tracy: \n" + cust);
	}
	
	//test the retrieval of Customer Obj. by customer name 
	@Ignore
	@Test
	public void testInsertCust() {
		Customer cust = new Customer("TestInsert");
		cust.setAddr("47162 Washington Dr.");
		cust.setEmail("test@test.com");
		cust.setPhone("472-597-9371");
		cust.setState("UN");
		int origCount = custmerDAO.findCustCount();
		custmerDAO.insertCust(cust);
		int newCount = custmerDAO.findCustCount();
		assertEquals(1, newCount-origCount);
		
		Customer custRetr = custmerDAO.findCustByName("TestInsert");
		assertEquals(cust, custRetr);
	}	

	//test to delte the customer by id 
	@Ignore
	@Test
	public void testDeleteCust() {
		int origCount = custmerDAO.findCustCount();
		custmerDAO.deleteCust(2);
		int newCount = custmerDAO.findCustCount();
		assertEquals(1, origCount-newCount);
	
	}
		
	//test to update the customer addr. by id 
	@Test
	public void testUpdateCustAddr() {
		String newAddr = "New Addr. for Tracy";
		custmerDAO.updateCustAddr(1, newAddr);
		Customer cust = custmerDAO.findCustByName("Tracy");
		String custAddr = cust.getAddr();
		assertEquals(newAddr, custAddr);		
	}

	//test to update the customer addr. by id 
	@Test
	public void testUpdateCustEmail() {
		String newEmail = "tracy@new.com";
		custmerDAO.updateCustEmail(1, newEmail);
		Customer cust = custmerDAO.findCustByName("Tracy");
		String custEmail = cust.getEmail();
		assertEquals(newEmail, custEmail);		
	}
	//test to update the customer addr. by id 
	@Test
	public void testUpdateCustPhone() {
		String newPhone = "510-123-4567";
		custmerDAO.updateCustPhone(1, newPhone);
		Customer cust = custmerDAO.findCustByName("Tracy");
		String custPhone = cust.getPhone();
		assertEquals(newPhone, custPhone);		
	}
}
