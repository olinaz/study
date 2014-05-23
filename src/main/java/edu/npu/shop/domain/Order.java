package edu.npu.shop.domain;

import java.util.*;

public class Order {
	private int id;
	private Date date;
	private String cusname;
	private List<OrderItem> itemsOrdered;
	
	private double subtotal;
	private double tax;
	private double total;
	
	public void setId(int id){this.id = id;}	
	public void setDate(Date date) {this.date = date;}
	public void setCusname(String cusname) {this.cusname = cusname;}
	public void setSubtotal(double subtotal) {this.subtotal = subtotal;}	
	public void setTax(double tax) {this.tax = tax;}	
	public void setTotal(double total) {this.total = total;}	
	public void setItemsOrdered(List<OrderItem> itemsOrdered){
		this.itemsOrdered = itemsOrdered;
	}
	public void calcOrderTotal() {total = subtotal + tax;}	
	
	public int getId(){return id;}
	public Date getDate(){return date;}
	public String getCusname(){return cusname;}
	public double getSubtotal(){return subtotal;}
	public double getTax(){return tax;}
	public double getTotal(){return total;}
	public List<OrderItem> getItemsOrdered() { return itemsOrdered;}
	
	public Order() {
		itemsOrdered = new ArrayList<OrderItem>();
	}
	public Order(int id){
		this.id = id;
		itemsOrdered = new ArrayList<OrderItem>();
	}
	public Order(String cusname) {
		this.cusname = cusname;
		itemsOrdered = new ArrayList<OrderItem>();
	}
	public Order(String cusname, int prodId, int num){
		this.cusname = cusname;
		itemsOrdered = new ArrayList<OrderItem>();
		
		OrderItem orderItem = new OrderItem(prodId, num);
		itemsOrdered.add(orderItem);		
	}
	
	public void addItem(OrderItem item){
		//If the Product is the same as an already existing Product in the Order, 
		//do not add the OrderItem. Instead, merge it into the existing OrderItem
		int numOfProd = itemsOrdered.size();
		int i;
		OrderItem eachItem;
		for(i=0;i<numOfProd;i++){
			eachItem = itemsOrdered.get(i);
			if(eachItem.matches(item)){
				int newNum = item.getNumOfProdOrdered() + eachItem.getNumOfProdOrdered();
				eachItem.setNumOfProdOrdered(newNum);
				break;
			}
		}		
		//new product into the order
		if(i== numOfProd){
			itemsOrdered.add(item);
		}
	}
	
	public void updateProduct(int prodId, int newNum) {
		int i;
		int numOfProd = itemsOrdered.size();
		OrderItem eachItem;
		for(i=0;i<numOfProd;i++){
			eachItem = itemsOrdered.get(i);
			if(eachItem.getProdId() == prodId){
				eachItem.setNumOfProdOrdered(newNum);
				break;
			}
		}		
		//to-be-updated product not found
		//if(i== numOfProd){
		//	throw new Exception("Exception: to-be-updated Product not found");
		//}
	}
	
	public OrderItem getOrderItemAt(int index) {
		OrderItem orderItem = itemsOrdered.get(index);
		return orderItem;
	}
	
	public int getNumOfOneProd(int prodId){
		int i;
		int numOfProd = itemsOrdered.size();
		OrderItem eachItem;
		for(i=0;i<numOfProd;i++){
			eachItem = itemsOrdered.get(i);
			if(eachItem.getProdId() == prodId){
				return eachItem.getNumOfProdOrdered();
			}
		}	
		return 0;
	}
	
	public int getNumProd(){
		return itemsOrdered.size();
	}
	
	public String toString(){
		return "++++++Order#: " + id
				+ "\t Customer: " + cusname
				+ "\n" + itemsOrdered
				+ "\n\tSubtotal: \t" + subtotal
				+ "\n\tTax: \t\t" + tax
				+ "\n\tTotal: \t\t" + total
				+ "\nEnd of Order Information+++++++++";
	}
	

}
