package com.wellevate.PractitionerAccount;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wellevate.base.BasePage;

public class EmailPrefrencesPage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public EmailPrefrencesPage(WebDriver driver) {
		EmailPrefrencesPage.driver = driver;
	}

	// Visit your dashboard button
	public WebElement emailNotificationPatientsReg() {
		return getElementfluent(By.xpath(
				"//md-checkbox[@aria-label='Turn off an email notification from a new patient registration']/div/div[@class='md-icon']"));
	}
}
