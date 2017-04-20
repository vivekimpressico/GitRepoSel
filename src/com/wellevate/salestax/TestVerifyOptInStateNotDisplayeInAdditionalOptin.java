package com.wellevate.salestax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
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
import com.wellevate.utilities.StateListAdd;

import jxl.read.biff.BiffException;

public class TestVerifyOptInStateNotDisplayeInAdditionalOptin {
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
	ArrayList<String> saleTaxState;
	ArrayList<String> saleTaxState1;
	String actualPatientStateInProfilePage;
	String practitionerStates;
	String url;
	String ActualpractitionerStatesAfterChangeToNonTaxable;
	String practitionerStatesChangeNonTaxable;
	String PractitionerProfileState;
	String taxStatesOptIn;
	String taxFromOrder;
	PractitionerDispensaryObject practionerdispansary;
	String taxStatesOptOut;
	String taxStatesOptOut1;
	String pageAppear;
	String taxStatesOptOutNotAvilable;
	String taxStatesOptOutNotAvilable1;
	String taxNotTakenFromOrder;
	ArrayList<String> addtitonalOptInState;
	ArrayList<String> profileStatepractitioner;
	ArrayList<String> practitionerStatesAfterChangeToNonTaxable;
	ArrayList<String> practitionerStatesAfterChangeToNonNexus;
	ArrayList<String> practitionerStatesAfterChangeToNexus;
	ArrayList<String> profileStatepractitionerNonTaxable;
	String ActualpractitionerStatesAfterChangeToNonNexus;
	String ActualpractitionerStatesAfterChangeToNexus;
	String taxnonTaxablestate;
	ProductSearchObject productsearch;
	String listpage = "list";
	String createpage = "create";
	ArrayList<String> profileStatepractitioner1;
	String practitionerStatesNonNexus;
	String practitionerStatesNonTaxable;
	String practitionerStatesToNexus;
	ArrayList<String> profileStatepractitionerNexus;

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
		practionerdispansary = new PractitionerDispensaryObject(driver);
		jse = (JavascriptExecutor) driver;
		accountsettingpage = new AccountSettingsPage(driver);
		praSalesTax = new PractitionerSalesTaxObject(driver);
		stateListAdd = new StateListAdd();
		productsearch = new ProductSearchObject(driver);
		nonnexusStateList = (ArrayList<String>) stateListAdd.NonNexus();
		nexusStateList = (ArrayList<String>) stateListAdd.Nexus();
		nonTaxbleStateList = (ArrayList<String>) stateListAdd.NonTaxable();

	}

	// Login to account using Practitioner Credential
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPractitioner() throws InterruptedException, IOException {
		basepage.elementWait(2000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(2000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("NonNexussalestaxstatechange"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		basepage.elementWait(2000);
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void practitionerProfilestateNonNonNexus() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		accsettingapp.accountSetting();
		basepage.elementWait(3000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(3000);
		String OptInState = praSalesTax.practitionerbillingRegion().getText();
		basepage.elementWait(3000);
		driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/select")).click();
		basepage.elementWait(3000);
		List<WebElement> elementList = driver.findElements(By.xpath("//table/tbody/tr[2]/td[1]/div/select/option"));
		for (int i = 0; i < elementList.size(); i++) {
			System.out.println(elementList.get(i).getText());
			if (elementList.get(i).getText().equals(OptInState.trim())) {
				System.out.println("State is present");
				break;
			}
			else{
				
			}
		}
	}
}