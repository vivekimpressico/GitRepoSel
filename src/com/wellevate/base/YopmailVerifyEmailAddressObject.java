package com.wellevate.base;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class YopmailVerifyEmailAddressObject extends BasePage {

	static WebElement element;
	static WebDriver driver;

	public YopmailVerifyEmailAddressObject(WebDriver driver) {
		YopmailVerifyEmailAddressObject.driver = driver;
	}

	// yopmail Email address Field
	public WebElement emailTextField() {

		return getElementeExplicit(By.xpath("//input[@id='login']"));
	}

	// yopmail Email address Field
	public WebElement submitEmail() {

		return getElementfluent(By.xpath("//input[@type='submit']"));
	}

	// ========================= practioner Verify Their email
	// =============================
	// Confomation email for practioner
	public List<WebElement> practionerConfrmationEmail() {
		List<WebElement> elementlist = null;
		try {
			WebElement fr = driver.findElement(By.id("ifinbox"));
			driver.switchTo().frame(fr);
			elementlist = driver.findElements(By.xpath("//span[contains(text(),'Email Verification')]"));
			elementWait(5000);
			elementlist.get(0).click();
			elementWait(5000);
			driver.switchTo().defaultContent();

		} catch (Exception e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return elementlist;
	}

	// verify email address for practioner
	public WebElement practionerVerifyEmailButton() throws InterruptedException {
		element = null;
		try {
			WebElement fr = driver.findElement(By.id("ifmail"));
			driver.switchTo().frame(fr);
			element = driver.findElement(By.xpath("//*[@id='mailmillieu']//table//tr/td/table[4]/tbody//table//td/a"));
			element.click();
			elementWait(5000);
			driver.switchTo().defaultContent();
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}
	// ================ patient verify if they get recommendation mail or not
	// ===============================

	// Confomation email for patient recommendation
	public List<WebElement> patientRecommendation() {
		List<WebElement> elementlist = null;
		try {
			WebElement fr = driver.findElement(By.id("ifinbox"));

			driver.switchTo().frame(fr);
			elementlist = driver.findElements(By.xpath("//span[contains(text(),'Your New Recommendation')]"));
			elementlist.get(0).click();
			elementWait(5000);
			driver.switchTo().defaultContent();
			elementWait(5000);

		} catch (Exception e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return elementlist;

	}

	// view recommendation for patents
	public WebElement patientsRecommendationEmailButton() throws InterruptedException {
		element = null;
		try {
			WebElement fr = driver.findElement(By.id("ifmail"));
			driver.switchTo().frame(fr);
			element = driver.findElement(By.xpath("//a[contains(text(),'View My Recommendation')]"));
			element.click();
			elementWait(5000);
			driver.switchTo().defaultContent();
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// ============= Patients Verify Their
	// Account========================================================
	// Confirmation email for patient Account
	public List<WebElement> invitationOfNewPatients() {
		List<WebElement> elementlist = null;
		try {
			WebElement fr = driver.findElement(By.id("ifinbox"));
			driver.switchTo().frame(fr);
			elementlist = driver.findElements(By.xpath("//span[contains(text(),'Invitation to Wellevate')]"));
			elementlist.get(0).click();
			elementWait(5000);
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return elementlist;

	}

	// Patients Verify their Own Account For Sign Up
	public WebElement verifyInvitationOfNewPatients() throws InterruptedException {
		element = null;
		try {
			WebElement fr = driver.findElement(By.id("ifmail"));
			driver.switchTo().frame(fr);
			element = driver.findElement(By.xpath("//a[contains(text(),'Activate your account')]"));
			element.click();
			implicitywait(1000);
			driver.switchTo().defaultContent();
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}
	// ================ Patient Submit Their Order Verification Email
	// =====================================================================

	// Patients order submit mail
	public List<WebElement> patientsOrderVerfication() throws InterruptedException {
		List<WebElement> elementlist = null;
		try {
			WebElement fr = driver.findElement(By.id("ifinbox"));
			driver.switchTo().frame(fr);
			elementlist = driver.findElements(By.xpath("//span[contains(text(),'Order Confirmation from wellevate')]"));
			elementlist.get(0).click();
			implicitywait(1000);
			driver.switchTo().defaultContent();
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return elementlist;
	}

	// Patients order submit mail verify
	public String verifypatientsOrder() throws InterruptedException {
		String element = null;
		try {
			WebElement fr = driver.findElement(By.id("ifmail"));
			driver.switchTo().frame(fr);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("scroll(0, 45000)");
			element = driver.findElement(By.xpath("//h6[contains(text(),'Your Order')]")).getText().substring(1000, 2000)
					.trim();
			implicitywait(1000);
			driver.switchTo().defaultContent();
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;

	}
}
