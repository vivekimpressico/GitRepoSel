package com.wellevate.PractitionerHeaderFooter;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.wellevate.base.BasePage;

public class PractitionerHeaderFooter extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public PractitionerHeaderFooter(WebDriver driver) {
		PractitionerHeaderFooter.driver = driver;
	}

	// About
	public WebElement aboutFooter() {
		return getElementfluent(By.xpath("//a[(@href ='#/page/about')]"));
	}
	
	//FAQ without login
	
			public WebElement FAQheader() {
				return getElementfluent(By.xpath("//*[contains(text(),'FAQ')]"));
				
			}
			
		//FAQ with login
			
			public WebElement FAQ1header() {
				return getElementfluent(By.xpath("//span[contains(text(),'FAQ')]"));
			}

	// Support
	public WebElement SupportFooter() {
		return getElementfluent(By.xpath("//a[(@href ='https://wellevate.zendesk.com/hc/en-us')]"));

	}

	// Terms of Use

	public WebElement TermsOfUseFooter() {
		return getElementfluent(By.xpath("//*[contains(text(),'Terms of Use')]"));

	}
	// Privacy

	public WebElement PrivacyFooter() {
		return getElementfluent(By.xpath("//*[contains(text(),'Privacy')]"));

	}

	// How it Works

	public WebElement HowitworksHeader() {
		return getElementfluent(By.xpath("//*[contains(text(),'How it works')]"));

	}
	// Get started

	public WebElement Getstarted() {
		return getElementfluent(By.xpath("//*[contains(text(),'Get started')]"));

	}

	// Professionals

	public WebElement Professionals() {
		return getElementfluent(By.xpath("//a[(@href ='#practitioners')]"));

	}

	// Patient

	public WebElement Patient() {
		return getElementfluent(By.xpath("//a[(@href ='#patients')]"));

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
		return getElementfluent(By.xpath("//b[contains(text(),'Registration Forms')]"));

	}

	// Privacy Redirection page

	public WebElement PrivacyText() {
		return getElementfluent(By.xpath("//*[contains(text(), 'Wellevate Privacy')]"));

	}

	// HowItWorks Redirection page

	public WebElement HowitWorksText() {
		return getElementfluent(By.xpath("//*[contains(text(), 'A New Definition of Personalized Medicine')]"));

	}

	// Get Started Redirection page

	public WebElement practitionerRegistation() {
		return getElementfluent(By.xpath("//h1[@class='mb0']"));

	}

}
