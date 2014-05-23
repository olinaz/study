package edu.npu.shop.services;

import edu.npu.shop.domain.*;

import java.util.*;

import org.springframework.stereotype.Service;

//@Service("taxServiceForSales")
public class TaxServiceForSales implements TaxService {
	private Map<String, String> stateTaxRates;
	
	public void setStateTaxRates(Map<String, String> stateTaxRates){
		this.stateTaxRates = stateTaxRates;
	}
	public Map<String, String> getStateTaxRates(){
		return stateTaxRates;
	}
	public void printTaxRates(){
		Set<String> keys = stateTaxRates.keySet();
		Iterator<String> iter = keys.iterator();
		while(iter.hasNext()){
			String state = iter.next().toString();
			String rate = stateTaxRates.get(state);
			System.out.println("Tax Rate for " + state + " is: " + rate);
		}
	}
	public double getTaxRateForState(String state) throws Exception {		
		String rateStr = stateTaxRates.get(state);
		System.out.println("+++++++++++++string rate= " + rateStr);
		if(rateStr != null){
			try{
			double rate = Double.parseDouble(rateStr); //assume no error
			return rate;
			} catch (NumberFormatException ex){
				throw new Exception("Exception: invalid input for state rates");
			}
		} else {
			return 0;
		}
	}
	
	public double computeTax(Order order, String state){
		double orderSubtotal = order.getSubtotal();
		double rate;
		
		try{
		rate= getTaxRateForState(state);		
		} catch (Exception ex){
			System.out.println("ex msg:" + ex.getMessage());
			rate = 0;
		}
		//System.out.println("sub: " + orderSubtotal + "rate: " + rate + "For ST: " + state);
		double tax = orderSubtotal * rate;		
		return tax;
	}
}
