package edu.npu.shop.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import edu.npu.shop.domain.Customer;
import edu.npu.shop.domain.Order;
import edu.npu.shop.domain.Product;
import edu.npu.shop.services.CustomerService;
import edu.npu.shop.services.OrderService;
import edu.npu.shop.services.ProductService;

@Controller
public class CustomerController {
	@Autowired
	ProductService prodService;
	@Autowired
	CustomerService custService;
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = {"/", "login"}, method = RequestMethod.GET)
	public ModelAndView presentCustLoginForm() {
		ModelAndView modelView = null;
		
		modelView = new ModelAndView("custLogin");
		modelView.addObject("customer", new Customer());
		return modelView;
	}
	@RequestMapping(value = {"/", "login"}, method = RequestMethod.POST)
	public ModelAndView processCustLoginForm(ModelAndView modelView, 
			@Valid Customer customer, BindingResult result,HttpSession session) {

		if(result.hasErrors()) {
			//re-presenting with errors
			modelView.setViewName("custLogin");
			modelView.addObject(customer);
			return modelView;
		}
		String cusname = customer.getName();
		boolean isCustExisted = custService.isCustomerNameExisted(cusname);
		if(!isCustExisted) {
			//re-presenting with errors
			modelView.setViewName("redirect:unknownCustLogin");
			modelView.addObject(customer);
			return modelView;
		}
		
		//login successful, show the product lists		
		String cusNum = customer.getName();
		Order order = orderService.createNewCustOrder(cusNum);
		session.setAttribute("customer", customer);
		session.setAttribute("order", order);
		
		modelView.setViewName("redirect:product/prodList");
		return modelView;
		
	}
	
	/*
	@RequestMapping(value = {"/", "login"}, method = RequestMethod.POST)
	public String processCustLoginForm(@Valid Customer customer, BindingResult result,HttpSession session) {

		if(result.hasErrors()) {
			//re-presenting with errors
			return "redirect:login";
		}
		String cusname = customer.getName();
		boolean isCustExisted = custService.isCustomerNameExisted(cusname);
		if(!isCustExisted) {
			//re-presenting with errors
			return "redirect:unknownCustLogin";
		}
		
		//login successful, show the product lists		
		String cusNum = customer.getName();
		Order order = orderService.createNewCustOrder(cusNum);
		session.setAttribute("customer", customer);
		session.setAttribute("order", order);
		
		return "redirect:product/prodList";
	}
	*/
	
	@RequestMapping(value = {"unknownCustLogin"}, method = RequestMethod.GET)
	public ModelAndView unknownCustLogin() {
		ModelAndView modelView = null;
		modelView = new ModelAndView("unknownCustLogin");
		return modelView;		
	}
	
	@RequestMapping(value = {"register"}, method = RequestMethod.GET)
	public ModelAndView presentCustRegForm(@Valid Customer customer, BindingResult result) {
		ModelAndView modelView = null;
		modelView = new ModelAndView("custRegistration");
		modelView.addObject("customer", new Customer());
		return modelView;
	}
	
	/*
	@RequestMapping(value = {"register"}, method = RequestMethod.POST)
	public String processCustRegForm(@Valid Customer customer, BindingResult result, HttpSession session) {
		//ModelAndView modelView = null;
		
		if(result.hasErrors()) {
			//re-presenting with errors: validation errors
			//modelView = new ModelAndView("custRegistration","customer",customer);
			//return modelView;
			return "redirect:register";
		}
		String cusname = customer.getName();
		boolean isCustExisted = custService.isCustomerNameExisted(cusname);
		if(isCustExisted) {
			//re-presenting with errors: cusname already existed
			//modelView = new ModelAndView("custRegistration","customer",customer);
			//return modelView;
			return "redirect:register";
		}
		
		//add new customer into customer table
		boolean isCustCreated = custService.registerNewCust(customer);
		if(!isCustCreated){
			//re-presenting with errors: new customer insertion failed
			//modelView = new ModelAndView("custRegistration","customer",customer);
			//return modelView;
			return "redirect:register";
		}
		
		//login successful, show the product lists		
		String cusNum = customer.getName();
		Order order = orderService.createNewCustOrder(cusNum);
		session.setAttribute("customer", customer);
		session.setAttribute("order", order);
				
		return "redirect:product/prodList";
		
	}*/
	@RequestMapping(value = {"register"}, method = RequestMethod.POST)
	public ModelAndView processCustRegForm(ModelAndView modelView,
			@Valid Customer customer, BindingResult result, HttpSession session) {

		if(result.hasErrors()) {
			//re-presenting with errors: validation errors
			modelView.setViewName("custRegistration");
			modelView.addObject(customer);
			return modelView;			
		}
		String cusname = customer.getName();
		boolean isCustExisted = custService.isCustomerNameExisted(cusname);
		if(isCustExisted) {
			//re-presenting with errors: cusname already existed
			modelView.setViewName("custRegistration");
			modelView.addObject("errorMessage", "The customer ID already existed, please try something else");
			modelView.addObject(customer);
			return modelView;	
		}
		
		//add new customer into customer table
		boolean isCustCreated = custService.registerNewCust(customer);
		if(!isCustCreated){
			//re-presenting with errors: new customer insertion failed
			modelView.setViewName("custRegistration");
			modelView.addObject("errorMessage", "The customer registration is failed, please re-try");
			modelView.addObject(customer);
			return modelView;	
		}
		
		//login successful, show the product lists		
		String cusNum = customer.getName();
		Order order = orderService.createNewCustOrder(cusNum);
		session.setAttribute("customer", customer);
		session.setAttribute("order", order);
		
		modelView.setViewName("redirect:product/prodList");		
		return modelView;	
	}
	
}
