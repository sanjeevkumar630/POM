package com.objectrepo;

import org.openqa.selenium.By;

public interface PersonalInformationPageProp {
	
	By FIRSTNAME_LOCATOR=By.id("firstname");
	By LASTNAME_LOCATOR=By.id("lastname");
	By CURRENTPASSWORD_LOCATOR=By.id("old_passwd");
	By SAVEBUTTON_LOCATOR=By.name("submitIdentity");

}
