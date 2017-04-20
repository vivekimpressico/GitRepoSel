package com.wellevate.salestax;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;
import com.wellevate.utilities.StateListAdd;

import jxl.read.biff.BiffException;

public class TestPractitionerAddDeleteAdditionalStates {
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
	public void loginPractitioner() throws InterruptedException, IOException {
		basepage.elementWait(2000);
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("adddeleteStateOptinemailPractitioner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		basepage.implicitywait(1000);
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
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
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(3000);
		// Sales Tax OptIn
	}

	// Add state to Additional state list
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 3)
	public void addDeleteStateToListInAdditionalState() throws InterruptedException, IOException {
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		praSalesTax.addanotherState().click();
		basepage.elementWait(3000);
		ArrayList<String> afterstatelist = praSalesTax.additionalOptInStateName1();
		basepage.elementWait(3000);
		praSalesTax.salesTaxState1("Alaska");
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		accountsettingpage.profileSetUpTab().click();
		basepage.elementWait(3000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(3000);
		praSalesTax.salesTaxStateDeletestate(0);
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		ArrayList<String> beforestatelist = praSalesTax.additionalOptInStateName1();
		basepage.elementWait(5000);
		ArrayList<String> StateListMatches = new ArrayList<String>();
		// make sure that the value of every <option> element equals the
		// expected value
		for (int i = 0; i < beforestatelist.size(); i++) {
			String optionValue = afterstatelist.get(i);
			if (optionValue.equals(beforestatelist.get(i))) {
				System.out.println("passed on: " + optionValue);
				StateListMatches.add(optionValue);
			} else {
				System.out.println("failed on: " + optionValue);
			}
		}
		SoftAssertions.verifyEquals(StateListMatches, afterstatelist, "State name matches", "State name matches");

	}
	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
