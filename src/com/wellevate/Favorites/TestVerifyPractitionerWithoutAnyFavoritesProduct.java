package com.wellevate.Favorites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.wellevate.utilities.Log4jReport;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestVerifyPractitionerWithoutAnyFavoritesProduct {
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
	int favorites = 3;

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
		basepage = new BasePage();
		practitionerFav = new PractitionerFavoritePage(driver);
		jse = (JavascriptExecutor) driver;
		proffinformation = new ProfessionalInformationPractObject(driver);
		productsearch = new ProductSearchObject(driver);
	}

	// Login to account using Practitioner Credential
	@SuppressWarnings("static-access")
	 @Test(priority = 1)
	public void loginPractitionerFavorite() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(5000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("FavoriteemailPractitioner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(7000);
		if (practitionerFav.productIsFavoriteOrNotInDispensaryPage().isDisplayed()) {
			basepage.elementWait(5000);
			SoftAssertions.verifyEquals(practitionerFav.productIsFavoriteOrNotInDispensaryPage().isDisplayed(), true,
					"Visit your dispensary or product search to start adding your Favorites now. text is Displayed",
					"Visit your dispensary or product search to start adding your Favorites now. Text is  not Displayed");
		} else if (practitionerFav.viewAllFavPageTab().isDisplayed()) {
			BasePage.elementWait(7000);
			practitionerFav.viewAllFavPageTab().click();
			basepage.elementWait(5000);
			// Fav Select in Dispensary
			practitionerFav.unselectallfavoritesProductDispensaryPage();
			BasePage.elementWait(5000);
			proffinformation.dashBoard().click();
			BasePage.elementWait(5000);
			SoftAssertions.verifyEquals(practitionerFav.productIsFavoriteOrNotInDispensaryPage().isDisplayed(), true,
					"Visit your dispensary or product search to start adding your Favorites now. text is Displayed",
					"Visit your dispensary or product search to start adding your Favorites now. Text is  not Displayed");
		} else {
			SoftAssertions.verifyEquals(practitionerFav.viewAllFavPageTab().isDisplayed(), false,
					"View all favorite link is not displayed", "");
		}
	}


	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
