package com.wellevate.Validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wellevate.base.BasePage;

public class PatientsSignUpValidation extends BasePage {
	static WebElement element;
	static WebDriver driver;

	public PatientsSignUpValidation(WebDriver driver) {
		PatientsSignUpValidation.driver = driver;
     }
	// Login Validation Message for FirstName
	public WebElement firstNameValidation() {
		return getElementfluent(By.xpath("//input[@name='firstname']/following-sibling::div[@role='alert']/span"));
	}
	// Login Validation Message for FirstName
		public WebElement firstNameValidation2Charcter() {
			return getElementfluent(By.xpath("//input[@name='firstname']/following-sibling::div[@role='alert']/div"));
		}

	// Login Validation Message for Last Name
	public WebElement lastNameValidation() {
		return getElementfluent(By.xpath("//input[@name='lastname']/following-sibling::div[@role='alert']/span"));
	}

	// Login Validation Message Email
	public WebElement emailIdValidation() {
		return getElementfluent(By.xpath("//input[@name='email']/following-sibling::div[@role='alert']/span"));
	}
	// Login Validation Message Password
	public WebElement passwordValidation() {
		return getElementfluent(By.xpath("//input[@name='password']/following-sibling::div[@class='hint error-hint']"));
	}

	// Login Validation Message Confirm Password
	public WebElement confirmPasswordValidation() {
		return getElementfluent(By.xpath("//input[@name='passwordConfirm']/following-sibling::div[@ng-messages='registrationForm.passwordConfirm.$error']/span"));
	}

	// Login Validation Message Confirm Password
	public WebElement confirmPasswordValidation1() {
		return getElementfluent(By.xpath("//input[@name='passwordConfirm']/following-sibling::div[@ng-messages='registrationForm.passwordConfirm.$error']/div"));
	}
	// Signup Validation Message for Already Exit Account
	public WebElement invalidCredentialLogin() {
		return getElementfluent(By.cssSelector(".toast-message"));
		
	}
}
