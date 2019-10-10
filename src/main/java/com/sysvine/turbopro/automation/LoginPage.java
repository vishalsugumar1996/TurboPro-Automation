package com.sysvine.turbopro.automation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage 
{

	
	@FindBy(linkText = "Login")
	WebElement loginLink; 
	
	@FindBy(name = "userName")
	WebElement userNameInputBox;
	
	@FindBy(name = "password")
	WebElement passwordInputBox;
	
	@FindBy(className = "login_submit")
	WebElement loginButton;
	
	@FindBy( xpath = "//*[@id='errormsg']" )
	public WebElement errorMessage;
	
	private void setUserName(String userName) {
		userNameInputBox.sendKeys(userName);
	}
	
	private void setPassword(String password) {
		passwordInputBox.sendKeys(password);
	}
	
	public void login(String username, String password) {

		Commons.driver.get("https://qe.tt.eb.local/turbotracker/turbo/login");
		userNameInputBox.clear();
		passwordInputBox.clear();
		setUserName(username);
		setPassword(password);
		loginButton.click();
				
	}
	
}
