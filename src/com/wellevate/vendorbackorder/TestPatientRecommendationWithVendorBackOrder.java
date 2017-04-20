package com.wellevate.vendorbackorder;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.BusinessInformationPage;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.patientdiscount.AddNewPatientsThroughADDNEWButtonInHomePage;
import com.wellevate.patientdiscount.PatientsDetailsPage;
import com.wellevate.patientdiscount.PatientsPageObject;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPatientRecommendationWithVendorBackOrder {
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
	String actVBOproductMsg;
	String expProductsName;
	String expVBOproductMsg;
	String actProductsName;
	String expproductCode;
	ProductSearchObject productsearch;
	PatientSubmitOrderObject patsubmitorder;

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
		patsubmitorder = new PatientSubmitOrderObject(driver);
		creatingRandomEmailAddress = new CreatingRandomEmailAddress();
		basepage = new BasePage();
		productsearch = new ProductSearchObject(driver);
	}

	// Login To Application
	@SuppressWarnings("static-access")
	 @Test(priority = 1)
	public void loginPractitioner() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qaresetPatientdiscount"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
	}

	// To verify that the Practitioner should be able to filter patients on
	// Patients by Patient Discount.(Filter For: Patient Specific Discount )
	@SuppressWarnings("static-access")
	 @Test(priority = 2)
	public void patientsDetailsPage() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		patientspage.patientsNameList().get(0).click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Set Patients specific discount from Recommended Page

	@SuppressWarnings("static-access")
	 @Test(priority = 3)
	public void recommendedVBOproducts() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		patitentsrecoemened.newRecommendationInPatientEdit().click();
		basepage.elementWait(5000);
		patitentsrecoemened.pesonalMsg().sendKeys(genericMethods.ConfigFile("recomendationPersonalMsg"));
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, 2000)");
		basepage.elementWait(5000);
		patitentsrecoemened.addProducts().click();
		basepage.elementWait(5000);
		actProductsName = genericMethods.ConfigFile("vboProduct1");
		productsearch.searchProductsDispensary().sendKeys(actProductsName);
		basepage.elementWait(3000);
		// productsearch.searchProductsDispensary().sendKeys(Keys.ENTER);
		// basepage.elementWait(3000);
		List<WebElement> searchRes = productsearch.getSerchProducts();
		if (searchRes.size() > 0) {
			searchRes.get(0);
			productsearch.clickOnProductssearch(0);
		} else {
			Reporter.log("No Search Results.");
		}
		basepage.elementWait(3000);
		actVBOproductMsg = genericMethods.ConfigFile("vboProductErrorMsg");
		expProductsName = productsearch.ProductCode().getText().split(" ")[2];
		productsearch.vboProductText(actVBOproductMsg);
		SoftAssertions.verifyEquals(actProductsName, expProductsName, "Product Name  Should be match",
				"Product Name  Should not be match");
		String addrecommendationButton = productsearch.ADDTORECOMMENDATIONbutton().getAttribute("disabled");
		SoftAssertions.verifyEquals(addrecommendationButton, "true", " ADD TO RECOMMENDATION is disable",
				" ADD TO RECOMMENDATION  is enable");
		basepage.elementWait(7000);
		productsearch.closeProductPopup().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}
	@SuppressWarnings("static-access")
	 @Test(priority = 4)
	public void recommendedNonVBOProducts() throws InterruptedException, IOException {

		jse.executeScript("scroll(0, 2000)");
		basepage.elementWait(5000);
		patitentsrecoemened.addProducts().click();
		basepage.elementWait(5000);
		actProductsName = genericMethods.ConfigFile("nonvboProduct");
		productsearch.searchProductsDispensary().clear();
		productsearch.searchProductsDispensary().sendKeys(actProductsName);
		basepage.elementWait(3000);
		// productsearch.searchProductsDispensary().sendKeys(Keys.ENTER);
		// basepage.elementWait(3000);
		List<WebElement> searchRes = productsearch.getSerchProducts();
		if (searchRes.size() > 0) {
			searchRes.get(0);
			productsearch.clickOnProductssearch(0);
		} else {
			Reporter.log("No Search Results.");
		}
		basepage.elementWait(3000);
		actVBOproductMsg = genericMethods.ConfigFile("vboProductErrorMsg");
		expProductsName = productsearch.ProductCode().getText().split(" ")[2];
		productsearch.vboProductText(actVBOproductMsg);
		SoftAssertions.verifyEquals(actProductsName, expProductsName, "Product Name  Should be match",
				"Product Name  Should not be match");
		SoftAssertions.verifyEquals(productsearch.ADDTORECOMMENDATIONbutton().isDisplayed(), true, " ADD TO RECOMMENDATION is enable",
				" ADD TO RECOMMENDATION  is disable");
		basepage.elementWait(7000);
		productsearch.closeProductPopup().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
