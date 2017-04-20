package com.wellevate.base;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class LogoutApplication extends BasePage {
	static WebElement element;
	static WebDriver driver;

	public LogoutApplication(WebDriver driver) {
		LogoutApplication.driver = driver;

	}

	// image on top of screen
	public WebElement userIconPractitioner() {

		return getElementfluent(By.xpath("//div[@class='circular']/img"));
	}

	// image on top of screen
	public WebElement userIconPatient() {
		return getElementfluent(By.xpath("//img[@class='img-circle']"));
	}

	// Logout button Practitioner
	public WebElement LogoutButtonPractititoner() {
		return getElementfluent(By.xpath("//a[contains(text(),' Log out')]"));
	}
	// Logout button patient
		public WebElement LogoutButtonPatient() {
			return getElementfluent(By.xpath("//a[contains(text(),' Log out')]"));
		}
	// Logout Practitioner
	public WebElement LogoutPractitioner() throws InterruptedException {

		try {
			userIconPractitioner().click();
			elementWait(3000);
			LogoutButtonPractititoner().click();
			elementWait(3000);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// Logout Patient
	public WebElement LogoutPatient() throws InterruptedException {

		try {
			userIconPatient().click();
		    elementWait(3000);
			LogoutButtonPractititoner().click();
			elementWait(3000);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}
}
