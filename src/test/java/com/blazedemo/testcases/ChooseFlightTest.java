package com.blazedemo.testcases;

import java.time.Duration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.blazedemo.Base.BaseClass;
import com.blazedemo.PageObjects.ChooseFlightPage;
import com.blazedemo.PageObjects.HomePage;
import com.blazedemo.PageObjects.PurchaseFlightPage;

public class ChooseFlightTest extends BaseClass {
	public WebDriver driver;
	HomePage hp;
	ChooseFlightPage cf;
	PurchaseFlightPage pf;
	Logger log;
	public ChooseFlightTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws Throwable
	{
		log=LogManager.getLogger(ChooseFlightTest.class);
		driver= initialiseBrowserAndOpenApp(prop.getProperty("browser"));
		driver.manage().window().maximize();
		hp=new HomePage(driver);
		//Reporter.log("Entered into home page",true);
		log.info("Entered into home page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		hp.selectDepartureCity(prop.getProperty("departurecityname"));
		hp.selectDestinationCity(prop.getProperty("destinationcityname"));
		//Reporter.log("Entered Destination and Departure cities",true);
		log.info("Entered Destination and Departure cities");
		cf=hp.clickOnFindFlightButton();
		//Reporter.log("Reached into choose flight page",true);
		log.info("Entered into choose flight page");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		pf=cf.selectLowestPricedFlight();
    	log.info("Clicked on choose flight button");
		}
    @AfterMethod
	public void tearDown() 
    {
    	driver.quit();
    }
    @Test(priority=1)
    public void validatePurchasePage() {  
    	//Reporter.log("Clicked on choose flight button",true);
    	Reporter.log("try to find out heading of purchase flight page",true);
    	String r=pf.headingOfPurchaseFlightPageDisplayed();
    	Assert.assertEquals(r,"Your flight from TLV to SFO has been reserved.");
    	Reporter.log("heading matched in purchase flight page",true);
    	log.info("heading matched in purchase flight page");
}
}
