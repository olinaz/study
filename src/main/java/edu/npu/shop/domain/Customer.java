package edu.npu.shop.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Customer {
	private int id;
	@NotEmpty
	@Size(min=2,max=8)
	private String name;
	private String state;
	//private String pwd;
	@NotEmpty
	private String addr;
	@Email
	private String email;
	private String phone;

	public Customer(){
		
	}
	public Customer(String name){
		this.name = name;
	}
	public Customer(String name, String state){
		this.name = name;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	
	public String getState(){
		return state;
	}
	public String getName(){
		return name;
	}
	public String getAddr(){
		return addr;
	}
	public String getEmail(){
		return email;
	}
	public String getPhone(){
		return phone;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setAddr(String addr){
		this.addr = addr;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCustState(){
		return state;
	}
	
	public String toString(){
		return "Name: " + name 
				+ "\n\tAddress: " + addr + "\tState: " + state
				+ "\n\tEmail: " + email + "\tPhone: " + phone;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Customer) {
			Customer cust = (Customer) obj;
			if(this.id == cust.id) 
				return true;
		}
		return false;
	}
}
