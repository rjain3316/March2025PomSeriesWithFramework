package com.qa.opencart.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ElementUtils;

public class DemoTest extends BaseTest{
	
	ElementUtils eleUtil;
	
	@BeforeMethod
	public void demoSetup()
	{
		driver.get("https://classic.crmpro.com/");
		eleUtil = new ElementUtils(driver);
	}

	@Test
	public void testDemo()
	{
		eleUtil.doSendKeys(By.name("username"), "testautomation");
		eleUtil.doSendKeys(By.name("password"), "testautomation");
		eleUtil.doClick(By.xpath("//input[@value='Login']"));
	}
}
