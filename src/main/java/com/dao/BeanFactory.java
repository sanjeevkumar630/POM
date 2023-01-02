package com.dao;

import org.apache.commons.lang3.RandomStringUtils;

public class BeanFactory {

	public void setDataForCreateNewUser(CreateUserBeen createUserBeen) {

		createUserBeen.setEmailAddress(RandomStringUtils.randomAlphabetic(7) + "@gmail.com");
		createUserBeen.setFirstName(RandomStringUtils.randomAlphabetic(5));
		createUserBeen.setLastName(RandomStringUtils.randomAlphabetic(5));
		createUserBeen.setPassword("sanjeev@123");
		createUserBeen.setAddress(RandomStringUtils.randomAlphabetic(5));
		createUserBeen.setCity(RandomStringUtils.randomAlphabetic(5));
		createUserBeen.setState("2");
		createUserBeen.setZipCode("00000");
		createUserBeen.setPhoneNumber(RandomStringUtils.randomNumeric(10));
		createUserBeen.setAlias(RandomStringUtils.randomAlphabetic(5));

	}
	public void setDataForEditUserDetails(PersonalInformationBean personalInformationBean) {
		personalInformationBean.setFirstName(RandomStringUtils.randomAlphabetic(5));
		personalInformationBean.setLastName(RandomStringUtils.randomAlphabetic(5));
		personalInformationBean.setPassword("sanjeev@123");
	}

}
