package com.blazedemo.testcases;

import java.time.Duration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.blazedemo.Base.BaseClass;
import com.blazedemo.PageObjects.ChooseFlightPage;
import com.blazedemo.PageObjects.HomePage;
import com.blazedemo.PageObjects.PurchaseConfirmationPage;
import com.blazedemo.PageObjects.PurchaseFlightPage;
import com.blazedemo.Utils.Utilities;

public class PurchaseConfirmationTest extends BaseClass{
public WebDriver driver;
HomePage hp;
ChooseFlightPage cf;
PurchaseFlightPage pf;
Logger log;
PurchaseConfirmationPage pc;
public PurchaseConfirmationTest() {
	super();
}
    @AfterMethod
     public void teardown()
     {
	  driver.quit();
     }
	
	@Test(priority=1)
	public void purchaseConfirmation() throws Throwable
	{
		log=LogManager.getLogger(PurchaseConfirmationTest.class);
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
		pc=pf.clickOnPurchaseFlightbutton();
	    log.info("clicked on purchaseFlight button");
    	String actHeading=pc.getConfirmationPageHeading();
     	log.info("Got Heading of purchase confirmation Page");
   		String expectedHeading="Thank you for your purchase today!";
   		Assert.assertEquals(actHeading,expectedHeading);
   		log.info("Heading Matched of purchase confirmation Page");
   		//int idNumber=pc.getgeneratedId();
   		
		System.out.println(pc.getgeneratedId()); 
		log.info("Got generated ID number");
		Utilities.captureScreeshotTest(driver);
		//File PurchaseConfirm=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(PurchaseConfirm,new File(".//Screenshots/confirmID"+Utilities.generateTimeStamp()+".jpg"));
	}

}
