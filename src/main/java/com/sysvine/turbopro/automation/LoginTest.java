package com.sysvine.turbopro.automation;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sysvine.turbopro.automation.Commons;
import com.sysvine.turbopro.automation.LoginPage;


public class LoginTest 

{
	private WebDriverWait wait;
	
	private LoginPage userLogin;
	

	@BeforeTest
	public void initiate() {
		userLogin = new LoginPage();
		wait = new WebDriverWait( Commons.driver, 2 );
		PageFactory.initElements(Commons.driver, userLogin);

	}

	@Test( priority = 0 )
	public void verifyIncorrectLogin() {
		userLogin.login("random", "random");	
		Assert.assertNotNull( wait.until( ExpectedConditions.visibilityOf(userLogin.errorMessage) ) );	
	}

	@Test( priority = 1 )
	public void verifyLogin() {
		userLogin.login( "Admin","D3m0" );
		Assert.assertEquals( Commons.driver.getTitle(), "TurboPro - Home" );
	}

}
