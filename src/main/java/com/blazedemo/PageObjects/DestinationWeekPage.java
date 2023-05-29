package com.blazedemo.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.blazedemo.ActionDrivers.Action;

public class DestinationWeekPage {
	WebDriver driver;
	
	public DestinationWeekPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
    @FindBy(xpath="//div[normalize-space()='Destination of the week: Hawaii !']")
	WebElement DestinationWeekPageHeading;
	
	public String getCurrentURL() 
	{
		return driver.getCurrentUrl();
	}
	public boolean checkDestinationPageHeadingPresent() {
		return Action.isDisplayed(driver,DestinationWeekPageHeading);
	}
}
