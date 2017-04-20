package com.wellevate.Validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wellevate.base.BasePage;

public class LoginValidationObject extends BasePage {

	static WebElement element;
	static WebDriver driver;

	public LoginValidationObject(WebDriver driver) {
		LoginValidationObject.driver = driver;

	}

	// Login Email Address Validation Message for no Text
	public WebElement loginEmailNoTextVald() {
		return getElementfluent(By.xpath("//div[@ng-messages='loginForm.email.$error']/span"));
	}

	// Login Email Address Validation Message for InValid Email Format
	public WebElement loginEmailValdEmailFormat() {
		return getElementfluent(By.xpath("//span[contains(text(),'Please enter a valid email address.')]"));
	}

	// Login Password Validation Message for InValid Email Format
	public WebElement loginPasswordInValdFormat() {
		return getElementfluent(
				By.xpath("//span[contains(text(),'Please enter a valid password with a minimum of 8 characters')]"));
	}

	// Login Validation Message for InValid Combination Format
	public WebElement invalidCredentialLogin() {
		return getElementfluent(By.cssSelector(".toast-message"));

	}
}
