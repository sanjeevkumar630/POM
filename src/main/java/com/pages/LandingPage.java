package com.pages;

import org.openqa.selenium.WebDriver;

import com.objectrepo.LandingPageProp;
import com.webdriver.utils.WebDriverUtil;

public class LandingPage extends WebDriverUtil implements LandingPageProp {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public SignInPage clickSignIn() {
		click(SIGNIN_LOCATOR);
		return new SignInPage(driver);
	}

}
