package com.bc;

import java.util.ArrayList;

//import com.bc.Address;

public class Person {

		private String personCode;
		private String name;
		private Address address;
		private ArrayList<String> emails;
		
		
		//getters and setters
		public String getPersonCode() {
			return personCode;
		}
		public void setPersonCode(String personCode) {
			this.personCode = personCode;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public ArrayList<String> getEmails() {
			return emails;
		}
		public void setEmails(ArrayList<String> emails) {
			this.emails = emails;
		}
		
		//constructor
		public Person(String personCode, String name, Address address, ArrayList<String> emails) {
			super();
			this.personCode = personCode;
			this.name = name;
			this.address = address;
			this.emails = emails;
		}

		
		//methods
		
		
		
		
}
