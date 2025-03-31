package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInforSetup()
	{
		accPageObj = loginPageObj.doLogin(prop.getProperty("username") , prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] productTestData()
	{
		return new Object[][]
				{
			{"MacBook", "MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung" , "Samsung Galaxy Tab 10.1"}
				};
	}

	
	@Test(dataProvider = "productTestData")
	public void productHeaderTest(String searchKey , String productName)
	{
		searchResPageObj = accPageObj.doSearch(searchKey);
		ProductInfoPageObj = searchResPageObj.selectProduct(productName);
		String actProductHeader =  ProductInfoPageObj.getProductHeaderValue();
		Assert.assertEquals(actProductHeader, productName);
	}
	
	
	@DataProvider
	public Object[][] productData()
	{
		return new Object[][]
				{
			{"MacBook", "MacBook Pro" , 4},
			{"MacBook", "MacBook Air" , 4},
			{"iMac", "iMac" , 3},
			{"Samsung", "Samsung SyncMaster 941BW" , 1},
			{"Samsung" , "Samsung Galaxy Tab 10.1" , 7}
				};
	}
	
	
	@DataProvider
	public Object[][] productSheetData()
	{
		return ExcelUtil.getTestData("product");
	}
	
	
	@Test(dataProvider = "productSheetData")
	public void productImagesCountTest(String searchKey , String productName , String expProductImagesCount)
	{
		searchResPageObj = accPageObj.doSearch(searchKey);
		ProductInfoPageObj = searchResPageObj.selectProduct(productName);
		int actProductImagesCount =  ProductInfoPageObj.getProductImagesCount();
		Assert.assertEquals(actProductImagesCount, expProductImagesCount);
		System.out.println("image count is matching with expected");
	}
	
	
	@Test
	public void productInfoTest()
	{
		searchResPageObj = accPageObj.doSearch("MacBook");
		ProductInfoPageObj = searchResPageObj.selectProduct("MacBook Pro");
		Map<String, String> productActualData =ProductInfoPageObj.getProductData();
		System.out.println(productActualData);
		softAssert.assertEquals(productActualData.get("Brand"), "Apple");
		softAssert.assertEquals(productActualData.get("Availability"), "In Stock");
		softAssert.assertEquals(productActualData.get("productheader"), "MacBook Pro");
		softAssert.assertEquals(productActualData.get("price"), "$2,000.00");
		softAssert.assertEquals(productActualData.get("Reward Points"), "800");
		softAssert.assertAll();      //imp to write
	}
}
