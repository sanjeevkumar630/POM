package com.pages;

import org.openqa.selenium.WebDriver;

import com.dao.PersonalInformationBean;
import com.objectrepo.PersonalInformationPageProp;
import com.webdriver.utils.WebDriverUtil;

public class PersonalInformationPage extends WebDriverUtil implements PersonalInformationPageProp {
	WebDriver driver;

	public PersonalInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public void editFirstName(PersonalInformationBean personalInformationBean) {
		
		enterText(FIRSTNAME_LOCATOR, personalInformationBean.getFirstName());
	}
	
	public void editLastName(PersonalInformationBean personalInformationBean) {
		enterText(LASTNAME_LOCATOR, personalInformationBean.getLastName());
	}
	
	public void enterCurrentPassword(PersonalInformationBean personalInformationBean) {
		enterText(CURRENTPASSWORD_LOCATOR, personalInformationBean.getPassword());
	}
	
	public MyAccountPage clickSaveButton() {
		click(SAVEBUTTON_LOCATOR);
		return new MyAccountPage(driver);
		
	}

}
