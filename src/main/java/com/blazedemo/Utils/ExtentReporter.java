package com.blazedemo.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
    public static ExtentReports generateExtentReport() {
	ExtentReports extentreporter= new ExtentReports(); 
	
	File extentfile =new File(".//test-output/ExtentReports/extentReport.html");
	//ExtentSparkReporter extentspark= new ExtentSparkReporter("ExtentReport.html");
	ExtentSparkReporter extentspark= new ExtentSparkReporter(extentfile);
	extentspark.config().setTheme(Theme.DARK);
	extentspark.config().setReportName("BlazeDemo Automation Test Results");
	extentspark.config().setDocumentTitle("BD Automation Report");
	extentspark.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
	
	extentreporter.attachReporter(extentspark);
	
	Properties props=new Properties();
	File propFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\blazedemo\\confi\\config.properties");
	try {
	FileInputStream file=new FileInputStream(propFile);
	props.load(file);}
	catch(Throwable e) {
		e.printStackTrace();}
	
	
	extentreporter.setSystemInfo("AplicationURL",props.getProperty("Appurl"));
	extentreporter.setSystemInfo("BrowserName",props.getProperty("browser"));
	extentreporter.setSystemInfo("OS Version", System.getProperty("os.version"));
	extentreporter.setSystemInfo("OS Name",System.getProperty("os.name"));
	extentreporter.setSystemInfo("Java version",System.getProperty("java.version"));

	return extentreporter;
}
}