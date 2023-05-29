package com.blazedemo.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities 
{
	
	public static String generateTimeStamp()
	{	
		Date d = new Date();
		String timestamp=d.toString().replace(" ", "_").replace(":", "_");
		return timestamp;
	}
	public static void captureScreeshotTest(WebDriver driver) throws IOException {
		File PurchaseConfirm=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(PurchaseConfirm,new File(".//Screenshots/confirmID"+Utilities.generateTimeStamp()+".jpg"));
		}
	
	public static String captureScreeshotTestFailure(WebDriver driver, String testname) {
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinatnScreenshotpath=".//ExtentSS/ExtentScreenshots"+testname+".png";
		try {
			FileHandler.copy(srcScreenshot,new File(destinatnScreenshotpath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinatnScreenshotpath;
	}
	
}
