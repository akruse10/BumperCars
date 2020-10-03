package com.bc;

import java.util.ArrayList;

public class Rental extends Product{

	private double dailyCost;
	private double deposit;
	private double cleaning;
	private double daysRented;
	
	//getters and setters
	public double getDailyCost() {
		return dailyCost;
	}
	public void setDailyCost(double dailyCost) {
		this.dailyCost = dailyCost;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public double getCleaning() {
		return cleaning;
	}
	public void setCleaning(double cleaning) {
		this.cleaning = cleaning;
	}
	public double getNumDays() {
		return daysRented;
	}
	public void setNumDays(double numDays) {
		this.daysRented = numDays;
	}
	
	//constructor
	public Rental(String productCode, String type, String label, Prices prices, double dailyCost, double deposit, double cleaning) {
		super(productCode, type, label, prices);
		this.dailyCost = dailyCost;
		this.deposit = deposit;
		this.cleaning = cleaning;
	}
	
	public Rental(String productCode, String type, String label, Prices prices, double dailyCost, double deposit, double cleaning, double numDays) {
		super(productCode, type, label, prices);
		this.dailyCost = dailyCost;
		this.deposit = deposit;
		this.cleaning = cleaning;
		this.daysRented = numDays;
	}
	
	
	//copy constructor
	public Rental(Rental oldVersion, double daysRented) {
		super(oldVersion.getProductCode(), oldVersion.getType(), oldVersion.getLabel(), oldVersion.getPrices());
		this.dailyCost = oldVersion.getDailyCost();
		this.deposit = oldVersion.getDeposit();
		this.cleaning = oldVersion.getCleaning();
		this.daysRented = daysRented;
	}
	
	
	public Rental rentalPrices(Product product, ArrayList<Customer> customers, Invoice invoice) {
		double subtotal = 0;
		double discount = 0;
		double taxes = 0;
		double total = 0;
		Rental rental = (Rental) product;

		// SUBTOTAL
		subtotal = ((double) rental.getNumDays() * (double) rental.getDailyCost())
				- (double) rental.getDeposit() + (double) rental.getCleaning();

		// TAXES
		// loop through each customer to find matching customer code
		int customerListLength = customers.size();
		int u = 0;
		for (u = 0; u < customerListLength; u++) {
			if (invoice.getAccountCode().getAccountCode().equals(customers.get(u).getAccountCode())) {
				// check type of matching customer and apply taxes
				if (customers.get(u).getType().equals("B")) {
					taxes = subtotal * .0425;
				} else if (customers.get(u).getType().equals("P")) {
					taxes = subtotal * .08;
				}
			}
		}
		
		// TOTAL
		total = subtotal + taxes;
		Prices productPriceCalculations = new Prices(subtotal, discount, taxes, total);
		rental.setPrices(productPriceCalculations);
		return rental;
		 
	}
	
	
	
}
