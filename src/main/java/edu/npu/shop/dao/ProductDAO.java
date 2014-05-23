package edu.npu.shop.dao;

import java.util.List;
import edu.npu.shop.domain.Product;

public interface ProductDAO {
	public int findProductCount();
	public int findInvtQuantityByName(String prodName);	
	public int findInvtQuantityById(int id);
	public String findProdNameById(int id);
	public void insertProduct(Product prod);
	public Product findProductByName(String name);
	public Product findProductById(int id);
	public List<Product> findProductsByKeyword(String name);
	public List<Product> findAllProducts();
	public List<Product> findAllProductsWithInvt();
	public int updateProdInvt(int id, int invt);
	public int updateProdPrice(int id, double price);
}

