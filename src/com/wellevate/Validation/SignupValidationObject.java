package com.wellevate.Validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wellevate.base.BasePage;

public class SignupValidationObject extends BasePage {

	static WebElement element;
	static WebDriver driver;

	public SignupValidationObject(WebDriver driver) {
		SignupValidationObject.driver = driver;
     }
	// Login Validation Message for FirstName
	public WebElement firstNameValidation() {
		return getElementfluent(By.xpath("//input[@name='firstname']//ancestor::div/div/span"));
	}

	// Login Validation Message for Last Name
	public WebElement lastNameValidation() {
		return getElementfluent(By.xpath("//input[@name='lastname']//ancestor::div/div/span"));
	}

	// Login Validation Message Email
	public WebElement emailIdValidation() {
		return getElementfluent(By.xpath("//input[@name='email']//ancestor::div/div/span"));
	}
	// Login Validation Message Email
		public WebElement emailIdValidation1() {
			return getElementfluent(By.xpath("//div[@class='toast-message']"));
		}
	// Login Validation Message Password
	public WebElement passwordValidation() {
		return getElementfluent(By.xpath("//input[@name='password']//following::div[1]"));
	}

	// Login Validation Message Confirm Password
	public WebElement confirmPasswordValidation() {
		return getElementfluent(By.xpath("//input[@name='confirmPassword']//ancestor::div/div/span"));
	}

	// Signup Validation Message for Already Exit Account
	public WebElement invalidCredentialLogin() {
		return getElementfluent(By.xpath("//div[@class='toast-message']"));
		
	}
}
