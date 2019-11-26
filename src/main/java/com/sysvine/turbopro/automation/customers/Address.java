package com.sysvine.turbopro.automation.customers;

import java.util.HashMap;
import java.util.Map;


public class Address implements Comparable<Address>  {

	String addressLine1;
	String addressLine2;
	String city;
	String state;
	String pincode;
	Map<String, Float> taxTerritory = new HashMap<String, Float>();


	public Address(String addressLine1, String addressLine2, String city, String state, String pincode,
			Map<String, Float> taxTerritory) {

		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.taxTerritory = taxTerritory;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPincode() {
		return pincode;
	}

	public Map<String, Float> getTaxTerritory() {
		return taxTerritory;
	}

	public int compareTo(Address address) {

		if( (this.addressLine1 + this.addressLine2)
				.compareTo((address.addressLine1 + address.addressLine2)) > 1 ) {
			return 1;
		}

		else if ((this.addressLine1 + this.addressLine2)
				.compareTo((address.addressLine1 + address.addressLine2)) < 1 ) {
			return -1;
		}

		else 
			return 0;
	}
	
	public String toString() {
		String completeAddress = addressLine1+"\n"+addressLine2+"\n"+city+"\n"+state+"\n"+pincode;
		return completeAddress;
	}



}
