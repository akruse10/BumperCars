package com.bc;

import java.util.ArrayList;

public class Concession extends Product{

	private double cost;
	private double quantity;
	private Repair associatedRepair;

	//constructor
	public Concession(String productCode, String type, String label, Prices prices, double cost) {
		super(productCode, type, label, prices);
		this.cost = cost;
	}

	public Concession(String productCode, String type, String label, Prices prices, double cost, double quantity) {
		super(productCode, type, label, prices);
		this.cost = cost;
		this.quantity = quantity;
	}

	public Concession(String productCode, String type, String label, Prices prices, double cost, double quantity, Repair associatedRepair) {
		super(productCode, type, label, prices);
		this.cost = cost;
		this.quantity = quantity;
		this.associatedRepair = associatedRepair;
	}
	
	
	//copy constructor
	public Concession(Concession oldVersion, double quantity) {
		super(oldVersion.getProductCode(), oldVersion.getType(), oldVersion.getLabel(), oldVersion.getPrices());
		this.cost = oldVersion.getCost();
		this.quantity = quantity;
	}

	public Concession(Concession oldVersion, double quantity, Repair associatedRepair) {
		super(oldVersion.getProductCode(), oldVersion.getType(), oldVersion.getLabel(), oldVersion.getPrices());
		this.cost = oldVersion.getCost();
		this.quantity = quantity;
		this.associatedRepair = associatedRepair;
	}
	
	//getters and setters
	public double getCost() {
		return cost;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Repair getAssociatedRepair() {
		return associatedRepair;
	}

	public void setAssociatedRepair(Repair associatedRepair2) {
		this.associatedRepair = associatedRepair2;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	
	public Concession concessionPrices(Product product, ArrayList<Customer> customers, Invoice invoice) {
		// concession is discounted if R is included
		// typecasting the product into a rental type
		Concession concession = (Concession) product;
		double subtotal = 0;
		double discount = 0;
		double taxes = 0;
		double total = 0;
		// SUBTOTAL
		subtotal = ((double) concession.getCost() * (double) concession.getQuantity());

		// DICOUNTS
		if (concession.getAssociatedRepair() != null) {
			discount = subtotal * .1;
		}

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
		concession.setPrices(productPriceCalculations);
		return concession;
	}
	
	
	
}
