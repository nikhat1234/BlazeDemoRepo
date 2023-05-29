package com.blazedemo.testcases;
import java.time.Duration;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.blazedemo.Base.BaseClass;
import com.blazedemo.PageObjects.DestinationWeekPage;
import com.blazedemo.PageObjects.HomePage;

public class HomeTest extends BaseClass {
	public WebDriver driver;
	HomePage hp;
	DestinationWeekPage dw;
	Logger log;
	public HomeTest() 
	{
		super();
	}
	@BeforeMethod
	public void setup()
	{
		driver= initialiseBrowserAndOpenApp(prop.getProperty("browser"));
		driver.manage().window().maximize();
	}
	 
	@AfterMethod
	public void teardown()
	{
		 driver.quit();
	}
	
	@Test(priority=1)
	public void verifyHeading()
	{
		//log=Logger.getLogger(Home.class);
		hp=new HomePage(driver);
		Reporter.log("Logged in",true);
		//log.info("Getting Heading");
		String actualTitle = hp.gethomepagHeading();
		//log.info("Got Heading");
		Reporter.log("Got Heading",true);
	    String expectedTitle=prop.getProperty("HomepageHeading");
	    Assert.assertEquals(actualTitle,expectedTitle );
	    //log.info("Heading Matched");
	    Reporter.log("Heading Matched",true);
	   }
    @Test(priority=2)
	public void destinationOfTheWeeklink()
    { 
     
   	hp=new HomePage(driver);
    dw=hp.clickOnDestinationWeekLink();
    Reporter.log("Entered into destination page",true);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    Assert.assertTrue(dw.checkDestinationPageHeadingPresent());
    Reporter.log("Checked heading of destination page",true);
    String actualURL=dw.getCurrentURL();
	String ExpectedURL="https://blazedemo.com/vacation.html";
	Assert.assertEquals(actualURL,ExpectedURL);
	Reporter.log("URL of destination page matched",true);
	boolean eurl=actualURL.contains("vacation");
	Assert.assertTrue(eurl);
	Reporter.log("Heading contains vacation",true);
	driver.navigate().back();
	Reporter.log("navigated back to home page successfully",true);
    }
    @Test(priority=3)
    public void findFlight() throws Throwable 
    {
    	hp=new HomePage(driver);
    	Reporter.log("Entered into home page",true);
    	hp.selectDepartureCity(prop.getProperty("departurecityname"));
		hp.selectDestinationCity(prop.getProperty("destinationcityname"));
		Reporter.log("Entered Destination and Departure cities",true);
		hp.clickOnFindFlightButton();
		Reporter.log("Reached into choose flight page",true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		boolean heading=driver.findElement(By.xpath("//div[@class='container']/h3")).isDisplayed();
		Reporter.log("Got heading of choose flight page",true);
		Assert.assertTrue(heading,"Choose Flight Page Heading not Matched");
		Reporter.log("Heading matched of choose flight page",true);
    }
}
