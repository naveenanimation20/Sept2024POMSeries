package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Description;

//@Listeners(ChainTestListener.class)
public class BaseTest {

	WebDriver driver;
	DriverFactory df;

	protected Properties prop;

	protected CommonsPage commonsPage;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;

	@Parameters({"browser"})
	@BeforeTest(description = "setup: init the driver and properties")
	public void setup(String browserName) {

		df = new DriverFactory();
		prop = df.initProp();
		
			if(browserName != null) {
				prop.setProperty("browser", browserName);
			}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		commonsPage = new CommonsPage(driver);
		
        ChainPluginService.getInstance().addSystemInfo("Build#", "1.0");
        ChainPluginService.getInstance().addSystemInfo("Headless#", prop.getProperty("headless"));
        ChainPluginService.getInstance().addSystemInfo("Incognito#", prop.getProperty("incognito"));
        ChainPluginService.getInstance().addSystemInfo("Owner#", "Naveen Automation Labs");

	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {//only for failuer test cases
			//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
			//ChainTestListener.embed(DriverFactory.getScreenshotByte(), "image/png");
			ChainTestListener.embed(DriverFactory.getScreenshotBase64(), "image/png");
		}
	}
	
	@AfterTest(description = "closing the browser...")
	public void tearDown() {
		driver.quit();
	}

}

