package com.wellevate.Validation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.ConnectWithStripeObject;
import com.wellevate.PractitionerAccount.DispensaryPageAccountSetting;
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
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestTextfieldChangesReflectOrNotFileUpload {
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
	WebDriverWait wait;
	WebElement element;
	BasePage basepage;
	String fileExceeds;
	JavascriptExecutor jse;
	String InvalidfileErrorMsg;
	String InvalidMsg;
	AccountSettingsPage accountsettingpage;
	DispensaryPageAccountSetting dispaensaryaccsetting;

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
		wait = new WebDriverWait(driver, 35);
		basepage = new BasePage();
		accountsettingpage = new AccountSettingsPage(driver);
		dispaensaryaccsetting = new DispensaryPageAccountSetting(driver);
		jse = (JavascriptExecutor) driver;
	}

	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void signUp() throws InterruptedException, IOException {
	
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("emailPractitioner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
	}

	// Profile changes not saved
	@SuppressWarnings({ "static-access" })
	@Test(priority = 2)
	public void practionerProfileChangesSavedOrNot() throws InterruptedException, IOException {
		
		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(7000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(3000);
		String actAboutText = genericMethods.ConfigFile("ProfileAbout").trim();
		proffinformation.prProfessionalAbout().clear();
		proffinformation.prProfessionalAbout().sendKeys(actAboutText);
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(3000);
		proffinformation.continueWithOutSaveButtton().click();
		basepage.elementWait(5000);
		accountsettingpage.profileSetUpTab().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		String expAboutText = proffinformation.prProfessionalAbout().getAttribute("value");
		SoftAssertions.verifyEquals(actAboutText.equalsIgnoreCase(expAboutText), false,
				"Changes in text fields is not done", "Changes on text fields is done");
		basepage.elementWait(3000);
	}

	// Profile changes saved
	@SuppressWarnings({ "static-access" })
	@Test(priority = 3)
	public void practionerProfileChangesSaved() throws InterruptedException, IOException {
		
		basepage.elementWait(7000);
		String actAboutText = genericMethods.ConfigFile("ProfileAbout").trim();
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(3000);
		proffinformation.prProfessionalAbout().clear();
		basepage.elementWait(3000);
		proffinformation.prProfessionalAbout().sendKeys(actAboutText);
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(3000);
		proffinformation.continueWithSaveButtton().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(5000);
		String expAboutText = proffinformation.prProfessionalAbout().getAttribute("value");
		SoftAssertions.verifyEquals(actAboutText.equalsIgnoreCase(expAboutText), true,
				"Changes in text fields is done", "Changes in text fields is not done");
		basepage.elementWait(5000);
	}

	// Profile changes saved
	@SuppressWarnings({ "static-access" })
	@Test(priority = 4)
	public void practionerPoffCredentailsChangesSaved() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(5000);
		String actDispensaryName = genericMethods.ConfigFile("dispensaryName");
		dispaensaryaccsetting.prDispensaryName().clear();
		dispaensaryaccsetting.prDispensaryName().sendKeys(actDispensaryName);
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);	
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-2000)");
		basepage.elementWait(5000);
		String expDispensaryName = dispaensaryaccsetting.prDispensaryName().getAttribute("value");
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(actDispensaryName.equalsIgnoreCase(expDispensaryName), true,
				"Dispensary text fields  name Changes is done", "Dispensary text fields  name Changes is not done");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void practionerFileUploadInProfileCredentials() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		practionerdispansary.prCredentilsTab().click();
		basepage.elementWait(5000);
		// proffinformation.continueWithSaveButtton().click();
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		// .jpg file
		proffCertification.prProfessionalCredentialsDocUploadValidation(
				System.getProperty("user.dir") + genericMethods.ConfigFile("FileUpload"));
		basepage.elementWait(1000000);
		// .Png file
		proffCertification.CredentialsDocUploadValidation().click();
		basepage.elementWait(5000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("pngImage"));
		basepage.elementWait(15000);

		// .gif File
		proffCertification.CredentialsDocUploadValidation().click();
		basepage.elementWait(5000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("gifImage"));
		basepage.elementWait(12000);

		// .pdf File
		proffCertification.CredentialsDocUploadValidation().click();
		basepage.elementWait(5000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("pdfImage"));
		basepage.elementWait(1000000);

		// FILE >5MB
		proffCertification.CredentialsDocUploadValidation().click();
		basepage.elementWait(5000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("biggerImage"));
		basepage.elementWait(12000);
		fileExceeds = proffCertification.fileExceeds5mb().getText();
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(proffCertification.fileExceeds5mb().isDisplayed(), true,
				"This file exceeds our maximum file size of 5MB",
				"This file exceeds our maximum file size of 5MB Text Not Visible");

		// Invalid File Format
		InvalidMsg = genericMethods.ConfigFile("InvalidFileMsg");
		proffCertification.CredentialsDocUploadValidation().click();
		basepage.elementWait(5000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("wrongFormatImage"));
		basepage.elementWait(5000);
		InvalidfileErrorMsg = proffCertification.invalidFileMsg().getText();
		SoftAssertions.verifyEquals(InvalidfileErrorMsg, InvalidMsg, "This file type is wrong",
				"This file type is wright");
		basepage.elementWait(5000);

	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}

}
