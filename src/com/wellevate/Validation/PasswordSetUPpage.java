package com.wellevate.Validation;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wellevate.base.BasePage;

public class PasswordSetUPpage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public PasswordSetUPpage(WebDriver driver) {
		PasswordSetUPpage.driver = driver;
	}

	// Current Password
	public WebElement prCurrentPassword() {
		return getElementfluent1(By.xpath("//input[@placeholder='CURRENT PASSWORD']"));

	}

	// New Password
	public WebElement prNewPassword() {
		return getElementfluent1(By.xpath("//input[@placeholder='NEW PASSWORD']"));
	}

	// Confirm password
	public WebElement prConfirmPassword() {
		return getElementfluent1(By.xpath("//input[@placeholder='CONFIRM NEW PASSWORD']"));

	}

	// New Pasword error msg
	public WebElement prNewPasswordErrormsg() {
		return getElementfluent1(By.xpath("//input[@placeholder='NEW PASSWORD']/following-sibling::div[1]"));
	}

	// New Pasword error msg
	public WebElement prCurrentPasswordErrorMsg() {
		return getElementfluent1(By.xpath("//input[@placeholder='CURRENT PASSWORD']/following-sibling::div[1]/span"));

	}

	// Confirm Password error msg
	public WebElement prConfirmPasswordErrorMsg() {
		return getElementfluent1(By.xpath("//span[@ng-message='compareTo']"));
	}

	// Submit button in password
	public WebElement prSubmitPassword() {
		return getElementfluent1(By.xpath("//button[@type='submit']"));
	}

	// Submit button in password
	public WebElement prSuccesfullyUpdate() {
		return getElementfluent1(By.xpath("//*[@id='toast-container']/div/div/div[2]"));
	}

}
