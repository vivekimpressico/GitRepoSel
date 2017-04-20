package com.wellevate.customdispensary;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.Favorites.PractitionerFavoritePage;
import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
import com.wellevate.PractitionerAccount.DispensaryPageAccountSetting;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PatientsAddressInformation_Object;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestCustomizedDispensaryButtonDisplayAftrerRecommentionPage {
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
	String strDispensary = null;
	WebDriverWait wait;
	WebElement element;
	BasePage basepage;
	PractitionerFavoritePage practitionerFav;
	JavascriptExecutor jse;
	ProductSearchObject productsearch;
	ArrayList<String> productsFavInDispensaryPage;
	ArrayList<String> productsFavInViewAllPage;
	ProfessionalInformationPractObject proffinformation;
	String actproductCode;
	ArrayList<String> FavproductListMatches;
	DispensaryPageAccountSetting dispensaryAccount;
	String productId;
	PractitionerRecommendation_Object patitentsrecoemened;
	String actPatientsName = "custom dispensary";

	// Set Up For Start Browser And Lunch Application
	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
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
		wait = new WebDriverWait(driver, 3000);
		productsearch = new ProductSearchObject(driver);
		basepage = new BasePage();
		practitionerFav = new PractitionerFavoritePage(driver);
		jse = (JavascriptExecutor) driver;
		proffinformation = new ProfessionalInformationPractObject(driver);
		dispensaryAccount = new DispensaryPageAccountSetting(driver);
		patitentsrecoemened = new PractitionerRecommendation_Object(driver);
	}

	// Login to account using Practitioner Credential
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPractitioner() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("customizeddispensaryemailPractitioner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
	}

	// Product add in Customized dispensary in Recommendation section
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 2)
	public void Productaddtocustomdispensary() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		patitentsrecoemened.prRecommenedButton().click();
		basepage.elementWait(5000);
		patitentsrecoemened.patientsSearch().sendKeys(actPatientsName);
		basepage.elementWait(5000);
		patitentsrecoemened.clickOnPatientName(0);
		basepage.elementWait(2000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		patitentsrecoemened.addProducts().click();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		String dispensaryType = dispensaryAccount.switchDispensary();
		if (dispensaryType.equalsIgnoreCase(genericMethods.ConfigFile("dispensaryTypeShopAlLRecommendation"))) {
			dispensaryAccount.shopAllRecommendationPage().click();
			basepage.elementWait(5000);
		} else if (dispensaryType
				.equalsIgnoreCase(genericMethods.ConfigFile("dispensaryTypeCustomizedRecommendation"))) {

		}
		basepage.elementWait(5000);
		productId = productsearch.AddProductToCustomizedDispensaryRecommendation();
		basepage.elementWait(5000);
		productsearch.editButtonRecommendationPage().click();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(dispensaryAccount.addProductToCustomDispensary(), "true",
				"Add to Custom Dispensary is displayed in Product details Page",
				"Add to Custom Dispensary is not displayed in Product details Page");
		BasePage.elementWait(5000);
		dispensaryAccount.productdetailsCloseQuickAdd().click();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
