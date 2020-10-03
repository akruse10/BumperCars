package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlatFileReader {

	// taking the DAT files and returning the tokenized info
	static ArrayList<Person> personlist = new ArrayList<Person>();

	public static ArrayList<Person> readPersons() {

		try {
			Scanner s = new Scanner(new File("data/Persons.dat"));
			int n = Integer.parseInt(s.nextLine());
			int i = 0;
			// going through each scanned line and tokenizing by ; to get each part
			for (i = 0; i < n; i++) {
				String line = s.nextLine();
				String[] separated = line.split(";");
				// tokenizing the last part by , to get each part of the address
				String addToken[] = separated[2].split(",");
				Address address = new Address(addToken[0], addToken[1], addToken[2], addToken[3], addToken[4]);
				//making an arraylist of emails for the constructor
				ArrayList<String> emailsList = new ArrayList<String>();
				if(separated.length>3) {
				String emails[] = separated[3].split(",");
				int emailsLength = emails.length;
				int a = 0;
				for(a=0; a<emailsLength; a++) {
					emailsList.add(emails[a]);
				}
				}
				Person person = new Person(separated[0], separated[1], address, emailsList);
				personlist.add(person);
				}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return personlist;
	}

	static ArrayList<Customer> customerlist = new ArrayList<Customer>();

	public static ArrayList<Customer> readCustomers() {

		try {
			Scanner s = new Scanner(new File("data/Customers.dat"));
			int n = Integer.parseInt(s.nextLine());
			int i = 0;

			for (i = 0; i < n; i++) {
				String line = s.nextLine();
				String[] separated = line.split(";");
				String addToken[] = separated[4].split(",");
				Address address = new Address(addToken[0], addToken[1], addToken[2], addToken[3], addToken[4]);

				Person person = new Person(null, null, null, null);
				int k = 0;
				int pLength = personlist.size();
				for (k = 0; k < pLength; k++) {
					if (separated[3].equals(personlist.get(k).getPersonCode())) {

						person = personlist.get(k);
					}
				}
				Customer customer = new Customer(separated[0], separated[1], separated[2], person, address);
				customerlist.add(customer);
			}
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		return customerlist;
	}

	static ArrayList<Product> productlist = new ArrayList<Product>();
	public static ArrayList<Product> readProducts() {
		try {
			Scanner s = new Scanner(new File("data/Products.dat"));
			int n = Integer.parseInt(s.nextLine());
			int i = 0;

			for (i = 0; i < n; i++) {
				String line = s.nextLine();
				String[] separated = line.split(";");
				Prices prices = new Prices(0, 0, 0, 0, 0, 0);
				if (separated[1].equals("R")) {
					Product p = new Rental(separated[0], separated[1], separated[2], prices, Double.parseDouble(separated[3]),
							Double.parseDouble(separated[4]), Double.parseDouble(separated[5]));
					productlist.add(p);
				} else if (separated[1].equals("F")) {
					Product p = new Repair(separated[0], separated[1], separated[2], prices, Double.parseDouble(separated[3]),
							Double.parseDouble(separated[4]));
					productlist.add(p);
				} else if (separated[1].equals("C")) {
					Product p = new Concession(separated[0], separated[1], separated[2], prices,
							Double.parseDouble(separated[3]));
					productlist.add(p);
				} else if (separated[1].equals("T")) {
					Product p = new Towing(separated[0], separated[1], separated[2], prices, Double.parseDouble(separated[3]));
					productlist.add(p);
				}
			}
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return productlist;
	}
	

	public static ArrayList<Invoice> readInvoices() {
		ArrayList<Invoice> invoicelist = new ArrayList<Invoice>();
		try {
			Scanner s = new Scanner(new File("data/Invoices.dat"));
			int n = Integer.parseInt(s.nextLine());
			int i = 0;
			for (i = 0; i < n; i++) {
				String line = s.nextLine();
				String[] separated = line.split(";");
				String addToken[] = separated[3].split(",");

				// PERSONCODE
				Person person = new Person(null, null, null, null);
				int k = 0;
				int pLength = personlist.size();
				for (k = 0; k < pLength; k++) {
					if (separated[1].equals(personlist.get(k).getPersonCode())) {
						person = personlist.get(k);
					}
				}

				// ACCOUNTCODE
				Customer customer = new Customer(null, null, null, null, null);
				int z = 0;
				int zLength = customerlist.size();
				for (z = 0; z < zLength; z++) {
					if (separated[2].equals(customerlist.get(z).getAccountCode())) {
						customer = customerlist.get(z);
					}
				}

				// PRODUCTS LIST
				ArrayList<Product> transactions = new ArrayList<Product>();
				int length = addToken.length;

				int j = 0;
				for (j = 0; j < length; j++) {

					String transactionParts[] = addToken[j].split(":");
					int size = productlist.size();
					int op = 0;
					for (op = 0; op < size; op++) {
						if (transactionParts[0].equals(productlist.get(op).getProductCode())) {
							// take that product and add it to our list of products
							if (productlist.get(op).getType().equals("R")) {
								double daysRented = Double.parseDouble(transactionParts[1]);
								Rental product = new Rental((Rental)productlist.get(op), daysRented); 
								transactions.add(product);
							}
							if (productlist.get(op).getType().equals("T")) {
								double milesTowed = Double.parseDouble(transactionParts[1]);
								Towing product = new Towing((Towing) productlist.get(op), milesTowed);
								transactions.add(product);
							}
							if (productlist.get(op).getType().equals("F")) {
								double hoursWorked = Double.parseDouble(transactionParts[1]);
								Repair product = new Repair((Repair)productlist.get(op), hoursWorked);
								transactions.add(product);
							}
							if (productlist.get(op).getType().equals("C")) {
								if (transactionParts.length == 2) {
									double quantity = Double.parseDouble(transactionParts[1]);
									Concession product = new Concession((Concession) productlist.get(op), quantity);
									transactions.add(product);
								} else if (transactionParts.length == 3) {
									double quantity = Double.parseDouble(transactionParts[1]);
								
									Repair associatedRepair = new Repair(null, null, null, null, 2.0, 3.0, 4.0);
									int productListLength = productlist.size();
									int b = 0;
									for(b=0; b<productListLength; b++) {
										if(transactionParts[2].equals(productlist.get(b).getProductCode())) {
											associatedRepair = (Repair)productlist.get(b);
										}
									}
									
									Concession product = (Concession) productlist.get(op);
									product.setQuantity(quantity);
									product.setAssociatedRepair(associatedRepair);
									transactions.add(product);
								}
							}
						}
					}
				}
				Invoice invoice = new Invoice(separated[0], person, customer, transactions);
				invoicelist.add(invoice);
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return invoicelist;
	}
}
