package com.sysvine.turbopro.automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.sysvine.turbopro.automation.Commons;

public class DOM {

	
	public static WebElement getParentNode(WebElement childElement) {
		JavascriptExecutor executor = (JavascriptExecutor)Commons.driver;
		WebElement parentElement = (WebElement)executor.executeScript
				("return arguments[0].parentNode;", childElement);
		return parentElement;
	}
}


