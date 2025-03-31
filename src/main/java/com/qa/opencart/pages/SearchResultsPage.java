package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtils eleutil; 
	
	private By productResults = By.cssSelector("div.product-layout");
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		eleutil = new ElementUtils(driver);
	}

	
	public int getSearchResultsCount()
	{
		return eleutil.waitForElementsVisible(AppConstants.SHORT_TIME_OUT, productResults).size();
	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		eleutil.clickElementWhenReady(By.linkText(productName), AppConstants.SHORT_TIME_OUT);
		return new ProductInfoPage(driver);     //TDD approach
	}
}
