package com.wellevate.PractitionerHeaderFooter;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
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
import com.wellevate.PractitionerInvitePatients.PatientsAddressInformation_Object;
import com.wellevate.PractitionerInvitePatients.PatientsSignUp;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
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

public class TestHeaderFooterWithLogin {

	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	SignUpPractitionerObject signupPrac;
	ProfessionalInformationPractObject proffinformation;
	GenericsMethods genericMethods;
	PractitionerHeaderFooter headerfooter;
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
	String url;
	PatientsSignUp patientsignup;
	String expsupportURL = "https://wellevate.zendesk.com/hc/en-us";
	PatientsAddressInformation_Object patientsaddInfrm;
	JavascriptExecutor jse;

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
		headerfooter = new PractitionerHeaderFooter(driver);

	}

	// Login
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void practitionerLogin() throws InterruptedException, IOException, AWTException {
		
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qainviteOnlyPractitioner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		pracLogin.prLogin().click();
		BasePage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void FAQHeader() throws InterruptedException, IOException, AWTException {

		// FAQ
		basepage.elementWait(7000);
		headerfooter.FAQ1header().click();
		BasePage.elementWait(5000);
		String Parent_Window = driver.getWindowHandle();
		// Switching from parent window to child window
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(Parent_Window);
		// Performing actions on child window
		BasePage.elementWait(3000);
		driver.switchTo().window(newTab.get(0));
		String actFAQURL = driver.getCurrentUrl();
		BasePage.elementWait(5000);
		SoftAssertions.verifyEquals(actFAQURL, expsupportURL, "FAQ Page is displayed", "FAQ Page is not displayed");
		BasePage.implicitywait(3000);
		driver.close();
		BasePage.implicitywait(3000);
		driver.switchTo().window(Parent_Window);

		BasePage.implicitywait(3000);

		BasePage.elementWait(3000);
		jse.executeScript("scroll(0, 5000)");
		BasePage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// About
	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void AboutFooter() throws InterruptedException, IOException, AWTException {
		
		basepage.elementWait(5000);
		headerfooter.aboutFooter().click();
		BasePage.elementWait(3000);
		SoftAssertions.verifyEquals(headerfooter.WellevateAboutText().isDisplayed(), true,
				"Wellevate About page is displayed ", "Wellevate About page is not displayed");
		BasePage.implicitywait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Support
	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void SupportFooter() throws InterruptedException, IOException, AWTException {
		
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, 5000)");
		BasePage.elementWait(5000);
		headerfooter.SupportFooter().click();
		BasePage.elementWait(20000);

		String Parent_Window1 = driver.getWindowHandle();
		// Switching from parent window to child window
		ArrayList<String> newTab1 = new ArrayList<String>(driver.getWindowHandles());
		newTab1.remove(Parent_Window1);
		// Performing actions on child window

		driver.switchTo().window(newTab1.get(0));
		String actsupportURL = driver.getCurrentUrl();
		BasePage.elementWait(5000);
		SoftAssertions.verifyEquals(actsupportURL, expsupportURL, "Support Page is displayed",
				"Support Page is not displayed");
		BasePage.implicitywait(3000);

		driver.close();
		basepage.elementWait(5000);
		driver.switchTo().window(Parent_Window1);
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Terms of Use
	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void TermsofuseFooter() throws InterruptedException, IOException, AWTException {
		
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, 5000)");
		basepage.elementWait(5000);
		headerfooter.TermsOfUseFooter().click();
		BasePage.elementWait(3000);
		SoftAssertions.verifyEquals(headerfooter.TermsofServiceText().isDisplayed(), true,
				"Wellevate Terms of Use Page is displayed", "Wellevate Terms of Use Page is not displayed");
		BasePage.implicitywait(3000);
		SoftAssertions.throwAsserationOnFailure();

	}

	// Privacy patient
	@Test(priority = 6)
	public void PrivacyFooter() throws InterruptedException, IOException, AWTException {
		
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, 5000)");
		BasePage.elementWait(3000);
		headerfooter.PrivacyFooter().click();
		BasePage.elementWait(3000);
		SoftAssertions.verifyEquals(headerfooter.PrivacyText().isDisplayed(), true,
				"Wellevate Privacy Page is displayed", "Wellevate Privacy Page is not displayed");
		BasePage.implicitywait(3000);
		SoftAssertions.throwAsserationOnFailure();

	}
	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
