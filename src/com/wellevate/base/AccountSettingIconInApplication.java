package com.wellevate.base;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class AccountSettingIconInApplication extends BasePage {
	static WebElement element;
	static WebDriver driver;
	

	public AccountSettingIconInApplication(WebDriver driver) {
		AccountSettingIconInApplication.driver = driver;

	}

	// Account Setting Icon 
	public WebElement userIcon() {

		return getElementfluent(By.xpath("html/body/div[3]/nav/ul[2]/li[4]/a/div/img"));
      }

	//  Account setting button  
	public WebElement accountSectionButton() {

		return getElementfluent(By.xpath("//a[contains(text(),'Account Settings')]"));
       }

	// Land To Practitioner Account Setting Details Page
	public WebElement accountSetting() throws InterruptedException {

		try {
			elementWait(7000);
			userIcon().click();
			elementWait(5000);
			accountSectionButton().click();
			elementWait(5000);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}
	// Image on top of screen
		public WebElement userIconInPatientPage() {
			return getElementfluent(By.xpath("//img[@class='img-circle']"));
		}
	// Land To Patient Account Setting Details Page
		public WebElement patientAccountSettingDetailsPage() throws InterruptedException {

			try {
				userIconInPatientPage().click();
				elementWait(3000);
				accountSectionButton().click();
				elementWait(3000);
			} catch (NoSuchElementException e) {
				Reporter.log("Fail: Web Element Not Found");
			}
			return element;
		}
}
