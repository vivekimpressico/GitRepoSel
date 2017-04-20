package com.wellevate.salestax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
import com.wellevate.utilities.SoftAssertions;
import com.wellevate.utilities.StateListAdd;

import jxl.read.biff.BiffException;

public class TestVerifyTaxableStateCanOptoutNonTaxableOrNot {
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
	ArrayList<String> taxgroupsstate1;
	String actualPatientStates;
	String practitonerstates;
	String practitonerstates1;
	String taxStatesOptIn;
	String taxFromOrder;
	PractitionerDispensaryObject practionerdispansary;
	String taxStatesOptOut;
	String taxStatesOptOut1;
	String taxStatesOptOutNotAvilable;
	String taxStatesOptOutNotAvilable1;
	String taxNotTakenFromOrder;
	ArrayList<String> addtitonalOptInState;
	ArrayList<String> profileStatepractitoner;
	ArrayList<String> profileStatepractitoner1;
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
	public void practitionerProfilestateGet() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		accsettingapp.accountSetting();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		profileStatepractitoner = new ArrayList<String>();
		if (driver.findElement(By.xpath("//select[@name='region']")).isDisplayed()) {
			List<WebElement> profileState = praSalesTax.profileSetUPState();
			for (int i = 0; i < profileState.size(); i++) {
				profileStatepractitoner.add(profileState.get(i).getText());
			}
		}
	}

	// Practitioner OptOut State
	@Test(priority = 3)
	@SuppressWarnings("static-access")
	public void profileStateVerifyWithStateList() throws InterruptedException, IOException {
		String actualPatientStates1 = profileStatepractitoner.toString();
		practitonerstates = actualPatientStates1.replaceAll("[\\[\\]]", "");
		basepage.elementWait(5000);
		taxgroupsstate = new ArrayList<String>();
		for (int i = 0; i < nonnexusStateList.size(); i++) {
			String patientstate = nonnexusStateList.get(i);
			if (practitonerstates.equals(nonnexusStateList.get(i))) {
				basepage.elementWait(5000);
				taxgroupsstate.add(patientstate);
				SoftAssertions.verifyEquals(nonnexusStateList.get(i), practitonerstates,
						"Sales Tax State name matches in NonNexus State List",
						"Sales Tax State not name matches in NonNexus State List");
				taxStatesOptOut = "OptOut avilable";
				break;
			}
		}
		for (int i = 0; i < nexusStateList.size(); i++) {
			String patientstate = nexusStateList.get(i);
			if (practitonerstates.equals(nexusStateList.get(i))) {
				basepage.elementWait(5000);
				taxgroupsstate.add(patientstate);
				SoftAssertions.verifyEquals(nexusStateList.get(i), practitonerstates,
						"Sales Tax State name matches in Nexus State List",
						"Sales Tax State name not matches in Nexus State List");
				taxStatesOptOut = "OptOut avilable";
				break;
			}
		}
		for (int i = 0; i < nonTaxbleStateList.size(); i++) {
			String patientstate = nonTaxbleStateList.get(i);
			if (practitonerstates.equals(nonTaxbleStateList.get(i))) {
				basepage.elementWait(5000);
				taxgroupsstate.add(patientstate);
				SoftAssertions.verifyEquals(nonTaxbleStateList.get(i), practitonerstates,
						"Sales Tax State name matches in NonTaxable State List",
						"Sales Tax State name not matches in NonTaxable State List");
				taxStatesOptOutNotAvilable = "OptOut not avilable";
				break;
			}
		}
		basepage.elementWait(5000);
	}

	// Verify Practitioner sales tax state
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 4)
	public void practitionerSalesTaxstate() throws InterruptedException, IOException {
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		String state = null;
		int i = driver.findElements(By.xpath("//input[@id='radioOptOut']")).size();
		basepage.elementWait(5000);
		if (i > 0) {
			state = "OptOut avilable";
			SoftAssertions.verifyEquals(taxStatesOptOut.trim(), state.trim(),
					"OptOut button is displayed:" + practitonerstates,
					"OptOut button is  not displayed :" + practitonerstates);
		} else {
			state = "OptOut not avilable";
			SoftAssertions.verifyEquals(taxStatesOptOutNotAvilable.trim(), state.trim(),
					"OptOut button is not displayed :" + practitonerstates,
					"OptOut button is displayed :" + practitonerstates);
		}
	}

	// Change state to nontaxable
	@SuppressWarnings({ "static-access" })
	@Test(priority = 5)
	public void profilepractitionerStatechangesProfile() throws InterruptedException, IOException {
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		accountsettingpage.profileSetUpTab().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		practionerdispansary.prProfessionalAddressStateDispensary("New Hampshire");
		basepage.elementWait(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(3000);
		proffinformation.stateChangeConformation();
		basepage.elementWait(3000);
	}

	// Get state
	@SuppressWarnings("static-access")
	@Test(priority = 6)
	public void practitionerSalesTaxProfileSetUp() throws InterruptedException, IOException {
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		profileStatepractitoner1 = new ArrayList<String>();
		if (driver.findElement(By.xpath("//select[@name='region']")).isDisplayed()) {
			List<WebElement> profileState = praSalesTax.profileSetUPState();
			for (int i = 0; i < profileState.size(); i++) {
				profileStatepractitoner1.add(profileState.get(i).getText());
			}
		}
	}

	// Match with state list
	@Test(priority = 7)
	@SuppressWarnings("static-access")
	public void stateVerfication() throws InterruptedException, IOException {

		String actualPatientStates1 = profileStatepractitoner1.toString();
		practitonerstates1 = actualPatientStates1.replaceAll("[\\[\\]]", "");
		basepage.elementWait(5000);
		taxgroupsstate1 = new ArrayList<String>();
		for (int i = 0; i < nonnexusStateList.size(); i++) {
			String patientstate = nonnexusStateList.get(i);
			if (practitonerstates1.equals(nonnexusStateList.get(i))) {
				basepage.elementWait(5000);
				taxgroupsstate1.add(patientstate);
				SoftAssertions.verifyEquals(nonnexusStateList.get(i), practitonerstates,
						"Sales Tax State name matches in NonNexus State List :",
						"Sales Tax State not name matches in NonNexus State List");
				taxStatesOptOut1 = "OptOut avilable";
				break;
			}
		}
		for (int i = 0; i < nexusStateList.size(); i++) {
			String patientstate = nexusStateList.get(i);
			if (practitonerstates1.equals(nexusStateList.get(i))) {
				basepage.elementWait(5000);
				taxgroupsstate1.add(patientstate);
				SoftAssertions.verifyEquals(nexusStateList.get(i), practitonerstates,
						"Sales Tax State name matches in Nexus State List",
						"Sales Tax State name not matches in Nexus State List");
				taxStatesOptOut1 = "OptOut avilable";
				break;
			}
		}
		for (int i = 0; i < nonTaxbleStateList.size(); i++) {
			String patientstate = nonTaxbleStateList.get(i);
			if (practitonerstates1.equals(nonTaxbleStateList.get(i))) {
				basepage.elementWait(5000);
				taxgroupsstate1.add(patientstate);
				SoftAssertions.verifyEquals(nonTaxbleStateList.get(i), practitonerstates,
						"Sales Tax State name matches in NonTaxable State List",
						"Sales Tax State name not matches in NonTaxable State List");
				taxStatesOptOutNotAvilable1 = "OptOut not avilable";
				break;
			}
		}
		basepage.elementWait(5000);
	}

	// Practitioner OptOut State
	@SuppressWarnings({ "static-access" })
	@Test(priority = 8)
	public void practitionerSalesTax() throws InterruptedException, IOException {
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		String state1 = null;
		int i = driver.findElements(By.xpath("//input[@id='radioOptOut']")).size();
		basepage.elementWait(7000);
		if (i > 0) {
			state1 = "OptOut avilable";
			SoftAssertions.verifyEquals(taxStatesOptOut1.trim(), state1.trim(), "OptOut button is displayed",
					"OptOut button is  not displayed");
		} else {
			state1 = "OptOut not avilable";
			taxStatesOptOutNotAvilable1 = "OptOut not avilable";
			SoftAssertions.verifyEquals(taxStatesOptOutNotAvilable1.trim(), state1.trim(),
					"OptOut button is not displayed :" + practitonerstates,
					"OptOut button is displayed :" + practitonerstates);
			basepage.elementWait(5000);
		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 9)
	public void profilepractitionerStatechangesProfilePage() throws InterruptedException, IOException {
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		accountsettingpage.profileSetUpTab().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(3000);
		practionerdispansary.prProfessionalAddressStateDispensary("Alabama");
		basepage.elementWait(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(3000);
		proffinformation.stateChangeConformation();
		basepage.elementWait(3000);
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
