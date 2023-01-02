package com.framework;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.runtime.CucumberException;

public class ActionsUtil  {

	private WebDriver driver ;//= BaseUtil.testUtilThread.get().getDriver();
	private Import report; //BaseUtil.testUtilThread.
	public ActionsUtil(WebDriver driver, Import report) {
		this.driver = driver;
		this.report = report;
		
	}
	
	int counter=1;

//************************ Frames Related Functions ***********************************************************************
    //		    private  WebDriver jsWaitDriver;
    private WebDriverWait jsWait;
    private JavascriptExecutor jsExec;

    /********************************Driver Related Functions**********************************************/
    public static void killChromeDriver() {
        try {
            Runtime.getRuntime().exec("TASKKILL /F /IM chrome.exe");
            Runtime.getRuntime().exec("TASKKILL /F /IM chromedriver.exe");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    

    public int getTotalFrames(WebDriver driver) {

        return driver.findElements(By.tagName("frame")).size();

    }

    public int getTotaliFrames(WebDriver driver) {

        return driver.findElements(By.tagName("iframe")).size();

    }

    public void switchByName(String frameName, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));

    }

    public void switchByLocator(By locator, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

    }


//************************** Window Related Functions *********************************************************************

    public void switchByIndex(int frameNumber, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNumber));

    }

    public void switchByFrameElement(WebElement element, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

    }

    public void switchToParentFrame(WebDriver driver) {
        driver.switchTo().parentFrame();
    }

    public void exit(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public int getTotalWindows(WebDriver driver) {
        return driver.getWindowHandles().size();
    }

    public void switchToNewWindow(WebDriver driver) {
        Set<String> allwindows = driver.getWindowHandles();
        for (String tempWindow : allwindows) {
            driver.switchTo().window(tempWindow);
        }

    }

    public boolean switchByTitle(String title) {
        boolean switchStatus = false;
        Set<String> allWindows = driver.getWindowHandles();
        try {
            for (String id : allWindows) {
                driver.switchTo().window(id);
                if (driver.getTitle().equals(title)) {
                    switchStatus = true;
                    break;
                }
            }
        } catch (NoSuchWindowException exp) {
            exp.printStackTrace();
        }
        return switchStatus;
    }

    public void switchById(String windowID) {
        driver.switchTo().window(windowID);
    }

    public boolean switchByIndex(int windowNumber) {
        boolean switchStatus = false;
        Set<String> allWindows = driver.getWindowHandles();
        int counter = 0;
        try {
            for (String id : allWindows) {
                counter++;
                if (counter == windowNumber) {
                    driver.switchTo().window(id);
                    switchStatus = true;
                    break;
                }
            }
        } catch (NoSuchWindowException exp) {
            exp.printStackTrace();
        }
        return switchStatus;
    }

    public boolean switchByPartialTitle(String partialTitle) {

        boolean switchStatus = false;
        String CurrentTitle = driver.getTitle();
        Set<String> allWindows = driver.getWindowHandles();
        try {
            for (String id : allWindows) {
                driver.switchTo().window(id);
                if (driver.getTitle().contains(CurrentTitle)) {
                    switchStatus = true;
                    break;
                }
            }
        } catch (NoSuchWindowException exp) {
            exp.printStackTrace();
        }
        return switchStatus;

    }

    public boolean switchByElement(By locator) {
        boolean switchStatus = false;
        Set<String> allWindows = driver.getWindowHandles();
        try {
            for (String id : allWindows) {
                driver.switchTo().window(id);
                if (waitForElementLocate(locator, 15)) {
                    switchStatus = true;
                    break;
                }
            }
        } catch (NoSuchWindowException exp) {
            exp.printStackTrace();
        }
        return switchStatus;

    }

    public boolean closeByTitle(String title) {
        boolean closeStatus = false;
        String CurrentTitle = driver.getTitle();
        Set<String> allWindows = driver.getWindowHandles();
        try {
            for (String id : allWindows) {
                driver.switchTo().window(id);
                if (driver.getTitle().equals(CurrentTitle)) {
                    driver.close();
                    closeStatus = true;
                    break;
                }
            }
        } catch (NoSuchWindowException exp) {
            exp.printStackTrace();
        }
        return closeStatus;


    }

//**************************************** Wait Related Functions *******************************************

    public boolean closeByIndex(int windowNumber) {
        boolean closeStatus = false;
        Set<String> allWindows = driver.getWindowHandles();
        int counter = 0;
        try {
            for (String id : allWindows) {
                counter++;
                if (counter == windowNumber) {
                    driver.switchTo().window(id);
                    driver.close();
                    closeStatus = true;
                    break;
                }
            }
        } catch (NoSuchWindowException exp) {
            exp.printStackTrace();
        }
        return closeStatus;

    }

    public void closeAllPopup(WebDriver driver) {
        String mainWindow = driver.getWindowHandle();
        Set<String> allwindowds = driver.getWindowHandles();
        for (String tempWindowId : allwindowds) {

            driver.switchTo().window(tempWindowId);
            if (!driver.getWindowHandle().equals(mainWindow)) {
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
    }

    public void waitForNumberOfWindows(int toatalWindows, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        wait.until(ExpectedConditions.numberOfWindowsToBe(toatalWindows));

    }

    public boolean waitForWindowTitle(String title, int sec) {
        boolean status = false;
        int counter = 0;
        while (sec > counter) {
            Set<String> allWindows = driver.getWindowHandles();
            for (String string : allWindows) {
                driver.switchTo().window(string);
                if (driver.getTitle().equals(title) || driver.getTitle().contains(title) ) {
                    status = true;
                    break;
                }
            }
            if (status)
                break;
            else
                sleep(2000);
            counter++;
        }

        return status;
    }

    public void waitForAlertMessage(int sec) {
        int counter = 0;
        while (driver.findElement(By.xpath("//div[@class='cdk-overlay-container']")).getText().equals("")) {
            counter++;
            sleep(1000);
            System.out.println("waitForAlertMessage:" + driver.findElement(By.xpath("//div[@class='cdk-overlay-container']")).getText());
            if (counter > sec)
                break;
        }
    }

    public boolean waitForTextVisible(String text, int sec) {
        boolean status = false;
        int counter = 0;
        text = text.toLowerCase();
        try {
            while (counter < sec) {
                if (getPageText().toLowerCase().contains(text)) {
                    status = true;
                    break;
                }
                sleep(1000);
                counter++;
            }
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    public void waitForLoading(WebDriver driver, long waitTime) {
        System.out.println("Spinner wait start...");

        int counter = 0;
        int attemptCounter = 0;
        boolean spinnerStop = false;
        while (true) {
            sleep(500);
            if (!driver.getPageSource().contains("Loading...") ||
                    !driver.findElement(By.tagName("body")).getText().contains("Loading...")) {
                spinnerStop = true;
                break;
            }
            if (counter > waitTime) {
                break;
            }
            counter++;
            System.out.println("Wait for page loading......");
        }
        if (attemptCounter == 2)
            throw new EOFException("Application is down unable to continue");

        if (!spinnerStop && attemptCounter < 2) {
            attemptCounter++;
            waitForLoading(driver, waitTime);
        }


        System.out.println("Spinner wait stop...");
    }

    public boolean waitForAlert(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitForElementLocate(By locator, long seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public WebElement waitForElement(final By by, int sec) {

        WebElement element = (new WebDriverWait(driver, sec)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver newdriver) {
                return newdriver.findElement(by);
            }
        });
        return element;
    }

    public WebElement fluentWait(final By by, int timeOutSec, int pollSec) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeOutSec))
                .pollingEvery(Duration.ofSeconds(pollSec))
                .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
        return element;
    }

    public boolean waitForElementClick(WebElement webElement, int sec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, sec);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitForElementPresent(By by, int sec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, sec);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void sleep(int mSeconds) {
        try {
            Thread.sleep(mSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean waitForElementVisbible(final By by, int sec) {
        boolean elementVisible = (new WebDriverWait(driver, sec)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver newdriver) {
                return newdriver.findElement(by).isDisplayed();
            }
        });
        return elementVisible;

    }

    public boolean waitForElementVisbible(WebDriver driver, final WebElement element, int sec) {
        boolean elementVisible = (new WebDriverWait(driver, sec)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver newdriver) {
                return element.isDisplayed();
            }
        });
        return elementVisible;

    }

    public void WaitForAllWindows(int toatalWindows, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        wait.until(ExpectedConditions.numberOfWindowsToBe(toatalWindows));

    }

    public boolean waitForTitleContains(String titlePattern, int sec) {
        try {
            return (new WebDriverWait(driver, sec)).until(titleContains(titlePattern));
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean waitForTitleNotContains(String titlePattern, int sec) {
        try {
            return (new WebDriverWait(driver, sec)).until(titleNotContains(titlePattern));
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean waitForURLContains(String urlPattern, int sec) {
        try {
            return (new WebDriverWait(driver, sec)).until(urlContains(urlPattern));
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean waitForURLNotContains(String urlPattern, int sec) {
        try {
            return (new WebDriverWait(driver, sec)).until(urlNotContains(urlPattern));
        } catch (WebDriverException e) {
            return false;
        }
    }

    private ExpectedCondition<Boolean> titleContains(final String title) {
        return new ExpectedCondition<Boolean>() {
            private String currentTitle = null;

            public Boolean apply(WebDriver driver) {
                try {
                    currentTitle = driver.getTitle();
                    return contains(currentTitle, title);
                } catch (WebDriverException e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String.format("Title pattern is '%s', current title is '%s'", title, currentTitle);
            }
        };
    }

    private ExpectedCondition<Boolean> titleNotContains(final String title) {
        return new ExpectedCondition<Boolean>() {
            private String currentTitle = null;

            public Boolean apply(WebDriver driver) {
                try {
                    currentTitle = driver.getTitle();
                    return !title.equals(currentTitle) && !contains(currentTitle, title);
                } catch (WebDriverException e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String.format("Title pattern is '%s', current title is '%s'", title, currentTitle);
            }
        };
    }

    private ExpectedCondition<Boolean> urlContains(final String url) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = null;

            public Boolean apply(WebDriver driver) {
                currentUrl = driver.getCurrentUrl();
                return contains(currentUrl, url);
            }

            @Override
            public String toString() {
                return String.format("Url Pattern is '%s', current url is '%s'", url, currentUrl);
            }
        };
    }


//	 ******************************** WebTable Related Functions **********************************************

    private ExpectedCondition<Boolean> urlNotContains(final String url) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = null;

            public Boolean apply(WebDriver driver) {
                currentUrl = driver.getCurrentUrl();
                return !contains(currentUrl, url);
            }

            @Override
            public String toString() {
                return String.format("Url Pattern is '%s', current url is '%s'", url, currentUrl);
            }
        };
    }

    private boolean contains(String source, String... strings) {
        if (StringUtils.isBlank(source)) {
            return false;
        }
        for (String s : strings) {
            if (!source.toUpperCase().contains(s.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    public boolean waitForPageChange(int sec) {
        String currentURL = driver.getCurrentUrl();
        return waitForURLNotContains(currentURL, sec);
    }

//    public boolean waitForPageToLoad(long timeOutInSeconds) {
//
//        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver driver) {
//                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
//            }
//        };
//        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
//        return wait.until(expectation);
//    }

    public List<WebElement> getAllRows(By locator) {

        WebElement table = waitForElement(locator, 10);
        return table.findElements(By.xpath("//tbody/tr"));
    }

    public List<WebElement> getRowAllColumns(By locator, int rowNumber) {
        WebElement table = waitForElement(locator, 10);
        return table.findElements(By.xpath("//tbody/tr" + rowNumber + "/td"));
    }

    public List<WebElement> getTableHeaders(By locator) {

        WebElement table = waitForElement(locator, 10);
        return table.findElements(By.xpath("//thead/th"));
    }

    public WebElement getRow(By locator, int rowNumber) {

        WebElement table = waitForElement(locator, 10);
        return table.findElement(By.xpath("//tbody/tr[" + rowNumber + "]"));
    }

    public WebElement getColumn(By locator, int rowNumber, int columnNumber) {

        WebElement table = waitForElement(locator, 10);
        return table.findElement(By.xpath("//tbody/tr[" + rowNumber + "]/td[" + columnNumber + "]"));
    }

    public String getRowText(By locator, int rowNumber) {

        WebElement table = waitForElement(locator, 10);
        return table.findElement(By.xpath("//tbody/tr[" + rowNumber + "]")).getText().trim();
    }

    public String getColumnText(By locator, int rowNumber, int columnNumber) {

        WebElement table = waitForElement(locator, 10);
        return table.findElement(By.xpath("//tbody/tr[" + rowNumber + "]/td[" + columnNumber + "]")).getText().trim();
    }

    public List<WebElement> getAllRows(WebElement table) {
        return table.findElements(By.xpath("//tbody/tr"));
    }

    public List<WebElement> getRowAllColumns(WebElement table, int rowNumber) {
        return table.findElements(By.xpath("//tbody/tr" + rowNumber + "/td"));
    }

    public List<WebElement> getTableHeaders(WebElement table) {
        return table.findElements(By.xpath("//thead/th"));
    }

    public WebElement getRow(WebElement table, int rowNumber) {
        return table.findElement(By.xpath("//tbody/tr[" + rowNumber + "]"));
    }

    public WebElement getColumn(WebElement table, int rowNumber, int columnNumber) {
        return table.findElement(By.xpath("//tbody/tr[" + rowNumber + "]/td[" + columnNumber + "]"));
    }


//	 ****************** Common Functions ***************************

    public String getRowText(WebElement table, int rowNumber) {
        return table.findElement(By.xpath("//tbody/tr[" + rowNumber + "]")).getText().trim();
    }

    public String getColumnText(WebElement table, int rowNumber, int columnNumber) {
        return table.findElement(By.xpath("//tbody/tr[" + rowNumber + "]/td[" + columnNumber + "]")).getText().trim();
    }

    public WebElement getRow(WebElement table, String rowText) {
        List<WebElement> allRowEle = getAllRows(table);
        for (WebElement tempRow : allRowEle) {
            String tempRowText = tempRow.getText();
            if (tempRowText.contains(rowText)) {
                return tempRow;
            }
        }

        return null;
    }

    //********************* ALERT Related Functions ***********************************************
    public boolean isAlertPresent(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method will scroll the page by 250 pixels every 2 seconds for as
     * long as {@code numberOfSeconds}.
     *
     * @param numberOfSeconds the duration in seconds to scroll
     */
    public void scrollDownContinuously(WebDriver driver,final int numberOfSeconds) {
        for (int i = 0; i < numberOfSeconds; i += 2) {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollBy(0,250);");

        }
    }

    /**
     * This method will scroll the page by 250 pixels every 2 seconds for as
     * long as {@code numberOfSeconds}.
     *
     * @param numberOfSeconds the duration in seconds to scroll
     */
    public void scrollUpContinuously(WebDriver c,final int numberOfSeconds) {
        for (int i = 0; i < numberOfSeconds; i += 2) {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollBy(0,-250);");

        }
    }

    public void scrollPageDown(WebDriver driver) {
        try {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("window.scrollBy(0,1000)");
        } catch (Exception exp) {
//			 exp.printStackTrace();
        }
    }

    public void scrollPageUp(WebDriver driver) {
        try {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("window.scrollBy(0,-600)");
        } catch (Exception exp) {
//			 exp.printStackTrace();
        }


    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
    }

    public String getPageText() {
        return driver.findElement(By.tagName("body")).getText().trim();
        // using javascript executor ------- document.body.innerText

    }

    public void enterText(WebDriver driver, WebElement element, String text, String label) throws Exception {
        try {
                new Actions(driver).moveToElement(element).build().perform();
                highlightElement(element);
                element.click();
                sleep(100);
                element.clear();
//                sleep(100);
                element.sendKeys(text);
//                sleep(200);
                String enteredText = element.getAttribute("value").trim();
                if (enteredText.equals(text))
                    report.log(LogStatus.INFO, "<b>" + text + "</b> is entered in <b>" + label + "</b> Textbox");
                else
                    report.log(LogStatus.FAIL_SCREENSHOT, "<b>" + text + "</b> is failed to entered in <b>" + label + "</b> Textbox");
        } catch (Exception e1) {
            report.log(LogStatus.FAIL_SCREENSHOT, SuiteUtil.convertExceptionAsString(e1));
           // throw new CucumberException(e1);
        }
    }

    public void enterPassword(WebDriver driver, WebElement element, String text) {
        try {
            new Actions(driver).moveToElement(element).build().perform();
            highlightElement(element);
            element.click();
            element.clear();
            element.sendKeys(text);
            String enteredText = element.getAttribute("value").trim();
            if (enteredText.equals(text))
                report.log(LogStatus.INFO, "Password <b>********</b> is entered in <b>Password</b> Field");
            else
                report.log(LogStatus.FAIL_SCREENSHOT, "Failed to entered in <b>Password</b> Field");
        } catch (Exception e1) {
            report.log(LogStatus.FAIL_SCREENSHOT, "Failed to entered in <b>Password</b> Field");
        }
    }

    public void clickByJavaScript(WebElement element, String eleType, String label) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            //Report.updateTestLog(LogStatus.PASS_SCREENSHOT, "Click on <b>"+label+"</b> "+eleType+"");
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
//	 			Report.updateTestLog(LogStatus.ERROR, "Failed to Click on <b>"+label+"</b> "+eleType+"");
            System.out.println("Failed to Click on <b>" + label + " " + eleType + "");
        }
    }

    public void enterByJavaScript(WebElement element,String label,String value) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].setAttribute('value', '" + value +"');", element);
        } catch (Exception e) {
        	report.log(LogStatus.FAIL_SCREENSHOT, SuiteUtil.convertExceptionAsString(e));
        }
    }
    
    public void click(WebDriver driver, WebElement element, String eleType, String label) {
        try {
            waitForElementVisbible(driver, element, 10);
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            highlightElement(element);
            if (eleType.equalsIgnoreCase("Button")) {
                report.log(LogStatus.SCREENSHOT, "Click on " + label + " " + eleType);
                element.click();
            } else {
                element.click();
                report.log(LogStatus.INFO, "Clicked on " + label + " " + eleType);
            }
        } catch (Exception e) {
            report.log(LogStatus.FAIL_SCREENSHOT, "Failed to click on " + label + " " + eleType);
//	 			System.out.println("Failed to Click on <b>"+label+" "+eleType+"");
        }
    }

    public void doubleClick(WebDriver driver, WebElement element, String eleType, String label) {
        try {
            waitForElementVisbible(driver, element, 10);
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            highlightElement(element);
            if (eleType.equalsIgnoreCase("Button")) {
                report.log(LogStatus.SCREENSHOT, "Click on " + label + " " + eleType);
                action.moveToElement(element).doubleClick().build().perform();
            } else {
                action.moveToElement(element).doubleClick().build().perform();
                report.log(LogStatus.INFO, "Clicked on " + label + " " + eleType);
            }
        } catch (Exception e) {
            report.log(LogStatus.FAIL_SCREENSHOT, "Failed to click on " + label + " " + eleType);
//	 			System.out.println("Failed to Click on <b>"+label+" "+eleType+"");
        }
    }

    public void mouseHover(WebDriver driver, WebElement element, String eleType, String label) {
        try {
            waitForElementVisbible(driver, element, 10);
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            highlightElement(element);
            if (eleType.equalsIgnoreCase("Button")) {
                report.log(LogStatus.SCREENSHOT, "Click on " + label + " " + eleType);
            } else {
                report.log(LogStatus.INFO, "Clicked on " + label + " " + eleType);
            }
        } catch (Exception e) {
            report.log(LogStatus.FAIL_SCREENSHOT, "Failed to Hover on " + label + " " + eleType);
//	 			System.out.println("Failed to Click on <b>"+label+" "+eleType+"");
        }
    }



//	************************** ACTIONS REUSABLE FUNCTIONS ************************

    public void datepicker(WebElement datePickerToggle, String date) {
//		  Ex Date format = dd-mm-yyyy
        if (!date.equals("")) {
            String dateArray[] = date.split("-");
            String day = dateArray[0];
            String month = getMonth(dateArray[1]);
            String year = dateArray[2];

            datePickerToggle.click();
            driver.findElement(By.xpath("//*[@aria-label='Choose month and year']")).click();
            WebElement calendarBody = driver.findElement(By.xpath("//div[@class='mat-calendar-content']"));
            while (!calendarBody.getText().contains(dateArray[2])) {
                driver.findElement(By.xpath("//button[@aria-label='Previous 20 years']")).click();
                calendarBody = driver.findElement(By.xpath("//div[@class='mat-calendar-content']"));
            }
            // clicking year in calendar
            WebElement yearElement = driver.findElement(By.xpath("//*[text()='" + year + "']/.."));
            if (!yearElement.getAttribute("class").equals("disabled"))
                yearElement.click();
            else
                throw new EOFException(dateArray[2] + " year is disabled in calendar");
            //clicking Month in calendar
            driver.findElement(By.xpath("//*[text()='" + month + "']/..")).click();

            //clicking day in calendar
            WebElement dateElement = null;
            if (day.startsWith("0")) {
                dateElement = driver.findElement(
                        By.xpath("//div[text()='" + day.substring(1) + "']/.."));
            } else {
                dateElement = driver.findElement(
                        By.xpath("//div[text()='" + day + "']/.."));
            }
            if (!dateElement.getAttribute("class").equals("disabled"))
                dateElement.click();
            else
                throw new FrameworkException(dateArray[0] + " Date is disabled in calendar");
        }


    }

    private String getMonth(String month) {
        switch (month) {
            case "01":
            case "1":
                return "JAN";
            case "02":
            case "2":
                return "FEB";
            case "03":
            case "3":
                return "MAR";
            case "04":
            case "4":
                return "APR";

            case "05":
            case "5":
                return "MAY";
            case "06":
            case "6":
                return "JUN";
            case "07":
            case "7":
                return "JUL";
            case "08":
            case "8":
                return "AUG";
            case "09":
            case "9":
                return "SEP";
            case "10":
                return "OCT";
            case "11":
                return "NOV";
            case "12":
                return "DEC";
            default:
                return "invalid month";
        }
    }

    public void verifyElementIsDisabled(WebElement element, String elementName) {
        if (!element.isEnabled())
//			 Report.updateTestLog(LogStatus.PASS, elementName+" is disabled as expected");
            System.out.println(elementName + " is disabled as expected");
        else
//			 Report.updateTestLog(LogStatus.FAIL_SCREENSHOT, elementName+" is not disabled");
            System.out.println(elementName + " is not disabled");
    }

    public void maximizeBrowser(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public void sendKeys(Keys key) {
        new Actions(driver).sendKeys(key).perform();
    }

    public void moveToElement(WebElement element) {
        new Actions(driver).moveToElement(element).build().perform();
    }

    public void moveToElement(WebElement target, int xOffset, int yOffset) {
        new Actions(driver).moveToElement(target, xOffset, yOffset).perform();
    }

    //Uploading Files by using Robot
    public void uploadFile(String fileLocation) {
        try {
            //Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Thread.sleep(2500);
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_V);
            Thread.sleep(1500);
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void setClipboardData(String string) {
        //StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public void clickBody(WebDriver driver) {
        try {
            driver.findElement(By.tagName("body")).click();
        } catch (Exception e) {
        }
    }

    public String setTimeFromClock(WebElement clockElement, String time) {
        Date dateFormat = null;
        if (!time.toLowerCase().contains("am") && !time.toLowerCase().contains("pm")) {
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            try {
                dateFormat = _24HourSDF.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            try {
                dateFormat = _12HourSDF.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String hour = new SimpleDateFormat("hh").format(dateFormat);
        String minute = new SimpleDateFormat("mm").format(dateFormat);
        String meridian = new SimpleDateFormat("a").format(dateFormat);

        clockElement.click();
        waitForElementVisbible(By.id("time-picker"), 10);
        char ch = hour.charAt(0);
        if (ch == '0')
            hour = String.valueOf(hour.charAt(1));

        ch = minute.charAt(0);
        if (ch == '0')
            minute = String.valueOf(minute.charAt(1));

        WebElement hourToggle = driver.findElement(By.xpath("//div[contains(@class,'time-picker-hour')]"));
        WebElement minitueToggle = driver.findElement(By.xpath("//div[contains(@class,'time-picker-minute')]"));

        // Setting hour in clock
        hourToggle.click();
// selecting hours in clock
        try {
            driver.findElement(By.xpath("//div[@class='time-picker-clock']/button[normalize-space(text())='" + hour + "']")).click();

        } catch (Exception e) {
            List<WebElement> colckElements = driver.findElements(By.xpath("//div[@class='time-picker-clock']/button"));
            for (WebElement tempHour : colckElements) {
                if (tempHour.getText().equals(hour)) {
                    tempHour.click();
                    break;
                }
            }
        }
        // setting minute in clock
        minitueToggle.click();

        try {
            driver.findElement(By.xpath("//div[@class='time-picker-clock']/button[normalize-space(text())='" + minute + "']")).click();
        } catch (Exception e) {

            List<WebElement> colckMinutes = driver.findElements(By.xpath("//div[@class='time-picker-clock']/button"));
            for (WebElement tempMins : colckMinutes) {
                if (tempMins.getText().equals(minute)) {
                    tempMins.click();
                    break;
                }
            }

        }

        if (meridian.toLowerCase().equals("am")) {
            WebElement amToggle = driver.findElement(By.xpath("//div[contains(@class,'time-picker-am')]"));
            amToggle.click();
        } else if (meridian.toLowerCase().equals("pm")) {
            WebElement pmToggle = driver.findElement(By.xpath("//div[contains(@class,'time-picker-pm')]"));
            pmToggle.click();
        } else
            throw new EOFException("Time Formate error: " + time);

        WebElement OK_Button = driver.findElement(By.xpath("//button[text()='Ok']"));
        try {
            OK_Button.click();
        } catch (Exception e) {
            OK_Button = driver.findElement(By.xpath("//button[text()='Ok']"));
            moveToElement(OK_Button);
            waitForElementClick(OK_Button, 10);
            OK_Button.click();
        }
//		 		From time Should be less than To time
        sleep(600);
        String errorMsg = driver.findElement(By.tagName("body")).getText();
        if (errorMsg.contains("From time Should be less than To time"))
            return "Error";
        else
            return "";
    }

    //Get the driver
//		    public AjaxWait (WebDriver driver) {
//		        jsWaitDriver = driver;
//		        
//		        jsExec = (JavascriptExecutor) jsWaitDriver;
//		    }

    public String boldText(String text) {

        return "<b>" + text + " </b>";
    }

    public String getTextboxValue(WebElement textboxEle) {
        String attributeValue = "";
        try {
            attributeValue = textboxEle.getAttribute("value").trim();
        } catch (Exception ex) {
            try {
                attributeValue = String.valueOf(((JavascriptExecutor) driver).executeScript("return arguments[0].value;", textboxEle));

            } catch (Exception e) {
                attributeValue = String.valueOf(((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('value');", textboxEle));

            }
        }
        return attributeValue;
    }

    public void ajaxComplete(WebDriver driver) {
        jsWait = new WebDriverWait(driver, 10);
        jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("var callback = arguments[arguments.length - 1];"
                + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
                + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
                + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
    }

    private void waitForJQueryLoad(WebDriver driver) {
        try {
            jsExec = (JavascriptExecutor) driver;
            ExpectedCondition<Boolean> jQueryLoad = driver1 -> ((Long) ((JavascriptExecutor) driver)
                    .executeScript("return jQuery.active") == 0);

            boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

            if (!jqueryReady) {
                jsWait = new WebDriverWait(driver, 10);
                jsWait.until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitForAngularLoad(WebDriver driver) {
        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        angularLoads(angularReadyScript);
    }

    private void waitUntilJSReady(WebDriver driver) {
        try {
            jsExec = (JavascriptExecutor) driver;
            ExpectedCondition<Boolean> jsLoad = jdriver -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").toString().equals("complete");

            boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");

            if (!jsReady) {
                jsWait = new WebDriverWait(driver, 10);
                jsWait.until(jsLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitUntilJQueryReady(WebDriver driver) {
        jsExec = (JavascriptExecutor) driver;
        Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined) {
            poll(20);

            waitForJQueryLoad(driver);

            poll(20);
        }
    }

    public void waitUntilAngularReady(WebDriver driver) {
        try {
            jsExec = (JavascriptExecutor) driver;
            Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
            if (!angularUnDefined) {
                Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");
                if (!angularInjectorUnDefined) {
                    poll(20);

                    waitForAngularLoad(driver);

                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void waitUntilAngular5Ready(WebDriver driver) {
        try {
            jsExec = (JavascriptExecutor) driver;
            Object angular5Check = jsExec.executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
            if (angular5Check != null) {
                Boolean angularPageLoaded = (Boolean) jsExec.executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
                if (!angularPageLoaded) {
                    poll(20);
                    waitForAngular5Load(driver);
                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
        }

        System.out.println("HTTP Calls Finshed");
    }

    public void waitForAngular5Load(WebDriver driver) {
        String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
        angularLoads(angularReadyScript);
    }

    private void angularLoads(String angularReadyScript) {
        try {
            jsExec = (JavascriptExecutor) driver;
            ExpectedCondition<Boolean> angularLoad = jdriver -> Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript(angularReadyScript).toString());

            boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

            if (!angularReady) {
                jsWait = new WebDriverWait(driver, 10);
                jsWait.until(angularLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void waitAllRequest(WebDriver driver) {
        waitUntilJSReady(driver);
        ajaxComplete(driver);
        waitUntilJQueryReady(driver);
        waitUntilAngularReady(driver);
        waitUntilAngular5Ready(driver);
    }

    /**
     * Method to make sure a specific element has loaded on the page
     *
     * @param by
     * @param expected
     */
    public void waitForElementAreComplete(By by, int expected) {
        ExpectedCondition<Boolean> angularLoad = jdriver -> {
            int loadingElements = driver.findElements(by).size();
            return loadingElements >= expected;
        };
        jsWait.until(angularLoad);
    }

    /**
     * Waits for the elements animation to be completed
     *
     * @param css
     */
    public void waitForAnimationToComplete(WebDriver jsWaitDriver, String css) {
        ExpectedCondition<Boolean> angularLoad = driver -> {
            int loadingElements = jsWaitDriver.findElements(By.cssSelector(css)).size();
            return loadingElements == 0;
        };
        jsWait.until(angularLoad);
    }

    private void poll(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollToVerticalView(WebElement element) {

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollTo(0," + element.getLocation().y + ")");
    }

    public void scrollToHorizantalView(WebElement element) {

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollTo(0," + element.getLocation().x + ")");
    }

    public void highlightElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
            //executor.executeScript("arguments[0].click();" ,element);
            executor.executeScript("arguments[0].setAttribute('style', 'background: light-yellow; border: 3px solid red;');", element);
            sleep(10);
            executor.executeScript("arguments[0].setAttribute('style', 'background: light-yellow; border: 1px solid white;');", element);
//            sleep(10);
//            executor.executeScript("arguments[0].setAttribute('style', 'background: light-yellow; border: 3px solid red;');", element);
//            sleep(10);
//            executor.executeScript("arguments[0].setAttribute('style', 'background: light-yellow; border: 1px solid white;');", element);
//            sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//	public void selectDropdownOption(WebElement dpbx,  String elelabel,String option) {
//		try {
//			highlightElement(dpbx);
//		 new Select(dpbx).selectByVisibleText(option);
//		 reporter.log(LogStatus.INFO, "Select "+option+" option from "+elelabel+" dropdown");
//		}
//		catch(Exception e) {
//			reporter.log(LogStatus.FAIL_SCREENSHOT, "Failed to Select "+option+" option from "+elelabel+" dropdown"+ e.getLocalizedMessage());
//		}
//	}

    public void verifyElement(WebElement ele, String text) {
        if (ele.isDisplayed()) {
            //report.log(LogStatus.PASS_SCREENSHOT, text + " is displayed");
            System.out.println(ele + " is disabled as expected");
        } else {
            //report.log(LogStatus.FAIL_SCREENSHOT, text + " is not displayed");
        	System.out.println(ele + " is not displayed");

        }
    }

    public void checkShowStopper() {
        if (driver.getPageSource().contains("message error")) {
            String ErrorMsg = driver.findElement(By.xpath("//div[@class='message error']")).getText();
            report.log(LogStatus.FAIL_SCREENSHOT, "Application down:" + " \n " + ErrorMsg);
            throw new CucumberException("Application down: \n " + ErrorMsg);
        }
    }

    public void selectDropdownOption(WebElement dpbx, String elelabel, String option) {
        try {
        	
        	if(dpbx.isEnabled())
        	{
//            highlightElement(dpbx);
            Select selectObj = new Select(dpbx);
            if (!option.equalsIgnoreCase("") && !option.equals(null) &&
                    !option.equalsIgnoreCase("NA") && !option.equalsIgnoreCase("NR")) {
            	 dpbx.click();
            	 clickBody();
                selectObj.selectByVisibleText(option);
                String selectedOption = selectObj.getFirstSelectedOption().getText().trim();
                if (selectedOption.equals(option))
                    report.log(LogStatus.INFO, "Select <b>" + option + " </b> option from <b>" + elelabel + "</b> dropdown");
                else
                    report.log(LogStatus.FAIL_SCREENSHOT, "Not Selected <b>" + option + " </b> option from <b>" + elelabel + "</b> dropdown");

            } else {
            	 List<WebElement> allOptions = selectObj.getOptions();
            	 int noOfElements = allOptions.size();
            	 Random rand = new Random();
                 int indexPostition = 0;
                 for (int i = 0; i < 5; i++) {
                	 indexPostition = rand.nextInt(noOfElements);
                	 if(indexPostition!=0)
                		 break;
				}
                 dpbx.click();
                 clickBody();
                 allOptions.get(indexPostition).click();
                System.out.println("selected Option: "+selectObj.getFirstSelectedOption().getText());
                report.log(LogStatus.INFO, "Select <b>" + allOptions.get(indexPostition).getText() + " </b> option from <b>" + elelabel + "</b> dropdown");
               }
            }
        } catch (Exception e) {
            report.log(LogStatus.FAIL_SCREENSHOT, "Failed to Select <b>" + option + " </b>option from <b>" + elelabel + " </b> dropdown" + e.getLocalizedMessage());
        }
    }
public void clickBody() {
	try {
   	 driver.findElement(By.tagName("body")).click();
    }catch(Exception e) {}
}
    public String getElementText(WebElement element) {
        return element.getText();
    }

    public String[] returnColumnNames(By by)
    {
        List<String> columnNames = driver.findElements(by)   // get table headers
                .stream()
                .map(WebElement::getText)        // get the text
                .map(String::trim)               // trim - no space
                .collect(Collectors.toList());   // collect to a list

        columnNames.removeAll(Collections.singleton(null));
        columnNames.removeAll(Collections.singleton(""));
        columnNames.removeAll(Collections.singleton("0"));

        String [] arrayNames=columnNames.toArray(new String[0]);
        return arrayNames;
    }
    
    public String[] returnColumnNames_NoSpace(By by)
    {
        List<String> columnNames = driver.findElements(by)   // get table headers
                .stream()
                .map(WebElement::getText)        // get the text
                .map(String::trim)               // trim - no space
                .collect(Collectors.toList());   // collect to a list

        columnNames.removeAll(Collections.singleton(null));
//        columnNames.removeAll(Collections.singleton(""));
        columnNames.removeAll(Collections.singleton("0"));

        String [] arrayNames=columnNames.toArray(new String[0]);
        return arrayNames;
    }
    
    public String getTableRowCellDataByRowIdentifier(String tableXpath,String columnName,String rowIdentifier)
    {
    	int columnNo=0;
    	String headers[]=returnColumnNames_NoSpace(By.xpath(tableXpath+"//th"));
    	
    	
    	String value="";
    	
    	
    	for(int i=0;i<=headers.length;i++)
    	{
    		if(headers[i].equals(columnName))
    		{
    			columnNo=i+1;
    			break;
    		}
    	}
    	
    	List<WebElement> rows = driver.findElements(By.xpath(tableXpath+"//tbody/tr"));
    	
    	for (int j=0;j<rows.size();j++) {
			if(rows.get(j).getText().contains(rowIdentifier))
			{
				value=driver.findElement(By.xpath(tableXpath+"//tbody/tr["+(j+1)+"]//td["+columnNo+"]")).getText();
				break;
			}
		}
    	
    	return value;
    	
    }

    public List<String> returnColumnNamesByList(By by)
    {
        List<String> columnNames = driver.findElements(by)   // get table headers
                .stream()
                .map(WebElement::getText)        // get the text
                .map(String::trim)               // trim - no space
                .collect(Collectors.toList());   // collect to a list

        columnNames.removeAll(Collections.singleton(null));
        columnNames.removeAll(Collections.singleton(""));
        columnNames.removeAll(Collections.singleton("0"));

        return columnNames;
    }

    public void acceptAlert() {
    	driver.switchTo().alert().accept();
    }

    public void generateAlerts(String value) throws InterruptedException {

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("alert('" + value + "')");
            Thread.sleep(500);
            acceptAlert();
            Thread.sleep(500);
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public int returnIndexPositionOfArray(String[] array,String key)
    {
    	int i=0;
    	int colNum=0;
    	
    	while(i<array.length)
    	{
    		if(array[i].contains(key))
    		{
    			colNum=i;
    			break;
    		}
    		
    		i++;
    	}
    	
    	return colNum;
    }

    public void navigateToURL(String url)
    {
    	  try {
              JavascriptExecutor js = (JavascriptExecutor) driver;
              js.executeScript("window.location.href='" + url + "'");
          }

          catch(Exception ex)
          {
              ex.printStackTrace();
          }
    }
    
    public void logDBReports(String value1,String value2,String fieldName)
    {
    	if(value1.trim().equalsIgnoreCase(value2.trim()))
    	{
    		report.log(LogStatus.PASS, "<b>"+value1+" </b> matched with the database value:<b> "+value2+" for field :<b>"+fieldName+"</b>");
    	}
    	
    	else
    	{
    		report.log(LogStatus.FAIL_SCREENSHOT, "<b>"+value1+" </b> not matched with the database value:<b> "+value2+" for field :<b>"+fieldName+"</b>");
    	}
    }
    
    public void logDBReports(double value1,double value2,String fieldName)
    {
    	if(Double.compare(value1, value2)==0)
    	{
    		report.log(LogStatus.PASS, "<b>"+value1+" </b> matched with the database value:<b> "+value2+" for field :<b>"+fieldName+"</b>");
    	}
    	
    	else
    	{
    		report.log(LogStatus.FAIL_SCREENSHOT, "<b>"+value1+" </b> not matched with the database value:<b> "+value2+" for field :<b>"+fieldName+"</b>");
    	}
    }
    
    public void logDBExtentReports(String value1,String value2,String fieldName)
    {
    	if(value1.trim().equalsIgnoreCase(value2.trim()))
    	{
    		report.log(LogStatus.PASS, "<b>"+value1+" </b> matched with the database value:<b> "+value2+" for field :<b>"+fieldName+"</b>");
    	}
    }
    
    public Boolean isEditable(WebElement element)
    {
    	try
    	{
    		String readOnly=element.getAttribute("disabled");
    		if(readOnly.equalsIgnoreCase("true"))
    		{
    			return true;
    		}
    		
    		else
    		{
    			return false;
    		}
    	}
    	
    	catch(Exception ex)
    	{
    		try
    		{
    			String readOnly=element.getAttribute("readonly");
    			if(readOnly.equalsIgnoreCase("true"))
        		{
        			return true;
        		}
        		
        		else
        		{
        			return false;
        		}
    		}
    		
    		catch(Exception ex1)
    		{
    			return false;
    		}
    	}
    	
    }
    
    public String convertDateFormat(String date) throws ParseException
    {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    	SimpleDateFormat sdf1=new SimpleDateFormat("MM/dd/yyyy");
    	
    	Date d1=sdf.parse(date);
    	
    	return sdf1.format(d1);
    }
    
    public String addDaysToTheCurrentDate(int noOfDaysToBeAdded)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		//Getting current date
		Calendar cal = Calendar.getInstance();
		//Number of Days to add
        cal.add(Calendar.DAY_OF_MONTH, noOfDaysToBeAdded);  
		//Date after adding the days to the current date
		String newDate = sdf.format(cal.getTime());  
		//Returning the new Date after addition of Days to current date
		return newDate;
    }
    
}
