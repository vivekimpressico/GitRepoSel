package com.wellevate.Validation;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.BusinessInformationPage;
import com.wellevate.PractitionerAccount.ConnectWithStripeObject;
import com.wellevate.PractitionerAccount.DispensaryPageAccountSetting;
import com.wellevate.PractitionerAccount.EmailPrefrencesPage;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.PractitionerSalesTaxObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.ProffessionalCertficationObject;
import com.wellevate.PractitionerAccount.ProfileSetUpPage;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
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

public class TestPatientPasswordValidationReset extends Data_Provider {
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
	PasswordSetUPpage passwordsetup;
	String actualPasswordColor;

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
		passwordsetup = new PasswordSetUPpage(driver);
	}

	// Login for Patient
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void LoginPatients() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("patientpasswordvalidation"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to password set up page
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void practitionerProfilePasswordTab() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		accsettingapp.patientAccountSettingDetailsPage();
		basepage.elementWait(7000);
		accountsettingpage.patientPasswordTab().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to password set up page Password field
	@SuppressWarnings("static-access")

	@Test(priority = 3, dataProvider = "InvalidPatientPassword")
	public void passwordReSetNewPasswordVal(String password, String ValidationKey, String PassColour)
			throws InterruptedException, BiffException {

		basepage.elementWait(5000);
		String actPasswordmessage = null, expectedPasswordmessage = null;
		passwordsetup.prCurrentPassword().clear();
		passwordsetup.prCurrentPassword().sendKeys(genericMethods.dataprp("password"));
		passwordsetup.prNewPassword().clear();
		passwordsetup.prNewPassword().sendKeys(password);
		passwordsetup.prConfirmPassword().click();
		actPasswordmessage = passwordsetup.prNewPasswordErrormsg().getText();
		expectedPasswordmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		String Passwordcolor = passwordsetup.prNewPasswordErrormsg().getCssValue("color");
		actualPasswordColor = genericMethods.color(Passwordcolor);
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(actPasswordmessage, expectedPasswordmessage,
				"Invalid Password Error Message is displayed", "Invalid Password Error Message is not displayed");
		SoftAssertions.verifyEquals(actualPasswordColor.equalsIgnoreCase(PassColour), true,
				"Inline password error msg is sucessfully highligthed",
				"Inline password error msg is sucessfully  not highligthed");
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to password set up page Confirm Password
	@SuppressWarnings("static-access")
	@Test(priority = 4, dataProvider = "InvalidConfirmPasswordReset")
	public void passwordReSetConfirmPassVal(String Confirmpassword, String ValidationKey)
			throws InterruptedException, BiffException {

		basepage.elementWait(5000);
		String actPasswordmessage = null, expectedPasswordmessage = null;
		passwordsetup.prCurrentPassword().clear();
		passwordsetup.prCurrentPassword().sendKeys(genericMethods.dataprp("password"));
		passwordsetup.prNewPassword().clear();
		passwordsetup.prNewPassword().sendKeys(genericMethods.dataprp("resetPassword"));
		passwordsetup.prConfirmPassword().clear();
		passwordsetup.prConfirmPassword().sendKeys(Confirmpassword);
		passwordsetup.prCurrentPassword().click();
		basepage.elementWait(5000);
		actPasswordmessage = passwordsetup.prConfirmPasswordErrorMsg().getText();
		expectedPasswordmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actPasswordmessage, expectedPasswordmessage,
				"Invalid Password Error Message is displayed", "Invalid Password Error Message is not displayed");
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to password set up page Wrong current Password
	@SuppressWarnings("static-access")
	@Test(priority = 5, dataProvider = "WrongCurrentPassword")
	public void passwordReSetWrongCurrentPassword(String currentpassword, String ValidationKey)
			throws InterruptedException, BiffException {

		basepage.elementWait(5000);
		String actPasswordmessage = null, expectedPasswordmessage = null;
		passwordsetup.prCurrentPassword().clear();
		passwordsetup.prCurrentPassword().sendKeys(currentpassword);
		passwordsetup.prNewPassword().clear();
		passwordsetup.prNewPassword().sendKeys(genericMethods.dataprp("resetPassword"));
		passwordsetup.prConfirmPassword().clear();
		passwordsetup.prConfirmPassword().sendKeys(genericMethods.dataprp("resetPassword"));
		passwordsetup.prSubmitPassword().click();
		basepage.elementWait(2000);
		actPasswordmessage = passwordsetup.prCurrentPasswordErrorMsg().getText();
		expectedPasswordmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actPasswordmessage, expectedPasswordmessage,
				"There was an error with your email/password combination. Please try again. Message is displayed",
				"There was an error with your email/password combination. Please try again. Message is not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to password set up page Wrong current Password
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 6)
	public void passwordReSetWithValidPassword() throws InterruptedException, BiffException {
		basepage.elementWait(5000);
		String actPasswordmessage = null, expectedPasswordmessage = null;
		basepage.elementWait(5000);
		passwordsetup.prCurrentPassword().clear();
		passwordsetup.prCurrentPassword().sendKeys(genericMethods.dataprp("password"));
		passwordsetup.prNewPassword().clear();
		passwordsetup.prNewPassword().sendKeys(genericMethods.dataprp("password"));
		passwordsetup.prConfirmPassword().clear();
		passwordsetup.prConfirmPassword().sendKeys(genericMethods.dataprp("password"));
		passwordsetup.prSubmitPassword().click();
		basepage.elementWait(5000);
		logoutapp.LogoutPatient();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login To Practitioner
	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void loginToAccountAfterPasswordChange() throws InterruptedException, IOException {
		projectSetup.openUrl("wellevateUrl");
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("patientpasswordvalidation"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.verifyEquals(pracLogin.welcomeText().isDisplayed(), true, "Land to home page",
				"Not land to Home Page");
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
