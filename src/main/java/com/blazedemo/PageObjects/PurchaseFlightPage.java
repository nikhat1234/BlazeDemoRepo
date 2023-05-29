package com.blazedemo.PageObjects;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class PurchaseFlightPage{
	WebDriver driver;
	Properties prop;
	public PurchaseFlightPage(WebDriver driver)
	{
		this.driver=driver;
	    PageFactory.initElements(driver,this );
	}
	
	@FindBy(xpath="//div[@class='container']/p[5]")
	WebElement totalCost;
	@FindBy(xpath="//div[@class='navbar navbar-inverse']/following-sibling::div/p[5]/em")
	WebElement amount;
	@FindBy(xpath="//input[@type='submit']")
	WebElement purchaseFlightButton;
	@FindBy(xpath="//div[@class='container']/h2")
	WebElement heading;
	public boolean totalCostFieldPresent() {
		return totalCost.isDisplayed();
	}
	public boolean amountMatchesformat() {
		return amount.getText().matches("[0-9]{3}[/.][0-9]{2}");
	}
	public PurchaseConfirmationPage clickOnPurchaseFlightbutton()
	{
		purchaseFlightButton.click();
		return new PurchaseConfirmationPage(driver);
	}
	public String headingOfPurchaseFlightPageDisplayed() {
		return heading.getText();
	}
	
}
