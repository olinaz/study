package edu.npu.shop.dao;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.shop.domain.Product;

@ContextConfiguration("classpath:OrderApplication-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestProductDao {
	final static double DELTA = 1e-15;
	@Autowired
	@Qualifier("productDaoJdbc")
	private ProductDAO productDAO;

	//test the retrieval of total types of product 
	@Test
	public void testProductCount() {
		int cnt = productDAO.findProductCount();
		System.out.println("The total number of product types is: " + cnt);
	}
	
	//test the retrieval of inventory quantity by product name
	@Test
	public void testInvtQuantityByProdName() {
		int invtQuantity = productDAO.findInvtQuantityByName("Blue Handbag");
		System.out.println("This inventory quantiry of product 'blue handbag' is: " + invtQuantity);
	}
	
	//test the retrieval of product name by product ID
	@Test
	public void testFindProdNameById() {
		String expectedProdName = "Blue Handbag";
		String name = productDAO.findProdNameById(2);
		assertEquals(name, expectedProdName);
	}
	
	//test to insert product
	@Ignore
	@Test
	public void testInsertProd(){
		Product prod = new Product("Black Wallet", 340);
		prod.setBrand("Prada");
		prod.setInvtQuantity(4);
		
		int origCount = productDAO.findProductCount();
		productDAO.insertProduct(prod);
		int newCount = productDAO.findProductCount();
		assertEquals(1,newCount-origCount);		
	}

	//test to update the prod. price by id 
	@Test
	public void testUpdateProdPrice() {
		double newPrice = 340;
		productDAO.updateProdPrice(1, newPrice);
		Product prod = productDAO.findProductByName("Purple Wallet");
		double prodPrice = prod.getPrice();
		assertEquals(newPrice, prodPrice, DELTA);		
	}
	
	//test to get the products by searching the name with input keyword 
	@Test
	public void testGetProductsByKeyword() {
		List<Product> listProd = productDAO.findProductsByKeyword("Wallet");
		int numProd = listProd.size();
		assertEquals(6, numProd);
		
	}
}
