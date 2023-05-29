package com.blazedemo.testcases;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.blazedemo.Base.BaseClass;
import com.blazedemo.PageObjects.ChooseFlightPage;
import com.blazedemo.PageObjects.HomePage;
import com.blazedemo.PageObjects.PurchaseConfirmationPage;
import com.blazedemo.PageObjects.PurchaseFlightPage;

public class PurchaseFlightTest extends BaseClass 
{
	public WebDriver driver;
	HomePage hp;
	ChooseFlightPage cf;
	PurchaseFlightPage pf;
	PurchaseConfirmationPage pc;
	Logger log;
	public PurchaseFlightTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws Throwable
	{
		log=Logger.getLogger(PurchaseFlightTest.class);
		driver= initialiseBrowserAndOpenApp(prop.getProperty("browser"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		hp=new HomePage(driver);
		log.info("Entered into home page");
		hp.selectDepartureCity(prop.getProperty("departurecityname"));
		hp.selectDestinationCity(prop.getProperty("destinationcityname"));
		log.info("Entered Destination and Departure cities");
		cf=hp.clickOnFindFlightButton();
		log.info("Entered into choose flight page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		pf=cf.selectLowestPricedFlight();
		log.info("Clicked on choose this flight button");	
	}
    @AfterMethod
	public void tearDown() 
    {
    	driver.quit();
    }

@Test(priority=1)
public void validateTotalAmount() throws Throwable {
	boolean v=pf.totalCostFieldPresent();
	log.info("Entered into purchase Flight page");
	log.info("Got total cost field");
	log.info("Verified TotalCost Field");
    Assert.assertTrue(v);
    boolean w=pf.amountMatchesformat();
    log.info("amount matched the format");
    Assert.assertTrue(w);
}
@Test(priority=2)
public void validatePurchaseConfirmationPage() throws Throwable {    
	    pc=pf.clickOnPurchaseFlightbutton();
	    log.info("clicked on purchaseFlight button");
    	String actualHeading=pc.getConfirmationPageHeading();
    	log.info("Entered into purchaseonfirmation Page");
    	log.info("Got Heading of purchase confirmation Page");
   		String expectedHeading="Thank you for your purchase today!";
   		Assert.assertEquals(actualHeading,expectedHeading);
   		log.info("Heading of purchase confirmation Page matched");
}
}