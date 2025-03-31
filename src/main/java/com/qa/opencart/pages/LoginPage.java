package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtils eleutil;
	
	//1. private By locators - page locators
	private By emailId= By.id("input-email");
	private By password= By.id("input-password");
	private By loginBtn= By.xpath("//input[@value='Login']");
	private By forgotPwdLink= By.linkText("Forgotten Password111");
	private By registerLink = By.linkText("Register");
	
	//2. public page constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleutil = new ElementUtils(driver);
	}
	
	//3. public page actions /methods
	public String getLoginPageTitle()
	{
		String title=eleutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE , AppConstants.SHORT_TIME_OUT);
		//String title= driver.getTitle();
		System.out.println("Login page title is : "+ title);
		return title;
	}

	public String getLoginPageURL()
	{
		String url=eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION , AppConstants.SHORT_TIME_OUT);
		//String url= driver.getCurrentUrl();
		System.out.println("Login page URL is : "+ url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist()
	{
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return eleutil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public AccountsPage doLogin(String username, String pwd)     //this method doing the page chaining
	{
		System.out.println("App creds are : "+username + " : "+ pwd);
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
//		return driver.getTitle();
		
		eleutil.waitForElementVisible(emailId, AppConstants.MEDIUM_TIME_OUT).sendKeys(username);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		//return eleutil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		return new AccountsPage(driver);       //As with doLogin(), we are landing on next page which is account page, thats why its doLogin() responsibility to return the object of AccountsPage class 
	}
	
	
	public RegisterPage navigateToRegisterPage()
	{
		eleutil.waitForElementVisible(registerLink, AppConstants.SHORT_TIME_OUT).click();
		return new RegisterPage(driver);
	}
}
