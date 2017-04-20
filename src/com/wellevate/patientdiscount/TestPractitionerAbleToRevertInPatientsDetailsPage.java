package com.wellevate.patientdiscount;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.BusinessInformationPage;
import com.wellevate.PractitionerAccount.ConnectWithStripeObject;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.PractitionerSalesTaxObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.ProffessionalCertficationObject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPractitionerAbleToRevertInPatientsDetailsPage {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	GenericsMethods genericMethods;
	LogoutApplication logoutapp;
	PractitionerPatientLoginObject pracLogin;
	AccountSettingIconInApplication accsettingapp;
	WebDriverWait wait;
	WebElement element;
	AddNewPatientsThroughADDNEWButtonInHomePage addnewpatientspopup;
	PractitionerInvitesPatientObject practitionerinvitesPatients;
	PatientsPageObject patientspage;
	PatientsDetailsPage patientsdetailspage;
	PractitionerRecommendation_Object patitentsrecoemened;
	String actPatientsName;
	String expPatientsName;
	String actpatientsdiscount;
	String exppatientsdiscount;
	JavascriptExecutor jse;
	PractitionerDispensaryObject practionerdispansary;
	String dispensaryPatientDiscountText;
	ProfessionalInformationPractObject proffinformation;
	CreatingRandomEmailAddress creatingRandomEmailAddress;
	BasePage basepage;
	AccountSettingsPage accountsettingpage;
	BusinessInformationPage bussinessinfrpage;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		accountsettingpage = new AccountSettingsPage(driver);
		bussinessinfrpage = new BusinessInformationPage(driver);
		proffinformation = new ProfessionalInformationPractObject(driver);
		logoutapp = new LogoutApplication(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		patitentsrecoemened = new PractitionerRecommendation_Object(driver);
		practitionerinvitesPatients = new PractitionerInvitesPatientObject(driver);
		practionerdispansary = new PractitionerDispensaryObject(driver);
		ExcelReaderExpected.connectExcel();
		wait = new WebDriverWait(driver, 3000);
		addnewpatientspopup = new AddNewPatientsThroughADDNEWButtonInHomePage(driver);
		genericMethods = new GenericsMethods();
		patientsdetailspage = new PatientsDetailsPage(driver);
		patientspage = new PatientsPageObject(driver);
		jse = (JavascriptExecutor) driver;
		creatingRandomEmailAddress = new CreatingRandomEmailAddress();
		basepage = new BasePage();
	}

	// Login
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qaresetPatientdiscount"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Get Practitioner Global Discount Percentage Form Profit Bar
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void practitionerProfitBarChanges() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		accountsettingpage.prBusinessSettingTab().click();
		basepage.elementWait(5000);
		dispensaryPatientDiscountText = practionerdispansary.dispensaryPatientDiscountPerText().getText();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Add patients details from add new button

	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void addNewPatientsSpecficDiscount() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		proffinformation.dashBoard().click();
		basepage.elementWait(7000);
		practitionerinvitesPatients.prAddNewButtonInTop().click();
		basepage.elementWait(5000);
		practitionerinvitesPatients.addNewPatientUnderAddNewButton().click();
		basepage.elementWait(3000);
		practitionerinvitesPatients.createPatient();
		basepage.elementWait(3000);
		creatingRandomEmailAddress.randomNumber();
		basepage.implicitywait(1000);
		addnewpatientspopup.patientDiscountTextField().clear();
		addnewpatientspopup.patientDiscountTextField().sendKeys(genericMethods.dataprp("PatientDiscountField"));
		basepage.implicitywait(3000);
		practitionerinvitesPatients.addPatientDetails1();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// To verify that the Practitioner is able to revert a single patient
	// account to the global discount setting on Patient Details page.

	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void patientsDetailsVerifyDiscount() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		actPatientsName = genericMethods.dataprp("patientsName") + " " + genericMethods.dataprp("patientsLastName");
		basepage.elementWait(5000);
		element = patientspage.patientsSearchBox();
		element.sendKeys(actPatientsName);
		// element.sendKeys(Keys.ENTER);
		basepage.elementWait(5000);
		expPatientsName = patientspage.patientsNameList().get(0).getText();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(actPatientsName.equalsIgnoreCase(expPatientsName), true, "Patients Name is matches",
				"Patients Name does not match");
		basepage.elementWait(5000);
		if (actPatientsName.equalsIgnoreCase(expPatientsName)) {
			patientspage.patientsNameList().get(0).click();
			basepage.elementWait(6000);
			// Reset Patient Discount in Patients Details page back to the
			// standard Dispensary Discount.
			patientsdetailspage.resetPatientsDiscount().click();
			basepage.elementWait(5000);
			patientsdetailspage.savebtnPatientsDiscount().click();
			basepage.elementWait(3000);
			actpatientsdiscount = patientsdetailspage.patientsDiscountPercentage().getText();
			basepage.elementWait(5000);
			exppatientsdiscount = genericMethods.dataprp("PatientDiscountField");
			SoftAssertions.verifyEquals(actpatientsdiscount.equalsIgnoreCase(dispensaryPatientDiscountText), true,
					"Patient Discount Percentage matches  After Reset", "Patient Discount Percentage does not matches  After Reset");
			basepage.elementWait(5000);
			SoftAssertions.throwAsserationOnFailure();
		}
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
