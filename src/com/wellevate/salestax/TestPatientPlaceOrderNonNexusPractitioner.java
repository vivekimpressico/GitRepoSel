package com.wellevate.salestax;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerSalesTaxObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PatientsAddressInformation_Object;
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

public class TestPatientPlaceOrderNonNexusPractitioner extends Data_Provider {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	PractitionerPatientLoginObject pracLogin;
	GenericsMethods genericMethods;
	PractitionerInvitesPatientObject pracinvitepatients;
	PractitionerRecommendation_Object addpatitentsrecoemened;
	YopmailVerifyEmailAddressObject yopmail;
	LogoutApplication logoutapp;
	SignUpPractitionerObject signupPrac;
	PatientsAddressInformation_Object patientsaddInfrm;
	PatientSubmitOrderObject patsubmitorder;
	String orderIDFromApplication;
	String strDispensary;
	ProfessionalInformationPractObject proffinformation;
	BasePage basepage;
	AccountSettingIconInApplication accsettingapp;
	AccountSettingsPage accountsettingpage;
	PractitionerSalesTaxObject praSalesTax;
	String noTax ;
	double taxcollection;
	int taxcollect ;
	JavascriptExecutor jse;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		accsettingapp = new AccountSettingIconInApplication(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		genericMethods = new GenericsMethods();
		pracinvitepatients = new PractitionerInvitesPatientObject(driver);
		addpatitentsrecoemened = new PractitionerRecommendation_Object(driver);
		yopmail = new YopmailVerifyEmailAddressObject(driver);
		logoutapp = new LogoutApplication(driver);
		signupPrac = new SignUpPractitionerObject(driver);
		patientsaddInfrm = new PatientsAddressInformation_Object(driver);
		patsubmitorder = new PatientSubmitOrderObject(driver);
		ExcelReaderExpected.connectExcel();
		proffinformation = new ProfessionalInformationPractObject(driver);
		basepage = new BasePage();
		accountsettingpage = new AccountSettingsPage(driver);
		praSalesTax = new PractitionerSalesTaxObject(driver);
		jse = (JavascriptExecutor) driver;
	}
	// Login to account using Practitioner Credential

	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPractitoner() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("SalesTaxNexusPractitioner"));
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
		basepage.elementWait(5000);
		// Sales Tax OptIn
		praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
		basepage.elementWait(5000);
		proffinformation.prProfessionalRegistationSubmit().click();
		logoutapp.LogoutPractitioner();
		basepage.elementWait(3000);
		
	}
	// Login to account using Practitioner Credential

	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void loginPatients() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("salesTaxNonTexablePatients"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		basepage.implicitywait(1000);
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
	}

	// Submit order for new user
	@SuppressWarnings("static-access")
	@Test(priority = 4, dataProvider = "creditCardInformation2")
	public void addPrducts(String name, String cardno, String cvv, String month, String year)
			throws InterruptedException, IOException {

		patsubmitorder.shopDispensary().click();
		basepage.elementWait(5000);
		// patsubmitorder.addProductsToCart(2);
		List<WebElement> Li = patsubmitorder.addProductsToCart();
		patsubmitorder.addProduct(Li, 3);
		basepage.elementWait(6000);
		patsubmitorder.cartTab().click();
		basepage.elementWait(5000);
		patsubmitorder.checkOutTab().click();
		basepage.elementWait(5000);
		patsubmitorder.vboRemoveButton();
		basepage.elementWait(5000);
		patsubmitorder.BillingAddressSelect(0);
		basepage.elementWait(5000);
		patsubmitorder.shippingAdressContinueBtn().click();
		basepage.elementWait(5000);
		patsubmitorder.vboRemoveButton();
		basepage.elementWait(5000);
		patsubmitorder.confirmShippingAddress(genericMethods.ConfigFile("confirmShippingAddress"));
		basepage.elementWait(5000);
		patsubmitorder.randomShippingMethod();
		basepage.elementWait(5000);
		patsubmitorder.shippingMetodNextStep().click();
		basepage.elementWait(5000);
		patsubmitorder.creditCard(genericMethods.ConfigFile("newCreditCard"), name, cardno, cvv, month, year);
		basepage.elementWait(5000);
		 patsubmitorder.saveCardholderCreditCard();
		basepage.elementWait(3000);
		patsubmitorder.nextStepCreditCard().click();
		basepage.elementWait(7000);
		patsubmitorder.closepPopup();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(3000);
		String tax = patsubmitorder.taxAmountCheckoutPage().getText().replace("$", "");
		basepage.elementWait(3000);
		double taxAmount = Double.parseDouble(tax);
		noTax = genericMethods.ConfigFile("salesTaxCollection");
		taxcollection = Double.parseDouble(noTax);
		taxcollect = Double.compare(taxAmount, taxcollection);
		if (taxcollect > 0.00) {
			System.out.println("obj1 is greater than obj2");
			SoftAssertions.verifyEquals(taxcollect > 0.00, true,
					" Sales Tax is collect from this order from Nexus State",
					"Sales Tax is not collect from this order from Nexus State");
		} else if (taxcollect <= 0.00) {
			System.out.println("obj1 is less than obj2");
			SoftAssertions.verifyEquals(taxcollect <= 0.00, true,
					"Sales Tax  is not collect from this order from Nexus State",
					"Sales Tax is collect from this order from Nexus State");
		} else {
			System.out.println("obj1 is equal to obj2");
		}
		patsubmitorder.placeOrder().click();
		basepage.elementWait(5000);
		orderIDFromApplication = patsubmitorder.orderNumber();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(patsubmitorder.orderSucessfulText().isDisplayed(), true,
				"Your order has been received text is displayed", "Your order has been received text not displayed");
		basepage.elementWait(5000);
		patsubmitorder.returnHoepage().click();
		basepage.elementWait(5000);
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();

	}
}
