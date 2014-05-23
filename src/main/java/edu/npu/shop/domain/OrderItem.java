package edu.npu.shop.domain;

public class OrderItem {
	private int orderItemId;
	private int orderId;
	private int numOfProdOrdered;
	private int prodId;
	
	public OrderItem(){}
	public OrderItem(int prodId, int num){
		this.numOfProdOrdered = num;
		this.prodId = prodId;
	}
	public void setOrderItemId(int id) {this.orderItemId = id;}
	public void setOrderId(int id) {this.orderId = id;}
	public void setNumOfProdOrdered(int num) { this.numOfProdOrdered = num; }
	public void setProdId(int prodId) { this.prodId = prodId; }
	
	public int getOrderItemId() {return this.orderItemId;}
	public int getOrderId() {return this.orderId;}
	public int getNumOfProdOrdered() { return this.numOfProdOrdered; }
	public int getProdId() { return this.prodId; }
	
	public String toString(){
		return "\t" + prodId + "\tx " + numOfProdOrdered + "\n";
	}
	
	public boolean matches(Object obj){
		if (obj instanceof OrderItem){
			OrderItem otherOrderItem = (OrderItem) obj;
			if(this.prodId == otherOrderItem.prodId)
				return true;				
		}
		return false;
	}
	public boolean matches(Product prod){
		if(this.prodId == prod.getId()){
			return true;		
		} else{
			return false;
		}
	}

}
