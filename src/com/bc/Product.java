package com.bc;

public abstract class Product {
	
	private String productCode;
	private String type;
	private String label;
	private Prices prices;
	
	
	public Prices getPrices() {
		return prices;
	}
	public void setPrices(Prices prices) {
		this.prices = prices;
	}
	//getters and setters
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	//constructor
	public Product(String productCode, String type, String label, Prices prices) {
		super();
		this.productCode = productCode;
		this.type = type;
		this.label = label;
		this.prices = prices;
	}
}
