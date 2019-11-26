package com.sysvine.turbopro.automation.customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.sysvine.turbopro.automation.Commons;
import com.sysvine.turbopro.automation.utils.DBConnector;
import com.sysvine.turbopro.automation.utils.Date;


/**
 * <p>
 * This class handles various functionalities in an invoice which is created out of a Job via
 * <b>'Company' - 'Customers' - 'Invoices'</b> module. There is a test method for crucial functionalities in the invoice
 * </p>
 * 
 * @author Vishal Sugumar
 */


public class InvoiceTest {

	static WebDriverWait wait;
	Invoice invoice;
	ArrayList<Address> exceptedShipToAdresses;
	ArrayList<Address> expectedAddress;
	ArrayList<Address> actualAddress;
	int rxMaster;

	/**
	 * Initializes the the WebElements in the page. The elements are declared in the {@link com.sysvine.turbopro.automation.customers.Invoice} class
	 */

	@BeforeTest
	public void initalizeElemets() {
		invoice = new Invoice();
		PageFactory.initElements(Commons.driver, invoice);

	}

	/**
	 * <h1>Customer Name Suggestions</h1>
	 * <p>
	 * This method verifies if the suggestions shown in the "Customer Name" input field contains the data given as the input.
	 * Fails the test case if the drop down option does not contain the data given as the input.
	 * </p>
	 */


	@Test (priority = 0) 
	public void checkCusNameSuggestions() {

		invoice.createNewInvoice();
		List<WebElement> dropdownOptions = invoice.getDropDownOptionsFor("aut");
		Assert.assertNotNull(dropdownOptions);

		for(WebElement option: dropdownOptions) { //Verifies if each and every options in the drop down contains the data given as the input
			if(!option.getText().toLowerCase().contains("aut")) {
				Assert.fail("Incorrect dropdown option -> "+option.getText());
			}
		}
	}

	/**
	 * <h1>Invoice Date</h1>
	 * Checks if the invoice has the current date, by comparing invoice date with the current date.
	 */

	@Test (priority = 1) 
	public void checkInvoiceDate() {
		String invoiceDate = invoice.getInvoiceDate();
		Assert.assertEquals(Date.getCurrentDate(), invoiceDate);

	}

	/**
	 * This method accomplished the following things listed below:
	 * <ol>
	 * 	 <li>Selects a customer name from the drop down options</li>
	 * 	 <li>Fetches the Ship To address of the selected customer from the DB</li>
	 *   <li>Fetches the multiple Ship To address in the UI</li>
	 *   <li>Compares these two addresses</li>
	 * </ol>
	 * 
	 * Fails the test case for the following cases:
	 * <ol>
	 * 	 <li>If the number of addresses mapped to the customer in DB and UI is not equal</li>
	 * 	 <li>If the string representation of these addresses are not equal</li>
	 * </ol>
	 */

	@Test (priority = 2)
	public void checkShipToAddress() {
		invoice.setCustomerName("automation");
		setAddress();

		if( expectedAddress.size() != actualAddress.size() ) {
			Assert.fail("Number of expected and actual addresses are not equal");
		}

		Iterator<Address> expectedAddressItr = expectedAddress.iterator();
		Iterator<Address> actualAddressItr   = actualAddress.iterator();

		while(expectedAddressItr.hasNext() && actualAddressItr.hasNext()) {
			String expected_address = expectedAddressItr.next().toString();
			String actual_address = actualAddressItr.next().toString();
			if ( expected_address.compareTo(actual_address) != 0 ) { 	
				Assert.fail("EXPECTED ADDRESS\n"+expected_address+"\nis not equal to ACTUAL ADDRESS\n"+actual_address);	
			}
		}

	}


	public void setAddress() {
		expectedAddress = getAddressesofCustomer("automation test");
		actualAddress = invoice.getShipToAddresses();
	}



	@Test (priority = 3)
	public void checkTaxTerritory() {
		Iterator<Address> expectedAddressItr = expectedAddress.iterator();
		Iterator<Address> actualAddressItr = actualAddress.iterator();

		while(expectedAddressItr.hasNext() && actualAddressItr.hasNext()) {
			Iterator<Entry<String, Float>> expectedTaxTerritory = expectedAddressItr.next().getTaxTerritory().entrySet().iterator();
			Iterator<Entry<String, Float>> actualTaxTerritory = actualAddressItr.next().getTaxTerritory().entrySet().iterator();

			while(expectedTaxTerritory.hasNext()&&actualTaxTerritory.hasNext()) {
				Entry<String, Float> expectedTT = expectedTaxTerritory.next();
				Entry<String, Float> actualTT = actualTaxTerritory.next();

				String expectedState = expectedTT.getKey();
				String actualState = actualTT.getKey();

				if( !expectedState.equals( actualState ) ){
					Assert.fail("EXPECTED TAX TERRITORY -> "+expectedState+"\tACTUAL TERRITORY -> "+actualTT.getKey());
				}

				float expectedTaxPercentage = expectedTT.getValue();
				float actualTaxPercentage = actualTT.getValue();
				if(expectedTaxPercentage != actualTaxPercentage) {
					Assert.fail("EXPECTED PERCETAGE FOR "+expectedState+" IS "+
							expectedTaxPercentage+"\tACTUAL PERCENTAGE FOR "+actualState+" IS "+actualTaxPercentage);
				}
			}

		}	

	}
	
	@Test (priority = 4)
	public void testCustomerEmailID() {
		
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
		Collections.sort(exceptedShipToAdresses);
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
	
	/**
	 * This method fetches all the email ID's mapped to a customer rxID
	 * 
	 * @param customer name for whom we need to fetch the email ID(s)
	 * @return All the email IDs mapped to a customer rxID
	 **/
	
	public ArrayList<String> getEmailAddress(String customerName) {
		
		String query = "SELECT EMail FROM BartosProd20170823.rxContact"
				+ "WHERE rxMasterID = ?";
		ArrayList<String> emailIDs = new ArrayList<String>();
		
		try {
			PreparedStatement emailQuery  = DBConnector.connection.prepareStatement(query);
			emailQuery.setInt(1, getIDofCustomer(customerName));
			ResultSet emailID = emailQuery.executeQuery();
			
			while(emailID.next()) {
				emailIDs.add(emailID.getString("EMail"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emailIDs;
		
	}
		
}
