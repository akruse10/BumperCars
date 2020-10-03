package com.bc;

import java.util.ArrayList;

public class Repair extends Product{

	private double parts;
	private double hourlyLabor;
	private double hoursWorked;
	
	//getters and setters
	public double getParts() {
		return parts;
	}

	public void setParts(double parts) {
		this.parts = parts;
	}
	public double getHourlyLabor() {
		return hourlyLabor;
	}
	public void setHourlyLabor(double hourlyLabor) {
		this.hourlyLabor = hourlyLabor;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	

	//constructor
	public Repair(String productCode, String type, String label, Prices prices, double parts, double hourlyLabor) {
		super(productCode, type, label, prices);
		this.parts = parts;
		this.hourlyLabor = hourlyLabor;
	}
	
	public Repair(String productCode, String type, String label, Prices prices, double parts, double hourlyLabor, double hoursWorked) {
		super(productCode, type, label, prices);
		this.parts = parts;
		this.hourlyLabor = hourlyLabor;
		this.hoursWorked = hoursWorked;
	}
	
	

	//copy constructor
	public Repair(Repair oldVersion, double hoursWorked) {
		super(oldVersion.getProductCode(), oldVersion.getType(), oldVersion.getLabel(), oldVersion.getPrices());
		this.parts = oldVersion.getParts();
		this.hourlyLabor = oldVersion.getHourlyLabor();
		this.hoursWorked = hoursWorked;
	}
	
	//calculating prices
	public Repair repairPrices(Product product, ArrayList<Customer> customers, Invoice invoice) {
		Repair repair = (Repair) product;
		double subtotal = 0;
		double discount = 0;
		double taxes = 0;
		double total = 0;
		// SUBTOTAL
		subtotal = ((double) repair.getHourlyLabor() * (double) repair.getHoursWorked())
				+ (double) repair.getParts();

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
		repair.setPrices(productPriceCalculations);
		return repair;
	}
	
}
