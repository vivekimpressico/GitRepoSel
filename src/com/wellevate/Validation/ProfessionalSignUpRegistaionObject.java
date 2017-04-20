package com.wellevate.Validation;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.wellevate.base.BasePage;

public class ProfessionalSignUpRegistaionObject extends BasePage {

	static WebElement element;
	static WebDriver driver;
	
	public ProfessionalSignUpRegistaionObject(WebDriver driver) {
		ProfessionalSignUpRegistaionObject.driver = driver;
     }

	// Registration Process Address Field
	public WebElement addRegistrationValidation() {
		return getElementfluent(By.xpath("//*[@id='registration']/form/fieldset[1]/div/span"));
	}

	// Registration Process City Field
	public WebElement cityRegistrationValidation() {
		return getElementfluent(By.xpath("//*[@id='registration']/form/div[3]/fieldset[1]/div/span"));
	}

	// Registration Process State Field
	public WebElement stateRegistrationValidation() {
		return getElementfluent(By.xpath("//*[@id='registration']/form/div[3]/fieldset[2]/div/span"));
	}

	// Registration Process Zip Code Field
	public WebElement zipCodeRegistrationValidation() {
		return getElementfluent(By.xpath("//*[@id='registration']/form/div[3]/div/div/span"));
	}

	// Registration Process PhoneNumber Field
	public WebElement phoneNumberRegistrationValidation() {
		return getElementfluent(By.xpath("//*[@id='registration']/form/div[4]/div/span"));
	}
	
	// Registration Process Practioner Type DropDown
		public WebElement practionerTypeRegistrationValidation() {
			return getElementfluent(By.xpath("//*[@id='registration']/form/div[5]/div/span"));
		}
}
