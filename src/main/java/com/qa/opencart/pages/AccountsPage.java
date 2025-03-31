package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtils eleutil;
	
	private By logoutLink = By.linkText("Logout");
	private By accHeaders= By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleutil = new ElementUtils(driver);
	}
	
	public String getAccPageTitle()
	{
		return eleutil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE,AppConstants.SHORT_TIME_OUT );
	}
	
	public Boolean isLogoutLinkExist()
	{
		return eleutil.waitForElementPresence(logoutLink, AppConstants.SHORT_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccountsPageHeader()
	{
		List<WebElement> headersList=eleutil.waitForElementsPresence(AppConstants.SHORT_TIME_OUT , accHeaders);
		List<String> headersValueList = new ArrayList<String>();
		for(WebElement e: headersList)
		{
			String header = e.getText();
			headersValueList.add(header);
		}
		System.out.println("Actual Headers are ===> " + headersValueList);
		return headersValueList;
	}
	
	
	public int getAccountsPageHeaderCount()
	{
		//return getAccountsPageHeader().size();
		return eleutil.waitForElementsPresence(AppConstants.SHORT_TIME_OUT , accHeaders).size();
	}
	
	public SearchResultsPage doSearch(String searchKey)
	{
		//eleutil.doSendKeys(search, searchKey);
		WebElement searchField = eleutil.waitForElementVisible(search, AppConstants.SHORT_TIME_OUT);
		searchField.clear();
		searchField.sendKeys(searchKey);
		eleutil.doClick(searchIcon);
		return new SearchResultsPage(driver);    //TDD (Test driven development)
	}
}
