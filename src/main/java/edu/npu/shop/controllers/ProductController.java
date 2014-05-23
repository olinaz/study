package edu.npu.shop.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import edu.npu.shop.domain.Product;
import edu.npu.shop.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService prodService;
	
	@RequestMapping(value = "/prodList", method = RequestMethod.GET)
	public ModelAndView listAllProducts() {
		List<Product> prodList;
		ModelAndView modelView = null;
		
		try{
		prodList = prodService.findAllProdWithInvt();
		modelView = new ModelAndView("productsList");
		modelView.addObject("prodList", prodList);
		
		} catch (Exception ex) {
			// exception in listing all the products
			modelView = new ModelAndView("error");
			modelView.addObject("errorMessage", "Errors while retrieving all products information, please retry");
			return modelView;
		}

		return modelView;
	}
	
	@RequestMapping(value = "/prodDetails/{prodId}")
	public ModelAndView productDetails(@PathVariable int prodId, HttpServletRequest request,  
            HttpServletResponse response) {
		Product product;
		ModelAndView modelView = null;

		try{
			product = prodService.findProdById(prodId);
			modelView = new ModelAndView("productDetails");
			modelView.addObject("product", product);		
		} catch (Exception ex) {
			// exception in listing all the products
			modelView = new ModelAndView("error");
			modelView.addObject("errorMessage", "Errors while retrieving product details, please retry");
			return modelView;
		}
		
		return modelView;
	}
}
