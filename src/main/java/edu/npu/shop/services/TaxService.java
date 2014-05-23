package edu.npu.shop.services;
import edu.npu.shop.domain.*;

public interface TaxService {
	public void printTaxRates();
	public double computeTax(Order order, String state);
}
