package com.wellevate.PractitionerAccount;

import java.awt.List;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.Validation.SignupValidationObject;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;


import jxl.read.biff.BiffException;

class TestPractitionerExcelCreateAccount extends Data_Provider {
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
	BasePage basepage;
	SignupValidationObject signupvald;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
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
		basepage = new BasePage();
		signupvald = new SignupValidationObject(driver);
	}

	// Sign up
	@Test(priority = 1, dataProvider = "ImpotSignupData1")
	@SuppressWarnings("static-access")
	public void proffesionalSignUp(String FirstName, String LastName, String Email, String Password, String RePassword,
			String designation, String bussinessname, String add, String add1, String city, String state,
			String zipcode, String phone, String practitionerType) throws InterruptedException {

		signupPrac.practitionersignUpButton().click();
		basepage.elementWait(2000);
		signupPrac.SignUpToApplication1(FirstName, LastName, Email, Password, RePassword);
		signupPrac.createAccount().click();
		basepage.elementWait(3000);

		java.util.List<WebElement> elements = driver.findElements(By.xpath("//div[@class='toast-message']"));
		if(elements.size()!=0){
			proffinformation.butterflyimage().click();
		}
		else{
		proffinformation.registationProffesionalDetails(designation, bussinessname, add, add1, city, state, zipcode,
				phone);
		basepage.elementWait(3000);
		proffCertification.proffesionalCertificationType(practitionerType);
		basepage.elementWait(3000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,50000)");
		basepage.implicitywait(2000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		java.util.List<WebElement> elements1 = driver.findElements(By.xpath("//a[contains(text(),'Visit my Dashboard')]"));
		if(elements1.size()!=0){
			proffinformation.butterflyimage().click();
		}
		
		}
	}

}
