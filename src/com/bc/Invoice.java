package com.bc;

import java.util.ArrayList;


public class Invoice {
	
	private String invoiceCode;
	private Person personCode;
	private Customer accountCode;
	private ArrayList<Product> productsList;
	
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public Person getPersonCode() {
		return personCode;
	}
	public void setPersonCode(Person personCode) {
		this.personCode = personCode;
	}
	public Customer getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(Customer accountCode) {
		this.accountCode = accountCode;
	}
	public ArrayList<Product> getProductsList() {
		return productsList;
	}
	public void setProductsList(ArrayList<Product> productsList) {
		this.productsList = productsList;
	}
	public Invoice(String invoiceCode, Person personCode, Customer accountCode, ArrayList<Product> productsList) {
		
		this.invoiceCode = invoiceCode;
		this.personCode = personCode;
		this.accountCode = accountCode;
		this.productsList = productsList;
	}
	
	
	//public Arra
	
	//

}
