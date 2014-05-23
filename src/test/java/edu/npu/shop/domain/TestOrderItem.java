package edu.npu.shop.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestOrderItem {
	private OrderItem testOrderItem;
	final double DELTA = 1e-15;
	
	@Before
	public void initTest(){
		//Product prod = new Product("Kindle", 179.99);
		testOrderItem = new OrderItem(1, 2);
	}
	
	@Test
	public void testGetNumProd() {
		System.out.println("--Test OrderItem Class: getNumProd()");	
		int numProd = testOrderItem.getNumOfProdOrdered();
		assertEquals(2, numProd);
	}

	@Test
	public void testSetNumProd() {
		System.out.println("--Test OrderItem Class: incNumProd()");	
		int numProd = testOrderItem.getNumOfProdOrdered();
		assertEquals(2, numProd);
		
		testOrderItem.setNumOfProdOrdered(3);
		numProd = testOrderItem.getNumOfProdOrdered();
		assertEquals(3, numProd);
	}
	
}
