package com.wellevate.PractitionerAccount;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.wellevate.base.BasePage;

public class PractitionerPatientLoginObject extends BasePage {

	static WebElement element;
	static WebDriver driver;
	WebDriverWait wait;

	public PractitionerPatientLoginObject(WebDriver driver) {
		PractitionerPatientLoginObject.driver = driver;
		wait = new WebDriverWait(driver, 3000);

	}

	// professional Certification
	public WebElement pracLoginButton() {

		return getElementfluent(By.xpath("//a[@id='login-dialog']"));
	}

	// Login In Text displayed
	public WebElement loginText() {

		return getElementfluent(By.xpath("//h2[contains(text(),'Log In')]"));
	}

	// Welcome Text displayed
	public WebElement welcomeText() {
		return getElementfluent(By.xpath("//h1[contains(text(),'Welcome')]"));
	}

	// Email TextBox
	public WebElement prEmail() {
		return getElementeExplicit(By.name("email"));
	}
	// Password TextBox
		public WebElement patEmail() {
			return getElementeExplicit(By.name("clientName"));
		}

	// Password TextBox
	public WebElement prPassword() {
		return getElementeExplicit(By.name("password"));
	}

	// Practioner Login TextBox
	public WebElement prLogin() {
		
		return getElementfluent(By.xpath("//button[contains(text(),'Log in')]"));
	}

	// Password TextBox
	public WebElement logoApp() {
		return getElementfluent(By.xpath("//*[@id='navbar-wsnyc']/ul[1]/li/a/img"));

	}

	// CLOSE
	public WebElement loginClose() {
		return getElementfluent(By.xpath("//button[@class='close']"));
	}
	// Patients login
		public WebElement loginPatient() {
			return getElementfluent(By.xpath("//button[@type='submit']"));
		}

	// Login Wellevate

	public void loginWellevate(String email, String password) {

		try {

			prEmail().sendKeys(email);
			prPassword().sendKeys(password);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
