package com.blazedemo.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class PurchaseConfirmationPage{
	WebDriver driver;

	public PurchaseConfirmationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
    
    @FindBy(xpath="//div[@class='container hero-unit']/h1")
    WebElement confirmationPageHeading;
    
    @FindBy(xpath="//tbody/tr[1]/td[2]")
    WebElement id;
    
    
    public String getConfirmationPageHeading() 
    {
    	return confirmationPageHeading.getText();
    }
    
    public String getgeneratedId()
    {
    	String idgen =id.getText();
    	//int intid=Integer.parseInt(idgen);
    	return idgen;
    }
}
