package com.wellevate.PractitionerHeaderFooter;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wellevate.base.BasePage;

public class PatientHeaderFooter extends BasePage {

	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public PatientHeaderFooter(WebDriver driver) {
		PatientHeaderFooter.driver = driver;
	}

	// About
	public WebElement aboutFooter() {
		return getElementfluent(By.xpath("//a[(@href ='#/page/about')]"));
	}

	// Support
	public WebElement SupportFooter() {
		return getElementfluent(By.xpath("//a[(@href ='https://wellevate.zendesk.com/hc/en-us')]"));

	}

	// FAQ

	public WebElement FAQheader() {
		return getElementfluent(By.xpath("//*[contains(text(),'FAQ')]"));
	}

	// FAQ

	public WebElement FAQ1header() {
		return getElementfluent(By.xpath("//span[contains(text(),'FAQ')]"));
	}

	// Terms of Use

	public WebElement TermsOfUseFooter() {
		return getElementfluent(By.xpath("//*[contains(text(),'Terms of Use')]"));

	}
	// Privacy

	public WebElement PrivacyFooter() {
		return getElementfluent(By.xpath("//*[contains(text(),'Privacy')]"));

	}

	// About Redirection page

	public WebElement WellevateAboutText() {
		return getElementfluent(By.xpath("//*[contains(text(), 'Wellevate About')]"));

	}

	// Customer Support Redirection page

	public WebElement CustomerSupportText() {
		return getElementfluent(By.xpath("//*[contains(text(), 'Customer Support')]"));

	}

	// Terms of Service Redirection page

	public WebElement TermsofServiceText() {
		return getElementfluent(By.xpath("//*[contains(text(), 'Wellevate Terms of Service')]"));

	}

	// Privacy Redirection page

	public WebElement PrivacyText() {
		return getElementfluent(By.xpath("//*[contains(text(), 'Wellevate Privacy')]"));

	}

}
