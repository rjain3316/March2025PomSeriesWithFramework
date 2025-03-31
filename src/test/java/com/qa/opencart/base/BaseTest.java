package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	protected WebDriver driver;
	protected Properties prop; 
	protected LoginPage loginPageObj;
	protected AccountsPage accPageObj;
	protected SearchResultsPage searchResPageObj;
	protected ProductInfoPage ProductInfoPageObj;
	DriverFactory df;
	protected SoftAssert softAssert;
	protected RegisterPage regPageObj;
	
	@BeforeTest
	public void setup()
	{
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPageObj = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown()
	{
		//driver.quit();
	}

}
