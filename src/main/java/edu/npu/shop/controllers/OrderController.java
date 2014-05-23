package edu.npu.shop.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import edu.npu.shop.domain.Customer;
import edu.npu.shop.domain.Order;
import edu.npu.shop.domain.OrderItem;
import edu.npu.shop.domain.Product;
import edu.npu.shop.services.OrderService;
import edu.npu.shop.services.ProductService;
import edu.npu.shop.services.PurchaseService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	ProductService prodService;
	@Autowired
	OrderService orderService;
	@Autowired
	PurchaseService purchaseService;
	
	@RequestMapping(value = "/addItem/{prodId}")
	public ModelAndView processAddItem(@PathVariable int prodId, HttpServletRequest request,  
            HttpServletResponse response, HttpSession session) {
		
		ModelAndView modelView = null;
		String numStr = request.getParameter("numOfProdOrdered");
		int numToBuy = Integer.parseInt(numStr);
		
		try{
			Product product = prodService.findProdById(prodId);
			int prodInvt = product.getInvtQuantity();
			if(numToBuy > prodInvt) {
				modelView = new ModelAndView("error");
				modelView.addObject("errorMessage", "Not enough inventory, please reduce your order quantities or come later");
				return modelView;
			}
			
		} catch(Exception ex) {
			modelView = new ModelAndView("error");
			modelView.addObject("errorMessage", "Errors while adding the items into your shopping cart, please retry");
			return modelView;
		}
		//save the orderItem into order obj. for this session
		Order order = (Order) session.getAttribute("order");
		orderService.addProdIntoOrder(order, prodId, numToBuy);	
		session.setAttribute("order", order);		
		
		modelView = new ModelAndView("addItemDone");

		return modelView;
	}
	
	//@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	@RequestMapping(value = "/placeOrder")
	public ModelAndView processPlaceOrder(HttpSession session) {
		
		ModelAndView modelView = null;
		Order order = (Order) session.getAttribute("order");
		List<OrderItem> orderItems = order.getItemsOrdered();
		int numOrderItems = orderItems.size();
		
		if(numOrderItems == 0){
			modelView = new ModelAndView("error");
			modelView.addObject("errorMessage", "No items in your shopping cart, please buy something before placing your order");
			return modelView;
		}
		
		try{
			order = purchaseService.processCustomerPurchase(order);
		} catch (Exception ex){
			modelView = new ModelAndView("error");
			modelView.addObject("errorMessage", "Errors while placing your order, please retry");
			return modelView;
		}

		List<Product> prodList = new ArrayList<Product>();
		try{
			for(int i=0;i<numOrderItems;i++){
				OrderItem eachItem = orderItems.get(i);
				int eachProdId = eachItem.getProdId();
				Product eachProd = prodService.findProdById(eachProdId);
				int totalOrders = eachItem.getNumOfProdOrdered();
				eachProd.setTotalOrders(totalOrders);
				prodList.add(eachProd);
			}
			modelView = new ModelAndView("orderSuccess");
			modelView.addObject("prodList", prodList);
			modelView.addObject("order", order);
			
		} catch (Exception ex) {
			// exception in listing all the products
			modelView = new ModelAndView("error");
			modelView.addObject("errorMessage", "Errors while listing your order, please send mail to supports");
			return modelView;
		}

		return modelView;
	}
	
	@RequestMapping(value = "/shoppingcart")
	public ModelAndView presentShoppingCart(HttpSession session) {
		
		ModelAndView modelView = null;
		Order order = (Order) session.getAttribute("order");
		
		List<OrderItem> orderItems = order.getItemsOrdered();
		int numOrderItems = orderItems.size();
		List<Product> prodList = new ArrayList<Product>();
		try{
			for(int i=0;i<numOrderItems;i++){
				OrderItem eachItem = orderItems.get(i);
				int eachProdId = eachItem.getProdId();
				Product eachProd = prodService.findProdById(eachProdId);
				int totalOrders = eachItem.getNumOfProdOrdered();
				eachProd.setTotalOrders(totalOrders);
				prodList.add(eachProd);
			}
			modelView = new ModelAndView("shoppingCart");
			modelView.addObject("prodList", prodList);
			
		} catch (Exception ex) {
			// exception in listing all the products
			modelView = new ModelAndView("error");
			modelView.addObject("errorMessage", "Errors while retrieving your shopping cart, please retry");
			return modelView;
		}
		
		return modelView;		
	}
}
