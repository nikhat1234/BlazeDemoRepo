package com.blazedemo.ActionDrivers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
public class Action {
	
	  public static boolean findElement(WebDriver ldriver, WebElement ele) {
		boolean flag=false;
		try {
			ele.isDisplayed();
			flag=true;
		}
		catch(Exception e)
		{
			flag=false;
		}
		finally {
			if(flag)
			{
				System.out.println("Successfully found Element");
			}
			else
			{
				System.out.println("unable to locate Element");
			}
			}
		return flag;
		}
	
	public static boolean selectByVisibleText(String visibleText, WebElement ele) {
		boolean flag=false;
		try {
			Select s= new Select(ele);
			s.selectByVisibleText(visibleText);
			flag=true;
			return true;
		   }
		catch (Exception e) {
			return false;

		}
		finally {
			if(flag)
			{
				System.out.println("option selected by visibleText");
			}
			else {
				System.out.println("option not selected by visibleText");
			}
		}
		}
	public static void click(WebDriver ldriver, WebElement locatorname) {
		Actions act =new Actions(ldriver);
		act.moveToElement(locatorname).click().build().perform();
		}
	public static boolean isDisplayed(WebDriver ldriver,WebElement ele) {
		boolean flag=false;
		flag=findElement(ldriver,ele);
		if(flag)
		{
		   flag=ele.isDisplayed();
		if(flag) {
			     System.out.println("Element is Displayed");
		      }
		else {
			System.out.println("Element is not Displayed");
		}
		}
		else
		{
			System.out.println("Not Displayed");
		}
        return flag;
        }
	public static void jsclick(WebDriver driver, WebElement locatorname) throws Throwable{
		boolean flag=false;
		try {
		JavascriptExecutor js=(JavascriptExecutor) driver; 
		js.executeScript("argument[0].click()",locatorname );
		flag=true;
		}
			catch (Exception e){
				throw e;
			}
			finally {
				if(flag) {
					System.out.println("option selected by visibleText");
				}
				else {
					System.out.println("option not selected by visibleText");
				}	
				}
			}
			{
	}
			public static void type() {
				
			}
	}

