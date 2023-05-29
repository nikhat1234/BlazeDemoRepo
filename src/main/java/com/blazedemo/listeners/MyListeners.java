package com.blazedemo.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.blazedemo.Utils.ExtentReporter;
import com.blazedemo.Utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentreport;
	ExtentTest extentTest;
	@Override
	public void onTestStart(ITestResult result) {
	   
	   String testname=result.getName();
		extentTest = extentreport.createTest(testname);
		extentTest.log(Status.INFO,testname+"executing successfully");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname=result.getName();
		extentTest.log(Status.PASS,testname+"got successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		String testname=result.getName();
		WebDriver driver=null;
		try {
		driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());}
		catch(Throwable e) {
			e.printStackTrace();}
	   //File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//String destinatnScreenshotpath=".//ExtentSS/ExtentScreenshots"+testname+".png";
		String destinatnScreenshotpath=Utilities.captureScreeshotTestFailure(driver, testname);
		
		extentTest.addScreenCaptureFromPath(destinatnScreenshotpath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,testname+"got failed");
}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname=result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,testname+"got skipped");
		}
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of project starts");
		extentreport = ExtentReporter.generateExtentReport();
		}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
	}

}
