package com.bc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class InvoiceReport {

	
	
	public static void main(String[] args) throws IOException {

		// also ask Jaelle about using try catch when were done

		// reading in the .dat files
		ArrayList<Person> persons = FlatFileReader.readPersons();
		ArrayList<Customer> customers = FlatFileReader.readCustomers();
		ArrayList<Product> products = FlatFileReader.readProducts();
		ArrayList<Invoice> invoices = FlatFileReader.readInvoices();

		// ALPHABETIZING THE INVOICES
		
		ArrayList<Invoice> sortedInvoices = new ArrayList<Invoice>();
		sortedInvoices = alphabetizing(invoices, persons);
		
		sortedInvoices = priceCalc(sortedInvoices, products, customers);
		
		System.out.print(sortedInvoices.get(0).getProductsList().get(0).getPrices().getSubtotal());
		//call a method that:
		//loops thru each invoice
		//loops thru each product and calculates prices
		//sets the prices variable for each product to have the correct values
		
		//then we should have the same ArrayList<Invoice> sortedInvoices but the products will all have filled in prices
		
		//call a method that prints the executive report
		
		//loop thru each invoice, and call a method that prints their individual report
		
		//print any ending stuff aka "thanks for doing business with us"
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// go thru each invoice (loop), using our type priceCalculations
		// create a pricecalculations type using constructor
		// use setters to set the different values
		// System.out.print(sortedInvoices.get(0).);

		// loop for each invoice to calculate prices

		// ArrayList containing an array list of prices
		
		
		
		
		/*
		ArrayList<ArrayList<Prices>> prices = new ArrayList<ArrayList<Prices>>();
		int invLength = sortedInvoices.size();
		int q = 0;
		// going through each invoice
		for (q = 0; q < invLength; q++) {
			int invoiceProductsListLength = sortedInvoices.get(q).getProductsList().size();
			int r = 0;
			ArrayList<Prices> singleProductPrices = new ArrayList<Prices>();
			// going through each product in invoice
			for (r = 0; r < invoiceProductsListLength; r++) {
				// going through each product of a single invoice
				// check type

				// going through each product within the master product list
				// ..so we can compare them both and see which type it is
				int productsMasterList = products.size();
				int t = 0;
				for (t = 0; t < productsMasterList; t++) {
					Invoice invoice = sortedInvoices.get(q);
					ArrayList<Product> productsListTemp = invoice.getProductsList();
					Product product = productsListTemp.get(r);
					if (product.getProductCode().equals(products.get(t).getProductCode())) {
						double subtotal = 0;
						double discount = 0;
						double taxes = 0;
						double total = 0;
						// RENTAL
						if (product.getType().equals("R")) {
							// typecasting the product into a rental type
							Rental rental = (Rental) product;

							// SUBTOTAL
							subtotal = ((double) rental.getNumDays() * (double) rental.getDailyCost())
									- (double) rental.getDeposit() + (double) rental.getCleaning();

							// TAXES
							// loop through each customer to find matching customer code
							int customerListLength = customers.size();
							int u = 0;
							for (u = 0; u < customerListLength; u++) {
								if (invoice.getAccountCode().equals(customers.get(u).getAccountCode())) {
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
							singleProductPrices.add(productPriceCalculations);
						}
						// TOWING
						if (product.getType().equals("T")) {
							// towing is discounted if it includes R, F, or T
							// typecasting the product into a rental type
							Towing towing = (Towing) product;

							// SUBTOTAL
							subtotal = ((double) towing.getNumMiles() * (double) towing.getCostPerMile());

							// DISCOUNT
							// go thru list of products, check for match on rental
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
								if (invoice.getAccountCode().equals(customers.get(u).getAccountCode())) {
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
							singleProductPrices.add(productPriceCalculations);

						}
						// REPAIR
						if (product.getType().equals("F")) {
							// typecasting the product into a rental type
							Repair repair = (Repair) product;

							// SUBTOTAL
							subtotal = ((double) repair.getHourlyLabor() * (double) repair.getHoursWorked())
									+ (double) repair.getParts();

							// TAXES
							// loop through each customer to find matching customer code
							int customerListLength = customers.size();
							int u = 0;
							for (u = 0; u < customerListLength; u++) {
								if (invoice.getAccountCode().equals(customers.get(u).getAccountCode())) {
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
							singleProductPrices.add(productPriceCalculations);

						}
						// CONCESSION
						if (product.getType().equals("C")) {
							// concession is discounted if R is included
							// typecasting the product into a rental type
							Concession concession = (Concession) product;

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
							singleProductPrices.add(productPriceCalculations);

						}
						System.out.print(singleProductPrices.get(0).getTotal());

					}

				}
			}
			prices.add(singleProductPrices);

		}
	*/
		


		

		// when i try to print values from prices(which is a list of lists of prices,
		// basically each invoices product's prices) or
		// from singleproductprices (list of prices, each products prices) none of the
		// values will print. I'm not sure why they aren't
		// going into the right spots. The error occurs on the line that I try to print.
		// Thanks!!

//		System.out.print(prices.get(0).get(1).getTotal());
//		System.out.print(prices.get(1).get(0).getTotal());

		// SUBTOTAL
		// figure out which kind of product it is, calculate subtotal
		// based off of that
		// new variable total = subtotal

		// DISCOUNTS
		// if towing, repair and rental all included, add a discount
		// of the price of the towing
		// if concession has associated repair, add a discount of 10% of
		// the concession
		// subtract discounts from total

		// FEES
		// if its a business account, add 75.50 to fees
		// add fees to total

		// TAXES
		// if B account, .0425 * total
		// if P account, .08 * total

		// DISCOUNTS AGAIN (POST TAX)
		// if account type is personal and emails >= 2, add a 5% discount
		// on total

		// set total

		// check the person on each invoice and sort alphabetically

		// EXECUTIVE SUMMARY REPORT

		// Write the stuff at the top, columns
		// start with first in the alpha list
		// write invoice code, name, account, pricecalculations
		// use a loop to write the total totals at the bottom

		// DETAILED REPORTS

	}
	
	

	//methods
	
	//make alphabetizing a method
	
	public static ArrayList<Invoice> alphabetizing(ArrayList<Invoice> invoices, ArrayList<Person> persons) {
		
		ArrayList<String> invoicePersonCodes = new ArrayList<String>();
		int length = (invoices.size());
		int i = 0;
		for (i = 0; i < length; i++) {
			invoicePersonCodes.add(invoices.get(i).getPersonCode().getPersonCode());
		}

		// creating a list of the persons that match each personcode from the invoices
		ArrayList<String> matchingInvoicePersons = new ArrayList<String>();
		int qLength = (invoicePersonCodes.size());
		int j = 0;
		for (j = 0; j < qLength; j++) {
			int k = 0;
			int pLength = persons.size();
			for (k = 0; k < pLength; k++) {
				if (invoicePersonCodes.get(j).equals(persons.get(k).getPersonCode())) {
					matchingInvoicePersons.add(persons.get(k).getName());
				}
			}
		}

		// sorting the list of names
		Collections.sort(matchingInvoicePersons);

		// changing the sorted list of person names back into person codes
		ArrayList<String> sortedInvoiceCodes = new ArrayList<String>();
		int sLength = (invoicePersonCodes.size());
		int l = 0;
		for (l = 0; l < sLength; l++) {
			int m = 0;
			int fLength = persons.size();
			for (m = 0; m < fLength; m++) {
				if (matchingInvoicePersons.get(l).equals(persons.get(m).getName())) {
					sortedInvoiceCodes.add(persons.get(m).getPersonCode());
				}
			}
		}

		// matching each sorted personcode to the invoice that contains it and putting
		// those invoices into a now sorted list
		ArrayList<Invoice> sortedInvoices = new ArrayList<Invoice>();
		int rLength = (invoicePersonCodes.size());
		int n = 0;
		for (n = 0; n < rLength; n++) {
			int p = 0;
			int fLength = persons.size();
			for (p = 0; p < fLength; p++) {
				if (sortedInvoiceCodes.get(n).equals(invoices.get(p).getPersonCode().getPersonCode())) {
					sortedInvoices.add(invoices.get(p));
				}
			}
		}
		
		return sortedInvoices;
		
	}
	
	
	//goes thru each invoice and adds in prices
	public static ArrayList<Invoice> priceCalc(ArrayList<Invoice> sortedInvoices, ArrayList<Product> products, ArrayList<Customer> customers){
		
		int invLength = sortedInvoices.size();
		int q = 0;
		// going through each invoice
		for (q = 0; q < invLength; q++) {
			int invoiceProductsListLength = sortedInvoices.get(q).getProductsList().size();
			int r = 0;
			// going through each product in invoice
			for (r = 0; r < invoiceProductsListLength; r++) {
				//check the type of product, call appropriate method
				int productsMasterList = products.size();
				int t = 0;
				for (t = 0; t < productsMasterList; t++) {
					Invoice invoice = sortedInvoices.get(q);
					ArrayList<Product> productsListTemp = invoice.getProductsList();
					Product product = productsListTemp.get(r);
					if (product.getProductCode().equals(products.get(t).getProductCode())) {

						// RENTAL
						if (product.getType().equals("R")) {
							Rental pricedRental = new Rental(null, null, null, null, 0, 0, 0);		
							pricedRental.rentalPrices(product, customers, invoice);
							sortedInvoices.get(q).getProductsList().set(r, pricedRental);
						}
						// TOWING
						if (product.getType().equals("T")) {
							//pass in whole products list at the end
							Towing pricedTowing = new Towing(null, null, null, null, 0);
							pricedTowing.towingPrices(product, customers, invoice, products);
							sortedInvoices.get(q).getProductsList().set(r, pricedTowing);
						}
						// REPAIR
						if (product.getType().equals("F")) {
							Repair pricedRepair = new Repair(null, null, null, null, 0, 0);
							pricedRepair.repairPrices(product, customers, invoice);
							sortedInvoices.get(q).getProductsList().set(r, pricedRepair);
						}
						// CONCESSION
						if (product.getType().equals("C")) {
							Concession pricedConcession = new Concession(null, null, null, null, 0);
							pricedConcession.concessionPrices(product, customers, invoice);
							sortedInvoices.get(q).getProductsList().set(r, pricedConcession);
						}
					}
				}
			}	
		}	
		return sortedInvoices;
	}
	
	//printing executive report
	
	//printing an individual report
	
	
	

}
