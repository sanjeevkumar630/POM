package com.pages;

import org.openqa.selenium.WebDriver;

import com.dao.CreateUserBeen;
import com.objectrepo.signInPageProp;
import com.webdriver.utils.WebDriverUtil;

public class SignInPage extends WebDriverUtil implements signInPageProp {
	WebDriver driver;

	public SignInPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void enterEmailAddress(CreateUserBeen createUserBeen) {
		enterText(EMAILADDRESS_LOCATOR, createUserBeen.getEmailAddress());
	}
	
	public CreateAccountPage clickCreateAccountButton(CreateUserBeen createUserBeen) {
		click(CREATEANACCOUNT_LOCATOR);
		return new CreateAccountPage(driver);
	}

}
