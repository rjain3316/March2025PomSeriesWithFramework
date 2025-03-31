package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup()
	{
		regPageObj = loginPageObj.navigateToRegisterPage();
	}
	
	
	@Test
	public String getRandomEmailId()
	{
		return "openauto"+System.currentTimeMillis()+"@open.com";
	}
	
	@DataProvider
	public Object[][] getUserRegData()
	{
		return new Object[][] {
			{"Pooja", "Agrawal", "9867990234", "pooja@123" , "yes"},
			{"Shubham", "Gupta", "9867190234", "shubh@123" , "no"},
			{"Mitaj", "Kumar", "8867990234", "mitaj@123" , "yes"},
		};
	}
	
	
	@DataProvider
	public Object[][] getUserRegSheetData()
	{
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	
	@Test(dataProvider = "getUserRegSheetData")
	public void userRegisterTest(String firstName, String lastname, String telephone, String password , String subscribe)
	{
		Assert.assertTrue(regPageObj.registerUser(firstName, lastname, getRandomEmailId(), telephone, password, subscribe));
	}

}
