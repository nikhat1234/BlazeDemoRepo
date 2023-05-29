package com.blazedemo.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.blazedemo.ActionDrivers.Action;
public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//div[@class='container']/h1")
	WebElement homepagHeading;
	@FindBy(linkText="destination of the week! The Beach!")
	WebElement destinationweekLink;
	@FindBy(xpath="//select[@name='fromPort']")
	WebElement departureDropDown;
	@FindBy(xpath="//select[@name='toPort']")
	WebElement destinationDropDown ;
	@FindBy(xpath="//input[@value='Find Flights']")
	 WebElement findFlightbutton;
	public String gethomepagHeading() {
		return homepagHeading.getText();
	}
	public DestinationWeekPage clickOnDestinationWeekLink()
	{
		Action.click(driver, destinationweekLink);
		return new DestinationWeekPage(driver);
	}
	public void selectDepartureCity(String value) 
	{
		Select s= new Select(departureDropDown);
		s.selectByVisibleText(value);
		
	}
	public void selectDestinationCity(String val) 
	{
        Select s= new Select(destinationDropDown);
		s.selectByVisibleText(val);
	}
	public ChooseFlightPage clickOnFindFlightButton() throws Throwable{
		Action.click(driver,findFlightbutton);
		return new ChooseFlightPage(driver);
	}
	
}
