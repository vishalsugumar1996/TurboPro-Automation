package com.sysvine.turbopro.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Commons {

	private final String siteURL = "https://qe.tt.eb.local/turbotracker/turbo/home";
	private final String driveExe = "/Users/vishal_sugumar/eclipse-workspace/turoproautomation/Essentials/chromedriver";
	public static WebDriver driver;
	
	
	@BeforeSuite
	public void openBrowser () {
		System.setProperty("webdriver.chrome.driver", driveExe);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(siteURL);
	}
	
	@AfterSuite
	public void closeBrowser() {
		driver.close();
	}
	
}
