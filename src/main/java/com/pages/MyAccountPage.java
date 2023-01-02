package com.pages;

import org.openqa.selenium.WebDriver;

import com.objectrepo.MyAccountPageProp;
import com.webdriver.utils.WebDriverUtil;

public class MyAccountPage extends WebDriverUtil implements MyAccountPageProp{
	
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public String getAccountName() {
		return getText(ACCOUNTNAME_LOCATOR);
	}
	
	public PersonalInformationPage clickMyPersonalInformation() {
		click(PEROSNALINFO_LOCATOR);
		return new PersonalInformationPage(driver);
	}

}
