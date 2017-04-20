package com.wellevate.Validation;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.base.BasePage;

public class ForgotPasswordPage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public ForgotPasswordPage(WebDriver driver) {
		ForgotPasswordPage.driver = driver;
	}

	// Forgot Password link
	public WebElement forgotpasswordlink() {
		return getElementfluent(By.xpath("//a[@ui-sref='forgot-password']"));
	}

	// Forgot Password
	public WebElement forgotpasswordForm() {
		return getElementfluent(By.xpath("//form[@name='forgotPasswordForm']"));
	}

	// Forgot Password
	public WebElement forgotpasswordFormLink() {
		return getElementfluent(By.xpath("html/body/div[3]/div/div/div/form/div[2]/button"));
	}

	// RESRT PASSWORD
	public WebElement resetPasswordLink() {
		return getElementfluent(By.xpath("//button[@type='submit']"));
	}

	// Please verify your email address
	public WebElement verifyEmailAddressMsg() {
		return getElementfluent(By.xpath("//*[@id='mainViewOverflow']/div/div/div/legend"));
	}

	// Succesfully sent email msg
	public WebElement succesfullyMsgSent() {
		return getElementfluent(By.xpath("//*[@id='mainViewOverflow']/div/div/div/p[1]"));
	}

	// Profession Popup
	public String resetPassword() {
		String Popup = null;
		try {
			int i = driver.findElements(By.xpath("//button[@type='submit']")).size();
			if (i > 0) {
				System.out.println("Reset Password is displayed");
				Popup = "true";
			} else {
				System.out.println("Reset Password is not displayed");
				Popup = "false";
			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("l  op");
		}
		return Popup;
	}
}
