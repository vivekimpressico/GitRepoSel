package com.wellevate.Validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wellevate.base.BasePage;

public class PatientAddressPageValidation extends BasePage {
	static WebElement element;
	static WebDriver driver;

	public PatientAddressPageValidation(WebDriver driver) {
		PatientAddressPageValidation.driver = driver;
	}

	// Patients address line 1
	public WebElement patAdd1val() {
		return getElementeExplicit(By.xpath("//input[@name='address1']/following-sibling::div[@role='alert']/span"));
	}

	// Patients city
	public WebElement patCityval() {
		return getElementeExplicit(By.xpath("//input[@name='city']/following-sibling::div[@role='alert']/span"));
	}

	// Patients State
	public WebElement patStateval() {
		return getElementeExplicit(By.xpath("//md-select[@name='region']/following-sibling::div[@role='alert']/span"));

	}

	// Patients zip code
	public WebElement patZipCodeval() {
		return getElementeExplicit(By.xpath("//input[@name='postcode']/following-sibling::div[@role='alert']/span"));
	}

	// Patients zip code
	public WebElement patPhoneval() {
		return getElementeExplicit(By.xpath("//input[@name='phone']/following-sibling::div[@role='alert']/span"));
	}
}
