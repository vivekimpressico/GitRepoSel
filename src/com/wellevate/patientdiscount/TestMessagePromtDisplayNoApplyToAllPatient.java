package com.wellevate.patientdiscount;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class TestMessagePromtDisplayNoApplyToAllPatient {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	GenericsMethods genericMethods;
	LogoutApplication logoutapp;
	PractitionerPatientLoginObject pracLogin;
	AccountSettingIconInApplication accsettingapp;
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
	String dispensaryPatientDiscountText;
	List<String> actpatientsName;
	List<String> actpatientsPercenatge;
	List<String> expactpatientsName;
	List<String> exppatientsPercenatge;
	BasePage basepage;
	AccountSettingsPage accountsettingpage;
	BusinessInformationPage bussinessinfrpage;
	WebDriverWait wait;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		genericMethods = new GenericsMethods();
		logoutapp = new LogoutApplication(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accountsettingpage = new AccountSettingsPage(driver);
		bussinessinfrpage = new BusinessInformationPage(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		ExcelReaderExpected.connectExcel();
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
		wait = new WebDriverWait(driver, 5000);
	}

	// Login To Application
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		pracLogin.pracLoginButton().click();
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qapatientsgloabalspecific"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(20000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void addNewPatientsSpecficDiscount() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		practitonerinvitesPatients.prAddNewButtonInTop().click();
		basepage.elementWait(7000);
		practitonerinvitesPatients.addNewPatientUnderAddNewButton().click();
		basepage.elementWait(7000);
		practitonerinvitesPatients.createPatient();
		basepage.elementWait(7000);
		// To verify that the Practitioner should be able to set Patient
		// discount on Add New Patient modal.
		addnewpatientspopup.patientDiscountTextField().clear();
		creatingRandomEmailAddress.randomNumber();

		// Change in Patient Percentage Discount in +ADDNEW
		addnewpatientspopup.patientDiscountTextField().sendKeys(genericMethods.dataprp("PatientDiscountField"));
		basepage.elementWait(7000);
		practitonerinvitesPatients.addPatientDetails().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Add patients details from +ADDNEW button Patients Specific
	@Test(priority = 3)
	@SuppressWarnings("static-access")
	public void addNewPatientsSpecficDiscount1() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		practitonerinvitesPatients.prAddNewButtonInTop().click();
		basepage.elementWait(7000);
		practitonerinvitesPatients.addNewPatientUnderAddNewButton().click();
		basepage.elementWait(7000);
		practitonerinvitesPatients.createPatient();
		basepage.elementWait(7000);
		// To verify that the Practitioner should be able to set Patient
		// discount on Add New Patient modal.
		addnewpatientspopup.patientDiscountTextField().clear();
		basepage.implicitywait(2000);
		creatingRandomEmailAddress.randomNumber();
		basepage.implicitywait(2000);
		// Change in Patient Percentage Discount in +ADDNEW
		addnewpatientspopup.patientDiscountTextField().sendKeys(genericMethods.dataprp("PatientDiscountField"));
		basepage.elementWait(5000);
		practitonerinvitesPatients.addPatientDetails().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void patientsPage() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		patientspage.patientsFilter(genericMethods.ConfigFile("filterOptionspecific"));
		basepage.elementWait(3000);
		actpatientsName = patientspage.patientsNameListSpecific();
		basepage.elementWait(3000);
		actpatientsPercenatge = patientspage.patientsPercentageList();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Practitioner should be notified when changing general discount if he
	// already has Patients with individual discounts.
	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void practitionerProfitBarChanges() throws InterruptedException, IOException {

		basepage.elementWait(3000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		accountsettingpage.prBusinessSettingTab().click();
		basepage.elementWait(3000);
		bussinessinfrpage.patientProfitType(genericMethods.ConfigFile("profitType"));
		basepage.elementWait(5000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(3000);
		practionerdispansary.patientsSpecficDiscountPopup();

		// Practitioner should be notified when changing general discount if he
		// already has Patients with individual discounts.
		SoftAssertions.verifyEquals(practionerdispansary.patientsSpecficDiscountPopup(), "true",
				"you currently have patients who get patient-specific discounts. Do you want to have this change applied to all patient popup is displayed",
				"you currently have patients who get patient-specific discounts. Do you want to have this change applied to all patient popup is not displayed");
		basepage.implicitywait(1000);
		practionerdispansary.noApplytoPatientwithoutpatientspecificdiscounts().click();
		basepage.elementWait(3000);
		dispensaryPatientDiscountText = practionerdispansary.dispensaryPatientDiscountPerText().getText();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// To verify that the global discount value is not updated for Patients
	@SuppressWarnings("static-access")
	// having Specific Discount on clicking “No, apply to patients without
	// patient-specific discounts only” on the notification displayed while
	// changing the global discount.(Test Data : Practitioner has patients with
	// specific discounts and global discount )
	@Test(priority = 6)
	public void patientsPageafterSort() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		patientspage.patientsTab().click();
		basepage.elementWait(7000);
		jse.executeScript("scroll(0, -150000)");
		basepage.elementWait(5000);
		patientspage.patientsFilter(genericMethods.ConfigFile("filterOptionspecific"));
		basepage.implicitywait(1000);
		expactpatientsName = patientspage.patientsNameListSpecific();
		exppatientsPercenatge = patientspage.patientsPercentageList();
		SoftAssertions.verifyEquals(actpatientsName, expactpatientsName, "Patients name is matches",
				"Patients name is not matching ");
		SoftAssertions.verifyEquals(actpatientsPercenatge, exppatientsPercenatge, "Patients discount matches",
				"Patients discount is not matching");
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void practitionerYesApplyToAllPatients() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		patientspage.patientsFilter(genericMethods.ConfigFile("filterOptionglobal"));
		basepage.elementWait(3000);
		patientspage.patientsNameList().get(0).click();
		actpatientsdiscount = patientsdetailspage.patientsDiscountPercentage().getText();
		SoftAssertions.verifyEquals(actpatientsdiscount.equalsIgnoreCase(dispensaryPatientDiscountText), true,
				"Patient Discount Percentage matches", "Patient Discount Percentage does not matches");
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
