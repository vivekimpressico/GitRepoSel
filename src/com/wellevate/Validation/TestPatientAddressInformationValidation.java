package com.wellevate.Validation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.DispensaryPageAccountSetting;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PatientsAddressInformation_Object;
import com.wellevate.PractitionerInvitePatients.PatientsSignUp;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPatientAddressInformationValidation extends Data_Provider {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	GenericsMethods genericMethods;
	String actualPasswordColor;
	WebDriverWait wait;
	WebElement element;
	BasePage basepage;
	PractitionerPatientLoginObject pracLogin;
	PatientsSignUp patientsignup;
	PatientsSignUpValidation patsignupval;
	SignUpPractitionerObject signupPrac;
	PatientsAddressInformation_Object patadressinformation;
	PatientAddressPageValidation patientaddressval;
	AccountSettingsPage accountsettingpage;
	AccountSettingIconInApplication accsettingapp;
	DispensaryPageAccountSetting dispaensaryaccsetting;
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	LogoutApplication logoutapp;
	String url;
	@SuppressWarnings("static-access")
	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		genericMethods = new GenericsMethods();
		ExcelReaderExpected.connectExcel();
		genericMethods = new GenericsMethods();
		wait = new WebDriverWait(driver, 3000);
		basepage = new BasePage();
		signupPrac = new SignUpPractitionerObject(driver);
		patientsignup = new PatientsSignUp(driver);
		patsignupval = new PatientsSignUpValidation(driver);
		patadressinformation = new PatientsAddressInformation_Object(driver);
		patientaddressval = new PatientAddressPageValidation(driver);
		basepage.elementWait(3000);
		patientsignup = new PatientsSignUp(driver);
		patsignupval = new PatientsSignUpValidation(driver);
		accountsettingpage = new AccountSettingsPage(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		dispaensaryaccsetting = new DispensaryPageAccountSetting(driver);
		logoutapp = new LogoutApplication(driver);
		
	}

	// Login
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void practitionerLogin() throws InterruptedException, IOException, AWTException {
		
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(7000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qacopypractitoneremailOpenAcess"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Copy Practitioner Patients Registration Url
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void practitionerCopyURL() throws InterruptedException, IOException, AWTException {
		
		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(5000);
		dispaensaryaccsetting.prCopyUrl().click();
		basepage.elementWait(5000);
		String Parent_Window = driver.getWindowHandle();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_T);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // switches to new tab
		basepage.elementWait(5000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		basepage.elementWait(3000);
		url = driver.getCurrentUrl();
		basepage.elementWait(3000);
		driver.close();
		basepage.elementWait(5000);
		driver.switchTo().window(Parent_Window);
		logoutapp.LogoutPractitioner();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@SuppressWarnings({ "static-access" })
	@Test(priority = 3)
	public void patientsAddress() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		projectSetup.openUrl1(url);
		basepage.elementWait(7000);
		String winHandleBefore = driver.getWindowHandle();
		basepage.elementWait(5000);
		patientsignup.PatientRegisterButton().click();
		basepage.elementWait(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		basepage.elementWait(5000);
		patientsignup.PatientSignUp1(genericMethods.dataprp("patientsLastName"), genericMethods.dataprp("qapassword1"),
				genericMethods.dataprp("qapassword1"));
		basepage.elementWait(3000);
		signupPrac.createAccount().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Proffessional Information
	@SuppressWarnings({ "static-access" })
	@Test(priority = 4, dataProvider = "InvaliAddRegistration")

	public void addressLine1ValidationRegistration(String add, String ValidationKey)
			throws InterruptedException, BiffException {
		
		basepage.elementWait(5000);
		String actAddmessage = null, expectedAddmessage = null;
		patadressinformation.patAdd1().clear();
		patadressinformation.patAdd1().sendKeys(add);
		patadressinformation.patAdd2().clear();
		patadressinformation.patAdd2().sendKeys(genericMethods.signUP("ProffAdd1"));
		patadressinformation.patCity().clear();
		patadressinformation.patCity().sendKeys(genericMethods.signUP("City"));
		patadressinformation.patState(genericMethods.signUP("StatePatient"));
		patadressinformation.patZipCode().clear();
		patadressinformation.patZipCode().sendKeys(genericMethods.signUP("ZipCode"));
		basepage.elementWait(5000);
		actAddmessage = patientaddressval.patAdd1val().getText();
		expectedAddmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actAddmessage, expectedAddmessage, "Invalid Address Error Message is displayed",
				"Invalid Address Error Message  is not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// City
	@SuppressWarnings({ "static-access" })
	@Test(priority = 5, dataProvider = "InvalidCityRegistration")

	public void cityValidationRegistration(String city, String ValidationKey)
			throws InterruptedException, BiffException {
		
		basepage.elementWait(5000);
		String actCitymessage = null, expectedCitymessage = null;
		patadressinformation.patAdd1().clear();
		patadressinformation.patAdd1().sendKeys(genericMethods.signUP("ProffAdd"));
		patadressinformation.patAdd2().clear();
		patadressinformation.patAdd2().sendKeys(genericMethods.signUP("ProffAdd1"));
		patadressinformation.patCity().clear();
		patadressinformation.patCity().sendKeys(city);
		patadressinformation.patState(genericMethods.signUP("StatePatient"));
		patadressinformation.patZipCode().clear();
		patadressinformation.patZipCode().sendKeys(genericMethods.signUP("ZipCode"));
		basepage.elementWait(5000);
		actCitymessage = patientaddressval.patCityval().getText();
		expectedCitymessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actCitymessage, expectedCitymessage, "Invalid City Error Message is displayed",
				"Invalid City Error Message is not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	// State Issue in ui
	@SuppressWarnings({ "static-access" })
	// @Test(priority = 6, dataProvider = "InvalidStateRegistration")

	public void stateValidationRegistration(String State, String ValidationKey)
			throws InterruptedException, BiffException {
		
		basepage.elementWait(5000);
		String actStatemessage = null, expectedStatemessage = null;
		patadressinformation.patAdd1().clear();
		patadressinformation.patAdd1().sendKeys(genericMethods.signUP("ProffAdd"));
		patadressinformation.patAdd2().clear();
		patadressinformation.patAdd2().sendKeys(genericMethods.signUP("ProffAdd1"));
		patadressinformation.patCity().clear();
		patadressinformation.patCity().sendKeys(genericMethods.signUP("City"));
		patadressinformation.patState(State);
		patadressinformation.patZipCode().click();
		patadressinformation.patZipCode().clear();
		patadressinformation.patZipCode().sendKeys(genericMethods.signUP("ZipCode"));
		basepage.elementWait(5000);
		actStatemessage = patientaddressval.patStateval().getText();
		expectedStatemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actStatemessage, expectedStatemessage, "Invalid State Error Message is displayed",
				"Invalid State Error Message is Not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	// zipcode
	@SuppressWarnings({ "static-access" })
	@Test(priority = 7, dataProvider = "InvalidZipCodeRegistration")
	public void zipCodeValidationRegistration(String zipCode, String ValidationKey)
			throws InterruptedException, BiffException {
		
		basepage.elementWait(5000);
		String actZipCodemessage = null, expectedZipCodemessage = null;
		patadressinformation.patAdd1().clear();
		patadressinformation.patAdd1().sendKeys(genericMethods.signUP("ProffAdd"));
		patadressinformation.patAdd2().clear();
		patadressinformation.patAdd2().sendKeys(genericMethods.signUP("ProffAdd1"));
		patadressinformation.patCity().clear();
		patadressinformation.patCity().sendKeys(genericMethods.signUP("City"));
		patadressinformation.patState(genericMethods.signUP("StatePatient"));
		patadressinformation.patZipCode().click();
		patadressinformation.patZipCode().clear();
		patadressinformation.patZipCode().sendKeys(zipCode);
		patadressinformation.patAdd2().click();
		basepage.elementWait(5000);
		actZipCodemessage = patientaddressval.patZipCodeval().getText();
		expectedZipCodemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actZipCodemessage, expectedZipCodemessage,
				"Invalid Zipcode Error Message is displayed", "Invalid Zipcode Error Message is not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings({ "static-access" })
	@Test(priority = 8, dataProvider = "InvalidPhoneRegistration")
	public void phoneNumberValidationRegistration(String phone, String ValidationKey)
			throws InterruptedException, BiffException {
		
		basepage.elementWait(5000);
		String actPhonemessage = null, expectedPhonemessage = null;
		patadressinformation.patAdd1().clear();
		patadressinformation.patAdd1().sendKeys(genericMethods.signUP("ProffAdd"));
		patadressinformation.patAdd2().clear();
		patadressinformation.patAdd2().sendKeys(genericMethods.signUP("ProffAdd1"));
		patadressinformation.patCity().clear();
		patadressinformation.patCity().sendKeys(genericMethods.signUP("City"));
		patadressinformation.patState(genericMethods.signUP("StatePatient"));
		patadressinformation.patZipCode().clear();
		patadressinformation.patZipCode().sendKeys(genericMethods.signUP("ZipCode"));
		patadressinformation.patientContactType(genericMethods.signUP("patientContactType"));
		patadressinformation.patPhoneTexttField().sendKeys(phone);
		patadressinformation.patAdd2().click();
		basepage.elementWait(5000);
		actPhonemessage = patientaddressval.patPhoneval().getText();
		expectedPhonemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actPhonemessage, expectedPhonemessage, "Invalid Phone Error Message is displayed",
				"Invalid Phone Error Message is not Displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}