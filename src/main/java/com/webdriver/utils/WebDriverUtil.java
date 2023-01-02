package com.webdriver.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtil {
	
	WebDriver driver;
	
	public WebDriverUtil(WebDriver driver) {

		this.driver=driver;
	}
	
	public void click(By prop) {
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(prop));
		driver.findElement(prop).click();
		
	}
	
	public void enterText(By prop,String value) {
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(prop));
		//clearValueFromTextBox(prop);
		driver.findElement(prop).sendKeys(value);
	}
	/*
	 * public void enterText1(WebElement ele1,String value) { new
	 * WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(ele1));
	 * //clearValueFromTextBox(prop); //driver.findElement(prop).sendKeys(value);
	 * ele1.sendKeys(value); }
	 * 
	 * 
	 * public String getText1(WebElement ele) { new WebDriverWait(driver,
	 * 50).until(ExpectedConditions.visibilityOf(ele)); //return
	 * driver.findElement(prop).getText(); return ele.getText(); }
	 */
	 
	
	
	public String getText(By prop) {
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(prop));
		return driver.findElement(prop).getText();
	}
	public void selectValueFromDropDown(By prop,String value) {
		new Select(driver.findElement(prop)).selectByValue(value);
	}

	public void selectValueFromDropDown(By prop,int index) {
		new Select(driver.findElement(prop)).selectByIndex(index);
	}
	public void clearValueFromTextBox(By prop) {
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(prop));
		driver.findElement(prop).clear();
	}
	/*
	 * public void takeScreenshotAtEndOfTest() throws IOException { File scrFile =
	 * ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); String
	 * currentDir = System.getProperty("user.dir"); FileUtils.copyFile(scrFile, new
	 * File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png")); }
	 */

	
	
	

}
