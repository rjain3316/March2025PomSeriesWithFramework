package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtils eleutil;
	
	public RegisterPage(WebDriver driver) 
	{
		this.driver = driver;
		eleutil = new ElementUtils(driver);
	}
	
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By email= By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmPassword=By.id("input-confirm");
	
	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']");
	
	private By agreeCheckbox=By.name("agree");
	private By continueButton=By.xpath("//input[@type='submit']");
	
	private By successMessage=By.cssSelector("div#content h1");
	private By logoutLink=By.linkText("Logout");
	private By registerLink=By.linkText("Register");

	public boolean registerUser(String firstName, String lastname, 
			String email, String telephone, 
			String password, String subscribe)
	{
		eleutil.waitForElementVisible(this.firstName, AppConstants.SHORT_TIME_OUT).sendKeys(firstName);
		eleutil.doSendKeys(this.lastName, lastname);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.telephone, telephone);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes"))
		{
			eleutil.doClick(subscribeYes);
		}
		else
		{
			eleutil.doClick(subscribeNo);
		}
		
		eleutil.doClick(agreeCheckbox);
		eleutil.doClick(continueButton);
		
		String successMsg= eleutil.waitForElementVisible(successMessage, AppConstants.MEDIUM_TIME_OUT).getText();
		System.out.println(successMsg);
		
		if(successMsg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG))
		{
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			return true;
		}
		else
		{
			return false;
		}
	}

}
