package com.blazedemo.Base;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	 WebDriver driver;
	 public Properties prop;
	 Logger log;
	 @BeforeMethod
	 public void setConfig()
	 {
		 log=LogManager.getLogger(BaseClass.class);  
		 PropertyConfigurator.configure("log4j.properties");
		 
		 prop=new Properties();
		   File propfile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\blazedemo\\confi\\config.properties"); 
		   try
		   {
		   FileInputStream fis=new FileInputStream(propfile);
		   prop.load(fis);
		   }
		   catch(Throwable e)
		   {
			   e.printStackTrace();
		   }
	 }
	
	 public WebDriver initialiseBrowserAndOpenApp(String browsername)
	   {
		 if(browsername.equalsIgnoreCase("chrome"))
			{
				driver =new ChromeDriver();
			}
			else if(browsername.equalsIgnoreCase("firefox"))
			{
				driver= new FirefoxDriver();
			}
			else if(browsername.equalsIgnoreCase("edge"))
			{
				driver= new EdgeDriver();
			}
		    driver.get(prop.getProperty("Appurl"));
		    return driver;
       }
	
	 public void doSelectByVisibleText(String name)
	    {
			WebElement dd=driver.findElement(By.xpath("//select[@class='fromPort']"));
			Select ss= new Select(dd);
			List<WebElement> list=ss.getOptions();
			for(int i=0;i<list.size();i++)
			{
				String city=list.get(i).getText();
				if(city.equals(name))
				{
					list.get(i).click();
				}
			}
		}
}
