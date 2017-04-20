package com.wellevate.salestax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
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
import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;
import com.wellevate.utilities.StateListAdd;
import com.wellevate.utilities.StateSearch;

import jxl.read.biff.BiffException;

public class TestPatientPlaceOrderNonTexablePractitioner extends Data_Provider {
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
	String noTax;
	double taxcollection;
	int taxcollect;
	String optInState;
	String optOutState;
	StateListAdd stateListAdd;
	JavascriptExecutor jse;
	String patientsShippingState;
	String ShippingState;
	ArrayList<String> nonnexusStateList;
	ArrayList<String> nexusStateList;
	ArrayList<String> nonTaxbleStateList;
	ArrayList<String> TaxbleProductlist;
	ArrayList<String> taxgroupsstate;
	String actualPatientStates;
	String taxStatesOptIn;
	String taxFromOrder;
	String taxStatesOptOut;
	String taxNotTakenFromOrder;
	ArrayList<String> addtitonalOptInState;
	String taxnonTaxablestate;
	ProductSearchObject productsearch;

	@SuppressWarnings("unused")
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
		jse = (JavascriptExecutor) driver;
		accountsettingpage = new AccountSettingsPage(driver);
		praSalesTax = new PractitionerSalesTaxObject(driver);
		stateListAdd = new StateListAdd();
		productsearch = new ProductSearchObject(driver);

	}

	// Login to account using Practitioner Credential
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPractitioner1() throws InterruptedException, IOException {
		basepage.elementWait(2000);
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("salesTaxNonNexusPractitioner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		basepage.implicitywait(1000);
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
	}

	// Sales Tax OptIn and agree Checkbox
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 2)
	public void practitionerSalesTax1() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		accsettingapp.accountSetting();
		basepage.elementWait(3000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(3000);
		// Sales Tax OptIn
		optInState = praSalesTax.verifyTaxStateSelectionOptIn();
		optOutState = praSalesTax.verifyTaxStateSelectionOptOut();

		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(3000);
		// Additional state
		addtitonalOptInState = new ArrayList<String>();
		if (driver.findElement(By.xpath("//select[@name='state']")).isDisplayed()) {
			List<WebElement> optitonalOptIn = praSalesTax.additionalOptInState();
			for (int i = 0; i < optitonalOptIn.size(); i++) {
				addtitonalOptInState.add(optitonalOptIn.get(i).getText());
			}
		}
		basepage.elementWait(5000);
		logoutapp.LogoutPatient();
		basepage.elementWait(3000);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void loginPatients1() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		// pracLogin.prEmail().sendKeys(genericMethods.dataprp("salesTaxNonNexusPatientsNonNexusPrac"));
		// pracLogin.prEmail().sendKeys(genericMethods.dataprp("salesTaxNonTaxblePatientNonNexusPrac"));
		// pracLogin.prEmail().sendKeys(genericMethods.dataprp("salesTaxNexusPatientNonNexusPrac"));
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("salesTaxNexusPatientNonNexusPrac1"));

		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		basepage.implicitywait(1000);
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
	}

	@Test(priority = 4)
	@SuppressWarnings("static-access")
	public void addPrductsgetPatientOrderState1() throws InterruptedException, IOException {
		nonnexusStateList = (ArrayList<String>) stateListAdd.NonNexus();
		nexusStateList = (ArrayList<String>) stateListAdd.Nexus();
		nonTaxbleStateList = (ArrayList<String>) stateListAdd.NonTaxable();
		TaxbleProductlist = (ArrayList<String>) stateListAdd.TaxableProducts();

		patsubmitorder.shopDispensary().click();
		basepage.elementWait(5000);

		for (int i = 0; i < TaxbleProductlist.size(); i++) {
			String productlist = TaxbleProductlist.get(i);
			productsearch.searchProductsDispensary().clear();
			basepage.elementWait(3000);
			productsearch.searchProductsDispensary().sendKeys(productlist);
			basepage.elementWait(3000);
			List<WebElement> searchRes = productsearch.getSerchProducts();
			if (searchRes.size() > 0) {
				searchRes.get(0);
				basepage.elementWait(3000);
				productsearch.clickOnProductssearch(0);
				basepage.elementWait(3000);
				productsearch.addcartProductDetails().click();
				basepage.elementWait(3000);
			} else {
				Reporter.log("No Search Results.");
			}
		}
		basepage.elementWait(6000);
		patsubmitorder.cartTab().click();
		basepage.elementWait(3000);
		patsubmitorder.checkOutTab().click();
		basepage.elementWait(5000);
		patsubmitorder.BillingAddressSelect(0);
		patsubmitorder.confirmShippingAddress(genericMethods.ConfigFile("confirmShippingAddress"));
		basepage.implicitywait(3000);
		ShippingState = patsubmitorder.shippingState().getText();
		String state = ShippingState.split(",")[2].trim();
		patientsShippingState = state.replaceAll("[0-9]+", " ").replaceAll("[-+.^:,]", "").trim();
		basepage.elementWait(5000);
		taxgroupsstate = new ArrayList<String>();
		for (int i = 0; i < nonnexusStateList.size(); i++) {
			String patientstate = nonnexusStateList.get(i);
			if (patientsShippingState.equals(nonnexusStateList.get(i))) {
				System.out.println("non-nexus on: " + patientsShippingState);
				taxgroupsstate.add(patientstate);
				SoftAssertions.verifyEquals(nonnexusStateList.get(i), patientsShippingState,
						"Sales Tax State name matches in NonNexus State List",
						"Sales Tax State not name matches in NonNexus State List");
				break;
			}
		}
		for (int i = 0; i < nexusStateList.size(); i++) {
			String patientstate = nexusStateList.get(i);
			if (patientsShippingState.equals(nexusStateList.get(i))) {
				System.out.println("nexus on: " + patientsShippingState);
				taxgroupsstate.add(patientstate);
				SoftAssertions.verifyEquals(nonnexusStateList.get(i), patientsShippingState,
						"Sales Tax State name matches in Nexus State List",
						"Sales Tax State name not matches in Nexus State List");
				break;
			}
		}
		for (int i = 0; i < nonTaxbleStateList.size(); i++) {
			String patientstate = nonTaxbleStateList.get(i);
			if (patientsShippingState.equals(nonTaxbleStateList.get(i))) {
				System.out.println("non-taxable on: " + patientsShippingState);
				taxgroupsstate.add(patientstate);
				SoftAssertions.verifyEquals(nonTaxbleStateList.get(i), patientsShippingState,
						"Sales Tax State name matches in NonTaxable State List",
						"Sales Tax State name not matches in NonTaxable State List");
				taxStatesOptOut = "Tax should not be collected";
				break;
			}
		}
		String actualPatientStates1 = taxgroupsstate.toString();
		actualPatientStates = actualPatientStates1.replaceAll("[\\[\\]]", "");

		if (actualPatientStates.equalsIgnoreCase(optInState)) {
			System.out.println("sales tax taken");
			taxStatesOptIn = "Tax should be collected";
		} else if (actualPatientStates.equalsIgnoreCase(optOutState)) {
			System.out.println("sales tax not taken");
			taxStatesOptOut = "Tax should not be collected";
		}
		for (int i = 0; i < addtitonalOptInState.size(); i++) {
			String additinalOptIn = addtitonalOptInState.get(i);
			if (actualPatientStates.equalsIgnoreCase(additinalOptIn)) {
				System.out.println("sales tax taken");
				taxStatesOptIn = "Tax should be collected";
				break;
			}
		}

	}

	@Test(priority = 5, dataProvider = "creditCardInformation2")
	@SuppressWarnings("static-access")
	public void TaxInOrder(String name, String cardno, String cvv, String month, String year)
			throws InterruptedException, IOException {
		basepage.elementWait(7000);
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
		basepage.elementWait(5000);
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