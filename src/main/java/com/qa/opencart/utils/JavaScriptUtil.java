package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavaScriptUtil(WebDriver driver)
	{
		this.driver=driver;
		js=(JavascriptExecutor)this.driver;
	}
	
	public String getTitleByJS()
	{
		return js.executeScript("return document.title").toString();
	}
	
	public void goBackwardwithJS()
	{
		js.executeScript("history.go(-1)");
	}
	
	public void goForwardwithJS()
	{
		js.executeScript("history.go(1)");
	}
	
	public void pageRefreshwithJS()
	{
		js.executeScript("history.go(0)");
	}

	public void generateJSAlert(String msg) throws InterruptedException
	{
		js.executeScript("alert('"+msg+"')");
		Thread.sleep(500);
		driver.switchTo().alert().accept();
	}
	
	public void generateJSConfirm(String msg) throws InterruptedException
	{
		js.executeScript("confirm('"+msg+"')");
		Thread.sleep(500);
		driver.switchTo().alert().accept();
	}
	
	public void generateJSPrompt(String msg, String text) throws InterruptedException
	{
		js.executeScript("prompt('"+msg+"')");
		Thread.sleep(1500);
		driver.switchTo().alert().sendKeys(text);
		driver.switchTo().alert().accept();
	}
	
	public String getPageInnerText()
	{
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	
	public void scrollPageDown()
	{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollPageDown(String height)
	{
		js.executeScript("window.scrollTo(0, "+height+")");
	}
	
	public void scrollPageUp()
	{
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	
	public void scrollPageDownMiddlePage()
	{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
	}
	
	public void zoomWithJS_chromeEdgeSafari(String percentage)
	{
		js.executeScript("document.body.style.zoom='"+percentage+"'");
	}
	
	public void drawBorderwithJS(WebElement element)
	{
		js.executeScript("arguments[0].style.border='3px solid red'" , element);
	}
	
	public void flash(WebElement element)
	{
		String bgcolor = element.getCssValue("background color");
		for(int i =0; i<10; i++)
		{
			changeColor("rgb(0,200,0)" , element);
			changeColor(bgcolor , element);
		}
	}
	
	private void changeColor(String color, WebElement element)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'",  element);
	
		try
		{
			Thread.sleep(20);
		}
		catch(InterruptedException e)
		{}
	}
}
