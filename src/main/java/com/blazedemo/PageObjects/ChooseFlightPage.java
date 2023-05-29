package com.blazedemo.PageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class ChooseFlightPage {
	WebDriver driver;
	public ChooseFlightPage(WebDriver driver) {
		this.driver=driver;
	    PageFactory.initElements(driver,this );
	}
	
	@FindBy(xpath="//tbody/tr/td[6]")
	List<WebElement> allflightprice;
    @FindBy(xpath="//input[@value='Choose This Flight']")
	List<WebElement> allChooseFlightbutton;
    @FindBy(xpath="//tbody//tr")
    List<WebElement> allRows;
   
	public List<WebElement> getallChoosetheFlightbuttons() {
		
		return allflightprice;
	}
	public List<WebElement> getallflightprice() {
		return allflightprice;
	}
	public PurchaseFlightPage selectLowestPricedFlight() {
		int rowcount=allRows.size();
		ArrayList<Double> pricelist= new ArrayList<Double>();
    	for(int i=1;i<=rowcount;i++) 
    	{
    	  String fp="//tbody//tr[";
      	  String lp="]//td[6]";
    	  String p=driver.findElement(By.xpath(fp+i+lp)).getText().replace("$","");
    	  Double price=Double.parseDouble(p);
    	  pricelist.add(price);
    	}
    	Collections.sort(pricelist);
    	double minprice=pricelist.get(0);
    	System.out.println(minprice);
    	for(int i=1;i<=rowcount;i++) 
    	{
    	  String fp="//tbody//tr[";
    	  String lp="]//td[6]";
    	  String p=driver.findElement(By.xpath(fp+i+lp)).getText().replace("$","");
    	  double price1=Double.parseDouble(p);
    	  if(price1==minprice)
    	  {
    		  driver.findElement(By.xpath(fp+i+lp+"/preceding-sibling::td/input[@value='Choose This Flight']")).click();
    		  break;
    	  }
    	  
    	  }
    	  return new PurchaseFlightPage(driver);
    	}
}
