package com.wellevate.Validation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.ProffessionalCertficationObject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.base.BasePage;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestProfessionalSignUpAddressValidation extends Data_Provider {

	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	SignUpPractitionerObject signupPrac;
	ProfessionalInformationPractObject proffinformation;
	GenericsMethods genericMethods;
	ProffessionalCertficationObject proffCertification;
	ProfessionalSignUpRegistaionObject proffregistaion;
	WebDriverWait wait;
	WebElement element;
	BasePage basepage ;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		signupPrac = new SignUpPractitionerObject(driver);
		proffinformation = new ProfessionalInformationPractObject(driver);
		genericMethods = new GenericsMethods();
		proffCertification = new ProffessionalCertficationObject(driver);
		proffregistaion = new ProfessionalSignUpRegistaionObject(driver);
		ExcelReaderExpected.connectExcel();
		wait = new WebDriverWait(driver, 3000);
		basepage = new BasePage();
	}

	// Sign up
	@SuppressWarnings("static-access")
	@Test(priority = 1, dataProvider = "ValidSignupData")
	public void professionalSignUp(String FirstName, String LastName, String Password, String RePassword)
			throws InterruptedException {

		signupPrac.practitionersignUpButton().click();
		basepage.elementWait(5000);
		signupPrac.SignUpToApplication(FirstName, LastName, Password, RePassword);
		basepage.implicitywait(1000);
		signupPrac.createAccount().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Proffessional Information
	@SuppressWarnings({ "unused", "static-access" })
	@Test(priority = 2, dataProvider = "InvaliAddRegistration")

	public void addressValidationRegistration(String add, String ValidationKey)
			throws InterruptedException, BiffException {
		String actAddmessage = null, expectedAddmessage = null;
		SoftAssertions.verifyEquals(proffinformation.prRegistationText().isDisplayed(), true,
				"Registration Text is displayed", "Registration Text is not displayed");

		proffinformation.prProfessionalDesignation().clear();
		proffinformation.prProfessionalDesignation().sendKeys(genericMethods.signUP("ProffDesgnation"));
		proffinformation.prBusinessName().clear();
		proffinformation.prBusinessName().sendKeys(genericMethods.signUP("BusinessName"));
		proffinformation.prProfessionalAddress().clear();
		proffinformation.prProfessionalAddress().sendKeys(add);
		proffinformation.prProfessionalAddress1().clear();
		proffinformation.prProfessionalAddress1().sendKeys(genericMethods.signUP("ProffAdd1"));
		proffinformation.prProfessionalAddressCity().clear();
		proffinformation.prProfessionalAddressCity().sendKeys(genericMethods.signUP("City"));
		proffinformation.prProfessionalAddressState(genericMethods.signUP("State"));
		proffinformation.prProfessionalZipCode().clear();
		proffinformation.prProfessionalZipCode().sendKeys(genericMethods.signUP("ZipCode"));
		proffinformation.prProfessionalPhone().clear();
		proffinformation.prProfessionalPhone().sendKeys(genericMethods.signUP("Phone"));
		proffCertification.proffesionalCertificationType(genericMethods.signUP("PrctionerType"));
        actAddmessage = proffregistaion.addRegistrationValidation().getText();
		expectedAddmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
        SoftAssertions.verifyEquals(actAddmessage, expectedAddmessage, "Invalid Address Error Message is displayed",
				"Invalid Address Error Message  is not displayed");
		driver.navigate().refresh();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// City
	@SuppressWarnings({ "unused", "static-access" })
	@Test(priority = 3, dataProvider = "InvalidCityRegistration")

	public void cityValidationRegistration(String city, String ValidationKey)
			throws InterruptedException, BiffException {
		String actCitymessage = null, expectedCitymessage = null;
		proffinformation.prProfessionalDesignation().clear();
		proffinformation.prProfessionalDesignation().sendKeys(genericMethods.signUP("ProffDesgnation"));
		proffinformation.prBusinessName().clear();
		proffinformation.prBusinessName().sendKeys(genericMethods.signUP("BusinessName"));
		proffinformation.prProfessionalAddress().clear();
		proffinformation.prProfessionalAddress().sendKeys(genericMethods.signUP("ProffAdd"));
		proffinformation.prProfessionalAddress1().clear();
		proffinformation.prProfessionalAddress1().sendKeys(genericMethods.signUP("ProffAdd1"));
		proffinformation.prProfessionalAddressCity().clear();
		proffinformation.prProfessionalAddressCity().sendKeys(city);
		proffinformation.prProfessionalAddressState(genericMethods.signUP("State"));
		proffinformation.prProfessionalZipCode().clear();
		proffinformation.prProfessionalZipCode().sendKeys(genericMethods.signUP("ZipCode"));
		proffinformation.prProfessionalPhone().clear();
		proffinformation.prProfessionalPhone().sendKeys(genericMethods.signUP("Phone"));
		proffCertification.proffesionalCertificationType(genericMethods.signUP("PrctionerType"));
		actCitymessage = proffregistaion.cityRegistrationValidation().getText();
		expectedCitymessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actCitymessage, expectedCitymessage, "Invalid City Error Message is displayed",
				"Invalid City Error Message is Not displayed");
		driver.navigate().refresh();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	// State
	@SuppressWarnings({ "unused", "static-access" })
	@Test(priority = 4, dataProvider = "InvalidStateRegistration")

	public void stateValidationRegistration(String State, String ValidationKey)
			throws InterruptedException, BiffException {
		String actStatemessage = null, expectedStatemessage = null;
		proffinformation.prProfessionalDesignation().clear();
		proffinformation.prProfessionalDesignation().sendKeys(genericMethods.signUP("ProffDesgnation"));
		proffinformation.prBusinessName().clear();
		proffinformation.prBusinessName().sendKeys(genericMethods.signUP("BusinessName"));
		proffinformation.prProfessionalAddress().clear();
		proffinformation.prProfessionalAddress().sendKeys(genericMethods.signUP("ProffAdd"));
		proffinformation.prProfessionalAddress1().clear();
		proffinformation.prProfessionalAddress1().sendKeys(genericMethods.signUP("ProffAdd1"));
		proffinformation.prProfessionalAddressCity().clear();
		proffinformation.prProfessionalAddressCity().sendKeys(genericMethods.signUP("City"));
		proffinformation.prProfessionalAddressStateValidation().click();
		proffinformation.prProfessionalZipCode().clear();
		proffinformation.prProfessionalZipCode().sendKeys(genericMethods.signUP("ZipCode"));
		proffinformation.prProfessionalPhone().clear();
		proffinformation.prProfessionalPhone().sendKeys(genericMethods.signUP("Phone"));
		proffCertification.proffesionalCertificationType(genericMethods.signUP("PrctionerType"));
		actStatemessage = proffregistaion.stateRegistrationValidation().getText();
		expectedStatemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actStatemessage, expectedStatemessage, "Invalid State Error Message  is displayed",
				"Invalid State Error Message is Not displayed");
		driver.navigate().refresh();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	// zipcode
	@SuppressWarnings({ "unused", "static-access" })
	@Test(priority = 5, dataProvider = "InvalidZipCodeRegistration")
	public void zipCodeValidationRegistration(String zipCode, String ValidationKey)
			throws InterruptedException, BiffException {
		String actZipCodemessage = null, expectedZipCodemessage = null;
		proffinformation.prProfessionalDesignation().clear();
		proffinformation.prProfessionalDesignation().sendKeys(genericMethods.signUP("ProffDesgnation"));
		proffinformation.prBusinessName().clear();
		proffinformation.prBusinessName().sendKeys(genericMethods.signUP("BusinessName"));
		proffinformation.prProfessionalAddress().clear();
		proffinformation.prProfessionalAddress().sendKeys(genericMethods.signUP("ProffAdd"));
		proffinformation.prProfessionalAddress1().clear();
		proffinformation.prProfessionalAddress1().sendKeys(genericMethods.signUP("ProffAdd1"));
		proffinformation.prProfessionalAddressCity().clear();
		proffinformation.prProfessionalAddressCity().sendKeys(genericMethods.signUP("City"));
		proffinformation.prProfessionalAddressState(genericMethods.signUP("State"));
		proffinformation.prProfessionalZipCode().clear();
		proffinformation.prProfessionalZipCode().sendKeys(zipCode);
		proffinformation.prProfessionalPhone().clear();
		proffinformation.prProfessionalPhone().sendKeys(genericMethods.signUP("Phone"));
		actZipCodemessage = proffregistaion.zipCodeRegistrationValidation().getText();
		proffCertification.proffesionalCertificationType(genericMethods.signUP("PrctionerType"));
		expectedZipCodemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actZipCodemessage, expectedZipCodemessage,
				"Invalid Zipcode Error Message is Displayed", "Invalid Zipcode Error Message  is Not Displayed");
		driver.navigate().refresh();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Invalid Phone Registration
	@SuppressWarnings({ "unused", "static-access" })
	@Test(priority = 6, dataProvider = "InvalidPhoneRegistration")
	public void phoneValidationRegistration(String Phone, String ValidationKey)
			throws InterruptedException, BiffException {
		String actPhonemessage = null, expectedPhonemessage = null;
		proffinformation.prProfessionalDesignation().clear();
		proffinformation.prProfessionalDesignation().sendKeys(genericMethods.signUP("ProffDesgnation"));
		proffinformation.prBusinessName().clear();
		proffinformation.prBusinessName().sendKeys(genericMethods.signUP("BusinessName"));
		proffinformation.prProfessionalAddress().clear();
		proffinformation.prProfessionalAddress().sendKeys(genericMethods.signUP("ProffAdd"));
		proffinformation.prProfessionalAddress1().clear();
		proffinformation.prProfessionalAddress1().sendKeys(genericMethods.signUP("ProffAdd1"));
		proffinformation.prProfessionalAddressCity().clear();
		proffinformation.prProfessionalAddressCity().sendKeys(genericMethods.signUP("City"));
		proffinformation.prProfessionalAddressState(genericMethods.signUP("State"));
		proffinformation.prProfessionalZipCode().clear();
		proffinformation.prProfessionalZipCode().sendKeys(genericMethods.signUP("ZipCode"));
		proffinformation.prProfessionalPhone().clear();
		proffinformation.prProfessionalPhone().sendKeys(Phone);
		proffCertification.proffesionalCertificationType(genericMethods.signUP("PrctionerType"));
		actPhonemessage = proffregistaion.phoneNumberRegistrationValidation().getText();
		expectedPhonemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actPhonemessage, expectedPhonemessage,
				"Invalid PhoneNumber Error Message is displayed", "Invalid PhoneNumber Error Message is Not displayed");
		driver.navigate().refresh();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Invalid Phone Registration
	@SuppressWarnings({ "unused", "static-access" })
	@Test(priority = 7, dataProvider = "InvalidPractionerTyRegistration")
	public void practitionerTypeValidationRegistration(String practionertype, String ValidationKey)
			throws InterruptedException, BiffException {
		String actPractionerTymessage = null, expectedPractionerTymessage = null;
		proffinformation.prProfessionalDesignation().clear();
		proffinformation.prProfessionalDesignation().sendKeys(genericMethods.signUP("ProffDesgnation"));
		proffinformation.prBusinessName().clear();
		proffinformation.prBusinessName().sendKeys(genericMethods.signUP("BusinessName"));
		proffinformation.prProfessionalAddress().clear();
		proffinformation.prProfessionalAddress().sendKeys(genericMethods.signUP("ProffAdd"));
		proffinformation.prProfessionalAddress1().clear();
		proffinformation.prProfessionalAddress1().sendKeys(genericMethods.signUP("ProffAdd1"));
		proffinformation.prProfessionalAddressCity().clear();
		proffinformation.prProfessionalAddressCity().sendKeys(genericMethods.signUP("City"));
		proffinformation.prProfessionalAddressState(genericMethods.signUP("State"));
		proffinformation.prProfessionalZipCode().clear();
		proffinformation.prProfessionalZipCode().sendKeys(genericMethods.signUP("ZipCode"));
		proffinformation.prProfessionalPhone().clear();
		proffinformation.prProfessionalPhone().sendKeys(genericMethods.signUP("Phone"));
		proffCertification.proffesionalCertificationTypeValidation().click();
		proffinformation.prProfessionalPhone().click();
		actPractionerTymessage = proffregistaion.practionerTypeRegistrationValidation().getText();
		expectedPractionerTymessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actPractionerTymessage, expectedPractionerTymessage,
				"Invalid Practioner Type Error Message is displayed",
				"Invalid Practioner Type Error Message  is Not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
