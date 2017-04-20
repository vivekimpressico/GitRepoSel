package com.wellevate.PractitionerInvitePatients;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.base.BasePage;

public class PatientsAddressInformation_Object extends BasePage {
	static WebElement element;
	static WebDriver driver;

	public PatientsAddressInformation_Object(WebDriver driver) {
		PatientsAddressInformation_Object.driver = driver;
	}

	// Patients address line 1
	public WebElement patAdd1() {
		return getElementeExplicit(By.name("address1"));
	}

	// Patients address line 2
	public WebElement patAdd2() {
		return getElementeExplicit(By.name("address2"));
	}

	// Patients city
	public WebElement patCity() {
		return getElementeExplicit(By.name("city"));
	}

	// Patients State
	public void patState(String State) {

		try {
			WebElement element = driver.findElement(By.name("region"));
			elementWait(2000);
			Actions action = new Actions(driver).click(element);
			action.build().perform();
			elementWait(2000);
			List<WebElement> eles = driver.findElements(By.xpath("//md-option[@ng-repeat='(key,state) in vm.states']"));
			for (int i = 0; i < eles.size(); i++) {
				System.out.println(eles.get(i).getText());
				if (eles.get(i).getText().equals(State.trim())) {
					eles.get(i).click();
					break;
				}
			}

			elementWait(2000);

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not  Found");
		}

	}

	// Patients State
	public void patState1(String State) {

		try {
			WebElement element = driver.findElement(By.name("region"));
			Select sel = new Select(element);
			sel.deselectByVisibleText(State);

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not  Found");
		}

	}

	// Patients zip code
	public WebElement patZipCode() {
		return getElementeExplicit(By.name("postcode"));
	}

	// Prefer contact type
	public void patPreferContactType(String patientContactType) {

		try {

			if (driver.findElement(By.xpath("//img[@ng-hide='registrationStep2Ctrl.user.emailSelected']")).getText()
					.equalsIgnoreCase(patientContactType)) {
				driver.findElement(By.xpath("//img[@ng-hide='registrationStep2Ctrl.user.emailSelected']")).click();
			} else if (driver.findElement(By.xpath("//img[@ng-hide='registrationStep2Ctrl.user.phoneSelected']"))
					.getText().equalsIgnoreCase(patientContactType)) {
				driver.findElement(By.xpath("//img[@ng-hide='registrationStep2Ctrl.user.phoneSelected']")).click();
			}
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
	}

	// Gender
	public void patGender(String patGender) {

		try {

			if (driver.findElement(By.xpath("//img[@ng-hide='registrationStep2Ctrl.user.female']")).getText()
					.equalsIgnoreCase(patGender)) {
				driver.findElement(By.xpath("//img[@ng-hide='registrationStep2Ctrl.user.female']")).click();
			} else if (driver.findElement(By.xpath("//img[@ng-hide='registrationStep2Ctrl.user.male']")).getText()
					.equalsIgnoreCase(patGender)) {
				driver.findElement(By.xpath("//img[@ng-hide='registrationStep2Ctrl.user.male']")).click();
			}
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}

	}

	// Patients field complete
	public WebElement patPhoneTexttField() {
		return getElementfluent(By.xpath("//input[@name='phone']"));
	}

	// Patients field complete
	public WebElement patCompleteButton() {
		return getElementfluent(By.xpath("//button[@type='submit']"));
	}

	// Patients address information details

	public void patientsInformation(String add1, String add2, String city, String State, String zipcode) {

		try {
			patAdd1().clear();
			patAdd1().sendKeys(add1);
			patAdd2().clear();
			patAdd2().sendKeys(add2);
			patCity().clear();
			patCity().sendKeys(city);
			implicitywait(2000);
			patState(State);
			patZipCode().click();
			implicitywait(2000);
			patZipCode().clear();
			patZipCode().sendKeys(zipcode);
			implicitywait(2000);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("l p");
		}

	}
	// Practitioner Profit Radio Button

	public void patientContactType(String contactType) throws InterruptedException {

		try {

			// Shop All Products
			if (driver.findElement(By.xpath("//md-radio-button[@value='telephone']/div/span")).getText()
					.equalsIgnoreCase(contactType)) {
				WebElement makeprofit_radio_button = driver
						.findElement(By.xpath("//md-radio-button[@value='telephone']"));
				Thread.sleep(5000);
				boolean status = makeprofit_radio_button.isDisplayed();
				System.out.println("Profit radio button is Displayed >>" + status);
				boolean enabled_status = makeprofit_radio_button.isEnabled();
				System.out.println("Profit radio button is Enabled >>" + enabled_status);
				boolean selected_status = makeprofit_radio_button.isSelected();
				System.out.println("Profit radio button is Selected >>" + selected_status);
				makeprofit_radio_button.click();
				Thread.sleep(5000);
				boolean selected_status_new = makeprofit_radio_button.isSelected();
				System.out.println("Profit radio button is Selected >>" + selected_status_new);
				Thread.sleep(5000);

				// Shop My Customized Dispensary
			} else if (driver.findElement(By.xpath("//md-radio-button[@value='email']/div/span")).getText()
					.equalsIgnoreCase(contactType)) {
				WebElement makeprofit_radio_button = driver.findElement(By.xpath("//md-radio-button[@value='email']"));
				boolean status = makeprofit_radio_button.isDisplayed();
				System.out.println("No Profit radio button is Displayed >>" + status);
				boolean enabled_status = makeprofit_radio_button.isEnabled();
				System.out.println("No Profit radio button is Enabled >>" + enabled_status);
				boolean selected_status = makeprofit_radio_button.isSelected();
				System.out.println("No Profit radio button is Selected >>" + selected_status);
				makeprofit_radio_button.click();
				boolean selected_status_new = makeprofit_radio_button.isSelected();
				System.out.println("No Profit radio button is Selected >>" + selected_status_new);

			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}

	}

}
