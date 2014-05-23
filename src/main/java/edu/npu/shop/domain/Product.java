package edu.npu.shop.domain;

public class Product {
	private int id;
	private String brand;
	private int invtQuantity;
	private int totalOrders;
	public int getTotalOrders() {
		return totalOrders;
	}
	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	private String name;
	private double price;
	
	public void setId(int id) { this.id = id;}
	public void setName(String name) { this.name = name;}
	public void setBrand(String brand) { this.brand = brand;}
	public void setPrice(double price) { this.price = price;}
	public void setInvtQuantity(int invtQuantity) { this.invtQuantity  = invtQuantity;}
	
	public int getId() {return id;}
	public String getName() {return name;}
	public String getBrand() {return brand;}
	public double getPrice() {return price;}
	public int getInvtQuantity() {return invtQuantity;}
	
	public Product(){}
	public Product(String name, double price){
		this.name = name;
		this.price = price;
	}
	
	public String toString(){
		return name + "\tunit price: " + price; 
	}
	
	public boolean equals(Object obj){
		if (obj instanceof Product){
			Product otherProd = (Product) obj;
			if(name.equals(otherProd.name) && price==otherProd.price)
				return true;				
		}
		return false;
	}
}
