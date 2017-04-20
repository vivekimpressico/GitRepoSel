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

public class TestNonNexusStateSalesTaxValidation {
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
		jse = (JavascriptExecutor) driver;
		basepage = new BasePage();
		bussinessinfrpage = new BusinessInformationPage(driver);
		dispaensaryaccsetting = new DispensaryPageAccountSetting(driver);
	}
	// Login to account using Practitioner Credential

	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPractitioner() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		pracLogin.prEmail().sendKeys("testnonnexus15@yopmail.com");
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		basepage.implicitywait(1000);
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Sales Tax OptIn and agree Checkbox
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 2)
	public void practitionerSalesTaxVisibility() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		accsettingapp.accountSetting();
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(accountsettingpage.prSalesTaxTab().isDisplayed(), true,
				"Sales Tax Tab is  displayed", "Sales Tax Tab is not displayed");
		if (practionerdispansary.salesTaxChangeIcon().isDisplayed()) {
			accountsettingpage.prSalesTaxTab().click();
			basepage.elementWait(3000);
			if (praSalesTax.prSalesTaxCheckBox().isSelected()) {
				SoftAssertions.verifyEquals(praSalesTax.prSalesTaxCheckBox().isSelected(), true,
						"Agree Sales Tax is Checked", "Agree Sales Tax is not Checked");
			} else if (!praSalesTax.prSalesTaxCheckBox().isSelected()) {

				SoftAssertions.verifyEquals(praSalesTax.prSalesTaxCheckBox().isSelected(), false,
						"Agree Sales Tax is not Checked", "Agree Sales Tax is Checked");
			}
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	// Professional Sales Tax collection
	@Test(priority = 3)
	public void practitionersalesTaxCollectionRadioButton() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		if (praSalesTax.prOptInSalesTaxCheckBoxTexable().isSelected()) {
			SoftAssertions.verifyEquals(praSalesTax.prOptInSalesTaxCheckBoxTexable().isSelected(), true,
					"OptIn radio button is Selected", "OptIn radio button is not Selected");
		} else if (!praSalesTax.prOptInSalesTaxCheckBoxTexable().isSelected()) {

			SoftAssertions.verifyEquals(praSalesTax.prOptInSalesTaxCheckBoxTexable().isSelected(), false,
					"OptIn radio button is not Selected", "OptIn radio button is  Selected");
		}

		if (praSalesTax.prOptOutSalesTaxCheckBoxTexable().isSelected()) {
			SoftAssertions.verifyEquals(praSalesTax.prOptOutSalesTaxCheckBoxTexable().isSelected(), true,
					"OptOut radio button is Selected", "OptOut radio button is not Selected");
		} else if (!praSalesTax.prOptOutSalesTaxCheckBoxTexable().isSelected()) {

			SoftAssertions.verifyEquals(praSalesTax.prOptOutSalesTaxCheckBoxTexable().isSelected(), false,
					"OptOut radio button is not Selected", "OptOut radio button is  Selected");
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	// Sales Tax Save Changes button
	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void practitionerSalesTaxSubmitButtonAfterChanges() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
				"Sales Tax Submit Button is not displayed before Selecting Mandatory Fileld",
				"Sales Tax Submit Button is displayed before Selecting Mandatory Fileld");
		if (!praSalesTax.prSalesTaxCheckBox().isSelected()) {
			praSalesTax.prSalesTaxCheckBox().click();
			basepage.elementWait(3000);
			SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
					"Sales Tax Submit Button is not displayed after agree Sales tax CheckBox is Checked",
					"Sales Tax Submit Button is displayed after agree Sales tax CheckBox is Checked");
		}
		if (!praSalesTax.prSalesTaxCheckBox().isSelected()) {
			praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
			SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
					"Sales Tax Submit Button is displayed after after agree Sales tax checked and Professional Sales Tax collection selected ",
					"Sales Tax Submit Button is not displayed after agree Sales tax checked and Professional Sales Tax collection selected ");
		}
		if (!praSalesTax.prSalesTaxCheckBox().isSelected()) {
			praSalesTax.prSalesTaxCheckBox().click();
			basepage.elementWait(3000);
			praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
			basepage.elementWait(3000);
			SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
					"Sales Tax Submit Button is not displayed after Professional Sales Tax collection selected",
					"Sales Tax Submit Button is displayed after Professional Sales Tax collection selected");
		}
		basepage.elementWait(3000);
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void practitionerAgreeCheckbox() throws InterruptedException, IOException, BiffException {

		basepage.elementWait(5000);
		praSalesTax.prSalesTaxCheckBox().click();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
				"Sales Tax Submit Button is not displayed after Selecting Checkbox",
				"Sales Tax Submit Button is displayed before Selecting Checkbox");
		basepage.elementWait(3000);
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 6)
	public void practitionerResaleCertificationError() throws InterruptedException, IOException, BiffException {
		basepage.elementWait(5000);
		praSalesTax.prSalesTaxCheckBox().click();
		basepage.elementWait(5000);
		praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
				"Sales Tax Submit Button is not displayed after selecting checkbox and Resale certification",
				"Sales Tax Submit Button is displayed after selecting checkbox and Resale certification");
		basepage.elementWait(3000);
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void practitionerUploadCertificationErrorWithoutFile() throws InterruptedException, IOException, BiffException {
		basepage.elementWait(5000);
		praSalesTax.prSalesTaxCheckBox().click();
		basepage.elementWait(3000);
		praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
		basepage.elementWait(3000);
		praSalesTax.salesTaxResaleCertificationField1(0, "avc 1233@");
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxResaleCertificationError().isDisplayed(), true,
				"File Upload error message is displayed", "File Upload error message is not displayed");
		SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
				"Sales Tax Submit Button is not displayed after selecting checkbox and Resale certification text field",
				"Sales Tax Submit Button is displayed after selecting checkbox and Resale certification text field");
		basepage.elementWait(3000);
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 8)
	public void practitionerUploadCertificationError() throws InterruptedException, IOException, BiffException {
		basepage.elementWait(5000);
		praSalesTax.prSalesTaxCheckBox().click();
		basepage.elementWait(3000);
		praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
		basepage.elementWait(3000);
		praSalesTax.salesTaxResaleCertificationField1(0, "12334");
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxFileUploadError().isDisplayed(), true,
				"File Upload error message is displayed", "File Upload error message is not displayed");
		SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
				"Sales Tax Submit Button is not displayed after selecting checkbox and Resale certification text field",
				"Sales Tax Submit Button is displayed after selecting checkbox and Resale certification text field");
		basepage.elementWait(3000);
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 9)
	public void practitionerUploadCertification() throws InterruptedException, IOException, BiffException {
		basepage.elementWait(5000);
		praSalesTax.prSalesTaxCheckBox().click();
		basepage.elementWait(3000);
		praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
		basepage.elementWait(3000);
		praSalesTax.salesTaxResaleCertificationField1(0, "12334");
		basepage.elementWait(3000);
		praSalesTax.salesTaxFileUpload().click();
		basepage.elementWait(3000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("pngImage"));
		basepage.elementWait(20000);
		SoftAssertions.verifyEquals(proffinformation.prProfessionalRegistationSubmit().isDisplayed(), true,
				"Sales Tax Submit Button is displayed after selecting checkbox and Resale certification text field",
				"Sales Tax Submit Button is not displayed after selecting checkbox and Resale certification text field");
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 13)
	public void practitionerAdditionalFileUploadMaxsizeImage() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		praSalesTax.prSalesTaxCheckBox().click();
		basepage.elementWait(3000);
		praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
		basepage.elementWait(3000);
		praSalesTax.salesTaxResaleCertificationField1(0, "12334");
		basepage.elementWait(3000);
		praSalesTax.salesTaxFileUpload().click();
		basepage.elementWait(3000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("biggerImage"));
		basepage.elementWait(20000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddFileError(), "true",
				"Incorrect file size, max 5MB error message is not displayed",
				"Incorrect file size, max 5MB error message is displayed");
		BasePage.elementWait(5000);
		driver.navigate().refresh();
		basepage.elementWait(5000);
		basepage.AlertAccept();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@SuppressWarnings("static-access")
	@Test(priority = 14)
	public void practitionerAdditionalFileUploadWrongFormat() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		praSalesTax.prSalesTaxCheckBox().click();
		basepage.elementWait(3000);
		praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
		basepage.elementWait(3000);
		praSalesTax.salesTaxResaleCertificationField1(0, "12334");
		basepage.elementWait(3000);
		praSalesTax.salesTaxFileUpload().click();
		basepage.elementWait(3000);
		genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("wrongFormatImage"));
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(praSalesTax.salesTaxaddFileError(), "true",
				"Incorrect file type, please use JPG, GIF, PNG or PDF.error message is not displayed",
				"Incorrect file type, please use JPG, GIF, PNG or PDF.error message is displayed");
		BasePage.elementWait(5000);
		driver.navigate().refresh();
		basepage.elementWait(3000);
		basepage.AlertAccept();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}
	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
