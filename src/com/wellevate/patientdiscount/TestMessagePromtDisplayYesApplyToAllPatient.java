package com.wellevate.patientdiscount;

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
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestMessagePromtDisplayYesApplyToAllPatient {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	GenericsMethods genericMethods;
	LogoutApplication logoutapp;
	PractitionerPatientLoginObject pracLogin;
	AccountSettingIconInApplication accsettingapp;
	WebDriverWait wait;
	WebElement element;
	AddNewPatientsThroughADDNEWButtonInHomePage addnewpatientspopup;
	PractitionerInvitesPatientObject practitonerinvitesPatients;
	PatientsPageObject patientspage;
	PatientsDetailsPage patientsdetailspage;
	PractitionerRecommendation_Object patitentsrecoemened;
	String actPatientsName;
	String expPatientsName;
	String actpatientsdiscount;
	String exppatientsdiscount;
	JavascriptExecutor jse;
	CreatingRandomEmailAddress creatingRandomEmailAddress;
	PractitionerDispensaryObject practionerdispansary;
	ProfessionalInformationPractObject proffinformation;
	PractitionerPricingOject praPricing;
	BasePage basepage;
	AccountSettingsPage accountsettingpage;
	BusinessInformationPage bussinessinfrpage;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		genericMethods = new GenericsMethods();
		logoutapp = new LogoutApplication(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		ExcelReaderExpected.connectExcel();
		wait = new WebDriverWait(driver, 3000);
		practitonerinvitesPatients = new PractitionerInvitesPatientObject(driver);
		patientspage = new PatientsPageObject(driver);
		addnewpatientspopup = new AddNewPatientsThroughADDNEWButtonInHomePage(driver);
		patientsdetailspage = new PatientsDetailsPage(driver);
		patitentsrecoemened = new PractitionerRecommendation_Object(driver);
		jse = (JavascriptExecutor) driver;
		creatingRandomEmailAddress = new CreatingRandomEmailAddress();
		practionerdispansary = new PractitionerDispensaryObject(driver);
		proffinformation = new ProfessionalInformationPractObject(driver);
		praPricing = new PractitionerPricingOject(driver);
		basepage = new BasePage();
		accountsettingpage = new AccountSettingsPage(driver);
		bussinessinfrpage = new BusinessInformationPage(driver);
	}

	// Login To Application
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {
		basepage.elementWait(7000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(7000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qapatientsgloabalspecific"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Add patients details from +ADDNEW button Patients Specific
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void addNewPatientsSpecficDiscount() throws InterruptedException, IOException {
		basepage.elementWait(7000);
		practitonerinvitesPatients.prAddNewButtonInTop().click();
		basepage.elementWait(5000);
		practitonerinvitesPatients.addNewPatientUnderAddNewButton().click();
		basepage.elementWait(3000);
		practitonerinvitesPatients.createPatient();
		basepage.elementWait(3000);
		// To verify that the Practitioner should be able to set Patient
		// discount on Add New Patient modal.
		addnewpatientspopup.patientDiscountTextField().clear();
		basepage.elementWait(5000);
		creatingRandomEmailAddress.randomNumber();
		basepage.elementWait(5000);
		// Change in Patient Percentage Discount in +ADDNEW
		addnewpatientspopup.patientDiscountTextField().sendKeys(genericMethods.dataprp("PatientDiscountField"));
		basepage.elementWait(5000);
		practitonerinvitesPatients.addPatientDetails().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Add patients details from +ADDNEW button Patients Specific
	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void addNewPatientsSpecficDiscount1() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		practitonerinvitesPatients.prAddNewButtonInTop().click();
		basepage.elementWait(7000);
		practitonerinvitesPatients.addNewPatientUnderAddNewButton().click();
		basepage.elementWait(3000);
		practitonerinvitesPatients.createPatient();
		basepage.elementWait(3000);
		// To verify that the Practitioner should be able to set Patient
		// discount on Add New Patient modal.
		addnewpatientspopup.patientDiscountTextField().clear();
		creatingRandomEmailAddress.randomNumber();
		basepage.elementWait(3000);
		// Change in Patient Percentage Discount in +ADDNEW
		addnewpatientspopup.patientDiscountTextField().sendKeys(genericMethods.dataprp("PatientDiscountField"));
		basepage.elementWait(3000);
		practitonerinvitesPatients.addPatientDetails().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Practitioner should be notified when changing general discount if he
	@SuppressWarnings("static-access")
	// already has Patients with individual discounts.
	@Test(priority = 4)
	public void practitionerProfitBarChanges() throws InterruptedException, IOException {
		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(7000);
		accountsettingpage.prBusinessSettingTab().click();
		basepage.elementWait(3000);
		bussinessinfrpage.patientProfitType(genericMethods.ConfigFile("profitType"));
		basepage.elementWait(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(2000);
		// Practitioner should be notified when changing general discount if he
		// already has Patients with individual discounts.
		SoftAssertions.verifyEquals(practionerdispansary.patientsSpecficDiscountPopup(), "true",
				"You currently have patients who get patient-specific discounts. Do you want to have this change applied to all patient popup is displayed",
				"You currently have patients who get patient-specific discounts. Do you want to have this change applied to all patient popup is not displayed");
		basepage.elementWait(5000);
		practionerdispansary.yesApplytoAllPatient().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}
	// To verify that all patients having Specific Discount is updated with
	// Global Discount on clicking “Yes, apply to all Patient” on the
	// notification displayed while changing the global discount.(Test Data :
	// Practitioner has patients with specific discounts)

	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void practitionerYesApplyToAllPatients() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		patientspage.patientsTab().click();
		basepage.elementWait(7000);
		jse.executeScript("scroll(0, -150000)");
		basepage.elementWait(3000);
		patientspage.patientsFilter(genericMethods.ConfigFile("filterOptionspecific"));
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(patientspage.patientsSpecficDiscount().isDisplayed(), true,
				"Once you have invited your first patient, you will  see them listed here. is displayed ",
				"Once you have invited your first patient, you will see not  them listed here. is not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
