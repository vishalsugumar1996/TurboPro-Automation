package com.sysvine.turbopro.automation.customers;

import java.util.HashMap;
import java.util.Map;


public class Address {
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

	public Map<String, Float> getTaxTerritory() {
		return taxTerritory;
	}

}
