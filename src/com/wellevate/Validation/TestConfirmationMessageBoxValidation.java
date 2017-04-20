package com.wellevate.Validation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class TestConfirmationMessageBoxValidation extends Data_Provider {

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
	JavascriptExecutor jse;
	String actproffessionalcredential, expproffessionalcredential;
	String actproffessionalProductAcess, expproffessionalProductAcess;
	String actproffessionalPricing, expproffessionalPricing;
	String actproffessionalSalesTax, expproffessionalSalesTax;
	String actproffessionalStripeAccount, expproffessionalStripeAccount;
	String actproffessionalPatientAcessOption, expproffessionalPatientAcessOption;
	String personalSetUpMessage;
	String actproffessionalcredential1, expproffessionalcredential1;
	String actproffessionalProductAcess1, expproffessionalProductAcess1;
	String actproffessionalPricing1, expproffessionalPricing1;
	String actproffessionalSalesTax1, expproffessionalSalesTax1;
	String actproffessionalStripeAccount1, expproffessionalStripeAccount1;
	String actproffessionalPatientAcessOption1, expproffessionalPatientAcessOption1;
	BasePage basepage;
	AccountSettingsPage accountsettingpage;
	BusinessInformationPage bussinessinfrpage;
	DispensaryPageAccountSetting dispaensaryaccsetting;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		accountsettingpage = new AccountSettingsPage(driver);
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
		wait = new WebDriverWait(driver, 5000);
		basepage = new BasePage();
		bussinessinfrpage = new BusinessInformationPage(driver);
		dispaensaryaccsetting = new DispensaryPageAccountSetting(driver);
		jse = (JavascriptExecutor) driver;
	}

	// Sign up
	@SuppressWarnings("static-access")
	@Test(priority = 1, dataProvider = "ValidSignupData")
	public void professionalSignUp(String FirstName, String LastName, String Password, String RePassword)
			throws InterruptedException {

		basepage.elementWait(7000);
		signupPrac.practitionersignUpButton().click();
		basepage.elementWait(3000);
		signupPrac.SignUpToApplication(FirstName, LastName, Password, RePassword);
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(3000);
		signupPrac.createAccount().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Proffessional Information
	@SuppressWarnings({ "static-access" })
	@Test(priority = 2, dataProvider = "pracProffessionalInfromation")

	public void professionalRegistrationDetails(String designation, String bussinessname, String add, String add1,
			String city, String state, String zipcode, String phone) throws InterruptedException, BiffException {

		basepage.elementWait(7000);
		proffinformation.registationProffesionalDetails(designation, bussinessname, add, add1, city, state, zipcode,
				phone);
		// What type ur bussiness You need Solo or Group read from config
		// file
		basepage.elementWait(5000);
		proffCertification.proffesionalCertificationType(genericMethods.ConfigFile("practitionerType"));
		// basepage.implicitywait(1000);(2000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		// proffCertification.prProfessionalCredentialsDocUpload();
		// //basepage.implicitywait(1000);(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.implicitywait(8000);
		SoftAssertions.verifyEquals(proffinformation.visitDashBoard().isDisplayed(), true,
				"Visit DashBoard Button Should be displayed", "Visit DashBoard Button Should not be displayed");
		basepage.elementWait(7000);
		proffinformation.visitDashBoard().click();
		basepage.elementWait(5000);
	}

	// All 6 Message Box Validation
	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void practitionerHomePageErrorMesaageVerification() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		personalSetUpMessage = proffinformation.personalMessageAfterSignUp().getText();
		// basepage.implicitywait(1000);(50000);
		actproffessionalcredential = proffinformation.uploadProfessionalCredentials().getText().trim();
		// basepage.implicitywait(1000);(50000);
		expproffessionalcredential = genericMethods.ConfigFile("UPLOAD_PROFESSIONAL_CREDENTIAL").trim();
		// basepage.implicitywait(1000);(50000);
		actproffessionalProductAcess = proffinformation.preferredproductaccess().getText().trim();
		// basepage.implicitywait(1000);(50000);
		expproffessionalProductAcess = genericMethods.ConfigFile("PRODUCT_ACESS").trim();
		// basepage.implicitywait(1000);(50000);
		actproffessionalPricing = proffinformation.patientPricing().getText().trim();
		// basepage.implicitywait(1000);(50000);
		expproffessionalPricing = genericMethods.ConfigFile("PATIENTS_PRICING").trim();
		// basepage.implicitywait(1000);(50000);
		actproffessionalSalesTax = proffinformation.salesTax().getText().trim();
		// basepage.implicitywait(1000);(50000);
		expproffessionalSalesTax = genericMethods.ConfigFile("SALES_TAX").trim();
		// basepage.implicitywait(1000);(50000);
		actproffessionalStripeAccount = proffinformation.stripeAccount().getText().trim();
		// basepage.implicitywait(1000);(50000);
		expproffessionalStripeAccount = genericMethods.ConfigFile("STRIPE_ACCOUNT").trim();
		// basepage.implicitywait(1000);(50000);
		// basepage.implicitywait(1000);(20000);
		actproffessionalPatientAcessOption = proffinformation.patientAcessOption().getText().trim();
		// basepage.implicitywait(1000);(50000);
		expproffessionalPatientAcessOption = genericMethods.ConfigFile("PATIENT_ACESS_OPTION").trim();
		SoftAssertions.verifyEquals(proffinformation.registrationCompletedMsg().isDisplayed(), true,
				"Congratulations you have completed your registration. is displayed",
				"Congratulations you have completed your registration. is displayed");
		SoftAssertions.verifyEquals(proffinformation.personalMessageAfterSignUp().isDisplayed(), true,
				"Confirmation Message on dashboard is displayed", "Confirmation Message on dashboard is displayed");
		SoftAssertions.verifyEquals(actproffessionalcredential.equalsIgnoreCase(expproffessionalcredential), true,
				"Upload your Professional Credentials Text is Displayed",
				"Upload your Professional Credentials is not displayed");
		SoftAssertions.verifyEquals(actproffessionalProductAcess.equalsIgnoreCase(expproffessionalProductAcess), true,
				"Select your preferred product access Text is Displayed",
				"Select your preferred product access Text is not Displayed");
		SoftAssertions.verifyEquals(actproffessionalPricing.equalsIgnoreCase(expproffessionalPricing), true,
				"Set your Patient pricing Text is Displayed", "Set your Patient pricing Text is not Displayed");
		SoftAssertions.verifyEquals(actproffessionalStripeAccount.equalsIgnoreCase(expproffessionalStripeAccount), true,
				"Set up your Stripe account for payments Text is Displayed",
				"Set up your Stripe account for payments Text is not Displayed");
		SoftAssertions.verifyEquals(actproffessionalSalesTax.equalsIgnoreCase(expproffessionalSalesTax), true,
				"Confirm your Sales Tax requirements Text is Displayed",
				"Confirm your Sales Tax requirements Text is not Displayed");
		SoftAssertions.verifyEquals(
				actproffessionalPatientAcessOption.equalsIgnoreCase(expproffessionalPatientAcessOption), true,
				"Select Your Patient Access Options Text is Displayed",
				"Select Your Patient Access Options Text is not Displayed");
		SoftAssertions.verifyEquals(patitentsrecoemened.prRecommenedButton().isDisplayed(), true,
				"Recommeneded Tab  is not Displayed", "Recommeneded Tab is Displayed");
		SoftAssertions.verifyEquals(pracinvitepatients.prAddNewButtonInTop().isDisplayed(), true,
				"ADD NEW button is not displayed  ", "ADD NEW button is  displayed");
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();

	}

	// Taxable state Change
	@SuppressWarnings({ "unused", "static-access" })
	@Test(priority = 4)
	public void practitionerProfileChangesSavedOrNot() throws InterruptedException, IOException {

		basepage.elementWait(3000);
		accsettingapp.accountSetting();
		basepage.elementWait(3000);
		if (practionerdispansary.salesTaxChangeIcon().isDisplayed()) {
			System.out.println("Taxable State Is present ");
			proffinformation.dashBoard().click();
			basepage.elementWait(5000);
		} else {
			jse.executeScript("scroll(0,50000)");
			practionerdispansary.prProfessionalAddressStateDispensary("California");
			proffinformation.prProfessionalRegistationSubmit().click();
			basepage.elementWait(100000);
			String actStateChangePopUp = genericMethods.ConfigFile("StateChangePopUpText").trim();
			basepage.elementWait(5000);
			String expStateChangePopUp = practionerdispansary.stateChangePopUpText().getText().trim();
			// basepage.implicitywait(1000);(3000);
			// SoftAssertions.verifyEquals(actStateChangePopUp.equalsIgnoreCase(expStateChangePopUp),
			// true,
			// "STATE change POPup Text MATCH", "STATE change POPUP Text
			// match");
			practionerdispansary.stateChangePopUpOk().click();
			basepage.elementWait(5000);
			jse.executeScript("scroll(0,-50000)");
			SoftAssertions.verifyEquals(practionerdispansary.salesTaxChangeIcon().isDisplayed(), true,
					"Sales Tax alert icon is displayed ", "Sales Tax alert icon is not displayed");
			proffinformation.dashBoard().click();
			basepage.elementWait(5000);
			SoftAssertions.throwAsserationOnFailure();

		}

	}

	// Dash Board Message Box Text Verifiacation
	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void dashBoardVerfication() throws InterruptedException, BiffException {
		basepage.elementWait(5000);
		actproffessionalcredential1 = proffinformation.uploadProfessionalCredentials().getText().trim();
		actproffessionalProductAcess1 = proffinformation.preferredproductaccess().getText().trim();
		actproffessionalPricing1 = proffinformation.patientPricing().getText().trim();
		actproffessionalSalesTax1 = proffinformation.salesTax().getText().trim();
		actproffessionalStripeAccount1 = proffinformation.stripeAccount().getText().trim();
		actproffessionalPatientAcessOption1 = proffinformation.patientAcessOption().getText().trim();
		SoftAssertions.verifyEquals(actproffessionalcredential1.equalsIgnoreCase(expproffessionalcredential), true,
				"Upload your Professional Credentials Text is Displayed",
				"Upload your Professional Credentials is not displayed");
		SoftAssertions.verifyEquals(actproffessionalProductAcess1.equalsIgnoreCase(expproffessionalProductAcess), true,
				"Select your preferred product access Text is Displayed",
				"Select your preferred product access Text is not Displayed");
		SoftAssertions.verifyEquals(actproffessionalPricing1.equalsIgnoreCase(expproffessionalPricing), true,
				"Set your Patient pricing Text is Displayed", "Set your Patient pricing Text is not Displayed");
		SoftAssertions.verifyEquals(actproffessionalStripeAccount1.equalsIgnoreCase(expproffessionalStripeAccount),
				true, "Set up your Stripe account for payments Text is Displayed",
				"Set up your Stripe account for payments Text is not Displayed");
		SoftAssertions.verifyEquals(actproffessionalSalesTax1.equalsIgnoreCase(expproffessionalSalesTax), true,
				"Confirm your Sales Tax requirements Text is Displayed",
				"Confirm your Sales Tax requirements Text is not Displayed");
		SoftAssertions.verifyEquals(
				actproffessionalPatientAcessOption1.equalsIgnoreCase(expproffessionalPatientAcessOption), true,
				"Select Your Patient Access Options Text is Displayed",
				"Select Your Patient Access Options Text is not Displayed");
		SoftAssertions.verifyEquals(patitentsrecoemened.prRecommenedButton().isDisplayed(), true,
				"Recommeneded Tab  is not Displayed", "Recommeneded Tab is Displayed");
		SoftAssertions.verifyEquals(pracinvitepatients.prAddNewButtonInTop().isDisplayed(), true,
				"ADD NEW button is not displayed  ", "ADD NEW button is  displayed");
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();

	}

	// Sales Tax
	@SuppressWarnings({ "static-access" })
	@Test(priority = 6)
	public void practionerSalesTax() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		accsettingapp.accountSetting();
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(accountsettingpage.prSalesTaxTab().isDisplayed(), true, "Sales Tax Tab displayed",
				"Sales Tax Tab is not displayed");
		if (practionerdispansary.salesTaxChangeIcon().isDisplayed()) {
			basepage.elementWait(5000);
			accountsettingpage.prSalesTaxTab().click();
			basepage.elementWait(7000);
			praSalesTax.prSalesTaxCheckBox().click();
			basepage.elementWait(5000);
			// praSalesTax.salesTaxState("CA");
			// praSalesTax.resaleCertificateTextField().sendKeys("12345674");
			// basepage.implicitywait(1000);(3000);
			// praSalesTax.taxFileToUpload().click();
			// basepage.implicitywait(1000);(40);
			// proffCertification
			// .prProfessionalDoc(System.getProperty("user.dir") +
			// genericMethods.ConfigFile("FileUpload"));
			// basepage.implicitywait(1000);(5000);
			// SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(),
			// true,
			// "Sales Tax Submit Button is displayed", "Sales Tax Submit Button
			// is not displayed");
			proffinformation.prProfessionalRegistationSubmit().click();
			basepage.elementWait(3000);
			jse.executeScript("scroll(0,-2000)");
			SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
					"Sales Tax Submit Button is displayed", "Sales Tax Submit Button is not displayed");
			basepage.elementWait(3000);
		} else {
			System.out.println("sale Tax already given");
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to Dispensary Tab
	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void practitionerDispensary() throws InterruptedException, IOException {

		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-2000)");
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(accountsettingpage.prDispensaryTab().isDisplayed(), true,
				"Dispensary Tab is dispalyed", "Dispensary Tab is not dispalyed");
		basepage.elementWait(3000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(3000);
		dispaensaryaccsetting.prDispensaryName().sendKeys(genericMethods.ConfigFile("dispensaryName"));
		basepage.elementWait(3000);
		dispaensaryaccsetting.prDispensaryWelcomeMsg().sendKeys(genericMethods.ConfigFile("dispensaryMsg"));
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,50000)");
		dispaensaryaccsetting.ptProductAcess(genericMethods.ConfigFile("productAcess"));
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,50000)");
		dispaensaryaccsetting.PatientsAcessOption(genericMethods.ConfigFile("patientsacess"));
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
				"Dispensary Tab Save Changes  Button is dispalyed",
				"Dispensary Tab Save Changes  Button is not dispalyed");
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Bussiness Setting\
	@SuppressWarnings({ "static-access" })
	@Test(priority = 8)
	public void practitionerBussinessSetting() throws IOException, InterruptedException {
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-2000)");
		basepage.elementWait(3000);
		accountsettingpage.prBusinessSettingTab().click();
		basepage.elementWait(3000);
		bussinessinfrpage.patientProfitType(genericMethods.ConfigFile("profitType"));
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,50000)");
		bussinessinfrpage.savechangesBusinesssetting().click();
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(bussinessinfrpage.stripeButton().isDisplayed(), true,
				"Stripe Button  Should be dispalyed", "Stripe Button  Should not be dispalyed");
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		bussinessinfrpage.stripeButton().click();
		basepage.elementWait(5000);
		Parent_Window = driver.getWindowHandle();
		winHandleBefore = driver.getWindowHandle();
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		SoftAssertions.verifyEquals(connectwthStripe.Skipstripe().isDisplayed(), true,
				"Skip Stripe Button  Should be dispalyed", "Skip Stripe Button  Should not be dispalyed");
		basepage.elementWait(3000);
		connectwthStripe.Skipstripe().click();
		// Close the new window, if that window no more required
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(5000);
		bussinessinfrpage.savechangesBusinesssetting().click();
		basepage.elementWait(5000);
		driver.close();
		basepage.elementWait(3000);
		driver.switchTo().window(Parent_Window);
		SoftAssertions.verifyEquals(proffinformation.accountSettingPageText().isDisplayed(), true,
				"Land to Account Setting Page", "Not Land Account setting Page");
		// Switch back to original browser (first window)
		bussinessinfrpage.savechangesBusinesssetting().click();
		basepage.elementWait(5000);
		driver.navigate().refresh();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(bussinessinfrpage.accessmyStripeaccount().isDisplayed(), true,
				" Access my Stripe Account is Displayed", " Access my Stripe Account is  not Displayed");
		SoftAssertions.verifyEquals(patitentsrecoemened.prRecommenedButton().isDisplayed(), true,
				"Recommenedation is Displayed", "Recommenedation is  not Displayed");
		SoftAssertions.verifyEquals(pracinvitepatients.prAddNewButtonInTop().isDisplayed(), true,
				"ADD NEW button is Displayed in top ", "ADD NEW button is  not Displayed in top");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Practioner Credential Upload
	@SuppressWarnings({ "static-access" })
	@Test(priority = 9)
	public void practitionerFileUploadInProfileCredentials() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,-2000)");
		basepage.elementWait(5000);
		practionerdispansary.prCredentilsTab().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(3000);
		// .jpg file
		proffCertification.prProfessionalCredentialsDocUploadValidation(
				System.getProperty("user.dir") + genericMethods.ConfigFile("FileUpload"));
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 1000)

	public void dashBoardVerficationAfterCompleting() throws InterruptedException, BiffException {

		basepage.elementWait(5000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,-2000)");
		basepage.elementWait(5000);
		proffinformation.dashBoard().click();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(proffinformation.uploadProfessionalCredentials1(), "true",
				"Upload your Professional Credentials Text is not displayed",
				"Upload your Professional Credentials is displayed");
		SoftAssertions.verifyEquals(proffinformation.preferredproductaccess1(), "true",
				"Select your preferred product access Text is not displayed",
				"Select your preferred product access Text is displayed");
		SoftAssertions.verifyEquals(proffinformation.patientPricing1(), "true",
				"Set your Patient pricing Text is not displayed", "Set your Patient pricing Text is displayed");
		SoftAssertions.verifyEquals(proffinformation.salesTax1(), "true",
				"Confirm your Sales Tax requirements Text is not displayed",
				"Confirm your Sales Tax requirements Text is displayed");

		SoftAssertions.verifyEquals(proffinformation.stripeAccount1(), "true",
				"Set up your Stripe account for payments Text is not displayed",
				"Set up your Stripe account for payments Text is displayed");
		SoftAssertions.verifyEquals(proffinformation.patientAcessOption1(), "true",
				"Select Your Patient Access Options Text is Displayed",
				"Select Your Patient Access Options Text is not Displayed");
		SoftAssertions.verifyEquals(patitentsrecoemened.prRecommenedButton().isDisplayed(), true,
				"Recommeneded Tab  is displayed", "Recommeneded Tab is not displayed");
		SoftAssertions.verifyEquals(pracinvitepatients.prAddNewButtonInTop().isDisplayed(), true,
				"ADD NEW button is displayed  ", "ADD NEW button is  not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@AfterClass
	public void closeAPP() {

		driver.quit();
	}

}
