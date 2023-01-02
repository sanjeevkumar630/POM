package com.pages;

import org.openqa.selenium.WebDriver;

import com.dao.CreateUserBeen;
import com.objectrepo.CreateAccountPageProp;
import com.webdriver.utils.WebDriverUtil;

public class CreateAccountPage extends WebDriverUtil implements CreateAccountPageProp {

	WebDriver driver;

	public CreateAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public void enterFirstName(CreateUserBeen createUserBeen) {

		enterText(FIRSTNAME_LOCATOR, createUserBeen.getFirstName());
		

	}

	public void enterLasttName(CreateUserBeen createUserBeen) {

		enterText(LASTNAME_LOCATOR, createUserBeen.getLastName());

	}

	public void enterpassword(CreateUserBeen createUserBeen) {

		enterText(PASSWORD_LOCATOR, createUserBeen.getPassword());

	}

	public void enterAddress(CreateUserBeen createUserBeen) {

		enterText(ADDRESS_LOCATOR, createUserBeen.getAddress());

	}

	public void enterCityName(CreateUserBeen createUserBeen) {

		enterText(CITY_LOCATOR, createUserBeen.getCity());

	}

	public void selectState(CreateUserBeen createUserBeen) {

		selectValueFromDropDown(STATE_LOCATOR, createUserBeen.getState());

	}

	public void enterPostalCode(CreateUserBeen createUserBeen) {

		enterText(POSTALCODE_LOCATOR, createUserBeen.getZipCode());

	}

	public void enterPhoneNumber(CreateUserBeen createUserBeen) {

		enterText(PHONENUMBER_LOCATOR, createUserBeen.getPhoneNumber());

	}

	public void enterAlias(CreateUserBeen createUserBeen) {

		enterText(ALIAS_LOCATOR, createUserBeen.getAlias());

	}

	public void createAccount(CreateUserBeen createUserBeen) {

		enterFirstName(createUserBeen);
		enterLasttName(createUserBeen);
		enterpassword(createUserBeen);
		enterAddress(createUserBeen);
		enterCityName(createUserBeen);
		selectState(createUserBeen);
		enterPostalCode(createUserBeen);
		enterPhoneNumber(createUserBeen);
		enterAlias(createUserBeen);

	}

	public MyAccountPage clickRegister() {

		click(REGISTER_LOCATOR);
		return new MyAccountPage(driver);

	}

}
