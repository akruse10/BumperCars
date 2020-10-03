package com.bc;

public class Prices {

	private double subtotal;
	private double discounts;
	private double fees;
	private double taxes;
	private double postTaxDiscounts;
	private double total;
	
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getDiscounts() {
		return discounts;
	}
	public void setDiscounts(double discounts) {
		this.discounts = discounts;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public double getPostTaxDiscounts() {
		return postTaxDiscounts;
	}
	public void setPostTaxDiscounts(double postTaxDiscounts) {
		this.postTaxDiscounts = postTaxDiscounts;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Prices(double subtotal, double discounts, double fees, double taxes, double postTaxDiscounts, double total) {
		super();
		this.subtotal = subtotal;
		this.discounts = discounts;
		this.fees = fees;
		this.taxes = taxes;
		this.postTaxDiscounts = postTaxDiscounts;
		this.total = total;
	}
	
	public Prices(double subtotal, double discounts, double taxes, double total) {
		super();
		this.subtotal = subtotal;
		this.discounts = discounts;
		this.taxes = taxes;
		this.total = total;
	}
	
	
	
	
}
