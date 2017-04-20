package com.wellevate.PractitionerAccount;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.wellevate.base.BasePage;

public class ConnectWithStripeObject extends BasePage {

	static WebElement element;
	static WebDriver driver;

	public ConnectWithStripeObject(WebDriver driver) {
		ConnectWithStripeObject.driver = driver;
		}
	
	// Skip Stripe
	public WebElement Skipstripe() {
		return getElementfluent(By.xpath("//a[@id='skip-account-app']"));
	}
  // Refresh The Page
	public void refreshPageafterWindowClosed() {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			driver.navigate().refresh();
			// Actions actions = new Actions(driver);
			// actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}