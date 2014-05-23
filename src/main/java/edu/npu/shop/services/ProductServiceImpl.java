package edu.npu.shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.shop.dao.ProductDAO;
import edu.npu.shop.domain.Product;


@Service("productService")
@Transactional(readOnly=true)
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO prodDao;
	
	@Transactional(readOnly=false, rollbackFor=java.lang.Exception.class)
	public void productSale(int prodId, int num) throws Exception {
		int origNum = prodDao.findInvtQuantityById(prodId);
		if(origNum < num) {
			throw new Exception("ProductDao Exception: NOT enough inventory for purchase");
		}
		int newNum = origNum - num;
		int rowsUpdated = prodDao.updateProdInvt(prodId, newNum);
		if (rowsUpdated != 1) {
			throw new Exception("ProductDao Exception: Unable to update order total");
		}
	}
	
	@Transactional(readOnly=false)
	public void insertProduct(Product prod) {
		prodDao.insertProduct(prod);
	}
	
	@Transactional(rollbackFor=java.lang.Exception.class)
	public Product findProdByName(String prodName) throws Exception {
		Product prod;
		
		prod = prodDao.findProductByName(prodName);
		if (prod == null) {
			throw new Exception("ProductDao Exception: " + prodName + " Not Found");
		}
		return prod;
	}
	
	@Transactional(rollbackFor=java.lang.Exception.class)
	public List<Product> findAllProd() throws Exception {
		List<Product> prodList;
		
		prodList = prodDao.findAllProducts();
		if (prodList == null) {
			throw new Exception("ProductDao Exception: No products");
		}
		return prodList;
	}
	
	@Transactional(rollbackFor=java.lang.Exception.class)
	public List<Product> findAllProdWithInvt() throws Exception {
		List<Product> prodList;
		
		prodList = prodDao.findAllProductsWithInvt();
		if (prodList == null) {
			throw new Exception("ProductDao Exception: No products");
		}
		return prodList;
	}
	
	@Transactional(rollbackFor=java.lang.Exception.class)
	public Product findProdById(int id) throws Exception {
		Product product;
		
		product = prodDao.findProductById(id);
		if (product == null) {
			throw new Exception("ProductDao Exception: No products");
		}
		return product;
	}
}
