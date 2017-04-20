package com.wellevate.Favorites;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
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

public class TestVerifyPractitionerFavoriteProductPresentInPatientsDispensaryPage {
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
	ArrayList<String> productsFavInPatientsPage;
	ProfessionalInformationPractObject proffinformation;
	String actproductCode;
	ArrayList<String> FavproductListMatches;
	PatientsFavoritePage patientfavpage;
	int favoritesproduct = 5;

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
		patientfavpage = new PatientsFavoritePage(driver);
	}

	// Login to account using Practitioner Credential
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPractitioner() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("favemailPractitionerPatient"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Make Fav And Product code of Fav
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 2)
	public void SelectProductForFav() throws InterruptedException, IOException {
		basepage.elementWait(7000);
		productsearch.dispensaryTab().click();
		basepage.elementWait(7000);
		// Fav Select in Dispensary
		productsFavInDispensaryPage = practitionerFav.makeProductFavoritesAddToList(favoritesproduct);
		BasePage.elementWait(5000);
		logoutapp.LogoutPractitioner();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login to account using Practitioner Credential
	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void loginPatients() throws InterruptedException, IOException {	
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("favPatientProduct"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Submit order for new user
	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void patientShopDispensaryForFavorite() throws InterruptedException, IOException {
		
		basepage.elementWait(7000);
		patsubmitorder.shopDispensary().click();
		basepage.elementWait(5000);
		productsFavInPatientsPage = patientfavpage.patientFavoritesProductSelectionOnFavoritePage(favoritesproduct);
		FavproductListMatches = new ArrayList<String>();
		// make sure you found the right number of elements
		if (productsFavInDispensaryPage.size() != productsFavInPatientsPage.size()) {
			System.out.println("fail, wrong number of elements found");
		}
		FavproductListMatches = new ArrayList<String>();
		// make sure that the value of every <option> element equals the
		// expected value
		for (int i = 0; i < productsFavInDispensaryPage.size(); i++) {
			String optionValue = productsFavInPatientsPage.get(i);
			if (optionValue.equals(productsFavInDispensaryPage.get(i))) {
				System.out.println("passed on: " + optionValue);
				FavproductListMatches.add(optionValue);
			} else {
				System.out.println("failed on: " + optionValue);
			}
		}
		SoftAssertions.verifyEquals(FavproductListMatches, productsFavInDispensaryPage,
				"All products marked as favorite from practitioner's dispensary page is displayed correctly in Patient Dispensary Page",
				"All products marked as favorite from practitioner's dispensary page is not displayed correctly in Patient Dispensary Page");
		basepage.elementWait(3000);
		logoutapp.LogoutPatient();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login for Practitioner Customized Dispensary
	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void LoginPractitioner() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		projectSetup.openUrl("wellevateUrl");
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("favemailPractitionerPatient"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 6)
	public void DeSelectProductForFavorite() throws InterruptedException, IOException {
		
		basepage.elementWait(7000);
		productsearch.dispensaryTab().click();
		basepage.elementWait(7000);
		// Fav Select in Dispensary
		practitionerFav.unSelectTheFavoriteProduct(favoritesproduct);
		BasePage.elementWait(5000);
		logoutapp.LogoutPractitioner();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login to account using Practitioner Credential
	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void loginPatientsToVerifyFavoritePresentOrNot() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("favPatientProduct"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		patsubmitorder.shopDispensary().click();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(patientfavpage.favoriteProductPresentInPatientDispensaryPage(), "true",
				"No Favourite product is Displayed in Patients Dispensary Page",
				"Favourite product is Displayed in Patients Dispensary Page");
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
