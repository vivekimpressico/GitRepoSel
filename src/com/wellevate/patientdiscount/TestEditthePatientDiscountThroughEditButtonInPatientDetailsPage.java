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
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
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

public class TestEditthePatientDiscountThroughEditButtonInPatientDetailsPage {
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

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
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

	// Login To Application
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

	// Get the patients name from Patient Page
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void patientsPage() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		patientspage.patientsNameList().get(0).click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Edit Patient Discount Amount In Patients Details Page

	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void patientsDetailsVerifyEditButton() throws InterruptedException, IOException {
		
		basepage.elementWait(20000);
		actPatientsName = patientsdetailspage.patientsName().getText();
		basepage.elementWait(20000);
		// Edit Button in Patients Details Page
		patientsdetailspage.patientsEditButton().click();
		basepage.elementWait(7000);
		patientsdetailspage.patientsDiscountAmountField().clear();
		basepage.elementWait(2000);
		creatingRandomEmailAddress.randomNumber();
		basepage.elementWait(2000);
		patientsdetailspage.patientsDiscountAmountField().sendKeys(genericMethods.dataprp("PatientDiscountField"));
		patientsdetailspage.savebtnPatientsDiscount().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// To verify that the Practitioner should be able to set Patient discount on
	// Patient edit page.

	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void patientsDetailsVerifyDiscount() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		element = patientspage.patientsSearchBox();
		basepage.elementWait(5000);
		element.sendKeys(actPatientsName);
		basepage.elementWait(5000);
		expPatientsName = patientspage.patientsNameList().get(0).getText();
		patientspage.patientsNameList().get(0).click();
		basepage.elementWait(5000);
		actpatientsdiscount = patientsdetailspage.patientsDiscountPercentage().getText();
		exppatientsdiscount = genericMethods.dataprp("PatientDiscountField");
		SoftAssertions.verifyEquals(actpatientsdiscount.equalsIgnoreCase(exppatientsdiscount), true,
				"Patient Discount Percentage  matches After Edit", "Patient Discount Percentage does not match  After Edit");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
