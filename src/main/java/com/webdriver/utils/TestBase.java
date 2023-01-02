package com.webdriver.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;



// Testbase is for only driver initialization

public class TestBase implements SetUp {

	WebDriver driver;
	public static EventFiringWebDriver e_driver;
	public static WebDriverEventListener eventListener;

	public WebDriver intilizeDriver()
	{
		System.setProperty(CHROMEKEY, CHROMEPATH);
		driver=new ChromeDriver();
		
		/*
		 * e_driver = new EventFiringWebDriver(driver); // Now create object of
		 * EventListerHandler to register it with EventFiringWebDriver eventListener
		 * =new WebEventListener(); e_driver.register(eventListener); driver = e_driver;
		 */
		 
		driver.manage().window().maximize();
		return driver;
	}

	public void enterUrl(String url) {
		driver.get(url);
	}

	public void closeBrowser() {
		driver.quit();
	}

}
