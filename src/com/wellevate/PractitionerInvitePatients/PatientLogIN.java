package com.wellevate.PractitionerInvitePatients;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.base.BasePage;

public class PatientLogIN extends BasePage {
	static WebElement element;
	static WebDriver driver;
	WebDriverWait wait;

	public PatientLogIN(WebDriver driver) {
		PatientLogIN.driver = driver;
		wait = new WebDriverWait(driver, 3000);
	}

	// Patient Email text field
	public WebElement patEmail() {
		return getElementfluent(By.name("clientName"));
	}

	// Patient Password text field
	public WebElement patPassword() {
		return getElementfluent(By.name("password"));
	}

	// Practioner Login TextBox
	public WebElement patLogin() {
		return getElementfluent(By.xpath("//button[@type='submit']"));
	}
	// Practioner Login TextBox
		public WebElement patLoginSucessful() {
			return getElementfluent(By.xpath("//section[@class='patient-landing container relative ng-scope']/h1"));
		}
	
}
