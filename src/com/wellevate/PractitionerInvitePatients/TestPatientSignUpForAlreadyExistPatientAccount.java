package com.wellevate.PractitionerInvitePatients;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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

public class TestPatientSignUpForAlreadyExistPatientAccount {
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
	}

	// Login
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void practitionerLogin() throws InterruptedException, IOException, AWTException {

		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
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

		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, -25000)");
		basepage.elementWait(3000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(5000);
		dispaensaryaccsetting.prCopyUrl().click();
		basepage.elementWait(3000);
		String Parent_Window = driver.getWindowHandle();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_T);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // switches to new tab
		basepage.elementWait(3000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		basepage.elementWait(3000);
		url = driver.getCurrentUrl();
		basepage.elementWait(2000);
		driver.close();
		basepage.elementWait(5000);
		driver.switchTo().window(Parent_Window);
		logoutapp.LogoutPractitioner();
		basepage.elementWait(2000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Patients adds their Address details
	@SuppressWarnings({ "static-access" })
	@Test(priority = 3)
	public void patientsAddress() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		driver.get(url);
		basepage.elementWait(5000);
		String winHandleBefore = driver.getWindowHandle();
		basepage.elementWait(5000);
		patientsignup.PatientRegisterButton().click();
		basepage.elementWait(5000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		patientsignup.PatientSignUp2(genericMethods.dataprp("default2"), genericMethods.dataprp("patientsLastName"),
				genericMethods.dataprp("existemailPatientSignUp"), genericMethods.dataprp("qapassword1"),
				genericMethods.dataprp("qapassword1"));
		basepage.elementWait(7000);
		signupPrac.createAccount().click();
		basepage.elementWait(7000);
		SoftAssertions.verifyEquals(patientsignup.alreadyAssocitedEmail().isDisplayed(), true,
				"This email is already associated with a current patient. Please log in. Message is displayed",
				"This email is already associated with a current patient. Please log in.Message is  not displayed");
		basepage.elementWait(5000);
		driver.close();
		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login
	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void patientsLogin() throws InterruptedException, IOException, AWTException {
		projectSetup.openUrl("wellevateUrl");
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(5000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("existemailPatientSignUp"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(patlogin.patLoginSucessful().isDisplayed(), true, "Welcome Message is displayed",
				"Welcome Message is  not displayed");
		basepage.elementWait(5000);
		logoutapp.LogoutPatient();
		SoftAssertions.throwAsserationOnFailure();

	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}

}
