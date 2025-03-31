package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup()
	{
		accPageObj = loginPageObj.doLogin(prop.getProperty("username") , prop.getProperty("password"));
	}
	
	
	@Test
	public void accPageTitleTest()
	{
		String actAccPageTitle= accPageObj.getAccPageTitle();
		Assert.assertEquals(actAccPageTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void logoutLinkExistTest()
	{
//		Boolean actualResult = accPageObj.isLogoutLinkExist();
//	    Assert.assertTrue(actualResult, "Yes,,Forgot Link Exist");
		Assert.assertTrue(accPageObj.isLogoutLinkExist());
	}
	
	
	@Test
	public void accPageHeadersCountTest()
	{
		int actAccPageHeaderCount = accPageObj.getAccountsPageHeaderCount();
		System.out.println("Actual Account page header count = " + actAccPageHeaderCount);
		Assert.assertEquals(actAccPageHeaderCount, AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	
	@Test
	public void accPageHeadersTest()
	{
		List<String> actAccPageHeadersList = accPageObj.getAccountsPageHeader();
		Assert.assertEquals(actAccPageHeadersList, AppConstants.EXPECTED_ACC_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getSearchKey()
	{
		return new Object[][]
				{
			{"MacBook", 3},
			{"iMac", 1},
			{"Samsung",2}
				};
	}
	
	
	@Test(dataProvider = "getSearchKey")
	public void searchTest(String searchKey , int expProductCount)
	{
		searchResPageObj = accPageObj.doSearch(searchKey);
		int actualResultsCount = searchResPageObj.getSearchResultsCount();
		Assert.assertEquals(actualResultsCount, expProductCount);
	}
}
