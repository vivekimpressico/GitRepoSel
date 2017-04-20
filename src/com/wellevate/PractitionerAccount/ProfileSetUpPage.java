package com.wellevate.PractitionerAccount;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.wellevate.base.BasePage;

public class ProfileSetUpPage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	ProfessionalInformationPractObject proffinformationobj;
	
	public ProfileSetUpPage(WebDriver driver) {
		ProfileSetUpPage.driver = driver;
		proffinformationobj = new ProfessionalInformationPractObject(driver);
	}
	
	// Signup to account
		public void profileSetUpDetails(String prefix, String mdName, String suffix, String about) {
			try {

				proffinformationobj.prefix(prefix);
				proffinformationobj.prMiddleName().clear();
				// prMiddleName().sendKeys(mdName);
				proffinformationobj.suffix(suffix);
				proffinformationobj.prProfessionalAbout().clear();
				proffinformationobj.prProfessionalAbout().sendKeys(about);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		// Business Type
		public void prBusinessType(String businessType) {

			try {

				if (driver.findElement(By.xpath("//label[contains(text(),'Solo Practitioner')]")).getText()
						.equalsIgnoreCase(businessType)) {
					driver.findElement(By.xpath("//label[contains(text(),'Solo Practitioner')]")).click();
				} else if (driver.findElement(By.xpath("//label[contains(text(),'Group')]")).getText()
						.equalsIgnoreCase(businessType)) {
					driver.findElement(By.xpath("//label[contains(text(),'Group')]")).click();
				}
			} catch (NoSuchElementException e) {
				Reporter.log("Fail: Web Element Not Found");
			}

		}
		
}
