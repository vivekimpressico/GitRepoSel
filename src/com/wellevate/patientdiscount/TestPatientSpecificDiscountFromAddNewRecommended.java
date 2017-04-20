package com.wellevate.patientdiscount;

import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
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

public class TestPatientSpecificDiscountFromAddNewRecommended {
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
	BasePage basepage;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		genericMethods = new GenericsMethods();	
		logoutapp = new LogoutApplication(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		ExcelReaderExpected.connectExcel();
		wait = new WebDriverWait(driver, 5000);
		practitonerinvitesPatients = new PractitionerInvitesPatientObject(driver);
		patientspage = new PatientsPageObject(driver);
		addnewpatientspopup = new AddNewPatientsThroughADDNEWButtonInHomePage(driver);
		patientsdetailspage = new PatientsDetailsPage(driver);
		patitentsrecoemened = new PractitionerRecommendation_Object(driver);
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
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("addnewpatientspecificpopup"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(20000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Add patients details from +ADDNEW button

	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void addNewPatientsSpecficDiscount() throws InterruptedException, IOException {
		basepage.elementWait(8000);
		practitonerinvitesPatients.prAddNewButtonInTop().click();
		basepage.elementWait(5000);
		practitonerinvitesPatients.addNewPatientUnderAddNewButton().click();
		basepage.elementWait(2000);
		practitonerinvitesPatients.createPatient();
		basepage.elementWait(5000);
		// To verify that the Practitioner should be able to set Patient
		// discount on Add New Patient modal.

		addnewpatientspopup.patientDiscountTextField().clear();
		creatingRandomEmailAddress.randomNumber();
		basepage.elementWait(5000);
		// Change in Patient Percentage Discount in +ADDNEW
		addnewpatientspopup.patientDiscountTextField().sendKeys(genericMethods.dataprp("PatientDiscountField"));
		basepage.elementWait(5000);
		practitonerinvitesPatients.addPatientDetails().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Verify that same discount is displayed in patients details page or not

	// To verify that the Practitioner is able to set Patient Specific discount
	// from +Add New Patient modal window and the same discount is displayed in
	// Patient Detail page.

	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void patientsDetailsVerifyDiscount() throws InterruptedException, IOException {
	
		basepage.elementWait(7000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		actPatientsName = genericMethods.dataprp("patientsName") + " " + genericMethods.dataprp("patientsLastName");
		basepage.elementWait(5000);
		element = patientspage.patientsSearchBox();
		element.sendKeys(actPatientsName);
		basepage.elementWait(5000);
		expPatientsName = patientspage.patientsNameList().get(0).getText();
		SoftAssertions.verifyEquals(actPatientsName.equalsIgnoreCase(expPatientsName), true, "Patients Name is matches",
				"Patients Name is not matches");
		basepage.elementWait(5000);
		if (actPatientsName.equalsIgnoreCase(expPatientsName)) {
			patientspage.patientsNameList().get(0).click();
			basepage.elementWait(5000);
			actpatientsdiscount = patientsdetailspage.patientsDiscountPercentage().getText();
			exppatientsdiscount = genericMethods.dataprp("PatientDiscountField");
			basepage.elementWait(5000);
			SoftAssertions.verifyEquals(actpatientsdiscount.equalsIgnoreCase(exppatientsdiscount), true,
					"Patient Discount Percentage matches", "Patient Discount Percentage does not matches");
			basepage.elementWait(5000);
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	// Set Patients specific discount in Recommended Page

	// To verify that the Practitioner should be able to set Patient discount on
	// Recommendation page.

	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void recommendedPatientsSpecficDiscount() throws InterruptedException, IOException {
	
		basepage.elementWait(40);
		// basepage.implicitywait(1000);
		// patitentsrecoemened.prRecommenedButton().click();
		// basepage.implicitywait(1000);
		// actPatientsName = genericMethods.dataprp("patientsName") + " " +
		// genericMethods.dataprp("patientsLastName");
		// patitentsrecoemened.patientsSearch().sendKeys(actPatientsName);
		// basepage.implicitywait(1000);
		// patitentsrecoemened.clickOnPatientName(0);
		patitentsrecoemened.newRecommendationInPatientEdit().click();
		basepage.elementWait(3000);
		addnewpatientspopup.patientDiscountTextField().clear();
		basepage.implicitywait(1000);
		creatingRandomEmailAddress.randomNumber();
		basepage.implicitywait(1000);
		addnewpatientspopup.patientDiscountTextField().sendKeys(genericMethods.dataprp("PatientDiscountField"));
		basepage.implicitywait(1000);
		patitentsrecoemened.pesonalMsg().sendKeys(genericMethods.ConfigFile("recomendationPersonalMsg"));
		basepage.elementWait(3000);
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
		patitentsrecoemened.backToRecomendationPage().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// To verify that the Practitioner should be able to set Patient
	// discount on Recommendation page.

	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void patientsVerifyDiscountForRecommend() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		patientspage.patientsTab().click();
		basepage.elementWait(3000);
		element = patientspage.patientsSearchBox();
		element.sendKeys(actPatientsName);
		// element.sendKeys(Keys.ENTER);
		basepage.elementWait(5000);
		expPatientsName = patientspage.patientsNameList().get(0).getText();
		SoftAssertions.verifyEquals(actPatientsName.equalsIgnoreCase(expPatientsName), true, "Patients Name is matches",
				"Patients Name is not matches");
		basepage.elementWait(5000);
		if (actPatientsName.equalsIgnoreCase(expPatientsName)) {

			patientspage.patientsNameList().get(0).click();
			basepage.elementWait(5000);
			actpatientsdiscount = patientsdetailspage.patientsDiscountPercentage().getText();
			exppatientsdiscount = genericMethods.dataprp("PatientDiscountField");
			basepage.implicitywait(1000);
			SoftAssertions.verifyEquals(actpatientsdiscount.equalsIgnoreCase(exppatientsdiscount), true,
					"Patient Discount Percentage matches", "Patient Discount Percentage does not matches");
			basepage.elementWait(5000);
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
