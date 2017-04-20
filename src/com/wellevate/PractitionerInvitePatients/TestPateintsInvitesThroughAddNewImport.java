package com.wellevate.PractitionerInvitePatients;

import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.wellevate.PractitionerAccount.ConnectWithStripeObject;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.PractitionerSalesTaxObject;
import com.wellevate.PractitionerAccount.ProffessionalCertficationObject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPateintsInvitesThroughAddNewImport extends Data_Provider {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	PractitionerPatientLoginObject pracLogin;
	GenericsMethods genericMethods;
	PractitionerInvitesPatientObject pracinvitepatients;
	PractitionerRecommendation_Object addpatitentsrecoemened;
	YopmailVerifyEmailAddressObject yopmail;
	LogoutApplication logoutapp;
	SignUpPractitionerObject signupPrac;
	PatientsAddressInformation_Object patientsaddInfrm;
	BasePage basepage;
	PatientsSignUp patientsignup ;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		pracLogin = new PractitionerPatientLoginObject(driver);
		genericMethods = new GenericsMethods();
		pracinvitepatients = new PractitionerInvitesPatientObject(driver);
		addpatitentsrecoemened = new PractitionerRecommendation_Object(driver);
		yopmail = new YopmailVerifyEmailAddressObject(driver);
		logoutapp = new LogoutApplication(driver);
		signupPrac = new SignUpPractitionerObject(driver);
		patientsaddInfrm = new PatientsAddressInformation_Object(driver);
		ExcelReaderExpected.connectExcel();
		basepage = new BasePage();
		patientsignup = new PatientsSignUp(driver);
	}

	@SuppressWarnings("static-access")

	// Login to account using Practitioner Credential

	@Test(priority = 1)
	public void loginPractioner() throws InterruptedException, IOException {
		SoftAssertions.verifyEquals(pracLogin.pracLoginButton().isDisplayed(), true, "Login Button is displayed",
				"Login Button is displayed");
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracLogin.loginText().isDisplayed(), true, "Login Text is Displayed",
				"Login Text is  not Displayed");
		basepage.implicitywait(1000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("emailPractitoner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.implicitywait(3000);

	}

	// Add patients details from add new button

	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void patientManually() throws InterruptedException, IOException {
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracinvitepatients.prAddNewButtonInTop().isDisplayed(), true,
				"ADD NEW button is Displayed in top ", "ADD NEW button is  not Displayed in top");
		basepage.implicitywait(1000);
		pracinvitepatients.prAddNewButtonInTop().click();
		basepage.implicitywait(2000);
		SoftAssertions.verifyEquals(pracinvitepatients.addNewPatientUnderAddNewButton().isDisplayed(), true,
				"Patients text is Displayed on the below of ADD NEW button",
				"Patients text is not Displayed on the below of ADD NEW button");
		pracinvitepatients.addNewPatientUnderAddNewButton().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracinvitepatients.addNewPatientTextPopup().isDisplayed(), true,
				"Add New Patient Text is Displayed in popup", "Add New Patient is  not Displayed in popup");
		pracinvitepatients.createPatient();
		basepage.implicitywait(2000);
		SoftAssertions.verifyEquals(pracinvitepatients.addPatientDetails().isDisplayed(), true,
				"Add Button  is displayed after entering all value",
				"Add Button  is not displayed after entering all value");
		pracinvitepatients.addPatientDetails().click();
		basepage.elementWait(5000);
		logoutapp.LogoutPractitioner();
		basepage.implicitywait(2000);
	}

	// Add patients details from Importing csv file

	// @Test(priority = 3)
	@SuppressWarnings("static-access")
	public void importPatientCSV() throws InterruptedException {
		SoftAssertions.verifyEquals(pracinvitepatients.prAddNewButtonInTop().isDisplayed(), true,
				"ADD NEW button is Displayed in top ", "ADD NEW button is  not Displayed in top");
		pracinvitepatients.prAddNewButtonInTop().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracinvitepatients.addNewPatientUnderAddNewButton().isDisplayed(), true,
				"Patients text is Displayed just below ADD NEW button",
				"Patients text is not Displayed just below  ADD NEW button");
		pracinvitepatients.addNewPatientUnderAddNewButton().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracinvitepatients.importPatient().isDisplayed(), true,
				"Import Patients button is Displayed ", "Import Patients button is not Displayed");
		basepage.implicitywait(1000);
		pracinvitepatients.importPatient().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracinvitepatients.importYourPatientText().isDisplayed(), true,
				"Import your Patients text is Displayed in popup ",
				"Import your Patients text is not Displayed in popup");
		basepage.implicitywait(1000);
		pracinvitepatients.fileUploadImportPatient().click();
		basepage.implicitywait(1000);
		genericMethods.fileUpload(System.getProperty("user.dir") + "\\patientsdetails\\patientlist.csv");
		// basepage.implicitywait(1000);(3000);
		pracinvitepatients.continueImportPatient().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracinvitepatients.finshImportPatient().isDisplayed(), true,
				"Finsh & Import Button  is Displayed in popup ",
				"Import your Patients Button is not Displayed in popup");
		basepage.implicitywait(1000);
		pracinvitepatients.finshImportPatient().click();
		basepage.implicitywait(1000);
		pracinvitepatients.closeImportPatient().click();
		basepage.implicitywait(1000);
		logoutapp.LogoutPractitioner();
		basepage.implicitywait(3000);

	}

	// Verifying patients email
	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void mailVerify() throws InterruptedException {
		projectSetup.openUrl("yopmailUrl");
		basepage.implicitywait(3000);
		yopmail.emailTextField().sendKeys(genericMethods.dataprp("emailPatient"));
		basepage.implicitywait(1000);
		yopmail.submitEmail().click();
		basepage.implicitywait(1000);
		yopmail.invitationOfNewPatients();
		basepage.implicitywait(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 50000)");
		basepage.implicitywait(1000);
		yopmail.verifyInvitationOfNewPatients();
		basepage.implicitywait(1000);
	}

	// Patients SignUP section
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 5)
	public void patientsSignUp() throws InterruptedException, IOException {
		String winHandleBefore = driver.getWindowHandle();

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		patientsignup.PatientSignUp(genericMethods.dataprp("password"), genericMethods.dataprp("password"));
		System.out.println("lop");
		signupPrac.createAccount().click();
		basepage.implicitywait(1000);

	}

	// Patients adds their Address details
	@SuppressWarnings({ "static-access" })
	@Test(priority = 6, dataProvider = "patientAddress")
	public void patientsAddress(String add1, String add2, String city, String State, String zipcode)
			throws InterruptedException, IOException {

		patientsaddInfrm.patientsInformation(add1, add2, city, State, zipcode);
		basepage.implicitywait(2000);
		// patientsaddInfrm.patPreferContactType(genericMethods.ConfigFile("patientContactType"));
		// basepage.implicitywait(1000);(40);
		// patientsaddInfrm.patGender(genericMethods.ConfigFile("patGender"));
		// basepage.implicitywait(1000);(40);
		patientsaddInfrm.patCompleteButton().click();
		basepage.elementWait(3000);

	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}

}
