package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtils {
	
	private WebDriver driver;
	private Actions act;
	private JavaScriptUtil jsUtil;
	
	public ElementUtils(WebDriver driver)
	{
		this.driver=driver;
		act=new Actions(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public  WebElement getElement(By locator)
	{
		
		//WebElement element=null;
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsUtil.flash(element);
		}
		
		return element;
	}
	
	public List<WebElement> getElements(By Locator)
	{
		List<WebElement> list_ele=driver.findElements(Locator);
		return list_ele;
	}
	
	public  void doSendKeys(By locator, String value)
	{
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	public String doElementGetText(By locator)
	{
		String eleText = getElement(locator).getText();
		System.out.println("Element text is ===> " + eleText);
		return eleText;
	}
	
	public WebElement getLinkEleByText(String linkText)
	{
		return driver.findElement(By.linkText(linkText));
	}
	
	
	public String getAttributeValue(By locator, String value)
	{
		return getElement(locator).getAttribute(value);
	}
	
	public void ClickOnLink(By locator, String text)
	{
		List<WebElement> link_ele = getElements(locator);
		for(WebElement ele:link_ele)
		{
			String Text=ele.getText();
			
			if(Text.equalsIgnoreCase(text))
			{
				System.out.println("link is = "+ Text);
				ele.click();
				break;
			}
			
		}
	}
	
	public void SelectSuggestion(By searchLocator, By suggestionListLocator, String SearchText, String selectText) throws InterruptedException
	{

		doSendKeys(searchLocator, selectText);
		//driver.findElement(searchLocator).sendKeys(SearchText);
		
		Thread.sleep(5000);
		
		List<WebElement> sugg_list=driver.findElements(suggestionListLocator);
		System.out.println("Size of the list = "+ sugg_list.size());
		
		for(WebElement e:sugg_list)
		{
			String text=e.getText();
			if(text.contains(selectText))
			{
				e.click();
				break;
			}
			
		}
	}
	
	
	/*******************************DropDown********************************/
	
	public void doSelectDropDownByIndex(By locator, int index)
	{
		if(index<0)
		{
			System.out.println("Please provide the correct (+ve) index");
			return;
		}
		Select s=new Select(getElement(locator));
		s.selectByIndex(index);
	}
	public void doSelectDropDownByVisibleText(By locator, String selectText)
	{
		if(selectText==null)
		{
			System.out.println("Please provide the correct text and text can't be null");
			return;
		}
		Select s=new Select(getElement(locator));
		s.selectByVisibleText(selectText);
	}
	public void doSelectDropDownByVale(By locator, String valueText)
	{
		if(valueText==null)
		{
			System.out.println("Please provide the correct text and text can't be null");
			return;
		}
		Select s=new Select(getElement(locator));
		s.selectByValue(valueText);
	}
	
	public int getDropDownOptionsCount(By locator)
	{
		Select select=new Select(getElement(locator));
		return select.getOptions().size();
	}
	
	public List<String> getDropDownTextList(By locator)
	{
		Select select=new Select(getElement(locator));
		List<WebElement> optionsList=select.getOptions();
		List<String> listText= new ArrayList<String>();
		for(WebElement option : optionsList)
		{
			String text=option.getText();
			listText.add(text);
		}
		return listText;
	}
	
	public void doSelectDropDownValue(By locator, String dropDownvalue) 
	{
		List<WebElement> optionsList=getElements(locator);
    	
    	for(WebElement op:optionsList)
    	{
    		String text=op.getText();
			System.out.println(text);

    		if(text.equalsIgnoreCase(dropDownvalue))
    		{
    			op.click();
    			break;
    		}
    	}
	}
	
	
	public void doSelectWithoutSelectMethods(By locator, String dropDownvalue) 
	{
    	Select s=new Select(getElement(locator));
    	List<WebElement> optionsList=s.getOptions();
    	for(WebElement op:optionsList)
    	{
    		String text=op.getText();
			System.out.println(text);

    		if(text.equalsIgnoreCase(dropDownvalue))
    		{
    			op.click();
    			break;
    		}
    	}
	}
	
	/*****************************Action Util*************************/
	
	public void selectRightClickOption(By contextMenuLocator, String optionValue)
	{
		act.contextClick(getElement(contextMenuLocator)).perform();
		By optionLocator = By.xpath("//*[text()='"+optionValue+"']");
		getElement(optionLocator).click();
	}
	
	public void multiLevelMenuHandling(By level1MenuLocator, By level2MenuLocator) throws InterruptedException
	{
		act.moveToElement(getElement(level1MenuLocator)).perform();
		Thread.sleep(1500);
		doClick(level2MenuLocator);
	}
	
	public void multiLevelMenuHandling(By level1Locator, String level2, String level3, String level4) throws InterruptedException
	{
		act.click(getElement(level1Locator)).perform();
		Thread.sleep(2000);
		
		act.moveToElement(getLinkEleByText(level2)).perform();
		Thread.sleep(2000);

		act.moveToElement(getLinkEleByText(level3)).perform();
		Thread.sleep(2000);

		getLinkEleByText(level4).click();
	}
	
	//method overloading
	public void multiLevelMenuHandling(By level1Locator, String level2, String level3) throws InterruptedException
	{
		act.click(getElement(level1Locator)).perform();
		Thread.sleep(2000);
		
		act.moveToElement(getLinkEleByText(level2)).perform();
		Thread.sleep(2000);

		getLinkEleByText(level3).click();
		
	}

	public void doActionsClick(By locator)
	{
		act.click(getElement(locator)).perform();
	}
	
	public void doActionsSendkeys(By locator , String value)
	{
		act.sendKeys(getElement(locator), value).perform();
	}
	
	
	//***********************WaitUtils************************//
	
	public WebElement waitForElementPresence(By locator, int timeout)
	{
		WebDriverWait ex_wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return ex_wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementVisible(By firstName, int timeout)
	{
		WebDriverWait ex_wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return ex_wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
	}
	
	
	public String waitForTitleContain(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			} else {
				System.out.println("titan value not present...");
				return null;
			}
		} catch (Exception e) {
			System.out.println("titan value not present...");
			return null;
		}
	}

	public String waitForTitleIs(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleIs(titleValue))) {
				return driver.getTitle();
			} else {
				System.out.println("title value not present...");
				return null;
			}
		} catch (Exception e) {
			System.out.println("title value not present...");
			return null;
		}
	}
	
	
	public  String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			} else {
				System.out.println(urlFraction + "URL value not present...");
				return null;
			}
		} catch (Exception e) {
			System.out.println(urlFraction + "URL value not present...");
			return null;
		}
	}
	
	/**
	 * An expectation for the URL of the current page to be a specific url.
	 * @param urlValue
	 * @param timeOut
	 * @return
	 */
	public  String waitForURLToBe(String urlValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
				return driver.getTitle();
			} else {
				System.out.println(urlValue + "titan value not present...");
				return null;
			}
		} catch (Exception e) {
			System.out.println(urlValue + "titan value not present...");
			return null;
		}
	}

	public Alert waitForAlert(int timeOutvalue)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOutvalue));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public Boolean waitForNumberOfBrowserWindows(int timeout, int numberOfWindowsToBe)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindowsToBe));
	}
	
	public void waitForFrameByLocator(int timeout, By FrameLocator)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameLocator));
	}
	
	public void waitForFrameByIndex(int timeout, int FrameIndex)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameIndex));
	}
	
	public void waitForFrameByNameOrID(int timeout, String NameOrID)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(NameOrID));
	}
	
	public void waitForFrameElement(int timeout,  WebElement FrameElement)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameElement));
	}
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param timeout
	 * @param locator
	 * @return
	 */
	public List<WebElement> waitForElementsPresence(int timeout , By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}
	
	public List<WebElement> waitForElementsVisible(int timeout , By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeout
	 */
	
	public void clickElementWhenReady(By locator , int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	//-------------Fluent wait --------------//
	
	public WebElement waitforElementVisibleWithFluentWait( By locator, int timeout, int pollingTime)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage("------TimeOut is done------Element is not found--------" + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public WebElement waitforElementPresenceWithFluentWait( By locator, int timeout, int pollingTime)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage("------TimeOut is done------Element is not found--------" + locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public Alert waitforJSAlertWithFluentWait( By locator, int timeout, int pollingTime)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoAlertPresentException.class)
				.withMessage("------TimeOut is done------Element is not found--------" + locator);
        return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitForElementAndEnterValue(int timeOut, int pollingTime, By locator, String value)
	{
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait
			.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
					.withMessage("------TimeOut is done------Frame is not found with ID or Name--------"+ locator)
						.until(ExpectedConditions.presenceOfElementLocated(locator))
							.sendKeys(value);
	}

	public void waitForElementAndClick(int timeOut, int pollingTime, By locator)
	{
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait
			.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
					.withMessage("------TimeOut is done------Frame is not found with ID or Name--------"+ locator)
						.until(ExpectedConditions.presenceOfElementLocated(locator))
							.click();
	}
	
	
	//-------------------------------Custom wait----------------------------//
	
	public WebElement retryingElement(int timeout,By locator)
	{
		WebElement element=null;
		int attempts =0;
		
		while(attempts<timeout)
		{
			try {
			element=getElement(locator);
			System.out.println("Element is found " +locator + "in attempts "+ attempts);
			break;
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Element is  not found " +locator + "in attempts "+ attempts);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				attempts++;
			}
		}
		
		if(element==null)
		{
			System.out.println("Element is  not found  tried for " +timeout + " secs with interval of "+ 500+" milli secs" );
		}
		return element;
	}

	
	public WebElement retryingElement(int timeout,By locator, int pollingtime)    //method override with polling variable
	{
		WebElement element=null;
		int attempts =0;
		
		while(attempts<timeout)
		{
			try {
			element=getElement(locator);
			System.out.println("Element is found " +locator + "in attempts "+ attempts);
			break;
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Element is  not found " +locator + "in attempts "+ attempts);
				try {
					Thread.sleep(pollingtime);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				attempts++;
			}
		}
		
		if(element==null)
		{
			System.out.println("Element is  not found  tried for " +timeout + " secs with interval of "+ pollingtime +" milli secs" );
		}
		return element;
	}

	public boolean isPageLoaded(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String flag=wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'")).toString();
		return Boolean.parseBoolean(flag);
	}
}
