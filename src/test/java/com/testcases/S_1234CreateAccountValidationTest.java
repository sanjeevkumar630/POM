package com.testcases;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.dao.BeanFactory;
import com.dao.CreateUserBeen;
import com.dao.PersonalInformationBean;
import com.pages.CreateAccountPage;
import com.pages.LandingPage;
import com.pages.MyAccountPage;
import com.pages.PersonalInformationPage;
import com.pages.SignInPage;
import com.webdriver.utils.TestBase;

@Listeners(com.testcases.Listener123.class)
public class S_1234CreateAccountValidationTest extends TestBase {

	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		driver = intilizeDriver();
		enterUrl(AUTOMATIONURL);

	}

	@Test
	public void createAccountTestCase() {

		LandingPage landingPage = new LandingPage(driver);
		SignInPage signInPage = landingPage.clickSignIn();
		CreateUserBeen createUserBeen = new CreateUserBeen();
		BeanFactory beanFactory = new BeanFactory();
		beanFactory.setDataForCreateNewUser(createUserBeen);
		signInPage.enterEmailAddress(createUserBeen);
		CreateAccountPage createAccountPage = signInPage.clickCreateAccountButton(createUserBeen);
		createAccountPage.createAccount(createUserBeen);
		MyAccountPage myAccountPage = createAccountPage.clickRegister();

		Assert.assertEquals(myAccountPage.getAccountName(),
				createUserBeen.getFirstName() + " " + createUserBeen.getLastName());

	}

	@Test
	public void editAccountTestCase() {
		LandingPage landingPage = new LandingPage(driver);
		SignInPage signInPage = landingPage.clickSignIn();
		CreateUserBeen createUserBeen = new CreateUserBeen();
		BeanFactory beanFactory = new BeanFactory();
		beanFactory.setDataForCreateNewUser(createUserBeen);
		signInPage.enterEmailAddress(createUserBeen);
		CreateAccountPage createAccountPage = signInPage.clickCreateAccountButton(createUserBeen);
		createAccountPage.createAccount(createUserBeen);
		MyAccountPage myAccountPage = createAccountPage.clickRegister();

		PersonalInformationPage personalInformationPage = myAccountPage.clickMyPersonalInformation();
		PersonalInformationBean personalInformationBean = new PersonalInformationBean();
		beanFactory.setDataForEditUserDetails(personalInformationBean);
		personalInformationPage.editFirstName(personalInformationBean);
		personalInformationPage.editLastName(personalInformationBean);
		personalInformationPage.enterCurrentPassword(personalInformationBean);
		personalInformationPage.clickSaveButton();

		Assert.assertEquals(myAccountPage.getAccountName().toLowerCase(),
				personalInformationBean.getFirstName().toLowerCase() + " "
						+ personalInformationBean.getLastName().toLowerCase());
	}

	@AfterMethod
	public void afterMethod() {
		closeBrowser();
	}

}
