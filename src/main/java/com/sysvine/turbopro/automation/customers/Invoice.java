package com.sysvine.turbopro.automation.customers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.sysvine.turbopro.automation.Commons;
import com.sysvine.turbopro.automation.utils.DOM;


public class Invoice {

	private String invoiceURL = "https://qe.tt.eb.local/turbotracker/turbo/createinvoice?oper=create";

	@FindBy(xpath = "//input[@value='   Add']")
	WebElement addButton;

	@FindBy(id = "ui-dialog-title-msgDlg")
	WebElement informationPopupTitle;

	@FindBy(xpath = "//span[text()='New Invoice']")
	WebElement newInvoiceButton;
	
	@FindBy(id = "customerInvoice_shipDateID")
	WebElement datePicker;
	
	@FindBy(id = "loadingDivForCIGeneralTab")
	WebElement loadingSpinner;
	
	@FindBy(xpath = "//*[@class = 'ui-state-default ui-state-highlight ui-state-active ui-state-hover']") 
	WebElement todaysDate;
	
	@FindBy(xpath = "//span[@class = 'ui-datepicker-month']")
	WebElement currentMonth;
	
	@FindBy(xpath = "//span[@class = 'ui-datepicker-year']")
	WebElement currentYear;
	
	@FindBy(id = "customerInvoice_invoiceDateID")
	WebElement date;
	
	@FindBy(id = "shipToName")
	WebElement shipToName;
	
	@FindBy(id = "customerInvoice_customerInvoiceID")
	WebElement cusNameInputBox;
	
	@FindBy(xpath = "//*[contains(text(), 'Automation Test')]")
	WebElement dropdownOption;
	
	@FindBy(xpath = "/html/body/ul[25]")
	WebElement dropdownBox;
	
	@FindBy(id = "shipToAddress1")
	WebElement shipToaddressLine1;
	
	@FindBy(id = "shipToAddress2")
	WebElement shipToaddressLine2;
	
	@FindBy(id = "shipToCity")
	WebElement shipToCity;
	
	@FindBy(id = "shipToState")
	WebElement shipToState;
	
	@FindBy(id = "shipToZip")
	WebElement pinCode;
	
	@FindBy(id = "forWard")
	WebElement switchNextIcon;
	
	@FindBy(id = "backWard")
	WebElement switchPreviousIcon;
	
	@FindBy(xpath = "//a[@class = 'ui-corner-all']")
	List<WebElement> dropdownOptions;
	
	@FindBy(id = "customerInvoice_TaxTerritory")
	WebElement taxTerritory;
	
	@FindBy(id = "customerInvoice_generaltaxId")
	WebElement tax_percentage;
	
	@FindBy(id = "emailListCU")
	WebElement emailID;
	
	private ArrayList<Address> actualShipToAddresses = new ArrayList<Address>();
	private WebDriverWait wait;
	
	public Invoice() {
		wait = new WebDriverWait(Commons.driver, 5);
	}
		
	public void createNewInvoice() {
		Commons.driver.get(invoiceURL);
		addButton.click();
		wait.until(ExpectedConditions.visibilityOf(informationPopupTitle));
		newInvoiceButton.click();
		wait.until(ExpectedConditions.invisibilityOf(loadingSpinner));
	}

	public String getInvoiceDate() {
		return 	datePicker.getAttribute("value");
	}
	
	public void setCustomerName(String customerName) {
		cusNameInputBox.clear();
		cusNameInputBox.sendKeys(customerName);
		wait.until(ExpectedConditions.visibilityOf(dropdownBox));	
		DOM.getParentNode(dropdownOption).click();
	}
	
	public List<WebElement> getDropDownOptionsFor(String inputName) {
		cusNameInputBox.clear();
		cusNameInputBox.sendKeys(inputName);
		wait.until(ExpectedConditions.visibilityOf(dropdownBox));
		return dropdownOptions;
	}
	
	public void addShipToAddress() {
		String line1, line2, city, state, pincode; 
		Map<String, Float> taxTerritory = new HashMap<String, Float>();
		
		line1 = shipToaddressLine1.getAttribute("value");
		line2 = shipToaddressLine2.getAttribute("value");
		city = shipToCity.getAttribute("value");
		state = shipToState.getAttribute("value");
		pincode = pinCode.getAttribute("value");
		taxTerritory.put(this.taxTerritory.getAttribute("value"), 
				Float.valueOf( tax_percentage.getAttribute("value") ));
		actualShipToAddresses.add
		(new Address(line1, line2, city, state, pincode, taxTerritory));	
		if( isNextAddressAvailable() ) {
			addShipToAddress();
		}
	}
	
	public ArrayList<Address> getShipToAddresses(){
		addShipToAddress();
		Collections.sort(actualShipToAddresses);
		return actualShipToAddresses;
	}
	
	public boolean isNextAddressAvailable() {	
		String imageURL = switchNextIcon.getCssValue("background");		
		if( !imageURL.contains("DisabledArrowright" ) ) {
			switchNextIcon.click();
			return true;
		}	
		return false;
	}

}
