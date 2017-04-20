package com.wellevate.salestax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class TestVerifySalesTaxPageAppearForNonNexusStateChanges {
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
	static ArrayList<String> nonnexusStateList;
	static ArrayList<String> nexusStateList;
	static ArrayList<String> nonTaxbleStateList;
	static ArrayList<String> TaxbleProductlist;
	static ArrayList<String> saleTaxState;
	static ArrayList<String> saleTaxState1;
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
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		profileStatepractitioner = new ArrayList<String>();
		if (driver.findElement(By.xpath("//select[@name='region']")).isDisplayed()) {
			List<WebElement> profileState = praSalesTax.profileSetUPState();
			for (int i = 0; i < profileState.size(); i++) {
				profileStatepractitioner.add(profileState.get(i).getText());
				basepage.elementWait(3000);
			}
		}
	}

	// non-Nexus to Nexus State
	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void profileStateVerifyNonNexus() throws InterruptedException, IOException {
		actualPatientStateInProfilePage = profileStatepractitioner.toString();
		practitionerStates = actualPatientStateInProfilePage.replaceAll("[\\[\\]]", "");
		basepage.elementWait(5000);
		saleTaxState = new ArrayList<String>();

		for (int i = 0; i < nonnexusStateList.size(); i++) {
			String patientstate = nonnexusStateList.get(i);
			if (practitionerStates.equals(nonnexusStateList.get(i))) {
				basepage.elementWait(5000);
				saleTaxState.add(patientstate);
				SoftAssertions.verifyEquals(nonnexusStateList.get(i), practitionerStates,
						"Sales Tax State name matches in NonNexus State List",
						"Sales Tax State not name matches in NonNexus State List");
			}
		}
		for (int i = 0; i < nexusStateList.size(); i++) {
			String patientstate = nexusStateList.get(i);
			if (practitionerStates.equals(nexusStateList.get(i))) {
				basepage.elementWait(5000);
				saleTaxState.add(patientstate);
				SoftAssertions.verifyEquals(nexusStateList.get(i), practitionerStates,
						"Sales Tax State name matches in Nexus State List",
						"Sales Tax State name not matches in Nexus State List");
				break;
			}
		}
		for (int i = 0; i < nonTaxbleStateList.size(); i++) {
			String patientstate = nonTaxbleStateList.get(i);
			if (practitionerStates.equals(nonTaxbleStateList.get(i))) {
				basepage.elementWait(5000);
				saleTaxState.add(patientstate);
				SoftAssertions.verifyEquals(nonTaxbleStateList.get(i), practitionerStates,
						"Sales Tax State name matches in NonTaxable State List",
						"Sales Tax State name not matches in NonTaxable State List");
				break;
			}
		}
		basepage.elementWait(5000);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void profilepractitionerStatechangesProfilePage() throws InterruptedException, IOException {
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(3000);
		practionerdispansary.prProfessionalAddressStateDispensary("Alaska");
		basepage.elementWait(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		proffinformation.stateChangeConformation();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void changepractitionerProfilestateNonTaxable() throws InterruptedException, IOException {

		basepage.elementWait(3000);
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		practitionerStatesAfterChangeToNonTaxable = new ArrayList<String>();
		if (driver.findElement(By.xpath("//select[@name='region']")).isDisplayed()) {
			List<WebElement> profileState = praSalesTax.profileSetUPState();
			for (int i = 0; i < profileState.size(); i++) {
				practitionerStatesAfterChangeToNonTaxable.add(profileState.get(i).getText());
			}
		}
	}

	// non-Nexus to Nexus State
	@Test(priority = 6)
	@SuppressWarnings({ "static-access", "unused" })
	public void VerifySalesTaxafterChangetoNonTaxable() throws InterruptedException, IOException {
		String actualPatientStates1 = practitionerStatesAfterChangeToNonTaxable.toString();
		ActualpractitionerStatesAfterChangeToNonTaxable = actualPatientStates1.replaceAll("[\\[\\]]", "");
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,20000)");
		basepage.elementWait(5000);
		int k = driver.findElements(By.xpath("//input[@id='radioOptOut']")).size();
		basepage.elementWait(3000);
		int l = driver.findElements(By.xpath("//label[@for='has_agreed_terms']")).size();
		basepage.elementWait(3000);
		if (l > 0) {
			if (k > 0) {
				praSalesTax.prSalesTaxCheckBox().click();
				basepage.elementWait(3000);
				praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
				basepage.elementWait(3000);
				praSalesTax.salesTaxResaleCertificationField1(0, "12333");
				basepage.elementWait(3000);
				praSalesTax.salesTaxFileUpload().click();
				basepage.elementWait(3000);
				genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("pngImage"));
				basepage.elementWait(20000);
				jse.executeScript("scroll(0,150000)");
				basepage.elementWait(5000);
				proffinformation.prProfessionalRegistationSubmit().click();
			}
		}
		if (k > 0) {
			basepage.elementWait(3000);
			praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
			basepage.elementWait(3000);
			praSalesTax.salesTaxResaleCertificationField1(0, "12333");
			basepage.elementWait(3000);
			praSalesTax.salesTaxFileUpload().click();
			basepage.elementWait(3000);
			genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("pngImage"));
			basepage.elementWait(20000);
			jse.executeScript("scroll(0,150000)");
			basepage.elementWait(5000);
			proffinformation.prProfessionalRegistationSubmit().click();
		} else if (l > 0) {
			praSalesTax.prSalesTaxCheckBox().click();
			basepage.elementWait(5000);
			jse.executeScript("scroll(0,150000)");
			basepage.elementWait(5000);
			proffinformation.prProfessionalRegistationSubmit().click();
			basepage.elementWait(3000);
		} else {
			basepage.elementWait(3000);
			jse.executeScript("scroll(0,2000)");
			proffinformation.prProfessionalRegistationSubmit().click();
			basepage.elementWait(3000);
		}

		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		accountsettingpage.profileSetUpTab().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(3000);
		url = driver.getCurrentUrl();
		pageAppear = url.split("/")[7];
		basepage.elementWait(3000);

		String stateChange = saleTaxState.toString();
		PractitionerProfileState = stateChange.replaceAll("[\\[\\]]", "");
		for (int i = 0; i < nonnexusStateList.size(); i++) {
			if (PractitionerProfileState.equals(nonnexusStateList.get(i))) {
				for (int j = 0; j < nonTaxbleStateList.size(); j++) {
					if (ActualpractitionerStatesAfterChangeToNonTaxable.equals(nonTaxbleStateList.get(j))) {
						basepage.elementWait(5000);
						if (pageAppear.equalsIgnoreCase(listpage)) {
							SoftAssertions.verifyEquals(pageAppear, listpage,
									"List Page is opened:" + PractitionerProfileState + " to "
											+ ActualpractitionerStatesAfterChangeToNonTaxable,
									"Create Page is Opened:" + PractitionerProfileState + " to "
											+ ActualpractitionerStatesAfterChangeToNonTaxable);
						}
						break;
					}
				}
			}

		}
		for (int i = 0; i < nexusStateList.size(); i++) {
			if (PractitionerProfileState.equals(nexusStateList.get(i))) {
				for (int j = 0; j < nonTaxbleStateList.size(); j++) {
					if (ActualpractitionerStatesAfterChangeToNonTaxable.equals(nonTaxbleStateList.get(j))) {
						basepage.elementWait(5000);
						if (pageAppear.equalsIgnoreCase(listpage)) {
							SoftAssertions.verifyEquals(pageAppear, listpage,
									"List Page is opened:" + PractitionerProfileState + " to "
											+ ActualpractitionerStatesAfterChangeToNonTaxable,
									"Create Page is Opened:" + PractitionerProfileState + " to "
											+ ActualpractitionerStatesAfterChangeToNonTaxable);
						}
						break;
					}
				}
			}

		}

		for (int i = 0; i < nonTaxbleStateList.size(); i++) {
			if (PractitionerProfileState.equals(nonTaxbleStateList.get(i))) {
				for (int j = 0; j < nonTaxbleStateList.size(); j++) {
					if (ActualpractitionerStatesAfterChangeToNonTaxable.equals(nonTaxbleStateList.get(j))) {
						basepage.elementWait(5000);
						if (pageAppear.equalsIgnoreCase(listpage)) {
							SoftAssertions.verifyEquals(pageAppear, listpage,
									"List Page is opened:" + PractitionerProfileState + " to "
											+ ActualpractitionerStatesAfterChangeToNonTaxable,
									"Create Page is Opened:" + PractitionerProfileState + " to "
											+ ActualpractitionerStatesAfterChangeToNonTaxable);
						}
						break;
					}
				}
			}

		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void profilepractitionerStatechangesProfilePageNonNexus() throws InterruptedException, IOException {
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		accountsettingpage.profileSetUpTab().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(3000);
		practionerdispansary.prProfessionalAddressStateDispensary("Alabama");
		basepage.elementWait(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		proffinformation.stateChangeConformation();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 8)
	public void changepractitionerProfilestateNonNexus() throws InterruptedException, IOException {

		basepage.elementWait(3000);
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		practitionerStatesAfterChangeToNonNexus = new ArrayList<String>();
		if (driver.findElement(By.xpath("//select[@name='region']")).isDisplayed()) {
			List<WebElement> profileState = praSalesTax.profileSetUPState();
			for (int i = 0; i < profileState.size(); i++) {
				practitionerStatesAfterChangeToNonNexus.add(profileState.get(i).getText());
			}
		}
	}

	// non-Nexus to Nexus State
	@Test(priority = 9)
	@SuppressWarnings({ "static-access", "unused" })
	public void VerifySalesTaxafterChangetoNonNexus() throws InterruptedException, IOException {
		String actualPatientStates1 = practitionerStatesAfterChangeToNonNexus.toString();
		ActualpractitionerStatesAfterChangeToNonNexus = actualPatientStates1.replaceAll("[\\[\\]]", "");
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(3000);
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,30000)");
		basepage.elementWait(3000);
		int k = driver.findElements(By.xpath("//input[@id='radioOptOut']")).size();
		int l = driver.findElements(By.xpath("//label[@for='has_agreed_terms']")).size();
		basepage.elementWait(3000);
		if (l > 0) {
			if (k > 0) {
				praSalesTax.prSalesTaxCheckBox().click();
				basepage.elementWait(3000);
				praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
				basepage.elementWait(3000);
				praSalesTax.salesTaxResaleCertificationField1(0, "12333");
				basepage.elementWait(3000);
				praSalesTax.salesTaxFileUpload().click();
				basepage.elementWait(3000);
				genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("pngImage"));
				basepage.elementWait(20000);
				jse.executeScript("scroll(0,150000)");
				basepage.elementWait(5000);
				proffinformation.prProfessionalRegistationSubmit().click();
			}
		}
		if (k > 0) {
			basepage.elementWait(3000);
			praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
			basepage.elementWait(3000);
			praSalesTax.salesTaxResaleCertificationField1(0, "12333");
			basepage.elementWait(3000);
			praSalesTax.salesTaxFileUpload().click();
			basepage.elementWait(3000);
			genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("pngImage"));
			basepage.elementWait(20000);
			jse.executeScript("scroll(0,150000)");
			basepage.elementWait(5000);
			proffinformation.prProfessionalRegistationSubmit().click();
		} else if (l > 0) {
			praSalesTax.prSalesTaxCheckBox().click();
			basepage.elementWait(5000);
			jse.executeScript("scroll(0,150000)");
			basepage.elementWait(5000);
			proffinformation.prProfessionalRegistationSubmit().click();
			basepage.elementWait(3000);
		} else {
			jse.executeScript("scroll(0,150000)");
			basepage.elementWait(5000);
			proffinformation.prProfessionalRegistationSubmit().click();
			basepage.elementWait(3000);
		}

		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		accountsettingpage.profileSetUpTab().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(3000);
		url = driver.getCurrentUrl();
		pageAppear = url.split("/")[7];
		basepage.elementWait(3000);

		for (int i = 0; i < nonnexusStateList.size(); i++) {
			if (ActualpractitionerStatesAfterChangeToNonTaxable.equals(nonnexusStateList.get(i))) {
				for (int j = 0; j < nonnexusStateList.size(); j++) {
					if (ActualpractitionerStatesAfterChangeToNonNexus.equals(nonnexusStateList.get(j))) {
						basepage.elementWait(5000);
						if (pageAppear.equalsIgnoreCase(listpage)) {
							SoftAssertions.verifyEquals(pageAppear, listpage,
									"List Page is opened:" + ActualpractitionerStatesAfterChangeToNonTaxable + " to "
											+ ActualpractitionerStatesAfterChangeToNonNexus,
									"Create Page is Opened:" + ActualpractitionerStatesAfterChangeToNonTaxable + " to "
											+ ActualpractitionerStatesAfterChangeToNonNexus);
						}
						break;
					}
				}
			}

		}
		for (int i = 0; i < nexusStateList.size(); i++) {
			if (ActualpractitionerStatesAfterChangeToNonTaxable.equals(nexusStateList.get(i))) {
				for (int j = 0; j < nonnexusStateList.size(); j++) {
					if (ActualpractitionerStatesAfterChangeToNonNexus.equals(nonnexusStateList.get(j))) {
						basepage.elementWait(5000);
						if (pageAppear.equalsIgnoreCase(listpage)) {
							SoftAssertions.verifyEquals(pageAppear, listpage,
									"List Page is opened:" + ActualpractitionerStatesAfterChangeToNonTaxable + " to "
											+ ActualpractitionerStatesAfterChangeToNonNexus,
									"Create Page is Opened:" + ActualpractitionerStatesAfterChangeToNonTaxable + " to "
											+ ActualpractitionerStatesAfterChangeToNonNexus);
						}
						break;
					}
				}
			}
		}

		for (int i = 0; i < nonTaxbleStateList.size(); i++) {
			if (ActualpractitionerStatesAfterChangeToNonTaxable.equals(nonTaxbleStateList.get(i))) {
				for (int j = 0; j < nonnexusStateList.size(); j++) {
					if (ActualpractitionerStatesAfterChangeToNonNexus.equals(nonnexusStateList.get(j))) {
						basepage.elementWait(5000);
						if (pageAppear.equalsIgnoreCase(listpage)) {
							SoftAssertions.verifyEquals(pageAppear, listpage,
									"List Page is opened:" + ActualpractitionerStatesAfterChangeToNonTaxable + " to "
											+ ActualpractitionerStatesAfterChangeToNonNexus,
									"Create Page is Opened:" + ActualpractitionerStatesAfterChangeToNonTaxable + " to "
											+ ActualpractitionerStatesAfterChangeToNonNexus);
						}
						break;
					}
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 1000)
	public void profilepractitionerStatechangesProfilePageNexus() throws InterruptedException, IOException {
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		accountsettingpage.profileSetUpTab().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(3000);
		practionerdispansary.prProfessionalAddressStateDispensary("California");
		basepage.elementWait(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		proffinformation.stateChangeConformation();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);

	}

	@SuppressWarnings("static-access")
	@Test(priority = 11)
	public void changepractitionerProfilestateNexus() throws InterruptedException, IOException {

		basepage.elementWait(3000);
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		practitionerStatesAfterChangeToNexus = new ArrayList<String>();
		if (driver.findElement(By.xpath("//select[@name='region']")).isDisplayed()) {
			List<WebElement> profileState = praSalesTax.profileSetUPState();
			for (int i = 0; i < profileState.size(); i++) {
				practitionerStatesAfterChangeToNexus.add(profileState.get(i).getText());
			}
		}
	}

	// non-Nexus to Nexus State
	@Test(priority = 12)
	@SuppressWarnings({ "static-access", "unused" })
	public void VerifySalesTaxafterChangetoNexus() throws InterruptedException, IOException {
		String actualPatientStates1 = practitionerStatesAfterChangeToNexus.toString();
		ActualpractitionerStatesAfterChangeToNexus = actualPatientStates1.replaceAll("[\\[\\]]", "");
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(3000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,20000)");
		basepage.elementWait(3000);
		int k = driver.findElements(By.xpath("//input[@id='radioOptOut']")).size();
		basepage.elementWait(3000);
		int l = driver.findElements(By.xpath("//label[@for='has_agreed_terms']")).size();
		basepage.elementWait(3000);
		if (k > 0) {
			if (l > 0) {
				praSalesTax.prSalesTaxCheckBox().click();
				basepage.elementWait(3000);
				praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
				basepage.elementWait(3000);
				praSalesTax.salesTaxResaleCertificationField1(0, "12333");
				basepage.elementWait(3000);
				praSalesTax.salesTaxFileUpload().click();
				basepage.elementWait(3000);
				genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("pngImage"));
				basepage.elementWait(20000);
				jse.executeScript("scroll(0,150000)");
				basepage.elementWait(5000);
				proffinformation.prProfessionalRegistationSubmit().click();
			}
		}

		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		accountsettingpage.profileSetUpTab().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(3000);
		url = driver.getCurrentUrl();
		pageAppear = url.split("/")[7];
		basepage.elementWait(3000);

		String stateChange = saleTaxState.toString();
		PractitionerProfileState = stateChange.replaceAll("[\\[\\]]", "");
		for (int i = 0; i < nonnexusStateList.size(); i++) {
			if (ActualpractitionerStatesAfterChangeToNonNexus.equals(nonnexusStateList.get(i))) {
				for (int j = 0; j < nexusStateList.size(); j++) {
					if (ActualpractitionerStatesAfterChangeToNexus.equals(nexusStateList.get(j))) {
						basepage.elementWait(5000);
						if (pageAppear.equalsIgnoreCase(createpage)) {
							SoftAssertions.verifyEquals(pageAppear, createpage,
									"Create Page is opened:" + ActualpractitionerStatesAfterChangeToNonNexus + " to "
											+ ActualpractitionerStatesAfterChangeToNexus,
									"List Page is Opened:" + ActualpractitionerStatesAfterChangeToNonNexus + " to "
											+ ActualpractitionerStatesAfterChangeToNexus);
						}
						break;
					}
				}
			}
		}
		for (int i = 0; i < nexusStateList.size(); i++) {
			if (ActualpractitionerStatesAfterChangeToNonNexus.equals(nexusStateList.get(i))) {
				for (int j = 0; j < nexusStateList.size(); j++) {
					if (ActualpractitionerStatesAfterChangeToNexus.equals(nexusStateList.get(j))) {
						basepage.elementWait(5000);
						if (pageAppear.equalsIgnoreCase(createpage)) {
							SoftAssertions.verifyEquals(pageAppear, createpage,
									"Create Page is opened:" + ActualpractitionerStatesAfterChangeToNonNexus + " to "
											+ ActualpractitionerStatesAfterChangeToNexus,
									"List Page is Opened:" + ActualpractitionerStatesAfterChangeToNonNexus + " to "
											+ ActualpractitionerStatesAfterChangeToNexus);
						}
						break;
					}
				}
			}
		}

		for (int i = 0; i < nonTaxbleStateList.size(); i++) {
			if (ActualpractitionerStatesAfterChangeToNonNexus.equals(nonTaxbleStateList.get(i))) {
				for (int j = 0; j < nexusStateList.size(); j++) {
					if (ActualpractitionerStatesAfterChangeToNexus.equals(nexusStateList.get(j))) {
						basepage.elementWait(5000);
						if (pageAppear.equalsIgnoreCase(createpage)) {
							SoftAssertions.verifyEquals(pageAppear, createpage,
									"Create Page is opened:" + ActualpractitionerStatesAfterChangeToNonNexus + " to "
											+ ActualpractitionerStatesAfterChangeToNexus,
									"List Page is Opened:" + ActualpractitionerStatesAfterChangeToNonNexus + " to "
											+ ActualpractitionerStatesAfterChangeToNexus);
						}
						break;
					}
				}
			}
		}
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
