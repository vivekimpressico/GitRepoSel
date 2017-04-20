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

public class TestPractitionerAbleToRevertRecommendInPatientDetailsPage {
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
		proffinformation = new ProfessionalInformationPractObject(driver);
		logoutapp = new LogoutApplication(driver);
		accountsettingpage = new AccountSettingsPage(driver);
		bussinessinfrpage = new BusinessInformationPage(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		patitentsrecoemened = new PractitionerRecommendation_Object(driver);
		practitionerinvitesPatients = new PractitionerInvitesPatientObject(driver);
		practionerdispansary = new PractitionerDispensaryObject(driver);
		ExcelReaderExpected.connectExcel();
		wait = new WebDriverWait(driver, 5000);
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
		basepage.elementWait(5000);
	}

	// Get Practitioner Global Discount Text Percentage
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void practitionerProfitBarChanges() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(7000);
		accountsettingpage.prBusinessSettingTab().click();
		basepage.elementWait(5000);
		dispensaryPatientDiscountText = practionerdispansary.dispensaryPatientDiscountPerText().getText();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// To verify that the Practitioner should be able to filter patients on
	// Patients by Patient Discount.(Filter For: Patient Specific Discount )
	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void patientsPageVerifyDiscount() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		patientspage.patientsFilter(genericMethods.ConfigFile("filterOptionspecific"));
		basepage.elementWait(5000);
		actPatientsName = patientspage.patientsNameList().get(0).getText();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Set Patients specific discount from Recommended Page

	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void recommendedPatientsSpecficDiscount() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(patitentsrecoemened.prRecommenedButton().isDisplayed(), true,
				"Recommeneded tab is Displayed", "Recommeneded tab is Displayed");
		basepage.implicitywait(1000);
		patitentsrecoemened.prRecommenedButton().click();
		basepage.elementWait(5000);
		patitentsrecoemened.patientsSearch().sendKeys(actPatientsName);
		basepage.elementWait(5000);
		patitentsrecoemened.clickOnPatientName(0);
		basepage.elementWait(5000);
		patientsdetailspage.resetPatientsDiscount().click();
		basepage.elementWait(5000);
		patitentsrecoemened.pesonalMsg().sendKeys(genericMethods.ConfigFile("recomendationPersonalMsg"));
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, 2000)");
		basepage.elementWait(5000);
		patitentsrecoemened.addProducts().click();
		basepage.elementWait(3000);
		patitentsrecoemened.addProductsToCart(1);
		basepage.elementWait(3000);
		patitentsrecoemened.quickAddProducts().click();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0, 2000)");
		basepage.elementWait(5000);
		patitentsrecoemened.sendEmailtoPatients().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	// To verify that the Practitioner is able to revert a single patient
	// account to the global discount setting on Recommendation page.

	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void patientsDetailsVerifyDiscountRecommend() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		element = patientspage.patientsSearchBox();
		element.sendKeys(actPatientsName);
		basepage.elementWait(5000);
		patientspage.patientsNameList().get(0).click();
		basepage.elementWait(5000);
		actpatientsdiscount = patientsdetailspage.patientsDiscountPercentage().getText();
		SoftAssertions.verifyEquals(actpatientsdiscount.equalsIgnoreCase(dispensaryPatientDiscountText), true,
				"Patient Discount Percentage matches :" + actpatientsdiscount + "and" + dispensaryPatientDiscountText,
				"Patient Discount Percentage does not match:" + actpatientsdiscount + "and"
						+ dispensaryPatientDiscountText);
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
