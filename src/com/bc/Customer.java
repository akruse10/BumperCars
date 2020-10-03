package com.bc;

public class Customer {

		
		private String accountCode;
		private String type;
		private String accountName;
		private Person personCode;
		private Address address;
		
		public String getAccountCode() {
			return accountCode;
		}
		public void setAccountCode(String accountCode) {
			this.accountCode = accountCode;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getAccountName() {
			return accountName;
		}
		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}
		public Person getPersonCode() {
			return personCode;
		}
		public void setPersonCode(Person personCode) {
			this.personCode = personCode;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public Customer(String accountCode, String type, String accountName, Person personCode, Address address) {
			this.accountCode = accountCode;
			this.type = type;
			this.accountName = accountName;
			this.personCode = personCode;
			this.address = address;
		}
		
		

}
