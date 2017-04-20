package com.wellevate.PractitionerAccount;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPractitionerCreatingAccounts extends Data_Provider {

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
	EmailPrefrencesPage emailprefrences;

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
		emailprefrences = new EmailPrefrencesPage(driver);
	}

	// Sign up
	@Test(priority = 1, dataProvider = "ValidSignupData")
	@SuppressWarnings("static-access")
	public void proffesionalSignUp(String FirstName, String LastName, String Password, String RePassword)
			throws InterruptedException {
		basepage.elementWait(3000);
		signupPrac.practitionersignUpButton().click();
		basepage.implicitywait(1000);
		signupPrac.SignUpToApplication(FirstName, LastName, Password, RePassword);
		basepage.elementWait(5000);
		signupPrac.createAccount().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2, dataProvider = "pracProffessionalInfromation")
	// Proffessional Information
	@SuppressWarnings("static-access")
	public void professionalDetails(String designation, String bussinessname, String add, String add1, String city,
			String state, String zipcode, String phone) throws InterruptedException {
		basepage.elementWait(5000);
		proffinformation.registationProffesionalDetails(designation, bussinessname, add, add1, city, state, zipcode,
				phone);
		// What type ur bussiness You need Solo or Group read from config file
		basepage.elementWait(5000);
		proffCertification.proffesionalCertificationType(genericMethods.ConfigFile("practitionerType"));
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		proffCertification.prProfessionalCredentialsDocUpload(
				System.getProperty("user.dir") + genericMethods.ConfigFile("FileUpload"));
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(5);
		SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
				" Registation Submit Button Should be displayed", "Registation Submit Button Should not be displayed");
		basepage.elementWait(5);
		proffinformation.prProfessionalRegistationSubmit1();
		basepage.elementWait(5000);
		proffinformation.visitDashBoard();
		basepage.elementWait(5000);
		String personalSetUpMessage = proffinformation.personalMessageAfterSignUp().getText();
		basepage.elementWait(5000);
		// logoutapp.Logout();
		// basepage.implicitywait(1000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	// @Test(priority = 3)
	public void signUp() throws InterruptedException, IOException {
		// projectSetup.openUrl("wellevateUrl");
		// //Thread.sleep(3000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(5000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("emailPractitioner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Profile information
	@SuppressWarnings("static-access")
	// @Test(priority = 4, dataProvider = "practitionerProfile")
	public void practionerProfile(String prefix, String mdName, String suffix, String about)
			throws InterruptedException, IOException {
		basepage.elementWait(5000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		profilesetuppage.profileSetUpDetails(prefix, mdName, suffix, about);
		basepage.elementWait(5000);
		profilesetuppage.prBusinessType(genericMethods.ConfigFile("businessType"));
		basepage.elementWait(5000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-2000)");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// practitioner Sales Tax Details
	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void practitionerSalesTaxDetails() throws IOException, InterruptedException {
		basepage.elementWait(5000);
		// pracLogin.pracLoginButton().click();
		// pracLogin.prEmail().sendKeys(genericMethods.dataprp("emailPractitioner"));
		// pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		// pracLogin.prLogin().click();
		// basepage.elementWait(3000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, -25000)");
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(accountsettingpage.prSalesTaxTab().isDisplayed(), true, "Sale Tax Tab displayed",
				"Sale Tax Tab is not displayed");
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(5000);
		praSalesTax.prSalesTaxCheckBox().click();
		basepage.elementWait(5000);
		if (proffinformation.prProfessionalRegistationSubmit().isDisplayed()) {
			proffinformation.prProfessionalRegistationSubmit().click();
		} else {
			praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
			praSalesTax.resaleCertificateTextField().sendKeys("12345674");
			basepage.elementWait(5000);
			praSalesTax.taxFileToUpload().click();
			basepage.elementWait(1000);
			proffCertification.prProfessionalDoc(genericMethods.ConfigFile("FileUpload"));
			basepage.elementWait(5000);
			SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
					"Sale Tax Submit Button is displayed", "Sale Tax Submit Button is not displayed");
			proffinformation.prProfessionalRegistationSubmit().click();
			basepage.elementWait(5000);
			SoftAssertions.throwAsserationOnFailure();
		}
	}

	// Stripe card
	@SuppressWarnings({ "static-access" })
	@Test(priority = 6)
	public void practitionerBussinessSetting() throws IOException, InterruptedException {
		jse.executeScript("scroll(0, -25000)");
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(accountsettingpage.prBusinessSettingTab().isDisplayed(), true,
				"Business Setting Tab Should be dispalyed", "Business Setting Tab Should not  be dispalyed");
		accountsettingpage.prBusinessSettingTab().click();
		Thread.sleep(5000);
		bussinessinfrpage.patientProfitType(genericMethods.ConfigFile("profitType"));
		Thread.sleep(5000);
		bussinessinfrpage.savechangesBusinesssetting().click();
		Thread.sleep(5000);
		bussinessinfrpage.stripeButton().click();
		basepage.elementWait(5000);
		Parent_Window = driver.getWindowHandle();
		winHandleBefore = driver.getWindowHandle();
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		basepage.elementWait(5000);
		// Perform the actions on new window
		connectwthStripe.Skipstripe().click();
		// Close the new window, if that window no more required
		basepage.elementWait(5000);
		bussinessinfrpage.savechangesBusinesssetting().click();
		basepage.elementWait(5000);
		driver.close();
		basepage.elementWait(5000);
		driver.switchTo().window(Parent_Window);
		// Switch back to original browser (first window)
		bussinessinfrpage.savechangesBusinesssetting().click();
		basepage.elementWait(5000);
		driver.navigate().refresh();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to Dispensary Tab
	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void practionerDispensaryAccounSetting() throws InterruptedException, IOException {

		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(5000);
		dispaensaryaccsetting.prDispensaryName().sendKeys(genericMethods.ConfigFile("dispensaryName"));
		dispaensaryaccsetting.prDispensaryWelcomeMsg().sendKeys(genericMethods.ConfigFile("dispensaryMsg"));
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		dispaensaryaccsetting.ptProductAcess(genericMethods.ConfigFile("productAcess"));
		basepage.implicitywait(3000);
		dispaensaryaccsetting.PatientsAcessOption(genericMethods.ConfigFile("patientsacess"));
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(dispaensaryaccsetting.prCopyUrl().isDisplayed(), true,
				"Copy URL button Should be disabled ", "Copy URL button  Should  be enabled");
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(dispaensaryaccsetting.getHTMLcodetab().isDisplayed(), true,
				"Get HTML code button Should be disabled", "Get HTML code button  Should  be enabled");
		basepage.elementWait(5000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(dispaensaryaccsetting.prCopyUrl().isDisplayed(), true,
				"Copy URL button  Should  be enabled", "Copy URL button Should be disabled");
		basepage.implicitywait(3000);
		SoftAssertions.verifyEquals(dispaensaryaccsetting.getHTMLcodetab().isDisplayed(), true,
				"Get HTML code button Should  be enabled", "Get HTML code button  Should be disabled");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// practitioner Sales Tax Details
	@SuppressWarnings("static-access")
	@Test(priority = 8)
	public void practitionerEmailPrefrences() throws IOException, InterruptedException {
		jse.executeScript("scroll(0, -55000)");
		SoftAssertions.verifyEquals(accountsettingpage.prEmailPrefrencesTab().isDisplayed(), true,
				"Email Preferences Tab Should be dispalyed", "Email Preferences  Tab Should not be dispalyed");
		accountsettingpage.prEmailPrefrencesTab().click();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(emailprefrences.emailNotificationPatientsReg().isSelected(), false,
				"Email notification from a new patient registration checkbox is  not checked by default ",
				"Email notification from a new patient registration checkbox is checked");
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}