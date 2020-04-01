package Login;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class AllProductionLogin {
	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	public WebDriver driver;
	
	//  public String baseurl= "http://qa.tt.eb.local/turbotracker/turbo/";
	  
  @BeforeTest
  public void beforeTest() 
  {
	  System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
	  System.setProperty("webdriver.chrome.silentOutput", "true");
	  System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging
	  
	  driver = new ChromeDriver();
//		driver.manage().window().maximize(); 
  }
  
  @Test(priority=1)
  public void loginBartos() 
  {
	  driver.get("https://bartos.aandespecialties.com/turbotracker/turbo/");			//Bartos

	  driver.findElement(By.linkText("Login")).click();

	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();		//login
	  
		 WebElement settings = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		 WebElement logout = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a"));
				 
		 Actions action = new Actions(driver);
		 action.moveToElement(settings).perform();
		 action.click(logout).perform();				//logout
	
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }
  

  @Test(priority=2)
  public void loginMWG() 
  {
	  driver.get("https://millswilsongeorge.aandespecialties.com/turbotracker/turbo/");			//MWG

	  driver.findElement(By.linkText("Login")).click();

	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();		//login
	  
		 WebElement settings = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		 WebElement logout = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a"));
				 
		 Actions action = new Actions(driver);
		 action.moveToElement(settings).perform();
		 action.click(logout).perform();				//logout
	
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }

  
  @Test(priority=3)
  public void loginMPNService() 
  {
	  driver.get("https://mpnservices.aandespecialties.com/turbotracker/turbo/");			//MPN Service

	  driver.findElement(By.linkText("Login")).click();

	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();		//login
	  
		 WebElement settings = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		 WebElement logout = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a"));
				 
		 Actions action = new Actions(driver);
		 action.moveToElement(settings).perform();
		 action.click(logout).perform();				//logout
	
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }
  

  @Test(priority=4)
  public void loginMPNCompany() 
  {
	  driver.get("https://mpncompany.aandespecialties.com/turbotracker/turbo/");			//MPN Company

	  driver.findElement(By.linkText("Login")).click();

	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();		//login
	  
		 WebElement settings = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		 WebElement logout = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a"));
				 
		 Actions action = new Actions(driver);
		 action.moveToElement(settings).perform();
		 action.click(logout).perform();				//logout
	
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }


  @Test(priority=5)
  public void loginPennergy() 
  {
	  driver.get("https://pennergy.aandespecialties.com/turbotracker/turbo/vendorinvoicelist");			//Pennergy

	  driver.findElement(By.linkText("Login")).click();

	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();		//login
	  
		 WebElement settings = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		 WebElement logout = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a"));
				 
		 Actions action = new Actions(driver);
		 action.moveToElement(settings).perform();
		 action.click(logout).perform();				//logout
	
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }

  
  @Test(priority=6)
  public void loginChapman() 
  {
	  driver.get("https://chapman.aandespecialties.com/turbotracker/turbo/login");			//Chapman
	  driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	  
	  driver.findElement(By.linkText("Login")).click();
	  
	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();		//login
	  
		 WebElement settings = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		 WebElement logout = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a"));
				 
		 Actions action = new Actions(driver);
		 action.moveToElement(settings).perform();
		 action.click(logout).perform();				//logout
	
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }
  
  
  @Test(priority=7)
  public void loginHVAC() 
  {
	  driver.get("https://hvac.aandespecialties.com/turbotracker/turbo/login");			//HVAC

	  driver.findElement(By.linkText("Login")).click();

	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();		//login
	  
		 WebElement settings = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		 WebElement logout = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a"));
				 
		 Actions action = new Actions(driver);
		 action.moveToElement(settings).perform();
		 action.click(logout).perform();				//logout
	
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }
 
 
  @Test(priority=8)
  public void loginHDGrant() 
  {
	  driver.get("https://hdgrant.aandespecialties.com/turbotracker/turbo/");			//HDGrant

	  driver.findElement(By.linkText("Login")).click();

	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();		//login
	  
		 WebElement settings = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		 WebElement logout = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a"));
				 
		 Actions action = new Actions(driver);
		 action.moveToElement(settings).perform();
		 action.click(logout).perform();				//logout
	
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }


  @Test(priority=9)
  public void loginEMS() 
  {
	  driver.get("https://enviromechsales.aandespecialties.com/turbotracker/turbo/");			//EMS

	  driver.findElement(By.linkText("Login")).click();

	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();		//login
	  
		 WebElement settings = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		 WebElement logout = driver.findElement(By.xpath("//div[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a"));
				 
		 Actions action = new Actions(driver);
		 action.moveToElement(settings).perform();
		 action.click(logout).perform();				//logout
	
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }
  
  
  /*@AfterMethod
  public void afterMethod(ITestResult result)
  {
      try
   {
      if(result.getStatus() == ITestResult.SUCCESS)
      {

          //Do something here
          System.out.println("passed **********");
      }

      else if(result.getStatus() == ITestResult.FAILURE)
      {
           //Do something here
          System.out.println("Failed ***********");

      }

       else if(result.getStatus() == ITestResult.SKIP ){

          System.out.println("Skiped***********");

      }
  }
     catch(Exception e)
     {
       e.printStackTrace();
     }

  }
*/  
  
 @AfterTest
  public void teardown() 
  {
	  //driver.quit();
  }

}

//Script for navigating to Users module and searching a user 
