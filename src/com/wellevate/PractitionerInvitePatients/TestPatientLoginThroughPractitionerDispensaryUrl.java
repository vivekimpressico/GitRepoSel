package com.wellevate.PractitionerInvitePatients;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.BusinessInformationPage;
import com.wellevate.PractitionerAccount.ConnectWithStripeObject;
import com.wellevate.PractitionerAccount.DispensaryPageAccountSetting;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.PractitionerSalesTaxObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.ProffessionalCertficationObject;
import com.wellevate.PractitionerAccount.ProfileSetUpPage;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPatientLoginThroughPractitionerDispensaryUrl {
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
	ProfileSetUpPage profilesetuppage;
	BasePage basepage;
	AccountSettingsPage accountsettingpage;
	BusinessInformationPage bussinessinfrpage;
	DispensaryPageAccountSetting dispaensaryaccsetting;
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	String url;
	PatientsSignUp patientsignup;
	PatientsAddressInformation_Object patientsaddInfrm;
	PatientLogIN patlogin;
	WebDriverWait wait;

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
		profilesetuppage = new ProfileSetUpPage(driver);
		accountsettingpage = new AccountSettingsPage(driver);
		bussinessinfrpage = new BusinessInformationPage(driver);
		dispaensaryaccsetting = new DispensaryPageAccountSetting(driver);
		jse = (JavascriptExecutor) driver;
		patientsignup = new PatientsSignUp(driver);
		patientsaddInfrm = new PatientsAddressInformation_Object(driver);
		patlogin = new PatientLogIN(driver);
		wait = new WebDriverWait(driver, 5000);
	}

	// Login
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void practitionerLogin() throws InterruptedException, IOException, AWTException {
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		Thread.sleep(2000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qacopypractitoneremailOpenAcess"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		pracLogin.prLogin().click();
		Thread.sleep(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Copy Practitioner Patients Registration Url
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void practitionerCopyURL() throws InterruptedException, IOException, AWTException {
		basepage.elementWait(5000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, -25000)");
		basepage.elementWait(5000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, 55000)");
		basepage.elementWait(3000);
		url = dispaensaryaccsetting.inviteOnlyUrl().getAttribute("href");
		basepage.elementWait(7000);
		logoutapp.LogoutPractitioner();
	}

	//
	@SuppressWarnings({ "static-access" })
	@Test(priority = 3)
	public void loginPatient() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		driver.get(url);
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(patientsignup.LoginButton().isDisplayed(), true, "Login Button is displayed",
				"Login Button is  not displayed");
		SoftAssertions.verifyEquals(patientsignup.PatientRegisterButton1(), "true",
				"Register Button Should not present", "Register Button Should present");
		SoftAssertions.throwAsserationOnFailure();
	}

	// Patients adds their Address details
	@SuppressWarnings({ "static-access" })
	@Test(priority = 4)
	public void patientLogin() throws InterruptedException, IOException {
		String winHandleBefore = driver.getWindowHandle();
		basepage.elementWait(5000);
		patientsignup.LoginButton().click();
		basepage.elementWait(5000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		patlogin.patEmail().clear();
		patlogin.patEmail().sendKeys(genericMethods.dataprp("emailPatient"));
		patlogin.patPassword().clear();
		patlogin.patPassword().sendKeys(genericMethods.dataprp("password"));
		basepage.elementWait(5000);
		patlogin.patLogin().click();
		basepage.elementWait(5000);
		logoutapp.LogoutPatient();
		basepage.elementWait(5000);
		driver.close();
		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);

		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
