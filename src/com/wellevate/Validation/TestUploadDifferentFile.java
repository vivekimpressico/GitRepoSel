package com.wellevate.Validation;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.ConnectWithStripeObject;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.PractitionerSalesTaxObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.ProffessionalCertficationObject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestUploadDifferentFile {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	SignUpPractitionerObject signupPrac;
	ProfessionalInformationPractObject proffinformation;
	GenericsMethods genericMethods;
	ProffessionalCertficationObject proffCertification;
	PractitionerDispensaryObject practionerdispansary;
	PractitionerPricingOject praPricing;
	PractitionerSalesTaxObject praSalesTax;
	ConnectWithStripeObject connectwthStripe;
	YopmailVerifyEmailAddressObject yopmail;
	LogoutApplication logoutapp;
	PractitionerPatientLoginObject pracLogin;
	AccountSettingIconInApplication accsettingapp;
	PractitionerDispensaryObject patProductAcess;
	String winHandleBefore;
	String Parent_Window;
	PractitionerRecommendation_Object patitentsrecoemened;
	PractitionerInvitesPatientObject pracinvitepatients;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		
		Thread.sleep(5000);
		signupPrac = new SignUpPractitionerObject(driver);
		proffinformation = new ProfessionalInformationPractObject(driver);
		genericMethods = new GenericsMethods();
		proffCertification = new ProffessionalCertficationObject(driver);
		practionerdispansary = new PractitionerDispensaryObject(driver);
		praPricing = new PractitionerPricingOject(driver);
		praSalesTax = new PractitionerSalesTaxObject(driver);
		connectwthStripe = new ConnectWithStripeObject(driver);
		yopmail = new YopmailVerifyEmailAddressObject(driver);
		logoutapp = new LogoutApplication(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		patProductAcess = new PractitionerDispensaryObject(driver);
		praPricing = new PractitionerPricingOject(driver);
		patitentsrecoemened = new PractitionerRecommendation_Object(driver);
		pracinvitepatients = new PractitionerInvitesPatientObject(driver);
		ExcelReaderExpected.connectExcel();
	}

	// Sign up
	// @Test(priority = 1, dataProvider = "ValidSignupData")
	public void proffesionalSignUp(String FirstName, String LastName, String Password, String RePassword)
			throws InterruptedException {
		SoftAssertions.verifyEquals(signupPrac.practitionersignUpButton().isDisplayed(), true, " Signup Button Should be displayed",
				"SignUP Button Should not be displayed");
		signupPrac.practitionersignUpButton().click();
		Thread.sleep(2000);
		SoftAssertions.verifyEquals(signupPrac.proffesionalSignUpText().isDisplayed(), true,
				" Proffesional SignUp Text Should be displayed", "Proffesional SignUp Text Should not be displayed");

		signupPrac.SignUpToApplication(FirstName, LastName, Password, RePassword);
		Thread.sleep(3000);
		SoftAssertions.verifyEquals(signupPrac.createAccount().isDisplayed(), true,
				"Create Account Button  Should be displayed", "Create Account Button Should not be displayed");
		Thread.sleep(3000);
		signupPrac.createAccount().click();
		Thread.sleep(5000);
	}

	// Proffessional Information
	@SuppressWarnings("unused")
	// @Test(priority = 2, dataProvider = "pracProffessionalInfromation")

	public void professionalDetails(String designation, String bussinessname, String add, String add1, String city,
			String state, String zipcode, String phone) throws InterruptedException {

		SoftAssertions.verifyEquals(proffinformation.prRegistationText().isDisplayed(), true,
				"Registation Text Should be displayed", "Registation TextShould not be displayed");

		proffinformation.registationProffesionalDetails(designation, bussinessname, add, add1, city, state, zipcode,
				phone);
		// What type ur bussiness You need Solo or Group read from config file
		Thread.sleep(2000);
		proffCertification.proffesionalCertificationType("Medical Doctor");
		Thread.sleep(2000);

		Thread.sleep(3000);
		SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
				" Registation Submit Button Should be displayed", "Registation Submit Button Should not be displayed");
		proffinformation.prProfessionalRegistationSubmit().click();
		Thread.sleep(3000);
		SoftAssertions.verifyEquals(proffinformation.visitDashBoard().isDisplayed(), true,
				" Visit DashBoard Button Should be displayed", "Visit DashBoard Button Should not be displayed");
		proffinformation.visitDashBoard().click();
		Thread.sleep(3000);
		String personalSetUpMessage = proffinformation.personalMessageAfterSignUp().getText();
		SoftAssertions.verifyEquals(personalSetUpMessage, genericMethods.ConfigFile("personalMessageAfterSignUp"),
				"Personal Message SetUp Message Should be dispalyed",
				"Personal Message SetUp Message Should not  be dispalyed");
		Thread.sleep(5000);
		logoutapp.LogoutPractitioner();
		Thread.sleep(3000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void signUp() throws InterruptedException, IOException {
		// projectSetup.openUrl("wellevateUrl");
		// Thread.sleep(3000);
		pracLogin.pracLoginButton().click();
		Thread.sleep(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("emailPractitoner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		Thread.sleep(3000);
		pracLogin.prLogin().click();
		Thread.sleep(6000);
	}

	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 4)
	public void practionerFileUploadInProfileCredentials() throws InterruptedException, IOException {
		Thread.sleep(40);
		accsettingapp.accountSetting();
		Thread.sleep(40);
		practionerdispansary.prCredentilsTab().click();
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,50000)");
		Thread.sleep(3000);

		// .jpg file
		proffCertification.prProfessionalCredentialsDocUploadValidation(genericMethods.ConfigFile("FileUpload"));
		Thread.sleep(6000);

		// .Png file
		proffCertification.CredentialsDocUploadValidation().click();
		Thread.sleep(3000);
		genericMethods.fileUpload("C:\\Users\\user\\Desktop\\EmersonAutomation\\Wellevate\\image\\pngquant.png");
		Thread.sleep(7000);

		// .gif File
		proffCertification.CredentialsDocUploadValidation().click();
		Thread.sleep(3000);
		genericMethods.fileUpload("C:\\Users\\user\\Desktop\\EmersonAutomation\\Wellevate\\image\\1200043000.gif");
		Thread.sleep(7000);

		// .pdf File
		proffCertification.CredentialsDocUploadValidation().click();
		Thread.sleep(3000);
		genericMethods.fileUpload("C:\\Users\\user\\Desktop\\EmersonAutomation\\Wellevate\\image\\applicationform.pdf");
		Thread.sleep(8000);

		// FILE >5MB
		proffCertification.CredentialsDocUploadValidation().click();
		Thread.sleep(3000);
		genericMethods.fileUpload("C:\\Users\\user\\Desktop\\EmersonAutomation\\Wellevate\\image\\Enrique.jpg");
		Thread.sleep(7000);
		String fileExceeds = proffCertification.fileExceeds5mb().getText();
		SoftAssertions.verifyEquals(proffCertification.fileExceeds5mb().isDisplayed(), true,
				"This file exceeds our maximum file size of 5MB",
				"This file exceeds our maximum file size of 5MB Text Not Visible");

		// Invalid File Format
		String InvalidMsg = genericMethods.ConfigFile("InvalidFileMsg");
		proffCertification.CredentialsDocUploadValidation().click();
		Thread.sleep(3000);
		genericMethods.fileUpload("C:\\Users\\user\\Desktop\\EmersonAutomation\\Wellevate\\image\\test.txt");
		Thread.sleep(5000);
		String InvalidfileErrorMsg = proffCertification.invalidFileMsg().getText();
		SoftAssertions.verifyEquals(InvalidfileErrorMsg, InvalidMsg, "This file type is wrong",
				"This file type is wright");

	}
	@AfterClass
	public void closeAPP() throws InterruptedException {
        
		driver.quit();
	}
	
	}