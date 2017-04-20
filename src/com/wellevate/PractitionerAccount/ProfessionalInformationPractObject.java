package com.wellevate.PractitionerAccount;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.wellevate.base.BasePage;

public class ProfessionalInformationPractObject extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public ProfessionalInformationPractObject(WebDriver driver) {
		ProfessionalInformationPractObject.driver = driver;
	}

	// Professional Title
	public void prefix(String prefix) {

		try {
			WebElement titlepr = driver.findElement(By.id("prefix"));
			Select list1 = new Select(titlepr);
			list1.selectByVisibleText(prefix);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}

	}

	// Signup MiddleName Field
	public WebElement prMiddleName() {
		return getElementfluent1(By.name("middlename"));
	}

	// Professional Suffix
	public WebElement suffix(String suffix) {

		try {
			WebElement titlepr = driver.findElement(
					By.xpath("//select[@ng-model='profileCtrl.practitionerService.user.customer.suffix']"));
			Select list1 = new Select(titlepr);
			list1.selectByVisibleText(suffix);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// professional Certification
	public WebElement prProfessionalDesignation() {
		return getElementfluent1(By.name("professional_designation"));
	}

	// Business Name
	public WebElement prBusinessName() {
		return getElementfluent1(By.name("business_name"));
	}
	// Professional Address

	public WebElement prProfessionalAddress() {
		return getElementfluent1(By.name("professional_address"));
	}
	// Professional Address

	public WebElement prProfessionalAddress1() {
		return getElementfluent1(By.name("professional_address2"));
	}
	// Professional City

	public WebElement prProfessionalAddressCity() {
		return getElementfluent1(By.name("city"));
	}

	// Professional State
	public WebElement prProfessionalAddressState(String state) {

		try {
			WebElement statepr = driver.findElement(By.name("state"));
			Select list1 = new Select(statepr);
			list1.selectByValue(state);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("l op");
		}
		return element;
	}

	// Professional State
	public WebElement prProfessionalAddressStateValidation() {
		return getElementfluent1(By.name("state"));
	}

	// Professional ZipCode

	public WebElement prProfessionalZipCode() {
		return getElementfluent1(By.name("postcode"));
	}

	// Professional Phone number

	public WebElement prProfessionalPhone() {
		return getElementfluent1(By.name("phone_number"));
	}

	// Professional Pin code

	public WebElement prProfessionalPin() {
		return getElementfluent1(By.name("pin"));
	}

	// Professional Pin code

	public WebElement prProfessionalAbout() {
		return getElementfluent(By.xpath("//textarea[@ng-model='vm.practitionerService.user.about_me']"));
	}

	// Professional Page Next Button
	public WebElement prProfessionalRegistationSubmit1() {
		try {
			getElementfluent(By.xpath("//button[@type='submit']")).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("L OP");
		}
		return null;
	}

	// Professional Page Next Button
	public WebElement prProfessionalRegistationSubmit() {
		return getElementfluent(By.xpath("//button[@type='submit']"));
	}

	// Click On DashBoard
	public WebElement dashBoard() {
		return getElementfluent(By.xpath("//h6[contains(text(),'Dashboard')]"));
	}

	// Account Setting Page Text
	public WebElement accountSettingPageText() {
		return getElementfluent(By.xpath("//h1[contains(text(),'Account Settings')]"));
	}

	// Professional Registation Text
	public WebElement prRegistationText() {
		return getElementfluent(By.xpath("//button[@type='submit']"));
	}

	// Signup to account
	public void registationProffesionalDetails(String designation, String bussinessname, String add, String add1,
			String city, String state, String zipcode, String phone) {
		try {

			prProfessionalDesignation().clear();
			// System.out.println("lop");
			prProfessionalDesignation().sendKeys(designation);
			prBusinessName().clear();
			prBusinessName().sendKeys(bussinessname);
			prProfessionalAddress().clear();
			prProfessionalAddress().sendKeys(add);
			prProfessionalAddress1().clear();
			prProfessionalAddress1().sendKeys(add1);
			prProfessionalAddressCity().clear();
			prProfessionalAddressCity().sendKeys(city);
			prProfessionalAddressState(state);
			prProfessionalZipCode().clear();
			prProfessionalZipCode().sendKeys(zipcode);
			prProfessionalPhone().clear();
			prProfessionalPhone().sendKeys(phone);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LO P");
		}
	}

	// Visit your dashboard button
	public WebElement visitDashBoard() {
		return getElementfluent(By.xpath("//a[contains(text(),'Visit my Dashboard')]"));

	}

	// Visit your dashboard button
	public WebElement personalMessageAfterSignUp() {
		return getElementfluent(By.xpath("//p[contains(text(),'Congratulations you are just a few steps')]"));
	}

	// Upload your Professional Credentials
	public WebElement uploadProfessionalCredentials() {
		return getElementfluent(By.xpath("//li[contains(text(),'Upload your Professional Credentials')]"));
	}
	public String uploadProfessionalCredentials1() {
		String customDispensary = null;
		int i = driver.findElements(By.xpath("//li[contains(text(),'Upload your Professional Credentials')]")).size();
		try {

			if (i > 0) {
				customDispensary = "true";
				System.out.println("Dispensary button is Present");

			} else {
				customDispensary = "false";
				System.out.println("Dispensary button is not  Present");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return customDispensary;

	}
	// Select your preferred product access
	public WebElement preferredproductaccess() {
		return getElementfluent(By.xpath("//li[contains(text(),'Select your preferred product access')]"));
	}
	public String preferredproductaccess1() {
		String customDispensary = null;
		int i = driver.findElements(By.xpath("//li[contains(text(),'Select your preferred product access')]")).size();
		try {

			if (i > 0) {
				customDispensary = "true";
				System.out.println("Dispensary button is Present");

			} else {
				customDispensary = "false";
				System.out.println("Dispensary button is not  Present");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return customDispensary;

	}
	// Set your Patient pricing
	public WebElement patientPricing() {
		return getElementfluent(By.xpath("//li[contains(text(),'Set your Patient pricing')]"));
	}
	public String patientPricing1() {
		String customDispensary = null;
		int i = driver.findElements(By.xpath("//li[contains(text(),'Set your Patient pricing')]")).size();
		try {

			if (i > 0) {
				customDispensary = "true";
				System.out.println("Dispensary button is Present");

			} else {
				customDispensary = "false";
				System.out.println("Dispensary button is not  Present");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return customDispensary;

	}
	// Confirm your Sales Tax requirements
	public WebElement salesTax() {
		return getElementfluent(By.xpath("//li[contains(text(),'Confirm your Sales Tax requirements')]"));
	}
	public String salesTax1() {
		String customDispensary = null;
		int i = driver.findElements(By.xpath("//li[contains(text(),'Confirm your Sales Tax requirements')]")).size();
		try {

			if (i > 0) {
				customDispensary = "true";
				System.out.println("Dispensary button is Present");

			} else {
				customDispensary = "false";
				System.out.println("Dispensary button is not  Present");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return customDispensary;

	}
	// Set up your Stripe account for payments
	public WebElement stripeAccount() {
		return getElementfluent(By.xpath("//li[contains(text(),'Set up your Stripe account for payments')]"));
	}
	public String stripeAccount1() {
		String customDispensary = null;
		int i = driver.findElements(By.xpath("//li[contains(text(),'Set up your Stripe account for payments')]")).size();
		try {

			if (i > 0) {
				customDispensary = "true";
				System.out.println("Dispensary button is Present");

			} else {
				customDispensary = "false";
				System.out.println("Dispensary button is not  Present");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return customDispensary;

	}
	// Select Your Patient Access Options
	public WebElement patientAcessOption() {
		return getElementfluent(By.xpath("//li[contains(text(),'Select Your Patient Access Options')]"));
	}
	public String patientAcessOption1() {
		String customDispensary = null;
		int i = driver.findElements(By.xpath("//li[contains(text(),'Select Your Patient Access Options')]")).size();
		try {

			if (i > 0) {
				customDispensary = "true";
				System.out.println("Dispensary button is Present");

			} else {
				customDispensary = "false";
				System.out.println("Dispensary button is not  Present");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return customDispensary;

	}
	// Continue WithOut Saving Buttton
	public WebElement continueWithOutSaveButtton() {
		return getElementfluent(By.xpath("//button[contains(text(),'Continue without saving')]"));
	}

	// Saving Buttton
	public WebElement continueWithSaveButtton() {
		return getElementfluent(By.xpath("//button[contains(text(),'Save')]"));
	}

	// Professional Page Next Button
	public WebElement butterflyimage() {
		return getElementfluent(By.xpath("//img[@title='wellevate(SM)']"));
	}

	// Professional Page Next Button
	public void stateChangeConformation() {
		try {
			int i = driver.findElements(By.xpath("//button[@ng-click='ok()']")).size();
			if (i > 0) {
				driver.findElement(By.xpath("//button[@ng-click='ok()']")).click();
				elementWait(3000);
			} else {

				System.out.println("NonPopup");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Visit your dashboard button
	public WebElement registrationCompletedMsg() {
		return getElementfluent(By.xpath("//div[@ng-repeat='message in $ctrl.onlyNotReadMessages']/p/p"));
	}

}
