package edu.npu.shop.services;

import java.util.List;

import edu.npu.shop.domain.Product;


public interface ProductService {
	public void productSale(int prodId, int num) throws Exception;
	public List<Product> findAllProd() throws Exception;
	public List<Product> findAllProdWithInvt() throws Exception;
	public Product findProdById(int id) throws Exception;
}
