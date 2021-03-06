package com.wellevate.salestax;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPractitionerAdditionalstateNexus {
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
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	String actproffessionalcredential, expproffessionalcredential;
	String actproffessionalProductAcess, expproffessionalProductAcess;
	String actproffessionalPricing, expproffessionalPricing;
	String actproffessionalSalesTax, expproffessionalSalesTax;
	String personalSetUpMessage;
	String actproffessionalStripeAccount, expproffessionalStripeAccount;
	String actproffessionalPatientAcessOption, expproffessionalPatientAcessOption;
	BasePage basepage;
	AccountSettingsPage accountsettingpage;
	BusinessInformationPage bussinessinfrpage;
	DispensaryPageAccountSetting dispaensaryaccsetting;
	String salesTaxCheckedBox;
	String optInCheckedBox;
	String ActSalesTaxError, ExpSalesTaxError;
	String ActReSaleCertificationError, ExpReSaleCertificationError;
	String ActUploadFileError, ExpUploadFileError;

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
		wait = new WebDriverWait(driver, 3000);
		basepage = new BasePage();
		bussinessinfrpage = new BusinessInformationPage(driver);
		dispaensaryaccsetting = new DispensaryPageAccountSetting(driver);
	}
	// Login to account using Practitioner Credential

	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPatients() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		pracLogin.prEmail().sendKeys("testsalestax1@yopmail.com");
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		basepage.implicitywait(1000);
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Sales Tax OptIn and agree Checkbox
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 2)
	public void practitionerSalesTax() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		accsettingapp.accountSetting();
		basepage.elementWait(3000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(3000);
	}

	// Sales Tax Save Changes button
	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void practitionerAdditionalstatesNonNexus() throws InterruptedException, IOException {
		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Alabama");
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("- Select State -");
		BasePage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddStateError(), "true",
				"Additional Sale Tax error message is displayed", "Additional Sale Tax error message is not displayed");
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void practitionerAdditionalstatesNexus() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Connecticut");
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("- Select State -");
		BasePage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddStateError(), "true",
				"Additional Sale Tax error message is displayed", "Additional Sale Tax error message is not displayed");
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void practitionerAdditionalstatesNonTaxable() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Alaska");
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("- Select State -");
		BasePage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddStateError(), "true",
				"Additional Sale Tax error message is displayed", "Additional Sale Tax error message is not displayed");
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 6)
	public void practitionerAdditionalstateschanges() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Alabama");
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("- Select State -");
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Alaska");
		BasePage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddStateError(), "false",
				"Additional Sale Tax error message is not displayed", "Additional Sale Tax error message is displayed");
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void practitionerAdditionalResaleCertificationNonNexus() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Alabama");
		BasePage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddResaleCertificateError(), "false",
				"ReSale Certificationerror message is not displayed",
				"ReSale Certification error message is  displayed");
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 8)
	public void practitionerAdditionalResaleCertificationNonTaxable() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Alaska");
		BasePage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddResaleCertificateError(), "false",
				"ReSale Certificationerror message is not displayed",
				"ReSale Certification error message is displayed");
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 9)
	public void practitionerAdditionalResaleCertificationNexus() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Connecticut");
		BasePage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddResaleCertificateError(), "true",
				"ReSale Certificationerror message is displayed",
				"ReSale Certification error message is not displayed");
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 1000)
	public void practitionerAdditionalResaleCertification1() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Connecticut");
		BasePage.elementWait(3000);
		praSalesTax.salesTaxResaleCertificationField1(1, "avc 1233@");
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddResaleCertificateError(), "true",
				"ReSale Certificationerror message is displayed",
				"ReSale Certification error message is not displayed");
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 11)
	public void practitionerAdditionalFile() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Connecticut");
		BasePage.elementWait(3000);
		praSalesTax.salesTaxResaleCertificationField1(1, "1233");
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddFileError(), "true",
				"File Upload error message is displayed", "File Upload error message is not displayed");
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 12)
	public void practitionerAdditionalFileUploadpng() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Connecticut");
		BasePage.elementWait(3000);
		praSalesTax.salesTaxResaleCertificationField1(1, "1233");
		basepage.elementWait(3000);
		praSalesTax.salesTaxFileUpload1(1);
		basepage.elementWait(3000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("pngImage"));
		basepage.elementWait(20000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddFileError(), "false",
				"File Upload error message is not displayed", "File Upload error message is displayed");
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 13)
	public void practitionerAdditionalFileUploadMaxsizeImage() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Connecticut");
		BasePage.elementWait(3000);
		praSalesTax.salesTaxResaleCertificationField1(1, "1233");
		basepage.elementWait(3000);
		praSalesTax.salesTaxFileUpload1(1);
		basepage.elementWait(3000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("biggerImage"));
		basepage.elementWait(20000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddFileError(), "true",
				"Incorrect file size, max 5MB error message is not displayed",
				"Incorrect file size, max 5MB error message is displayed");
		BasePage.elementWait(5000);
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 14)
	public void practitionerAdditionalFileUploadWrongFormat() throws InterruptedException, IOException {

		BasePage.elementWait(3000);
		praSalesTax.prOptInSalesTaxCheckBoxNonTexable().click();
		BasePage.elementWait(3000);
		praSalesTax.salesTaxState("Connecticut");
		BasePage.elementWait(3000);
		praSalesTax.salesTaxResaleCertificationField1(1, "1233");
		basepage.elementWait(3000);
		praSalesTax.salesTaxFileUpload1(1);
		basepage.elementWait(3000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("wrongFormatImage"));
		basepage.elementWait(20000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddFileError(), "true",
				"Incorrect file type, please use JPG, GIF, PNG or PDF.error message is not displayed",
				"Incorrect file type, please use JPG, GIF, PNG or PDF.error message is displayed");
		BasePage.elementWait(5000);
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);

	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
