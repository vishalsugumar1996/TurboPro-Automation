package com.sysvine.turbopro.automation.customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.sysvine.turbopro.automation.Commons;
import com.sysvine.turbopro.automation.utils.DBConnector;
import com.sysvine.turbopro.automation.utils.Date;


public class InvoiceTest {

	static WebDriverWait wait;
	Invoice invoice;
	ArrayList<Address> exceptedShipToAdresses;

	@BeforeTest
	public void initalizeElemets() {
		invoice = new Invoice();
		PageFactory.initElements(Commons.driver, invoice);

	}

	@Test (priority = 0) 
	public void checkCusNameSuggestions() {

		invoice.createNewInvoice();
		List<WebElement> dropdownOptions = invoice.getDropDownOptionsFor("aut");
		Assert.assertNotNull(dropdownOptions);

		for(WebElement option: dropdownOptions) {
			if(!option.getText().toLowerCase().contains("aut")) {
				Assert.fail("Incorrect dropdown option -> "+option.getText());
			}
		}
	}

	@Test (priority = 1) 
	public void checkInvoiceDate() {
		String invoiceDate = invoice.getInvoiceDate();
		Assert.assertEquals(Date.getCurrentDate(), invoiceDate);

	}

	@Test (priority = 2)
	public void checkShipToAddress() {
		invoice.setCustomerName("automation");
		Assert.assertEquals(invoice.getShipToAddresses(), getAddressesofCustomer("automation test"));
	}

	public ArrayList<Address> getAddressesofCustomer(String customerName) {
		String query = "SELECT Address1, Address2, City, State, Zip, coTaxTerritoryID FROM"
				+ " BartosProd20170823.rxAddress where rxMasterID = ?;";

		exceptedShipToAdresses = new ArrayList<Address>();
		int rxCustomerID = getIDofCustomer(customerName);
		try {

			PreparedStatement customerAddressQuery =  DBConnector.connection.prepareStatement(query);
			customerAddressQuery.setInt(1, rxCustomerID);
			ResultSet addresses = customerAddressQuery.executeQuery();

			while(addresses.next()) {
				addAddress(addresses);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exceptedShipToAdresses; 
	}

	public void addAddress(ResultSet addresses) {
		String line1, line2, city, state, pincode;
		Map<String, Float> taxTerritory;
		try {
			line1 = addresses.getString("Address1");
			line2 = addresses.getString("Address2");
			city = addresses.getString("City");
			state = addresses.getString("State");
			pincode = addresses.getString("Zip");
			taxTerritory = getTaxTerritoryOfAddress(addresses.getInt("coTaxTerritoryID"));
			exceptedShipToAdresses.add(new 
					Address(line1, line2, city, state, pincode, taxTerritory));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public int getIDofCustomer(String customerName) {
		String query = "SELECT rxMasterID FROM BartosProd20170823.rxMaster WHERE "
				+ "Name = ?;";
		int rxMasterID = 0;
		try {
			PreparedStatement customerIDquery =  DBConnector.connection.prepareStatement(query);
			customerIDquery.setString(1, customerName);
			ResultSet customerIDs = customerIDquery.executeQuery();

			while(customerIDs.next()) {
				rxMasterID = customerIDs.getInt("rxMasterID");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rxMasterID;
	}

	public Map<String, Float> getTaxTerritoryOfAddress(int coTaxTerritoryID) {
		String query = "SELECT County, TaxRate "
				+ "FROM BartosProd20170823.coTaxTerritory where coTaxTerritoryID = ?";
		String county = null;
		float taxRate = 0;
		Map<String, Float> taxTerritory = new HashMap<String, Float>();
		
		try {
			PreparedStatement addressTaxTerritoryQuery  = DBConnector.connection.prepareStatement(query);
			addressTaxTerritoryQuery.setInt(1, coTaxTerritoryID);
			ResultSet taxTerritories = addressTaxTerritoryQuery.executeQuery();
			
			while(taxTerritories.next()) {
				county = taxTerritories.getString("County");
				taxRate = taxTerritories.getFloat("TaxRate");
			}
			taxTerritory.put(county, taxRate);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taxTerritory;
	
	}
}
