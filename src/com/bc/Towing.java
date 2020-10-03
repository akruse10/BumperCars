package com.bc;

import java.util.ArrayList;

public class Towing extends Product{
	
	private double costPerMile;
	private double milesTowed;

	//constructor
	public Towing(String productCode, String type, String label, Prices prices, double costPerMile) {
		super(productCode, type, label, prices);
		this.costPerMile = costPerMile;
	}

	public Towing(String productCode, String type, String label, Prices prices, double costPerMile, double numMiles) {
		super(productCode, type, label, prices);
		this.costPerMile = costPerMile;
		this.milesTowed = numMiles;
	}
	
	//copy constructor
	public Towing(Towing oldVersion, double milesTowed) {
		super(oldVersion.getProductCode(), oldVersion.getType(), oldVersion.getLabel(), oldVersion.getPrices());
		this.costPerMile = oldVersion.getCostPerMile();
		this.milesTowed = milesTowed;
	}
	
	//getters and setters
	public double getNumMiles() {
		return milesTowed;
	}

	public void setNumMiles(double numMiles) {
		this.milesTowed = numMiles;
	}

	public double getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(double costPerMile) {
		this.costPerMile = costPerMile;
	}

	public Towing towingPrices(Product product, ArrayList<Customer> customers, Invoice invoice, ArrayList<Product> products) {
		Towing towing = (Towing) product;
		double subtotal = 0;
		double discount = 0;
		double taxes = 0;
		double total = 0;
		// SUBTOTAL
		subtotal = ((double) towing.getNumMiles() * (double) towing.getCostPerMile());

		// DISCOUNT
		// go thru list of products, check for match on rental
		int productsMasterList = products.size();
		int v = 0;
		for (v = 0; v < productsMasterList; v++) {
			if (products.get(v).getType().equals("R")) {
				int w = 0;
				for (w = 0; w < productsMasterList; w++) {
					// if match on rental. go thru products again and check for repair match
					if (products.get(w).getType().equals("F")) {
						discount = subtotal;
					}
				}
			}
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
		total = subtotal + taxes - discount;
		Prices productPriceCalculations = new Prices(subtotal, discount, taxes, total);
		towing.setPrices(productPriceCalculations);
		return towing;
	}
	

}
