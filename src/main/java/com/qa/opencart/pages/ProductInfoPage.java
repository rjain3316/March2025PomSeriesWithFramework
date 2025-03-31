package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtils eleutil; 
	
	private By productheader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quantity = By.name("quantity");
	private By addToCart = By.id("button-cart");
	private By productMetaData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");
	private By productPriceData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");
	private Map<String, String> productmap;
	
	public ProductInfoPage(WebDriver driver) 
	{
		this.driver = driver;
		eleutil = new ElementUtils(driver);
	}
	
	public String getProductHeaderValue()
	{
		return eleutil.doElementGetText(productheader);
	}
	
	public int getProductImagesCount()
	{
		int actProductImagesCount= eleutil.waitForElementsVisible(AppConstants.SHORT_TIME_OUT, productImages).size();
		System.out.println("total product images for : "+ getProductHeaderValue() + " ====> "+ actProductImagesCount);
		return actProductImagesCount;
	}
	
	
	private void getProductMetaData()      //Concept user here - encapsulation
	{
		List<WebElement> metaList= eleutil.waitForElementsVisible(AppConstants.MEDIUM_TIME_OUT, productMetaData);
		//Map<String, String> metaMap = new HashMap<String, String>();
		
		for(WebElement e: metaList)
		{
			String metaText = e.getText();
			String key = metaText.split(":")[0].trim();
			String value = metaText.split(":")[1].trim();
			productmap.put(key, value);
		}
		//return metaMap;
	}
	
	
	private void getProductPriceData()   //Concept used here - encapsulation
	{
		List<WebElement> priceList= eleutil.waitForElementsVisible(AppConstants.MEDIUM_TIME_OUT, productPriceData);
		//Map<String, String> priceMap = new HashMap<String, String>();
		
		String actPrice = priceList.get(0).getText().trim();
		String exTax = priceList.get(1).getText().split(":")[0].trim();
		String exTaxValue = priceList.get(1).getText().split(":")[1].trim();

		productmap.put("price", actPrice);
		productmap.put(exTax, exTaxValue);
		
		//return priceMap;
	}
	
	public Map<String, String> getProductData()
	{
		productmap = new HashMap<String, String>();           //Hashmap
		//productmap = new LinkedHashMap<String, String>();       //Linked hashmap (for order based collection )
		//productmap = new TreeMap<String, String>();               //TreeMap (for sorting order)
		productmap.put("productheader", getProductHeaderValue());
		productmap.put("productimages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productmap;
	}

}
